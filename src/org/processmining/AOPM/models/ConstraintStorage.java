package org.processmining.AOPM.models;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ConstraintStorage {
	public Table<Object, Object, Object> table = HashBasedTable.create();
	public Set<ConstraintInstance> cis = new HashSet<ConstraintInstance>(); 
	
	public void addInstances(Set<ConstraintInstance> ci) {
		this.cis.addAll(ci);
		/*
		for(HashSet s : ss) {
			//System.out.println(s.get(0));
			this.table.put(s.get(0), s.get(1), s.get(2));
		}
		*/
	}
	
	public Set<ConstraintInstance> getCIS(){
		return this.cis;
	}
	
}
