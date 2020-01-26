package org.processmining.AOPM;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.algorithms.Simulator;

public class Main {
	public static void main (String[] args){		
		int e=1;
		Map<String,List <String>> action_pack = new LinkedHashMap<String,List <String>>();
		
		String constraint1 = "c1: An order must be delivered in 200";
		String action_condition1 = "count,>,10,100";
		String action1 = "alert operation manager";
		List<String> temp_set1 = new ArrayList<String>();
		temp_set1.add(constraint1);
		temp_set1.add(action_condition1);
		temp_set1.add(action1);
		action_pack.put("c1",temp_set1);
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
