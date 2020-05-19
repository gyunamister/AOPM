package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.processmining.AOPM.IS_OHP.ObjectType;
import org.processmining.AOPM.models.ConstraintInstance;

public class Helper {
		public <K, V> Stream<K> keys(Map<K, List<V>> map, V value) {
		    return map.entrySet().stream().filter(entry -> entry.getValue().contains(value)).map(Map.Entry::getKey);
		}
		
		//generate all related events based on a graph. 
		public Map<String, Map<String, Map<String, Object>>> generateEventsByGraph(Map<String, Map<String, Object>> filteredLog, Map<String,List<ObjectType>> objectGraph, List<ObjectType> entityList){
			Map<String, Map<String, Map<String, Object>>> entityEventMap = new HashMap<String, Map<String, Map<String, Object>>>();
			for(ObjectType o : entityList) {
				Stream<String>relatedGraphStream = this.keys(objectGraph, o);
				List<String> relatedGraphList = relatedGraphStream.collect(Collectors.toList());
				//add all related events (an object can belong to many graphs)
				List<String> events = new ArrayList<String>();
				for(String graphName:relatedGraphList) {
					for(ObjectType v : objectGraph.get(graphName)) {
						events.addAll(v.history);
					}
				}
				Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
				for(String s: events) {
					Map<String, Object> e = filteredLog.get(s);
					if(e!=null) {
						sublog.put(s,e);
					}
				}
				entityEventMap.put(o.getObjectName(),sublog);
			}
			return entityEventMap;
		}
		
		//generate constraint instance
		public ConstraintInstance generateConstraintInstance(String constraintName, String entityName, int currentTime, String result) {
			return new ConstraintInstance(constraintName, entityName, currentTime, result);
		}
	
	
	
}
