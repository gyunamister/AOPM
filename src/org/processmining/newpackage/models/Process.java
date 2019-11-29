package org.processmining.newpackage.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Process {
	public String prev_act;
	public String current_act;
	public String next_act;
	public int available_at;
	public boolean finished;
	
	public List<String> control_flow = new ArrayList<String>();
	
	public abstract void proceedNextActivity();
	
	public int recordEvent(Map<String, Map<String,Object>> eventlog, Order o1, Item i1, Package p1, 
			Route r1, String act, Resource res, int t, int e) {
		String event_id = "event" + e;
		eventlog.put(event_id, new HashMap<String,Object>());
		if(o1!=null) {
			eventlog.get(event_id).put("order_id",o1);
		}
		if(i1!=null) {
			eventlog.get(event_id).put("item_id",i1);
		}
		if(p1!=null) {
			eventlog.get(event_id).put("package_id",p1);
		}
		if(r1!=null) {
			eventlog.get(event_id).put("route_id",r1);
		}
		eventlog.get(event_id).put("activity",act);
		eventlog.get(event_id).put("resource",res);
		eventlog.get(event_id).put("timestamp",t);
		e = e+1;
		return e;
	}
}
