package org.processmining.AOPM.real;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.processmining.AOPM.IS_OHP.ObjectType;
import org.processmining.AOPM.models.ConstraintInstance;

public class RealConstraintMonitor {
	public List<String> constraint_list = new ArrayList<String>();
	Map<String, Map<String, Map<String,List<String>>>> constraintMap;
	RealHelper helper;
	RealDerivation calculator;
	RealEvaluation evaluator;

	public RealConstraintMonitor() {
		helper = new RealHelper();
		calculator = new RealDerivation();
		evaluator = new RealEvaluation();
	}

//	public void setConstraintList(List<String> s) {
//		constraint_list = s;
//	}
	public List<ConstraintInstance> monitor(int currentTime, int monitorInterval,
			List<RealEvent> eventlog, List<String> objectList, Map<String, Map<String, Map<String,List<String>>>> constraintMap) {
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		if(currentTime%monitorInterval==0) {
			for(String asName : constraintMap.keySet()) {
				List<ConstraintInstance> ciList = evaluate(currentTime, eventlog, objectList, constraintMap.get(asName));
				//record them into constraint-log
				for(ConstraintInstance ci : ciList) {
					this.recordCI(ci, currentTime, monitorInterval);
				}
				outputs.addAll(ciList);
			}
		}
		return outputs;
	}

	public List<ConstraintInstance> evaluate(int currentTime, List<RealEvent> eventlog, List<String> objectList, Map<String, Map<String,List<String>>> constraintSet){
		//initialize list for monitoring output
		List<ConstraintInstance> outputs = new ArrayList<ConstraintInstance>();
		//(TODO) generate eventlog
		List<RealEvent> filteredLog = eventlog;

		List<String> relevantEntityType = constraintSet.get("filter").get("relevantEntity");
		//(TODO) ongoing objects
		List<String> ongoingObjectList = objectList;

		//get process entities
		String selectedEntity = constraintSet.get("constraintINFO").get("processEntity").get(0);

		//ignore context
		List<String> selectedContextList = constraintSet.get("validate").get("condition");

		//(TODO) no objectgraph required - single case notion
		Map<String, List<RealEvent>> eventCollectionByEntity;
		if(relevantEntityType.size()==1) {
			eventCollectionByEntity =
					helper.generateEventsByObject(filteredLog,ongoingObjectList);
		}else {
			throw new IllegalArgumentException("only one object is allowed");
		}

		//condition check
		List<String> selectedEvalList = constraintSet.get("evaluate").get("condition");
		for(String entity: eventCollectionByEntity.keySet()) {
			//We simply assume that we already have predefined functions. So, what we need to is just to call the function.
			//evaluate
			String result = "ok";
			for(String c : selectedEvalList) {
				if(c.contains(">") | c.contains("=") | c.contains("<")) {
					String valueName=c.split(",")[0];
					String comp=c.split(",")[1];
					int thres=Integer.parseInt(c.split(",")[2]);
					int val = 0;
					if(c.contains("Throughput")) {
						val = calculator.calcThroughputTime(eventCollectionByEntity.get(entity), currentTime);
					}
					result = evaluator.evalRelational(val,comp,thres);
					if(result.equals("nok")) {
						break;
					}
				}
			}
			ConstraintInstance ci = helper.generateConstraintInstance(constraintSet.get("constraintINFO").get("constraintName").get(0), entity, currentTime, result);
//			System.out.println(ci);
			outputs.add(ci);
		}
		return outputs;
	}

	public void recordCI(ConstraintInstance ci, int currentTime, int monitorInterval) {
		List<String> lines = Arrays.asList(ci.toString());
		if(currentTime/monitorInterval==1) {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/experiment/real-constraint-log.csv");
			try {
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}else {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/experiment/real-constraint-log.csv");
			try {
				Files.write(file, lines, StandardOpenOption.APPEND);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}

	/*
	 * Below is a reference for stream operation!
	 */
	public Map<String, Map<String, Object>> getAllEvents(Map<String, Map<String, Object>> eventlog, List<ObjectType> g){
		List<String> events = new ArrayList<String>();
		for(ObjectType v : g) {
			events.addAll(v.history);
		}
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}

	public Map<String, Map<String, Object>> getSingleObjectEvents(Map<String, Map<String, Object>> eventlog, ObjectType o){
		List<String> events = new ArrayList<String>();
		events.addAll(o.history);
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}

	public Map<String, Map<String, Object>> getProcessComponentEvents(Map<String, Map<String, Object>> eventlog, String comp, String v, int t){
		int  relative_from_t;
		int from_t=100;
		if(t-from_t<0) {
			relative_from_t = 0;
		}else {
			relative_from_t = t-from_t;
		}
		List<String> events = new ArrayList<String>();
		events = eventlog.entrySet().stream()
				.filter(x -> x.getValue().get(comp).toString().equals(v) && relative_from_t<= (int)x.getValue().get("timestamp"))
				.map(x -> x.getKey()).collect(Collectors.toList());
		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
		for(String s: events) {
			Map<String, Object> e = eventlog.get(s);
			sublog.put(s,e);
		}
		return sublog;
	}

	private double calculateAverage(List <Integer> marks) {
		OptionalDouble average = marks.stream().mapToDouble(a -> a).average();
		return average.isPresent() ? average.getAsDouble() : 0;
	}

}
