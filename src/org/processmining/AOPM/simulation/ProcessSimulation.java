package org.processmining.AOPM.simulation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.processmining.AOPM.IS_OHP.OrderHandlingProcess;
import org.processmining.AOPM.IS_OHP.Resource;
import org.processmining.AOPM.IS_OHP.Scheduler;

import com.opencsv.CSVReader;


public class ProcessSimulation{
	public int e = 1;
	public OrderHandlingProcess mp;
	public Scheduler sch;
	public int speed;
	public String mode = "order-handling-process";
	public List<Resource> resource_list;
	public boolean isRestart = false;
	public boolean isSleep = false;

	public ProcessSimulation(){
		mp = new OrderHandlingProcess();
		String resoureFile = "/Users/GYUNAM/Documents/AOPM/tests/testfiles/res_act_mat.csv";
		resource_list = this.generateResource(resoureFile);
		sch = new Scheduler(this);
		mp.setResourceNames(sch.getResourceNames());
	}



	public OrderHandlingProcess getProcess() {
		return this.mp;
	}

	public void simulateProcess(int t) {
		//System.out.println(t + " begins:");
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
		this.e = this.sch.assign(mp.eventlog, mp.ready_object_list, t, this.e);
	}

	public List<Resource> generateResource(String csvFile) {
		List<Resource> rl = new ArrayList<Resource>();
		//String csvFile = "/Users/GYUNAM/Documents/AOPM/tests/testfiles/res_act_mat.csv";

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
