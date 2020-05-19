package org.processmining.AOPM.real;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ConstraintInstance;

public class RealActionEngine { 
	public RealActionStorage aStorage;
	public List<RealActionSet> afSet;
	public Map<String,List<Double>> agg_values = new LinkedHashMap<String,List<Double>>();
	public String messages = "";
	public int from_t = 100;
	
	public List<Double> value_list = new ArrayList<Double>();
	
	public RealConstraintStorage cs = new RealConstraintStorage();
	 
	public RealActionEngine(RealActionStorage a, List<RealActionSet> aCollection) {
		afSet = aCollection;
		for(RealActionSet as: afSet) {
			agg_values.put(as.getAction(), new ArrayList<Double>());
		}
		aStorage = a;
	}
	
	public void setConstraintStorage(RealConstraintStorage c) {
		this.cs = c;
	} 
	
	public Map<String, Map<String, Map<Integer, List<ConstraintInstance>>>> materialize(int currentTime, Map<String, List<String>> ccv){
		Map<String, Map<String, Map<Integer, List<ConstraintInstance>>>> matProcessCube = new LinkedHashMap<String, Map<String, Map<Integer, List<ConstraintInstance>>>>();
		String constraintName = ccv.get("cid").get(0);
		String pi = ccv.get("pi").get(0);
		String t = ccv.get("t").get(0);
		if(pi.equals("all")) {
			List<ConstraintInstance> filteredCInstanceList = this.cs.cis.stream() 
					.filter((x) -> constraintName.equals(x.getConstraintName()) && currentTime==x.getTime()).collect(Collectors.toList());
			matProcessCube = filteredCInstanceList.stream().collect(
					Collectors.groupingBy(ConstraintInstance::getConstraintName, 
							Collectors.groupingBy(ConstraintInstance::getGeneralEntity, 
									Collectors.groupingBy(ConstraintInstance::getTime))));
		}else if(pi.equals("individual")){
			List<ConstraintInstance> filteredCInstanceList = this.cs.cis.stream() 
					.filter((x) -> constraintName.equals(x.getConstraintName()) && currentTime==x.getTime()).collect(Collectors.toList());
			matProcessCube = filteredCInstanceList.stream().collect(
					Collectors.groupingBy(ConstraintInstance::getConstraintName, 
							Collectors.groupingBy(ConstraintInstance::getpEntity, 
									Collectors.groupingBy(ConstraintInstance::getTime))));
		}else {
			throw new IllegalArgumentException("only all or individual is defined.");
		}
		return matProcessCube;
	}
	
	public boolean assess(List<ConstraintInstance> cInstanceList, String agg, String output, String relational, double thre) {
		boolean isSatisfied=false;
		double aggResult = aggregateCIs(cInstanceList, agg, output);
		value_list.add(aggResult);
		if(relational.equals("=")) {
			if(aggResult==thre) {
//				System.out.println("Alert manager that " + value + "=" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals(">")) {
			if(aggResult>thre) {
//				System.out.println("Alert manager that " + value + ">" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals("<")) {
			if(aggResult<thre) {
//				System.out.println("Alert manager that " + value + "<" + thre);
				isSatisfied=true;
			}
		}
		return isSatisfied;
	}
	
	public double aggregateCIs(List<ConstraintInstance> cInstanceList, String agg, String output) {
		List<ConstraintInstance> filteredCInstanceList = cInstanceList.stream().filter((x) -> output
			.equals(x.getCheck()))
					.map(ConstraintInstance::getThis).collect(Collectors.toList());
		//if null return 0 so that no action can be taken
		double result = 0.0;
		if(!cInstanceList.isEmpty()) {
			if(agg.equals("ratio")) {
				result = ((double) filteredCInstanceList.size())/(((double) cInstanceList.size()) + ((double) filteredCInstanceList.size()));
			}
			if(agg.equals("count")) {
				result = filteredCInstanceList.size();
			}
			
		}
		return result;
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
	
	public void executeInstanceAction(int t, int executeInterval) {
		//Take action with designated time interval
		if(t%executeInterval==0) {
			for(RealActionSet as:this.afSet) {
				String output = "nok";
				String constraintName = as.ccv.get("cid").get(0);
				//materialize
				Map<String, Map<String, Map<Integer, List<ConstraintInstance>>>> matProcessCube = this.materialize(t,as.ccv);
//				System.out.println(matProcessCube);
				
			
				boolean isSatisfied=false;
				String action_condition = as.actionRequisite;
				//Satisfied if action condition is not none - aggregated action
				if(!action_condition.equals("empty")) {
					String agg = action_condition.split(",")[0];
					String relational = action_condition.split(",")[1];
					double thre = Double.parseDouble(action_condition.split(",")[2]);
//					(TODO) visualization - calculate it using another function 
					double v = this.aggregateInstances(t,constraintName,agg,from_t);
					for(String c: matProcessCube.keySet()) {
						for(String pi: matProcessCube.get(c).keySet()) {
							for(Integer time: matProcessCube.get(c).get(pi).keySet()) {
								isSatisfied=assess(matProcessCube.get(c).get(pi).get(time), agg, output, relational, thre);
								if(isSatisfied) {
									String action = as.action;
									String new_message = "Execute action: " + action;
									String info ="empty";
									new_message = aStorage.execute(t, executeInterval, c, pi, time, action, action_condition,info);
									messages = messages.concat(new_message + "\n");
								}
							}
						}
					}
					agg_values.get(as.getAction()).add(v);
				}
			}
		}
	}
	
	public List<String> findViolatedInstances(int t, String constr, int from_t) {
		int  relative_from_t;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> violated_instances;
		List<ConstraintInstance> cInstanceList = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!cInstanceList.isEmpty()) {
			List<String> nok_w_dup = cInstanceList.stream().filter((x) -> "nok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getpEntity).collect(Collectors.toList());
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
		List<ConstraintInstance> cInstanceList = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!cInstanceList.isEmpty()) {
			List<String> nok_w_dup = cInstanceList.stream().filter((x) -> "nok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getpEntity).collect(Collectors.toList());
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
		List<ConstraintInstance> cInstanceList = this.cs.cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && relative_from_t<=x.getTime()).collect(Collectors.toList());
		if(!cInstanceList.isEmpty()) {			
			List<String> ok_w_dup = cInstanceList.stream().filter((x) -> "ok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getpEntity).collect(Collectors.toList());
			Set<String> ok_wo_dup = new HashSet<String>(ok_w_dup);
			normal_instances = new ArrayList<String>(ok_wo_dup);
		}
		return normal_instances;
	}
}
