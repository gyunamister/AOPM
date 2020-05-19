package org.processmining.AOPM.IS_OHP;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.processmining.AOPM.simulation.ProcessSimulation;

public class Scheduler {
	public ProcessSimulation pSim;
	
	public List<String> getResourceNames(){
		List<String> r_names = new ArrayList<String>();
		for(Resource r : pSim.resource_list) {
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
	
	public Scheduler(ProcessSimulation sm) {
		this.pSim = sm; 
	}
	
	public int assign(Map<String, Map<String,Object>> eventlog, List<ObjectType> rot, int t, int e) {
		Random random = new Random(t);
		for(Resource r : this.pSim.resource_list) {
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
								int processingTime = ( (int) Math.round(random.nextGaussian()) + r.tasks.get(task));
								rot.get(index).available_at = t+processingTime;
								rot.get(index).status = "work_in_process";
								
								r.available_at = t+processingTime;
								r.setStatus("work_in_process");
							
								//record event
								if(rot.get(index) instanceof Order) {
									String orderName = ((Order)rot.get(index)).getObjectName();
									List<String> items = ((Order)rot.get(index)).items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = null;
									if(items.size()!=0) {
										itemNames = String.join("&", items);
									}
									e = rot.get(index).process.recordEvent(eventlog, orderName, itemNames, null, null, 
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Item) {
									String orderName = ((Item)rot.get(index)).order.getObjectName();
									String itemName = ((Item)rot.get(index)).getObjectName();
									e = rot.get(index).process.recordEvent(eventlog, orderName, itemName, null, null, 
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Package) {
									String orderName = ((Package)rot.get(index)).order.getObjectName();
									List<String> items = ((Package)rot.get(index)).items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = String.join("&", items);
									String packageName = ((Package)rot.get(index)).getObjectName();
									e = rot.get(index).process.recordEvent(eventlog, orderName, itemNames, packageName, null, 
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, e);
								}else if(rot.get(index) instanceof Route) {
									String orderName = ((Route)rot.get(index)).order.getObjectName();
									List<String> items = ((Route)rot.get(index)).packages.items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = String.join("&", items);
									String packageName = ((Route)rot.get(index)).packages.getObjectName();
									String routeName = ((Route)rot.get(index)).getObjectName();
									e = rot.get(index).process.recordEvent(eventlog, orderName, itemNames, packageName, routeName, 
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, e);
								}else {
									e = rot.get(index).process.recordEvent(eventlog, null, null, null, null, 
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, e);
								}
								
//								System.out.println(rot.get(index).object_name + " completes " + rot.get(index).process.current_act + 
//										" at " + rot.get(index).available_at);
								
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
		for(Resource r : this.pSim.resource_list) {
			if(r.available_at<=t) {
				r.setStatus("ready_to_assign");
			}
		}
	}
}
