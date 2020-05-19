package org.processmining.AOPM.real;

import java.util.List;
import java.util.Map;

public class RealActionSet {
	public Map<String, List<String>> ccv;
	public String actionRequisite;
	public String action;
	
	public RealActionSet(Map<String, List<String>> constraintCubeView, String ar, String a) {
		if(ar!=null) {
			actionRequisite = ar;
		}else {
			actionRequisite = "empty";
		}
		ccv=constraintCubeView;
		
		action = a;
	}
	
	public String toString() {
		return ccv + actionRequisite + action;
	}
	

	public String getAction() {
		return this.action;
	}
	
}
