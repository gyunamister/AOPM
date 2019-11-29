package org.processmining.newpackage.algorithms;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.newpackage.models.ActionStorage;
import org.processmining.newpackage.models.ConstraintInstance;
import org.processmining.newpackage.models.ConstraintStorage;
//import org.processmining.newpackage.plugins.ChartVisualizePanel;
import org.processmining.newpackage.models.Resource;

import com.opencsv.CSVReader;


public class Simulator implements Runnable{
	public Map<String,List <String>> action_pack;
	public List<String> constraints;
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

	public Simulator(Map<String,List<String>> s){
		action_pack = s;
		System.out.println(action_pack);
		constraints = new ArrayList<String>();
		
		for(String as : action_pack.keySet()) {
			constraints.add(action_pack.get(as).get(0));
		}
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
		mp.run(t);
		sch.updateResourceStatus(t);
		e = sch.assign(mp.eventlog, mp.ready_object_list, t, e);
		List<ConstraintInstance> ici = cm.instanceLevelCheck(t);
		cs.addInstances(ici);
		List<ConstraintInstance> pci = cm.processLevelCheck(t);
		cs.addInstances(pci);
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
