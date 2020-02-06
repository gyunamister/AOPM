package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.models.ConstraintInstance;

public class Evaluation {
		//Evaluate
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
		
		//generate constraint instance
		public ConstraintInstance generateConstraintInstance(String constraintName, String entityName, int currentTime, String result) {
			return new ConstraintInstance(constraintName, entityName, currentTime, result);
		}
	
	
	
}
