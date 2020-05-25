package org.processmining.AOPM.ConstraintCube;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.AOPM.models.ConstraintInstance;

public class oldConstraintCubeStructure {
	public Set<String> D;
	public Map<String,Set<Object>> elem;
	public Map<String,Set<Set<Object>>> hier;
	
	public oldConstraintCubeStructure() {
		this.elem = new LinkedHashMap<String,Set<Object>>();
	}
	
	public void setDimension(Set<ConstraintInstance> CIS) {
		String[] dims = {"cf","proc","act","res","Order","Item","Package","Route", "time"};
		for(String d: dims) {
			this.D.add(d);
		}
	}
	
	public void setElem(Set<ConstraintInstance> CIS) {	 
		List<String> nonContextDims = Arrays.asList("cf","time");
		for(String d: this.D) {
			Set<Object> dElem = new HashSet<Object>();
			if(!nonContextDims.contains(d)) {
				for(ConstraintInstance ci : CIS) {
					dElem.addAll(ci.projectContext(d));
				}
			}else if(d=="cf"){
				for(ConstraintInstance ci : CIS) {
					dElem.add(ci.getcfName());
				}
			}else if(d=="time") {
				for(ConstraintInstance ci : CIS) {
					dElem.add(ci.getTime());
				}
			} 
			this.elem.put(d, dElem);
		}
	}
	
	public void setHier(Set<ConstraintInstance> CIS) {
		for(Map.Entry<String,Set<Object>> e : this.elem.entrySet()) {
			Set<Set<Object>> dHier = new HashSet<Set<Object>>();
			for(Object o : e.getValue()) {
				Set<Object> temp = new HashSet<Object>();
				temp.add(o);
				dHier.add(temp);
			}
			this.hier.put(e.getKey(), dHier);
		}
	}
}
