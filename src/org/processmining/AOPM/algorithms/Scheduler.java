package org.processmining.AOPM.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.processmining.AOPM.models.Item;
import org.processmining.AOPM.models.ObjectType;
import org.processmining.AOPM.models.Order;
import org.processmining.AOPM.models.Package;
import org.processmining.AOPM.models.Resource;
import org.processmining.AOPM.models.Route;

public class Scheduler {
	public Simulator simulator;
	
	public List<String> getResourceNames(){
		List<String> r_names = new ArrayList<String>();
		for(Resource r : simulator.resource_list) {
			r_names.add(r.resource_name);
		}
		return r_names;
	}
	
	/*
	public void old_generateResource(int num_res) {
		for(int i=0;i<num_res;i++) {
			this.order_simulator.resource_list.add(new Resource("RO"+i,1));
			this.item_simulator.resource_list.add(new Resource("RI"+i,1));
			this.package_simulator.resource_list.add(new Resource("RP"+i,1));
			this.route_simulator.resource_list.add(new Resource("RR"+i,1));
		}
	}
	*/
	
	public Scheduler(Simulator sm) {
		this.simulator = sm;
	}
	
	public int assign(Map<String, Map<String,Object>> eventlog, List<ObjectType> rot, int t, int e) {
		Random random = new Random();
		for(Resource r : this.simulator.resource_list) {
			if(r.status.equals("ready_to_assign")) {
				int index=0;
				if(rot.size()>0) {
					outerloop:
					for(int i=0;i<rot.size();i++) {
						for(String task : r.tasks.keySet()) {
							if(rot.get(i).process.next_act.equals(task)) {
								index=i;
								//e was incremented in recordEvent -> need to deduct 1
								rot.get(index).setHistory("event"+e);
								//assign task and adjust values
								rot.get(index).process.proceedNextActivity();
								rot.get(index).prev_sojourn_time = t-rot.get(index).available_at; 
								rot.get(index).available_at = t+( (int) Math.round(random.nextGaussian()) + r.tasks.get(task));
								rot.get(index).status = "work_in_process";
								
								r.available_at = t+( (int) Math.round(random.nextGaussian()) + r.tasks.get(task));
								r.setStatus("work_in_process");
							
								//record event
								if(rot.get(index) instanceof Order) {
									e = rot.get(index).process.recordEvent(eventlog, (Order)rot.get(index), null, null, null, 
											rot.get(index).process.current_act, r, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Item) {
									e = rot.get(index).process.recordEvent(eventlog, null, (Item)rot.get(index), null, null, 
											rot.get(index).process.current_act, r, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Package) {
									e = rot.get(index).process.recordEvent(eventlog, null, null, (Package)rot.get(index), null, 
											rot.get(index).process.current_act, r, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Route) {
									e = rot.get(index).process.recordEvent(eventlog, null, null, null, (Route)rot.get(index), 
											rot.get(index).process.current_act, r, rot.get(index).available_at, e);
								}else {
									e = rot.get(index).process.recordEvent(eventlog, null, null, null, null, 
											rot.get(index).process.current_act, r, rot.get(index).available_at, e);
								}
//								System.out.println(rot.get(index).history);
								System.out.println(rot.get(index).object_name + " completes " + rot.get(index).process.current_act + 
										" at " + rot.get(index).available_at);
								
								rot.remove(rot.get(index));
								break outerloop;
							}
						}
						
					}
				}
			}
		}
		
	return e;
	}
	
	public void updateResourceStatus(int t) {
		for(Resource r : this.simulator.resource_list) {
			if(r.available_at<=t) {
				r.setStatus("ready_to_assign");
			}
		}
	}
}
