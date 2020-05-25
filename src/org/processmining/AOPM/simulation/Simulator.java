package org.processmining.AOPM.simulation;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.processmining.AOPM.ConstraintCube.ConstraintCubeStructure;
import org.processmining.AOPM.Exporter.AISExporter;
import org.processmining.AOPM.Importer.OCXMLImporter;
import org.processmining.AOPM.algorithms.ActionEngine;
import org.processmining.AOPM.algorithms.ConstraintMonitor;
import org.processmining.AOPM.models.AEConfig;
import org.processmining.AOPM.models.ActionInstance;
import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.ConstraintInstanceStream;
import org.processmining.AOPM.models.Dashboard;
import org.processmining.AOPM.models.Event;
import org.processmining.AOPM.models.EventStream;
import org.processmining.EIS.OHP.ActionGateway;
import org.processmining.EIS.Simulation.ProcessSimulation;


public class Simulator implements Runnable{
	public OCXMLImporter importer;
	public String AISfilePath; 
	public AISExporter exporter;
	public CMConfig cmConfig;
	public AEConfig aeConfig;
	public ConstraintMonitor cm;
	public ConstraintInstanceStream cs;
	public ConstraintCubeStructure ccs;
	public Dashboard db;
	public ActionEngine ae;
	public EventStream es;
	public ProcessSimulation pSimulator;
	public ActionGateway aGateway;
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
			this.cs = new ConstraintInstanceStream();
			this.db = new Dashboard(cmConfig, this.cs);
			this.ae = new ActionEngine(aeConfig);
			this.cmConfig = cmConfig;
			this.aeConfig = aeConfig;
		}else if(this.mode=="log-replay") {
		}else {
			System.out.println("Mode not existing");
		}
		
	}
	
	public Simulator(CMConfig cmConfig, AEConfig aeConfig, ProcessSimulation pSimulator){
		if(this.mode.equals("order-handling-process")) {
//			this.eventlog = new ArrayList<Event>();
			this.es = new EventStream();
			this.cm = new ConstraintMonitor(cmConfig);
			this.cs = new ConstraintInstanceStream();
//			(TODO) hard-coded properties
			Set<String> defaultProp= new HashSet<String>();
			Collections.addAll(defaultProp, "cf","proc","act","res","time");
			Set<String> objectProp= new HashSet<String>();
			Collections.addAll(objectProp, "Order","Item","Package","Route");
			Set<String> attrProp= new HashSet<String>();
			this.ccs = new ConstraintCubeStructure(this.cs.cis,defaultProp,objectProp,attrProp);
			this.db = new Dashboard(cmConfig, this.cs);
			this.ae = new ActionEngine(aeConfig);
			this.cmConfig = cmConfig;
			this.aeConfig = aeConfig;
			this.pSimulator=pSimulator;
			this.aGateway = new ActionGateway(pSimulator);
		}else {
			System.out.println("Mode not existing");
		}
		this.importer = new OCXMLImporter(pSimulator.filePath);
		this.AISfilePath = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/OH-AIS-%s.xml",new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		this.exporter = new AISExporter(this.AISfilePath);
	}

	



	public void simulate(int t) {
//		Set<Event> eventsAtT = this.readEvents(t);
		this.pSimulator.simulateProcess(t);
		Set<Event> eventsAtT = this.importer.readOCXML(t);
		this.es.setEventSet(eventsAtT);
		Set<ConstraintInstance> cis = this.cm.monitor(t,this.es);
		this.cs.addInstances(cis);
		this.ccs.updateElem(t);
		this.ccs.updateHier(t);
		this.db.updateVMap(t);
		Set<ActionInstance> ais = this.ae.engine(t,this.cs,this.ccs);
		this.recordAI(ais);
		this.aGateway.apply(ais);
	}
	
	public void recordAI(Set<ActionInstance> aisAtT) {
		for(ActionInstance ai:aisAtT) {
			try {
				this.exporter.recordAI(ai);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	}
