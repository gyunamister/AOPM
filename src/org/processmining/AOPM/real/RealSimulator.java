package org.processmining.AOPM.real;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.processmining.AOPM.models.ConstraintInstance;

import com.opencsv.CSVReader;


public class RealSimulator implements Runnable{
	List<RealActionSet> actionCollection;
	Map<String, Map<String, Map<String,List<String>>>> cfSet;
	public RealConstraintMonitor cm;
	public RealConstraintStorage cs;
	public RealActionStorage as;
	public RealActionEngine ae;
	public List<RealEvent> eventlog;
	//(TODO)
	public List<String> objectList = new ArrayList<String>();
	public int speed;

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

	public RealSimulator(Map<String, Map<String, Map<String,List<String>>>> cfSet, List<RealActionSet> aCollection){
		this.eventlog = this.readEvents();
		this.actionCollection = aCollection;
		this.cfSet = cfSet;
		cm = new RealConstraintMonitor();
		cs = new RealConstraintStorage();
		as = new RealActionStorage(this);
		ae = new RealActionEngine(as, aCollection);
	}





	public int simulate(int i, int e) {
		int monitorInterval = 24;
		int executeInterval = 24;
		//Eventlog Streaming
		this.objectList=this.updateObjectList(i,this.eventlog,this.objectList);
		this.recordSublog(i, executeInterval, this.eventlog, this.objectList);
		List<ConstraintInstance> ci = cm.monitor(t,monitorInterval, this.eventlog, this.objectList, this.cfSet);
		cs.addInstances(ci);
		ae.setConstraintStorage(cs);
		ae.executeInstanceAction(t,executeInterval);
		t=i;
		return e;
	}

	public List<RealEvent> readEvents() {
		List<RealEvent> eventlog = new ArrayList<RealEvent>();
		try {
			CSVReader csvReader = new CSVReader(new FileReader("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/experiment/final-BPI_Challenge_2019.csv"));
			String[] row;
			int e = -1;
			while ((row = csvReader.readNext()) != null) {
				e+=1;
				if(e==0) {
					continue;
				}
			    RealEvent event = new RealEvent(e,row[0], row[1], row[2], Integer.parseInt(row[3]));
			    String eid = "e"+e;
			    eventlog.add(event);
			}
			csvReader.close();
		}catch (IOException ex) {
			System.out.println(ex);
		}
		return eventlog;
	}

	public void recordSublog(int t, int executeInterval, List<RealEvent> eventlog, List<String> objectList) {
		if(t%executeInterval==0) {
			List<RealEvent> sublog = eventlog.stream().filter(x -> x.getTime()<=t && objectList.contains(x.getCaseid())).collect(Collectors.toList());
			int k=0;
			for(RealEvent e: sublog) {
				List<String> lines = Arrays.asList(e.toString());
				if(k==0) {
					List<String> header = Arrays.asList("eid,Caseid,Activity,Resource,CompleteTimestamp");
					Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/"
							+ "processmining/AOPM/experiment/real-wo-sublogs/sublog-"+ t + ".csv");
					try {
						Files.write(file, header, StandardCharsets.UTF_8);
						Files.write(file, lines, StandardOpenOption.APPEND);
					} catch (IOException ex) {
						System.out.println(ex);
					}
				}else {
					Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/"
							+ "processmining/AOPM/experiment/real-wo-sublogs/sublog-"+ t + ".csv");
					try {
						Files.write(file, lines, StandardOpenOption.APPEND);
					} catch (IOException ex) {
						System.out.println(ex);
					}
				}
				k+=1;
			}
		}
	}

	public void recordEventlog(List<RealEvent> eventlog) {
		int k=0;
		for(RealEvent e: eventlog) {
			List<String> lines = Arrays.asList(e.toString());
			if(k==0) {
				List<String> header = Arrays.asList("eid,Caseid,Activity,Resource,CompleteTimestamp");
				Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/"
						+ "processmining/AOPM/experiment/real-eventlog.csv");
				try {
					Files.write(file, header, StandardCharsets.UTF_8);
					Files.write(file, lines, StandardOpenOption.APPEND);
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}else {
				Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/"
						+ "processmining/AOPM/experiment/real-eventlog.csv");
				try {
					Files.write(file, lines, StandardOpenOption.APPEND);
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
			k+=1;
		}
	}

	public List<String> updateObjectList(int i, List<RealEvent> eventlog, List<String> objectList) {
		List<String> newCaseids = eventlog.stream().filter((x)
				-> i==x.getTime()).map(RealEvent::getCaseid).
				collect(Collectors.toList());
		objectList.addAll(newCaseids);
		objectList = new ArrayList<>(new HashSet<>(objectList));
		String lastAct = "Record Goods Receipt";
		List<String> oldCaseids = eventlog.stream().filter((x) -> (this.objectList.contains(x.getCaseid())) && (i > x.getTime())  && (lastAct.equals(x.getActivity()))).map(RealEvent::getCaseid).collect(Collectors.toList());
		objectList.removeAll(oldCaseids);
		objectList = new ArrayList<>(new HashSet<>(objectList));
		return objectList;
	}
}
