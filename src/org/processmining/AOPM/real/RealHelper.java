package org.processmining.AOPM.real;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ConstraintInstance;

public class RealHelper {
	public Map<String, List<RealEvent>> generateEventsByObject(List<RealEvent> eventlog, List<String> ongoingObjectList){
		Map<String, List<RealEvent>> entityEventMap = new LinkedHashMap<String, List<RealEvent>>();

		for(String s: ongoingObjectList) {
			List<RealEvent> sublog = eventlog.stream().filter(x -> x.getCaseid().equals(s)).collect(Collectors.toList());
			entityEventMap.put(s, sublog);
		}
		return entityEventMap;
	}
	
	//generate constraint instance
	public ConstraintInstance generateConstraintInstance(String constraintName, String entityName, int currentTime, String result) {
		return new ConstraintInstance(constraintName, entityName, currentTime, result);
	}
}
