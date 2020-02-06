package org.processmining.AOPM;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.models.ActionSet;
import org.processmining.AOPM.simulation.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {
	public static void main (String[] args) throws JsonParseException, JsonMappingException, IOException{		
		int e=1;
		String s = "{\"c1: An order must be delivered in 200\":{\"constraintINFO\":{\"constraintName\":[\"c1: An order must be delivered in 200\"],\"processEntity\":[\"Order\"]},\"filter\":{\"relevantEntity\":[\"Order\",\"Item\",\"Package\",\"Route\"]},\"validate\":{\"condition\":[\"membership,IN,Gold\"]},\"evaluate\":{\"condition\":[\"Throughput,>,200\"]}}}";
		System.out.println(s);
		Map<String, Map<String,List<String>>> constraint = new LinkedHashMap<String, Map<String,List<String>>>();
		List<String> nametList = new ArrayList();
		nametList.add("c1: An order must be delivered in 200");
		List<String> processEntityList = new ArrayList();
		processEntityList.add("Order");
		Map<String, List<String>> info = new LinkedHashMap<String, List<String>>();
		info.put("constraintName",nametList);
		info.put("processEntity",processEntityList);
		constraint.put("constraintINFO", info);
		
		List<String> relevantEntityList = new ArrayList();
		relevantEntityList.add("Order");
		relevantEntityList.add("Item");
		relevantEntityList.add("Package");
		relevantEntityList.add("Route");
		Map<String, List<String>> filter = new LinkedHashMap<String, List<String>>();
		filter.put("relevantEntity", relevantEntityList);
		constraint.put("filter", filter);
		
		List<String> validList = new ArrayList();
		validList.add("membership,IN,Gold");
		Map<String, List<String>> validate = new LinkedHashMap<String, List<String>>();
		validate.put("condition", validList);
		constraint.put("validate", validate);
		
		List<String> conditionList = new ArrayList();
		conditionList.add("Throughput,>,200");
		Map<String, List<String>> evaluate = new LinkedHashMap<String, List<String>>();
		evaluate.put("condition", conditionList);
		constraint.put("evaluate", evaluate);
		String action_condition1 = "count,>,10";
		String action1 = "alert operation manager";
		ActionSet as = new ActionSet(constraint,action_condition1,action1);
		System.out.println(as);
		Map<String, ActionSet> action_pack = new LinkedHashMap<String, ActionSet>();
		action_pack.put("c1", as);
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
		
		Simulator sm = new Simulator(action_pack);
		for(int t=0;t<1000;t++) {
			e = sm.simulate(t,e);
		}
	}
}
