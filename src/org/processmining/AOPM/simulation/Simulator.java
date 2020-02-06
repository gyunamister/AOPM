package org.processmining.AOPM.simulation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.algorithms.ActionEngine;
import org.processmining.AOPM.algorithms.ConstraintMonitor;
import org.processmining.AOPM.models.ActionSet;
import org.processmining.AOPM.models.ActionStorage;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.ConstraintStorage;
import org.processmining.AOPM.models.Resource;

import com.opencsv.CSVReader;


public class Simulator implements Runnable{
//	public Map<String,List <String>> action_pack;
	public Map<String, ActionSet> action_pack;
//	public List<String> constraints;
	public MultiProcess mp;
	public Scheduler sch;
	public ConstraintMonitor cm;
	public ConstraintStorage cs;
	public ActionEngine ae;
	public int speed;
 
	public List<Resource> resource_list;
	
	public int t=0;
	public int e=1;
	public boolean isRestart = false;
	public boolean isSleep = false;
	//public ChartVisualizePanel cvp;

	public void run() {
		synchronized(this) {
			for(int i=t;i<10000;i++) { 
				e = simulate(i,e);
				try {
					isSleep=false;
					Thread.sleep(1000/(speed*10));
	            } catch (InterruptedException ex) {
	            		System.out.println("I'm about to stop");
	            		return;
	            }
			}
		}
	}

	public Simulator(Map<String, ActionSet> s){
		action_pack = s;
		mp = new MultiProcess();
		cm = new ConstraintMonitor(mp, action_pack);
		cs = new ConstraintStorage();
		ae = new ActionEngine(mp, new ActionStorage(this), action_pack);
		String csvFile = "/Users/GYUNAM/Documents/Workshop/tests/testfiles/res_act_mat.csv";
		resource_list = this.generateResource(csvFile);
		sch = new Scheduler(this);
		mp.setResourceNames(sch.getResourceNames());
	}

	



	public int simulate(int a, int e) {
		System.out.println(t + " begins:");
		/*
		 * process runs at every time step.
		 * 1. generate order with probability
		 * 2. initialize relevant items
		 * 3. check if a set of items is ready to be packed
		 * 4. if so, initialize package
		 * 5. check if a package is ready to be delivered
		 * 6. initialize delivery (route)
		 * 7. if delivery failed, initialize another delivery
		 * 8. find ready-to-assign objects and give them priority (for resource scheduling)
		 * 9. Remove relevant objects if delivery is done
		 * */ 
		this.mp.run(t);
		/*
		 * update the current status of resources - to identify who is ready for assignment.
		 * */
		this.sch.updateResourceStatus(t);
		/*
		 * assign resource and generate event
		 * */
		e = this.sch.assign(mp.eventlog, mp.ready_object_list, t, e);
		/*
		 * 
		 * */
		List<ConstraintInstance> ci = cm.evaluate(t);
		cs.addInstances(ci);
		ae.setConstraintStorage(cs);
		ae.executeSingleAction(t);
		ae.executeAggAction(t);
		t=a;
		return e;
	}
	
	public List<Resource> generateResource(String csvFile) {
		List<Resource> rl = new ArrayList<Resource>();
		//String csvFile = "/Users/GYUNAM/Documents/Workshop/tests/testfiles/res_act_mat.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            String[] header = reader.readNext();
            
            while ((line = reader.readNext()) != null) {
                //System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            		Resource r = new Resource(line[0]);
            		rl.add(r);
            		for(int i=1;i<line.length;i++) {
            			if(!line[i].isEmpty()) {
            				r.setTask(header[i], Integer.parseInt(line[i]));
            			}
            			
            		}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rl;
	}
	
	public void addResource(String r_name, Map<String,Integer> tasks) {
		Resource r = new Resource(r_name);
		for(String task:tasks.keySet()) {
			r.setTask(task, tasks.get(task));	
		}
		this.resource_list.add(r);
		
	}
}
