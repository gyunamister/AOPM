package org.processmining.newpackage.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.processmining.newpackage.models.ConstraintInstance;
import org.processmining.newpackage.models.Item;
import org.processmining.newpackage.models.ObjectType;
import org.processmining.newpackage.models.Order;
import org.processmining.newpackage.models.Package;
import org.processmining.newpackage.models.Route;

public class Utility {
	public class Filter{
		
		
		//Object
		public Map<String, Map<String, Object>> getEventsByObject(Map<String, Map<String, Object>> eventlog, List<ObjectType> ongoingObjectList, List<String> selectedObjectList){
			List<String> events = new ArrayList<String>();
			for(String ot: selectedObjectList) {
				if(ot.equals("Order")) {
					for(ObjectType o : ongoingObjectList) {
						if(o instanceof Order) {
							events.addAll(o.history);
						}
					}
				}else if(ot.equals("Item")) {
					for(ObjectType o : ongoingObjectList) {
						if(o instanceof Item) {
							events.addAll(o.history);
						}
					}
				}else if(ot.equals("Pacakage")) {
					for(ObjectType o : ongoingObjectList) {
						if(o instanceof Package) {
							events.addAll(o.history);
						}
					}
				}else if(ot.equals("Route")) {
					for(ObjectType o : ongoingObjectList) {
						if(o instanceof Route) {
							events.addAll(o.history);
						}
					}
				}
			}
			
			Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
			for(String s: events) {
				Map<String, Object> e = eventlog.get(s);
				sublog.put(s,e);
			}
			return sublog;
		}
		
		//Process Entity Type
		public List<ObjectType> getObjectEntityList(String entity,Map<String, Map<String, Object>> eventlog, List<ObjectType> ongoingObjectList){
			List<ObjectType> entityList = new ArrayList<ObjectType>();
			if(entity.equals("Item")) {
				for(ObjectType o : ongoingObjectList) {
					if(o instanceof Item) {
						entityList.add(o);
					}
				}
			}
			return entityList;
		}
		
		public List<Object> getProcessEntityList(String entity,Map<String, Map<String, Object>> eventlog, List<ObjectType> ongoingObjectList){
			List<Object> entityList = new ArrayList<Object>();
			if(entity.equals("Resource")) {
				entityList = eventlog.entrySet().stream().map(x -> x.getValue().get("Resource")).collect(Collectors.toList());
			}else if(entity.equals("Activity")) {
				entityList = eventlog.entrySet().stream().map(x -> x.getValue().get("Activity")).collect(Collectors.toList());
			}
			return entityList;
		}
		
		//Output contextualize
		public Map<String, Map<String, Map<String, Object>>> generateEventsByObject(Map<String, Map<String, Object>> eventlog, List<ObjectType> objectList){
			Map<String, Map<String, Map<String, Object>>> entityEventMap = new HashMap<String, Map<String, Map<String, Object>>>();
			for(ObjectType o : objectList) {
				List<String> events = new ArrayList<String>();
				events.addAll(o.history);
				Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
				for(String s: events) {
					Map<String, Object> e = eventlog.get(s);
					sublog.put(s,e);
				}
				entityEventMap.put(o.getObjectName(),sublog);
			}
			return entityEventMap;
		}
		
		public <K, V> Stream<K> keys(Map<K, List<V>> map, V value) {
		    return map.entrySet().stream().filter(entry -> entry.getValue().contains(value)).map(Map.Entry::getKey);
		}
		
		//generate all related events based on a graph. 
		public Map<String, Map<String, Map<String, Object>>> generateEventsByGraph(Map<String, Map<String, Object>> filteredLog, Map<String,List<ObjectType>> objectGraph, List<ObjectType> objectList){
			Map<String, Map<String, Map<String, Object>>> entityEventMap = new HashMap<String, Map<String, Map<String, Object>>>();
			for(ObjectType o : objectList) {
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
					try {
						Map<String, Object> e = filteredLog.get(s);
						sublog.put(s,e);
					}catch(NullPointerException e){
						continue;
					}
				}
				entityEventMap.put(o.getObjectName(),sublog);
			}
			return entityEventMap;
		}
		
		//Data ACQ
		public int calcThroughputTime(Map<String, Map<String, Object>> sublog, int currentTime) {
			List<Object> times = new ArrayList<Object>();
			for(String e : sublog.keySet()) {			
				times.add(sublog.get(e).get("timestamp"));
			}
			int max=0;
			for(Object i : times) {
				if((int) i>max) {
					max=(int) i;
				}
			}
			int min=10000000;
			for(Object i : times) {
				if((int) i<min) {
					min= (int) i;
				}
			}
			int throughput_time = max-min;
			return throughput_time;
		}
		
		//Evaluate
		public String evalRelational(int a, String relation, int b) {
			String result = "ok";
			if(relation.equals(">")) {
				if(a<=b) {
					result = "nok";
				}
			}else if(relation.equals("=")) {
				if(a!=b) {
					result = "nok";
				}
			}else if(relation.equals("<")) {
				if(a>=b) {
					result = "nok";
				}
			}
			return result;
		}
		
		//generate constraint instance
		public ConstraintInstance generateConstraintInstance(String constraintName, String entityName, int currentTime, String result) {
			return new ConstraintInstance(constraintName, entityName, currentTime, result);
		}
		
	
	
	}
	
	
	
	
}
