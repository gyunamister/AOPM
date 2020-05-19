package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.AOPM.models.Event;

public class Derivation {
		//Data ACQ
		public int calcThroughputTime(Set<Event> sublog,int twEnd) {
			for(Event e: sublog) {
				if(e.getAct().equals("deliver_package")) {
					return 0;
				}
			}
			Set<Integer> times = new HashSet<Integer>();
			for(Event e : sublog) {			
				times.add(e.getTimestamp());
			}
			int max=0;
			for(Integer i : times) {
				if(i>max) {
					max= i;
				}
			}
			int min=10000000;
			for(Integer i : times) {
				if(i<min) {
					min= i;
				}
			}
			int throughput_time = twEnd-min;
			return throughput_time;
		}
		
		public String evalRelational(int a, String relation, int b) {
			String result = "ok";
			if(relation.equals(">")) {
				if(a<=b) {
					result = "nok";
				}
			}else if(relation.equals("=")) {
				if(a!=b) {
					result = "nok";
				}
			}else if(relation.equals("<")) {
				if(a>=b) {
					result = "nok";
				}
			}
			return result;
		}
		
		public String evalEventually(String c, Map<String, Map<String, Object>> eventCollection) {
			String result = "ok";
			String a = c.split(",")[0];
			String b = c.split(",")[2];
			List<Object> acts = new ArrayList<Object>();
			for(String e : eventCollection.keySet()) {
				acts.add(eventCollection.get(e).get("activity"));
			}
			if(!acts.contains(a) & acts.contains(b)) {
				result = "nok";
			}
			return result;
		}
	
}
