package org.processmining.AOPM;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.processmining.AOPM.models.AEConfig;
import org.processmining.AOPM.models.ActionFormula;
import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.ConstraintFormula;
import org.processmining.AOPM.models.ContextDescription;
import org.processmining.AOPM.models.TimeWindow;
import org.processmining.AOPM.simulation.Simulator;
import org.processmining.EIS.Simulation.ProcessSimulation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {
	public static void main (String[] args) throws JsonParseException, JsonMappingException, IOException{		
		
		/*
		String constraint2 = "c2: An availability of item must be checked before picking";
		String action_condition2 = "count,>,3";
		String action2 = "alert operation manager";
		Set<String> temp_set2 = new HashSet<String>();
		temp_set2.add(constraint2);
		temp_set2.add(action_condition2);
		temp_set2.add(action2);
		action_pack.put("c2",temp_set2);
		
		String constraint3 = "cp1: no activity exceeds average sojourn time of 100";
		String action_condition3 = "count,>,10";
		String action3 = "alert operation manager";
		Set<String> temp_set3 = new HashSet<String>();
		temp_set3.add(constraint3);
		temp_set3.add(action_condition3);
		temp_set3.add(action3);
		action_pack.put("c3",temp_set3);
		
		String constraint4 = "cp2: no resource involved in more than 3 activities";
		String action_condition4 = "count,>,1";
		String action4 = "alert operation manager";
		Set<String> temp_set4 = new HashSet<String>();
		temp_set4.add(constraint4);
		temp_set4.add(action_condition4);
		temp_set4.add(action4);
		action_pack.put("c4",temp_set4);
		*/
		String filePath = "/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/OH-OCL-2020-05-23-13-51-12.xml";
		CMConfig cmConfig = new CMConfig(10000);
		String cfName = "c1: An order must be delivered in 72";
		String filter= "event";
		
		Set<String> procSet = new HashSet<String>();
		Set<String> actSet = new HashSet<String>();
		Set<String> resSet = new HashSet<String>();
		Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
		Map<String,Set<String>> vmap = new LinkedHashMap<String,Set<String>>();
		String proc = "OH";
		Set<String> orderSet = new HashSet<String>();
		orderSet.add("foreach");
		omap.put("Order", orderSet);
		ContextDescription ctxdesc = new ContextDescription(procSet,actSet,resSet,omap,vmap);
		
		String predicate = "Throughput,<,72";
		
		ConstraintFormula cf = new ConstraintFormula(cfName, ctxdesc, filter, predicate);
		Map<String, Object> constraint = new LinkedHashMap<String, Object>();
		constraint.put("cf", cf);
		cmConfig.addConstraint(cf, 24, 24, new TimeWindow(-144,0));;
		
//		Map<String, Set<String>> ccvDescription = new LinkedHashMap<String, Set<String>>();
		AEConfig aeConfig = new AEConfig(10000);
		String afName = "a1: set higher priority for delayed orders";
		Map<String, Set <String>> ccvDescription = new LinkedHashMap<String, Set <String>>();
		Set<String> cfs = new HashSet<String>();
		cfs.add("c1: An order must be delivered in 72");
		ccvDescription.put("cf", cfs);
		Set<String> orders = new HashSet<String>();
		orders.add("foreach");
		ccvDescription.put("Order", orders);
		Set<String> timeSet = new HashSet<String>();
		timeSet.add("all");
		ccvDescription.put("time", timeSet);
		String actionPredicate = "count,>,0";
		String operation = "Set higher priority";
		Map<String, String> pmap = new LinkedHashMap<String, String>();
		pmap.put("target", "Order");
		ActionFormula af = new ActionFormula(afName, ccvDescription, actionPredicate, operation, pmap);
		aeConfig.addAction(af, 24, 24, new TimeWindow(-24,0));
		System.out.println(ccvDescription);

		String afName2 = "a2: alert case manager for the delayed orders";
		Map<String, Set <String>> ccvDescription2 = new LinkedHashMap<String, Set <String>>();
		Set<String> cfs2 = new HashSet<String>();
		cfs2.add("c1: An order must be delivered in 72");
		ccvDescription2.put("cf", cfs2);
		Set<String> orders2 = new HashSet<String>();
		orders2.add("foreach");
		ccvDescription2.put("Order", orders2);
		Set<String> timeSet2 = new HashSet<String>();
		timeSet2.add("all");
		ccvDescription2.put("time", timeSet2);
		String actionPredicate2 = "count,>,1";
		String operation2 = "Send an email to the case manager";
		Map<String, String> pmap2 = new LinkedHashMap<String, String>();
		pmap2.put("target", "Order");
		ActionFormula af2 = new ActionFormula(afName2, ccvDescription2, actionPredicate2, operation2, pmap2);
		aeConfig.addAction(af2, 24, 24, new TimeWindow(-48,0));
		
		ProcessSimulation pSim = new ProcessSimulation();
		Simulator sm = new Simulator(cmConfig,aeConfig,pSim);
		for(int t=0;t<720;t++) {
			pSim.simulateProcess(t);
			sm.simulate(t);
		}
	}
}
