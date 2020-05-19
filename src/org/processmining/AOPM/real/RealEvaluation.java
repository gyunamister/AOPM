package org.processmining.AOPM.real;

public class RealEvaluation {
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
}
