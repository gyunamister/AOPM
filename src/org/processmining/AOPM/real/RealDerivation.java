package org.processmining.AOPM.real;

import java.util.ArrayList;
import java.util.List;

public class RealDerivation {
	public int calcThroughputTime(List<RealEvent> sublog, int currentTime) {
		List<Object> times = new ArrayList<Object>();
		for(RealEvent pi : sublog) {			
			times.add(pi.getTime());
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
		int throughput_time = currentTime-min;
		return throughput_time;
	}
}
