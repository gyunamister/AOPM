package org.processmining.AOPM.models;

import java.util.Set;

public class ConstraintInstance {
	public String cfName;
	public Context ctx;
	public String outc;
	public int time;
	
	public ConstraintInstance(String cfName, Context ctx, int t, String outcome) {
		this.cfName = cfName;
		this.ctx = ctx;
		this.outc = outcome;
		this.time = t;
	}
	
	public ConstraintInstance getThis() {
		return this;
	}
	
	public String getcfName() {
		return this.cfName;
	}
	
	public Context getContext() {
		return this.ctx;
	}
	
	public String getoutc() {
		return this.outc;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String getOrder() {
		return this.ctx.getOmap().get("Order").iterator().next();
	}
	
	public String getNull() {
		return "null";
	}
	
	public Set<String> projectContext(String d){
		if(d=="proc") {
			return this.ctx.getProcSet();
		}else if(d=="act") {
			return this.ctx.getActSet();
		}else if(d=="res") {
			return this.ctx.getResSet();
		}else {
			return this.ctx.getOmap().get(d);
		}
	}
	
	public String toString() {
//		return this.cfName + "," + this.ctx + "," + this.time + "," + this.outc;
		return this.ctx.getOmap().get("Order").iterator().next() + "," + this.time + "," + this.outc;
	}
}
