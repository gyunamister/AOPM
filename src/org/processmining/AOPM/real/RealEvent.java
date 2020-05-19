package org.processmining.AOPM.real;


public class RealEvent {
	int e;
	String caseid;
	String activity;
	String resource;
	int time;
	
	public RealEvent(int e, String caseid, String activity, String resource, int time) {
		this.e = e;
		this.caseid = caseid;
		this.activity = activity;
		this.resource = resource;
		this.time = time;
				
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String getCaseid() {
		return this.caseid;
	}
	
	public String getActivity() {
		return this.activity;
	}
	
	public String toString() {
		return this.e + "," + this.caseid + "," + this.activity + "," + this.resource + "," + this.time;  
	}
}
