package org.processmining.AOPM.models;

public class ConstraintInstance {
	public String constraint_name;
	public String object_id;
	public String check;
	public int time;
	
	public ConstraintInstance(String constr, String o, int t, String c) {
		this.constraint_name = constr;
		this.object_id = o;
		this.check = c;
		this.time = t;
	}
	
	public String getConstraintName() {
		return this.constraint_name;
	}
	
	public String getObjectId() {
		return this.object_id;
	}
	
	public String getCheck() {
		return this.check;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String toString() {
		return this.constraint_name + this.object_id + this.check + this.time;
	}
}
