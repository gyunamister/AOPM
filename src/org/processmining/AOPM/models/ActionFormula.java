package org.processmining.AOPM.models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.processmining.AOPM.algorithms.Materialization;

public class ActionFormula {
	public String afName;
	public Map<String, Set<String>> ccvDescription;
	public String actionPredicate;
	public String op;
	public Map<String, String> pmap;
	
//	To delete
	public Materialization mat;
	
	public ActionFormula(String afName, Map<String, Set<String>> ccvDescription, String actionPredicate, String op, Map<String, String> pmap) {
		this.afName=afName;
		if(actionPredicate!=null) {
			this.actionPredicate = actionPredicate;
		}else {
			this.actionPredicate = "empty";
		}
		this.ccvDescription=ccvDescription;
		this.op = op;
		this.pmap = pmap;
		this.mat = new Materialization();
	}
	
	public Map<String, Set<String>> genCCV() {
//		(TODO)
		Map<String, Set<String>> CCV =  new LinkedHashMap<String, Set<String>>();
		
		
		return CCV;
	}
	
	public Set<Transaction> apply(Set<ConstraintInstance> CIS,TimeWindow tw){
		Set<Transaction> results = new HashSet<Transaction>();
		String constraintName = this.ccvDescription.get("constraintSet").iterator().next();
		//materialize
		Map<String, Map<String, Map<Integer, List<ConstraintInstance>>>> matProcessCube = this.mat.materialize(CIS,tw.getEnd(),this.ccvDescription);
		
		return results;
	}
	
	public String toString() {
		return this.ccvDescription + this.actionPredicate + this.op;
	}
	
	public Transaction genTrans(Map<String,String> cell){
		Map<String,String> vmap = new LinkedHashMap<String,String>(); 
		for(Entry<String, String> param : this.pmap.entrySet()) {
			vmap.put(param.getKey(), cell.get(this.pmap.get(param.getKey())));
//			if(cell.get(param.getValue())!=null) {
//				vmap.put(param.getKey(), cell.get(this.pmap.get(param.getKey())));
//			}
		}
		return new Transaction(this.op,vmap);
	}
	

	public String getAction() {
		return this.op;
	}
}
