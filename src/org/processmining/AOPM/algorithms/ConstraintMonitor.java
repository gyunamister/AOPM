package org.processmining.AOPM.algorithms;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.Constraint;
import org.processmining.AOPM.models.ConstraintInstance;
import org.processmining.AOPM.models.EvalResult;
import org.processmining.AOPM.models.EventStream;
import org.processmining.AOPM.models.TimeMoment;

public class ConstraintMonitor {
	CMConfig cmConfig;
	Classification cl;
	Helper helper;
	Derivation calculator;
	Evaluation evaluator;

	public ConstraintMonitor(CMConfig cmConfig) {
		cl = new Classification();
		helper = new Helper();
		calculator = new Derivation();
		evaluator = new Evaluation();
		this.cmConfig=cmConfig;
	}


	public Set<ConstraintInstance> monitor(int currentTime, EventStream es) {
		Set<ConstraintInstance> outputs = new HashSet<ConstraintInstance>();
		if(es.getEventSet().size()!=0) {
			for(Constraint c : this.cmConfig.getConstraintSet()) {
				Set<EvalResult> evalResults=new HashSet<EvalResult>();
				for(TimeMoment tm: c.getTM()) {
					if(tm.getTime()==currentTime) {
						evalResults = c.getCF().apply(es.getEventSet(), tm.getTimeWindow());
					}
				}
				for(EvalResult evalResult:evalResults) {
					ConstraintInstance ci = new ConstraintInstance(c.getCF().cfName,evalResult.getContext(), currentTime,evalResult.outc);
					outputs.add(ci);
				}
			}
		}
		this.recordCI(outputs, currentTime);
		return outputs;
	}

	public void recordCI(Set<ConstraintInstance> cis, int currentTime) {
		if(currentTime == 1) {
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
			try {
				String temp="";
				List<String> lines = Arrays.asList(temp);
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		for(ConstraintInstance ci:cis) {
			List<String> lines = Arrays.asList(ci.toString());
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
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
//	public Map<String, Map<String, Object>> getAllEvents(Map<String, Map<String, Object>> eventSet, Set<ObjectType> g){
//		Set<String> events = new HashSet<String>();
//		for(ObjectType v : g) {
//			events.addAll(v.history);
//		}
//		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
//		for(String s: events) {
//			Map<String, Object> e = eventSet.get(s);
//			sublog.put(s,e);
//		}
//		return sublog;
//	}
//
//	public Map<String, Map<String, Object>> getSingleObjectEvents(Map<String, Map<String, Object>> eventSet, ObjectType o){
//		Set<String> events = new HashSet<String>();
//		events.addAll(o.history);
//		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
//		for(String s: events) {
//			Map<String, Object> e = eventSet.get(s);
//			sublog.put(s,e);
//		}
//		return sublog;
//	}
//
//	public Map<String, Map<String, Object>> getProcessComponentEvents(Map<String, Map<String, Object>> eventSet, String comp, String v, int t){
//		int  relative_from_t;
//		int from_t=100;
//		if(t-from_t<0) {
//			relative_from_t = 0;
//		}else {
//			relative_from_t = t-from_t;
//		}
//		Set<String> events = new HashSet<String>();
//		events = eventSet.entrySet().stream()
//				.filter(x -> x.getValue().get(comp).toString().equals(v) && relative_from_t<= (int)x.getValue().get("timestamp"))
//				.map(x -> x.getKey()).collect(Collectors.toSet());
//		Map<String, Map<String, Object>> sublog = new HashMap<String, Map<String, Object>>();
//		for(String s: events) {
//			Map<String, Object> e = eventSet.get(s);
//			sublog.put(s,e);
//		}
//		return sublog;
//	}
//
//	private double calculateAverage(Set <Integer> marks) {
//		OptionalDouble average = marks.stream().mapToDouble(a -> a).average();
//		return average.isPresent() ? average.getAsDouble() : 0;
//	}

}
