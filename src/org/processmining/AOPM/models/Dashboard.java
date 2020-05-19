package org.processmining.AOPM.models;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class Dashboard {
	public Map<String,List<Double>> vMap;
	public ConstraintStorage cs;
	public Set<String> constraintNames;
	public String agg;
	
	
	public Dashboard(CMConfig cmConfig, ConstraintStorage cs) {
		this.vMap = new LinkedHashMap<String,List<Double>>();
		this.cs = cs;
		this.constraintNames = cmConfig.getCFNames();
		this.agg="count";
		for(String c:this.constraintNames) {
			vMap.put(c, new ArrayList<Double>());
		}
	}
	
	public void updateVMap(int t) {
		for(String c:this.constraintNames) {
			double resultAtT = this.aggCIs(this.cs.cis, t, c, this.agg);
			this.vMap.get(c).add(resultAtT);
		}
	}
	
	public double aggCIs(List<ConstraintInstance> cis, int t, String constr, String agg) {
		double result = 0.0;
		List<String>violated = findViolated(cis, t, constr);
		if(!violated.isEmpty()) {
			if(agg.equals("count")) {
				result = violated.size();
			}
		}
		return result;
	}
	
	public List<String> findViolated(List<ConstraintInstance> cis, int t, String constr){
		List<String> violated_instances = new ArrayList<String>();
		List<ConstraintInstance> cInstanceList = cis.stream() 
				.filter((x) -> constr.equals(x.getConstraintName()) && t==x.getTime()).collect(Collectors.toList());
		if(!cInstanceList.isEmpty()) {
			List<String> nok_w_dup = cInstanceList.stream().filter((x) -> "nok"
			.equals(x.getCheck()))
					.map(ConstraintInstance::getpEntity).collect(Collectors.toList());
			Set<String> nok_wo_dup = new HashSet<String>(nok_w_dup);			
			violated_instances = new ArrayList<String>(nok_wo_dup);
		}
		return violated_instances;
	}
}
