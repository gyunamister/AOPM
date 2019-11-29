package org.processmining.newpackage.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.newpackage.models.ActionStorage;
import org.processmining.newpackage.models.ConstraintInstance;
import org.processmining.newpackage.models.ConstraintStorage;

public class ActionEngine {
	public ActionStorage action_storage;
	public Map<String,List<String>> action_pack;
	public Map<String,List<Double>> agg_values = new LinkedHashMap<String,List<Double>>();
	public String messages = "";
	public int from_t = 100;
	
	public List<Double> value_list = new ArrayList<Double>();
	
	public ConstraintStorage cs = new ConstraintStorage();
	
	public ActionEngine(MultiProcess m, ActionStorage a, Map<String,List<String>> ap) {
		action_pack = ap;
		for(String s:action_pack.keySet()) {
			agg_values.put(s, new ArrayList<Double>());
		}
		action_storage = a;
	}
	
	public void setConstraintStorage(ConstraintStorage c) {
		this.cs = c;
	} 

	public void executeSingleAction(int t) {
		for(String s:action_pack.keySet()) {
			List<String> as = action_pack.get(s);
			String constraint = as.get(0);
			boolean isSatisfied=false;
			String action_condition = as.get(1);
			if(action_condition==null) { 
				isSatisfied=true;
				double v = this.aggregateInstances(t,constraint,"count",from_t);
				boolean ignoreIt = checkActionCondition(t, v, constraint, "count", ">", 999999999);
				agg_values.get(s).add(v);
			}
			if(isSatisfied && t%from_t==0) {
				List<String> violated = this.findViolated(t, constraint, from_t);
				if(!violated.isEmpty()) { 
					for(String info : violated) {
						String action = as.get(2);
						String new_message = "Execute action: " + action;
						new_message = action_storage.execute(constraint, action_condition, action,info);
						messages = messages.concat(new_message + "\n");
					}
				}
				
			}
		}
	}
	
	public void executeAggAction(int t) {
		for(String s:action_pack.keySet()) {
			List<String> as = action_pack.get(s);
			String constraint = as.get(0);
			boolean isSatisfied=false;
			String action_condition = as.get(1);
			if(action_condition!=null) {
				String agg = action_condition.split(",")[0];
				String relational = action_condition.split(",")[1];
				double thre = Double.parseDouble(action_condition.split(",")[2]);
//				int from_t = Integer.parseInt(action_condition.split(",")[3]);
				double v = this.aggregateInstances(t,constraint,agg,from_t);
				isSatisfied = checkActionCondition(t, v, constraint, agg, relational, thre);
				agg_values.get(s).add(v);
			}
			if(isSatisfied && t%from_t==0) {
				String action = as.get(2);
				String new_message = "Execute action: " + action;
				System.out.println(action);
				String info ="";
				new_message = action_storage.execute(constraint,action_condition,action,info);
				messages = messages.concat(new_message + "\n");
			}
		}
	}
	
	public boolean checkActionCondition(int t, double value, String constr, String agg, String relational, double thre) {
		boolean isSatisfied=false;
		value_list.add(value);
		if(relational.equals("=")) {
			if(value==thre) {
//				System.out.println("Alert manager that " + value + "=" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals(">")) {
			if(value>thre) {
//				System.out.println("Alert manager that " + value + ">" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals("<")) {
			if(value<thre) {
//				System.out.println("Alert manager that " + value + "<" + thre);
				isSatisfied=true;
			}
		}
		return isSatisfied;
	}
	
	public List<String> findViolatedInstances(int t, String constr, int from_t) {
		int  relative_from_t;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> violated_instances;
		List<ConstraintInstance> constr_instances = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!constr_instances.isEmpty()) {
			List<String> nok_w_dup = constr_instances.stream().filter((x) -> "nok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getObjectId).collect(Collectors.toList());
			Set<String> nok_wo_dup = new HashSet<String>(nok_w_dup);
			violated_instances = new ArrayList<String>(nok_wo_dup);
		}else {
			violated_instances = new ArrayList<String>();
		}
		return violated_instances; 
	}
	
	public List<String> findViolated(int t, String constr, int from_t){
		int  relative_from_t;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> violated_instances = new ArrayList<String>();
		List<ConstraintInstance> constr_instances = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!constr_instances.isEmpty()) {
			List<String> nok_w_dup = constr_instances.stream().filter((x) -> "nok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getObjectId).collect(Collectors.toList());
			Set<String> nok_wo_dup = new HashSet<String>(nok_w_dup);			
			violated_instances = new ArrayList<String>(nok_wo_dup);
		}
		return violated_instances;
	}
	
	public List<String> findNormal(int t, String constr, int from_t){
		int  relative_from_t;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> normal_instances = new ArrayList<String>();
		List<ConstraintInstance> constr_instances = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!constr_instances.isEmpty()) {			
			List<String> ok_w_dup = constr_instances.stream().filter((x) -> "ok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getObjectId).collect(Collectors.toList());
			Set<String> ok_wo_dup = new HashSet<String>(ok_w_dup);
			normal_instances = new ArrayList<String>(ok_wo_dup);
		}
		return normal_instances;
	}
	
	public double aggregateInstances(int t, String constr, String agg, int from_t) {
		//if null return 0 so that no action can be taken
		double result = 0.0;
		
		List<String>violated = findViolated(t,constr,from_t);
		List<String>normal = findNormal(t,constr,from_t);
		if(!violated.isEmpty()) {
			if(agg.equals("ratio")) {
				result = ((double) violated.size())/(((double) normal.size()) + ((double) violated.size()));
			}
			if(agg.equals("count")) {
				result = violated.size();
			}
			
		}
		return result;
	}
}
