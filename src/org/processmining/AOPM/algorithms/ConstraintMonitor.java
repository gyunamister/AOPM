package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ActionSet;
import org.processmining.AOPM.models.ConstraintFunction;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.Item;
import org.processmining.AOPM.models.ObjectType;
import org.processmining.AOPM.models.Order;

public class ConstraintMonitor {
	//Map<String, Map<String, Object>> eventlog = new HashMap<String, Map<String, Object>>();
	public MultiProcess mp;
	public List<String> constraint_list = new ArrayList<String>();
	public Map<String, ActionSet> action_pack;
	ConstraintFunction cf;
	Helper helper;
	
	public ConstraintMonitor(MultiProcess m, Map<String, ActionSet> ap) {
//		for(String as:action_pack.keySet()) {
//			constraint_list.add(action_pack.get(as).get(0));
//		}
		action_pack = ap;
		cf  = new ConstraintFunction();
		mp = m; 
		helper = new Helper();
	}
	
//	public void setConstraintList(List<String> s) { 
//		constraint_list = s;
//	}
	public List<ConstraintInstance> evaluate(int currentTime) {
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		for(String asName : action_pack.keySet()) {
			List<ConstraintInstance> ciList = testCheck(currentTime, action_pack.get(asName));
			outputs.addAll(ciList);
		}
		return outputs;
	}
	
	public List<ConstraintInstance> testCheck(int currentTime,ActionSet as){
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		Map<String, Map<String,List<String>>> constraint = as.constraint;
		//filter object
		List<String> selectedObjectList = constraint.get("Contextualize").get("Object");
		List<ObjectType> ongoingObjectList = mp.object_list;
		Map<String, Map<String, Object>> filteredLog = helper.getEventsByObject(mp.eventlog, ongoingObjectList, selectedObjectList);
		
		//get process entities
		String selectedEntity = constraint.get("Contextualize").get("ProcessEntity").get(0);
		List<ObjectType> entityList = helper.getObjectEntityList(selectedEntity,filteredLog,ongoingObjectList);
		
		//ignore context
		List<String> selectedContextList = constraint.get("Contextualize").get("Context");
		
		//generate event collections
		Map<String,List<ObjectType>> objectGraph = mp.object_graph;
		Map<String, Map<String, Map<String, Object>>> setOfEntityEvents;
		if(selectedObjectList.size()==1) {
			setOfEntityEvents =
					helper.generateEventsByObject(filteredLog,entityList);
		}else {
			setOfEntityEvents =
					helper.generateEventsByGraph(filteredLog,objectGraph,entityList);
		}
		
		
		//condition check 
		List<String> selectedCondList = constraint.get("Evaluate").get("Condition");
		for(String entity: setOfEntityEvents.keySet()) {
			//calculate user-defined values
			Map<String,Integer> value = new LinkedHashMap<String,Integer>();
			for(String formulaName : constraint.get("Acquire").get("formulaNameList")) {
				value.put(formulaName, 0);
				if(formulaName.contains("Throughput")) {
					int val = helper.calcThroughputTime(setOfEntityEvents.get(entity), currentTime);
					value.replace(formulaName, val);
				}
			}
			//evaluate
			String result = "ok";
			for(String c : selectedCondList) { 
				if(c.contains(">") | c.contains("=") | c.contains("<")) {
					String valueName=c.split(",")[0];
					String comp=c.split(",")[1];
					int thres=Integer.parseInt(c.split(",")[2]);
					System.out.println(value);
					result = helper.evalRelational(value.get(valueName),comp,thres);
					if(result.equals("nok")) {
						break;
					}
				}else if(c.contains("EVENTUALLY")) {
					String a = c.split(",")[0];
					String b = c.split(",")[2];
					List<Object> acts = new ArrayList<Object>();
					for(String e : setOfEntityEvents.get(entity).keySet()) {
						acts.add(setOfEntityEvents.get(entity).get(e).get("activity"));
					}
					if(!acts.contains(a) & acts.contains(b)) {
						result = "nok";
					}
				}
			}
			ConstraintInstance ci = helper.generateConstraintInstance(as.constraint.get("constraintINFO").get("constraintName").get(0), entity, currentTime, result);
			outputs.add(ci);
		}
		List<String> selectedCalcList = constraint.get("Acquire").get("Calculate");
		return outputs;
	}
	
	public List<ConstraintInstance> instanceLevelCheck(int t) {
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		//on multiple objects
		for(String key : mp.object_graph.keySet()) {
			Map<String, Map<String, Object>> sublog = getAllEvents(mp.eventlog, mp.object_graph.get(key));
			if(constraint_list.contains("c1: An order must be delivered in 200")) {
				ConstraintInstance s = checkCompliance1(key,sublog,t);
				outputs.add(s); 
			}
			if(constraint_list.contains("c3: An order must be delivered in 100")) {
				ConstraintInstance s = checkCompliance3(key,sublog,t);
				outputs.add(s); 
			}
		}
		//on single object
		for(ObjectType o : mp.object_list) {
			if(o instanceof Item) {
				Map<String, Map<String, Object>> sublog = getSingleObjectEvents(mp.eventlog, o);
				if(constraint_list.contains("c2: An availability of item must be checked before picking")) {
					ConstraintInstance s = checkCompliance2(o.getObjectName(),sublog,t);
					outputs.add(s);
				}
			}
		}
		return outputs;
	}
	
