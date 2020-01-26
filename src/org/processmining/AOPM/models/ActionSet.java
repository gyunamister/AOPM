package org.processmining.AOPM.models;

import java.util.List;
import java.util.Map;

public class ActionSet {
	public Map<String, Map<String,List<String>>> constraint;
	public String actionRequisite;
	public String action;
	
	public ActionSet(Map<String, Map<String,List<String>>> c, String ar, String a) {
		constraint = c; 
		if(ar!=null) {
			actionRequisite = ar;
		}else {
			actionRequisite = "empty";
		}
		
		action = a;
	}
	
	public String toString() {
		return constraint + actionRequisite + action;
	}
}
