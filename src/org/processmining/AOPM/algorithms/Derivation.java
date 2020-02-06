package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Derivation {
		//Data ACQ
		public int calcThroughputTime(Map<String, Map<String, Object>> sublog, int currentTime) {
			List<Object> times = new ArrayList<Object>();
			for(String e : sublog.keySet()) {			
				times.add(sublog.get(e).get("timestamp"));
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
			return throughput_time;
		}
	
}
