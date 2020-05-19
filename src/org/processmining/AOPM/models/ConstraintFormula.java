package org.processmining.AOPM.models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.AOPM.algorithms.Derivation;
import org.processmining.AOPM.algorithms.Evaluation;
import org.processmining.AOPM.algorithms.Filter;

public class ConstraintFormula {
	public String cfName;
	public ContextDescription ctxdesc;
	public String filter;
	public String predicate;
	public Filter ft = new Filter();
	public Derivation dv = new Derivation();
	public Evaluation evaluator = new Evaluation();
	
	public ConstraintFormula(String cfName, ContextDescription ctxdesc, String filter, String predicate) {
		this.ft = new Filter();
		this.cfName = cfName;
		this.ctxdesc = ctxdesc;
		this.filter = filter;
		this.predicate = predicate;
	}
	
	public Set<EvalResult> apply(Set<Event> eventSet,TimeWindow tw){
		Set<EvalResult> results = new HashSet<EvalResult>();
		Set<Context> ctxSet = this.genContextSet(eventSet,tw);
//		System.out.println(ctxSet);
		for(Context ctx:ctxSet) {
			String outcome = this.eval(eventSet, tw, ctx);
			EvalResult evalResult = new EvalResult(ctx,outcome);
			results.add(evalResult);
		}
		return results;
	}
	
	public Set<Context> genContextSet(Set<Event> eventSet, TimeWindow tw){
		Set<Context> ctxSet = new HashSet<Context>();
		Set<Event> twFtEventSet = eventSet.stream().filter((x) -> (x.getTimestamp() <= tw.getEnd()) && (x.getTimestamp() >= Math.max(0,tw.getStart()))).collect(Collectors.toSet());
		
		Set<Set<String>> procSetCollection = new HashSet<Set<String>>();
		Set<Set<String>> actSetCollection = new HashSet<Set<String>>();
		Set<Set<String>> resSetCollection = new HashSet<Set<String>>();
		Set<Map<String,Set<String>>> objectMapCollection = new HashSet<Map<String,Set<String>>>();
		
		if(this.ctxdesc.getProcSet().contains("forall")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getProcess).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> procSet = new HashSet<String>(); 
				procSet.add(p);
				procSetCollection.add(procSet);
			}
		}else {
			procSetCollection.add(this.ctxdesc.getProcSet());
		}
		if(this.ctxdesc.getActSet().contains("forall")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getAct).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> actSet = new HashSet<String>(); 
				actSet.add(p);
				actSetCollection.add(actSet);
			}
		}else {
			actSetCollection.add(this.ctxdesc.getActSet());
		}
		if(this.ctxdesc.getResSet().contains("forall")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getRes).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> resSet = new HashSet<String>(); 
				resSet.add(p);
				resSetCollection.add(resSet);
			}
		}else {
			resSetCollection.add(this.ctxdesc.getResSet());
		}
//		Assume forall is allowed for just one property
		for(String object: this.ctxdesc.getOmap().keySet()) { 
			if(this.ctxdesc.getOmap().get(object).contains("forall")) {
				Set<Map<String,Set<String>>> temp = twFtEventSet.stream().map(Event::getOmap).collect(Collectors.toSet());
				Set<String> objectSet = new HashSet<String>();
				for(Map<String,Set<String>> omap : temp) {
					objectSet.addAll(omap.get(object));
				}
				for(String o:objectSet) {
					Set<String> tempObjectSet = new HashSet<String>(); 
					tempObjectSet.add(o);
					Map<String,Set<String>> tempOmap = new LinkedHashMap<String,Set<String>>(); 
					tempOmap.put(object,tempObjectSet);
					objectMapCollection.add(tempOmap);
				}
			}else {
				objectMapCollection.add(this.ctxdesc.getOmap());
			}
		}
		for(Set<String> p : procSetCollection) {
			for(Set<String> a:actSetCollection) {
				for(Set<String> r: resSetCollection) {
					for(Map<String,Set<String>> o : objectMapCollection) {
						Map<String,Set<String>> v = new LinkedHashMap<String,Set<String>>();
						Context ctx = new Context(p,a,r,o,v);
						ctxSet.add(ctx);
					}
				}
			}
		}
		return ctxSet;
	}
	
//	public Set<Event> filter(Set<Event> eventSet, TimeWindow tw, Context ctx){
//		Set<Event> ec = new HashSet<Event>();
//		if(this.filter=="entity") {
//			ec = this.ft.caseFilter(eventSet, tw, ctx);
//		}else if(this.filter=="event"){
//			ec = this.ft.eventFilter(eventSet, tw, ctx);
//		}
//		return ec;
//	}
	
	public String eval(Set<Event> eventSet, TimeWindow tw, Context ctx){
		//We simply assume that we already have predefined functions. So, what we need to is just to call the function.
		String outcome = "ok"; 
		String c = this.predicate;
		int val = 0;
		if(c.contains("Throughput")) {
			Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
			if(ctx.getOmap().get("Order").iterator().next().equals("o70")) {
				System.out.println(tw.getEnd()+ " "+ec);
			}
			
			val = this.dv.calcThroughputTime(ec,tw.getEnd());
		}
		if(c.contains(">") | c.contains("=") | c.contains("<")) {
			String valueName=c.split(",")[0];
			String comp=c.split(",")[1];
			int thres=Integer.parseInt(c.split(",")[2]);
			outcome = this.evaluator.evalRelational(val,comp,thres);
//			if(evalResult.equals("nok")) {
//				break;
//			}
		}
		return outcome;
	}
	
	public String toString() {
		return this.ctxdesc + this.filter + this.predicate;
	}
}
