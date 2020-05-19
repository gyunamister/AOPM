package org.processmining.AOPM.algorithms;

import java.util.List;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ConstraintInstance;

public class Assessment {
	public boolean assess(List<ConstraintInstance> cInstanceList, String agg, String output, String relational, double thre) {
		boolean isSatisfied=false;
		double aggResult = aggregateCIs(cInstanceList, agg, output);
//		value_list.add(aggResult);
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
			.equals(x.getoutc())).collect(Collectors.toList());
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
}
