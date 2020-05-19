package org.processmining.AOPM.algorithms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ConstraintInstance;

public class Materialization {
	
	public Map<String, Map<String,List<ConstraintInstance>>> materialize(Set<ConstraintInstance> CIS, int currentTime, Map<String, Set<String>> CCV){
		
		Map<String, Map<String,List<ConstraintInstance>>> matProcessCube = new LinkedHashMap<String, Map<String,List<ConstraintInstance>>>();
		Set<String> cfSet = CCV.get("cf");
		Set<String> orderSet = CCV.get("Order");
		
		for(String cf: cfSet) {
			for(String o: orderSet) {
				if(o.equals("foreach")){
					Set<ConstraintInstance> filteredCInstanceSet = CIS.stream() 
							.filter((x) -> cf.equals(x.getcfName())).collect(Collectors.toSet());
					matProcessCube = filteredCInstanceSet.stream().collect(Collectors.groupingBy(ConstraintInstance::getcfName, Collectors.groupingBy(ConstraintInstance::getOrder)));
				}else{
					Set<ConstraintInstance> filteredCInstanceSet = CIS.stream() .filter((x) -> cf.equals(x.getcfName())).collect(Collectors.toSet());
					matProcessCube = filteredCInstanceSet.stream().collect(Collectors.groupingBy(ConstraintInstance::getcfName, Collectors.groupingBy(ConstraintInstance::getNull)));
				}
			}
		}
		return matProcessCube;
	}
}