	public List<ConstraintInstance> processLevelCheck(int t) {
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		if(constraint_list.contains("cp1: Maximum number of ongoing orders is 50")) {
			ConstraintInstance s = checkProcessCompliance1("process",mp.eventlog,t);
			outputs.add(s);
		}
		if(constraint_list.contains("cp2: There is no resource involved in more than 5 activities")) {
			for(String res : mp.resource_list) {
				Map<String, Map<String, Object>> sublog = getProcessComponentEvents(mp.eventlog, "resource",res, t);
				ConstraintInstance s = checkProcessCompliance2(res,sublog,t);
				outputs.add(s);
			}
		}
		if(constraint_list.contains("cp3: no activity exceeds average sojourn time of 100")) {
			for(String act : mp.activity_list) {
				Map<String, Map<String, Object>> sublog = mp.eventlog;
				ConstraintInstance s = checkProcessCompliance3(act,sublog,t);
				outputs.add(s);
			}
		}
		return outputs; 
	}
	
	public ConstraintInstance checkCompliance1(String name, Map<String, Map<String, Object>> sl, int t) {
		List<Object> times = new ArrayList<Object>();
		for(String e : sl.keySet()) {			
			times.add(sl.get(e).get("timestamp"));
		}
		int max=0;
		for(Object i : times) {
			if((int) i>max) {
				max=(int) i;
			}
		}
		int min=10000000;
		for(Object i : times) {
			if((int) i<min) {
				min= (int) i;
			}
		}
		int throughput_time = max-min;
		String result = "ok";
		if(throughput_time>200) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("c1: An order must be delivered in 200", name, t, result);
		return output;
	}
	
	public ConstraintInstance checkCompliance2(String name, Map<String, Map<String, Object>> sl, int t) {
		List<Object> acts = new ArrayList<Object>();
		for(String e : sl.keySet()) {
			acts.add(sl.get(e).get("activity"));
		}
		
		String result = "ok";
		if(!acts.contains("check_availability") & acts.contains("pick_item")) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("c2: An availability of item must be checked before picking", name, t, result);
		return output;
		
	}
	
	public ConstraintInstance checkCompliance3(String name, Map<String, Map<String, Object>> sl, int t) {
		List<Object> times = new ArrayList<Object>();
		for(String e : sl.keySet()) {
//			
			times.add(sl.get(e).get("timestamp"));
		}
		int max=0;
		for(Object i : times) {
			if((int) i>max) {
				max=(int) i;
			}
		}
		int min=10000000;
		for(Object i : times) {
			if((int) i<min) {
				min= (int) i;
			}
		}
		int throughput_time = max-min;
		String result = "ok";
		if(throughput_time>100) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("c3: An order must be delivered in 100", name, t, result);
		return output;
		
	}
	
	//check if there are more than 50 ongoing orders
	public ConstraintInstance checkProcessCompliance1(String name, Map<String, Map<String, Object>> sl, int t) {
		int num_ongoing_orders = 0;
		for(ObjectType o : mp.object_list) {
			if(o instanceof Order) {
				num_ongoing_orders +=1;
			}
		}
		String result = "ok";
		if(num_ongoing_orders>30) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("cp1: Maximum number of ongoing orders is 50", name, t, result);
		return output;
	}
	
	public ConstraintInstance checkProcessCompliance3(String name, Map<String, Map<String, Object>> sl, int t) {
		List<Integer> times = new ArrayList<Integer>();
		for(String e : sl.keySet()) {
			times.add((int) sl.get(e).get("timestamp"));
		}
		double avg_sojourn_time = calculateAverage(times);
		
		String result = "ok";
		if(avg_sojourn_time>100) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("cp1: no activity exceeds average sojourn time of 100", name, t, result);
		return output;
	}
	
	public ConstraintInstance checkProcessCompliance2(String name, Map<String, Map<String, Object>> sl, int t) {
		List<String> acts = new ArrayList<String>();
		for(String e : sl.keySet()) {
			acts.add(sl.get(e).get("activity").toString());
		}
		Set<String> acts_wo_dup = new HashSet<String>(acts);
		
		String result = "ok";
		if(acts_wo_dup.size()>5) {
			result = "nok";
		}
		ConstraintInstance output = new ConstraintInstance("cp2: There is no resource involved in more than 3 activities", name, t, result);
		return output;
	}
	
	public Map<String, Map<String, Object>> getAllEvents(Map<String, Map<String, Object>> eventlog, List<ObjectType> g){
		List<String> events = new ArrayList<String>();
		for(ObjectType v : g) {
			events.addAll(v.history);
		}
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}
	
	public Map<String, Map<String, Object>> getSingleObjectEvents(Map<String, Map<String, Object>> eventlog, ObjectType o){
		List<String> events = new ArrayList<String>();
		events.addAll(o.history);
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}
	
	public Map<String, Map<String, Object>> getProcessComponentEvents(Map<String, Map<String, Object>> eventlog, String comp, String v, int t){
		int  relative_from_t;
		int from_t=100;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> events = new ArrayList<String>();
		events = eventlog.entrySet().stream()
				.filter(x -> x.getValue().get(comp).toString().equals(v) && relative_from_t<= (int)x.getValue().get("timestamp"))
				.map(x -> x.getKey()).collect(Collectors.toList());
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}
	
	private double calculateAverage(List <Integer> marks) {
		OptionalDouble average = marks.stream().mapToDouble(a -> a).average();
		return average.isPresent() ? average.getAsDouble() : 0;
	}
	
}
