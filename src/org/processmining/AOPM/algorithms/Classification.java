package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.Event;

public class Classification {
	public List<Object> listProcessEntity(List<Event> eventlog,String entity){
		List<Object> entityList = new ArrayList<Object>();
		if(entity.equals("Resource")) {
			entityList = eventlog.stream().map(Event::getRes).collect(Collectors.toList());
		}else if(entity.equals("Activity")) {
			entityList = eventlog.stream().map(Event::getAct).collect(Collectors.toList());
		}else {
			//in case of objects
			List<Map<String,List<Object>>> omapList = eventlog.stream().map(Event::getOmap).collect(Collectors.toList());
			for(Map<String,List<Object>> omap : omapList) {
				entityList.addAll(omap.get(entity));
			}
		}
		return entityList;
	}
	
	//Output contextualize
	public Map<String, List<Event>> generateEventsByObject(List<Event> eventlog, String objectName){
		Map<String, List<Event>> entityEventMap = new HashMap<String, List<Event>>();
		List<Object> entityList = listProcessEntity(eventlog,objectName);
		for(Object o : entityList) {
			List<Event> events;
//			= new ArrayList<String>();
			events = eventlog.stream().filter((x)->x.getOmap().get(objectName).contains(o)).collect(Collectors.toList());
//			events.addAll(((ObjectType)o).history);
//			Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
//			for(String s: events) {
//				Map<String, Object> e = eventlog.get(s);
//				sublog.put(s,e);
//			}
			entityEventMap.put((String) o,events);
		}
		return entityEventMap;
	}
}
