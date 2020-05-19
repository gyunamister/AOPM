package org.processmining.AOPM.simulation;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.processmining.AOPM.algorithms.ActionEngine;
import org.processmining.AOPM.algorithms.ConstraintMonitor;
import org.processmining.AOPM.models.AEConfig;
import org.processmining.AOPM.models.ActionGateway;
import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.ConstraintStorage;
import org.processmining.AOPM.models.Dashboard;
import org.processmining.AOPM.models.Event;
import org.processmining.AOPM.models.EventStream;

import com.opencsv.CSVReader;


public class Simulator implements Runnable{
	public CMConfig cmConfig;
	public AEConfig aeConfig;
	public ConstraintMonitor cm;
	public ConstraintStorage cs;
	public Dashboard db;
	public ActionEngine ae;
	public EventStream es;
	public ProcessSimulation pSimulator;
	public ActionGateway aGateway;
//	public List<Event> eventlog;
	public Set<String> objectSet = new HashSet<String>();
	
	public int speed;
	public String mode = "order-handling-process";
	
	public int t=0;
	public int e=1;
	public boolean isRestart = false;
	public boolean isSleep = false;

	public void run() {
		synchronized(this) { 
			for(int i=t;i<10000;i++) { 
				simulate(i);
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

	public Simulator(CMConfig cmConfig, AEConfig aeConfig){
		if(this.mode=="order-handling-process") {
//			this.eventlog = new ArrayList<Event>();
			this.es = new EventStream();
			this.cm = new ConstraintMonitor(cmConfig);
			this.cs = new ConstraintStorage();
//			this.db = new Dashboard(cmConfig, this.cs);
			this.ae = new ActionEngine(this.cs, aeConfig);
			this.cmConfig = cmConfig;
			this.aeConfig = aeConfig;
		}else if(this.mode=="log-replay") {
		}else {
			System.out.println("Mode not existing");
		}
		
	}
	
	public Simulator(CMConfig cmConfig, AEConfig aeConfig, ProcessSimulation pSimulator){
		if(this.mode=="order-handling-process") {
//			this.eventlog = new ArrayList<Event>();
			this.es = new EventStream();
			this.cm = new ConstraintMonitor(cmConfig);
			this.cs = new ConstraintStorage();
//			this.db = new Dashboard(cmConfig, this.cs);
			this.ae = new ActionEngine(this.cs, aeConfig);
			this.cmConfig = cmConfig;
			this.aeConfig = aeConfig;
			this.aGateway = new ActionGateway(pSimulator);
		}else {
			System.out.println("Mode not existing");
		}
		
	}

	



	public void simulate(int t) {
		Set<Event> eventsAtT = this.readEvents(t);
		this.es.setEventSet(eventsAtT);
//		int monitorInterval = 24; 
//		int executeInterval = 24;
		Set<ConstraintInstance> cis = this.cm.monitor(t,this.es);
		this.cs.addInstances(cis);
//		this.db.updateVMap(t);
//		Set<ActionInstance> ais = this.ae.generateInstanceLevelAction(t);
//		this.aGateway.apply(ais);
	}
	
	public Set<Event> readEvents(int time) {
		Set<Event> eventAtT = new HashSet<Event>();  
		try {
			CSVReader csvReader = new CSVReader(new FileReader("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/IS_OHP/eventlog.csv"));
			String[] row;
			int e = -1;
			while ((row = csvReader.readNext()) != null) {
				e+=1;
				if(e==0) {
					continue;
				}
				String[] objectNames = {"Order", "Item", "Package", "Route"};
				Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
				for(int i=5;i<row.length;i++) {
					Set<String> objectSet = new HashSet<String>();
					for(String s:row[i].split("&")) {
						objectSet.add(s);
					}
					omap.put(objectNames[i-5], objectSet);
				}
				//Read the events at time t
				if(time==Integer.parseInt(row[4])) {
					Event event = new Event(row[0], row[1], row[2], row[3], Integer.parseInt(row[4]),omap);
					eventAtT.add(event);
				}
			}
			csvReader.close();
		}catch (IOException ex) {
			System.out.println(ex);
		}
		return eventAtT;
	}
	}
