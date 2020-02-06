package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ActionSet;
import org.processmining.AOPM.models.ConstraintFunction;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.ObjectType;
import org.processmining.AOPM.simulation.MultiProcess;

public class ConstraintMonitor {
	public MultiProcess mp;
	public List<String> constraint_list = new ArrayList<String>();
	public Map<String, ActionSet> action_pack;
	ConstraintFunction cf;
	Helper helper;
	Derivation calculator;
	Evaluation evaluator;
	
	public ConstraintMonitor(MultiProcess m, Map<String, ActionSet> ap) {
		action_pack = ap;
		cf  = new ConstraintFunction();
		mp = m; 
		helper = new Helper();
		calculator = new Derivation();
		evaluator = new Evaluation();
	}
	
//	public void setConstraintList(List<String> s) { 
//		constraint_list = s;
//	}
	public List<ConstraintInstance> evaluate(int currentTime) {
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		for(String asName : action_pack.keySet()) {
			List<ConstraintInstance> ciList = newCheck(currentTime, action_pack.get(asName));
			outputs.addAll(ciList);
		}
		return outputs;
	}
	
	public List<ConstraintInstance> newCheck(int currentTime,ActionSet as){
		//initialize list for monitoring output
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		Map<String, Map<String, Object>> filteredLog = mp.eventlog; 
		
		//set constraints to monitor
		Map<String, Map<String,List<String>>> constraint = as.constraint;

		//filter object
//		Current : {c1: An order must be delivered in 200={constraintINFO=
//		{constraintName=[c1: An order must be delivered in 200]}, 
//		Contextualize={Object=[Order, Item, Package, Route], ProcessEntity=[Order], Context=[]}, 
//		Evaluate={Condition=[Throughput,<,200]}, 
//		Acquire={formulaNameList=[Throughput], formulaList=[MAX(Timestamp) - MIN(Timestamp)]}}}

//		Update: ConstraintINFO={ConstraintName=[],processEntity=[]}, Filter={Associate=[Item,Package,Route]}, 
//		Validate={Context:[]}, Evaluate={Condition:[Throughput,<,200]}
		List<String> relevantEntityType = constraint.get("filter").get("relevantEntity");
		List<ObjectType> ongoingObjectList = mp.object_list;

		//not necessary (Optional)
		//Map<String, Map<String, Object>> filteredLog = helper.getEventsByObject(mp.eventlog, ongoingObjectList, relevantEntityType);
		
		//get process entities
		String selectedEntity = constraint.get("constraintINFO").get("processEntity").get(0);
		List<ObjectType> entityList = helper.getObjectEntityList(selectedEntity,ongoingObjectList);
		
		//ignore context
		List<String> selectedContextList = constraint.get("validate").get("condition");
		
//		(TODO) At the moment, we assume that all related objects are considered. Instead, we need rel function defined at Helper.
		//generate event collections
		Map<String,List<ObjectType>> objectGraph = mp.object_graph;
		Map<String, Map<String, Map<String, Object>>> setOfEntityEvents;
		if(relevantEntityType.size()==1) {
			setOfEntityEvents =
					helper.generateEventsByObject(filteredLog,entityList);
		}else {
			setOfEntityEvents =
					helper.generateEventsByGraph(filteredLog,objectGraph,entityList);
		}
		
		//condition check 
		List<String> selectedCondList = constraint.get("evaluate").get("condition");
		for(String entity: setOfEntityEvents.keySet()) {
			//We simply assume that we already have predefined functions. So, what we need to is just to call the function.
			//evaluate
			String result = "ok";
			for(String c : selectedCondList) { 
				if(c.contains(">") | c.contains("=") | c.contains("<")) {
					String valueName=c.split(",")[0];
					String comp=c.split(",")[1];
					int thres=Integer.parseInt(c.split(",")[2]);
					int val = 0;
					if(c.contains("Throughput")) {
						val = calculator.calcThroughputTime(setOfEntityEvents.get(entity), currentTime);
					}
					result = evaluator.evalRelational(val,comp,thres);
					if(result.equals("nok")) {
						break;
					}
				}else if(c.contains("EVENTUALLY")) {
					result = evaluator.evalEventually(c,setOfEntityEvents.get(entity));
				}
			}
			ConstraintInstance ci = helper.generateConstraintInstance(as.constraint.get("constraintINFO").get("constraintName").get(0), entity, currentTime, result);
			outputs.add(ci);
		}
		return outputs;
	}
	
	/*
	 * Below is a reference for stream operation!
	 */
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
