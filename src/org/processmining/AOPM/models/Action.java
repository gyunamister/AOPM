package org.processmining.AOPM.models;

import java.util.List;

public class Action {
	public ActionFormula af;
	public List<TimeMoment> TM;
	
	public Action(ActionFormula af, List<TimeMoment> TM) {
		this.af = af;
		this.TM = TM;
	}
	
	public ActionFormula getAF() {
		return this.af;
	}
	
	public List<TimeMoment> getTM(){
		return this.TM;
	}
}
