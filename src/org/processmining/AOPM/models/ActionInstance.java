package org.processmining.AOPM.models;

public class ActionInstance {
	public String afName;
	public 	Transaction tr;
	public int time;
	public ActionInstance(String afName, Transaction tr, int time) {
		this.afName = afName;
		this.tr = tr;
		this.time = time;
	}
	
	public String getAfName() {
		return this.afName;
	}
	
	public Transaction getTrans() {
		return this.tr;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String toString() {
//		return this.afName + ", " + this.tr + ", " + this.time;
		return this.getTrans().getOp() + "," + this.getTrans().getPmap().get("target") + "," + this.time;
	}
}
