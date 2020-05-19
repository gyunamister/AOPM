package org.processmining.AOPM.models;

import java.util.List;

public class Constraint {
	public ConstraintFormula cf;
	public List<TimeMoment> TM;
	
	public Constraint(ConstraintFormula constraintFormula, List<TimeMoment> timeMomentSet) {
		this.cf = constraintFormula;
		this.TM = timeMomentSet;
	}
	
	public ConstraintFormula getCF() {
		return this.cf;
	}
	
	public List<TimeMoment> getTM(){
		return this.TM;
	}
}
