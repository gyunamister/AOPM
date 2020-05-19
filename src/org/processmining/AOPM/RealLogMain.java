package org.processmining.AOPM;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.real.RealActionSet;
import org.processmining.AOPM.real.RealSimulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class RealLogMain {
	public static void main (String[] args) throws JsonParseException, JsonMappingException, IOException{		
		int e=1;
		Map<String, Map<String, Map<String,List<String>>>> constraintMap = new LinkedHashMap<String, Map<String, Map<String,List<String>>>>();
		Map<String, Map<String,List<String>>> constraint = new LinkedHashMap<String, Map<String,List<String>>>();
		List<String> nametList = new ArrayList();
		String c = "c1: Must be received in 14 days";  
		nametList.add(c);
		List<String> processEntityList = new ArrayList();
		processEntityList.add("Order");
		Map<String, List<String>> info = new LinkedHashMap<String, List<String>>();
		info.put("constraintName",nametList);
		info.put("processEntity",processEntityList);
		constraint.put("constraintINFO", info);
		
		List<String> relevantEntityList = new ArrayList();
		relevantEntityList.add("Caseid");
		Map<String, List<String>> filter = new LinkedHashMap<String, List<String>>();
		filter.put("relevantEntity", relevantEntityList);
		constraint.put("filter", filter);
		
		List<String> validList = new ArrayList();
		validList.add("membership,IN,Gold");
		Map<String, List<String>> validate = new LinkedHashMap<String, List<String>>();
		validate.put("condition", validList);
		constraint.put("validate", validate);
		
		List<String> conditionList = new ArrayList();
		conditionList.add("Throughput,<,504");
		Map<String, List<String>> evaluate = new LinkedHashMap<String, List<String>>();
		evaluate.put("condition", conditionList);
		constraint.put("evaluate", evaluate);
		constraintMap.put(constraint.get("constraintINFO").get("constraintName").get(0),constraint);
		
		
		
		
//		String action_condition1 = "count,>,10";
		Map<String, List<String>> ccv = new LinkedHashMap<String, List<String>>();
		List<String> cidList = new ArrayList<String>(); 
		cidList.add(c);
		//piList - individual or all
		List<String> piList = new ArrayList<String>(); 
		piList.add("all");
		List<String> tList = new ArrayList<String>();
		tList.add("-1");
		ccv.put("cid",cidList);
		ccv.put("pi",piList);
		ccv.put("t",tList);
		String assessment = "count,>,10000";
		String action1 = "a1: alert case manager";
		RealActionSet actionSet = new RealActionSet(ccv,assessment,action1);
		List<RealActionSet> actionCollection = new ArrayList<RealActionSet>();
		actionCollection.add(actionSet);
		/*
		String constraint2 = "c2: An availability of item must be checked before picking";
		String action_condition2 = "count,>,3";
		String action2 = "alert operation manager";
		List<String> temp_set2 = new ArrayList<String>();
		temp_set2.add(constraint2);
		temp_set2.add(action_condition2);
		temp_set2.add(action2);
		action_pack.put("c2",temp_set2);
		
		String constraint3 = "cp1: no activity exceeds average sojourn time of 100";
		String action_condition3 = "count,>,10";
		String action3 = "alert operation manager";
		List<String> temp_set3 = new ArrayList<String>();
		temp_set3.add(constraint3);
		temp_set3.add(action_condition3);
		temp_set3.add(action3);
		action_pack.put("c3",temp_set3);
		
		String constraint4 = "cp2: no resource involved in more than 3 activities";
		String action_condition4 = "count,>,1";
		String action4 = "alert operation manager";
		List<String> temp_set4 = new ArrayList<String>();
		temp_set4.add(constraint4);
		temp_set4.add(action_condition4);
		temp_set4.add(action4);
		action_pack.put("c4",temp_set4);
		*/
		
		RealSimulator sm = new RealSimulator(constraintMap,actionCollection);
		
		for(int t=0;t<10000;t++) {
			e = sm.simulate(t,e);
		}
		sm.recordEventlog(sm.eventlog);
	}
}
