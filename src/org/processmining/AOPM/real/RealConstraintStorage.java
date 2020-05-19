package org.processmining.AOPM.real;

import java.util.ArrayList;
import java.util.List;

import org.processmining.AOPM.models.ConstraintInstance;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class RealConstraintStorage {
	public Table<Object, Object, Object> table = HashBasedTable.create();
	public List<ConstraintInstance> cis = new ArrayList<ConstraintInstance>(); 
	
	public void addInstances(List<ConstraintInstance> ci) {
		this.cis.addAll(ci);
		/*
		for(ArrayList s : ss) {
			//System.out.println(s.get(0));
			this.table.put(s.get(0), s.get(1), s.get(2));
		}
		*/
	}
	
}
