package org.processmining.AOPM.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CDLListenerImpl extends CDLBaseListener {
	Map<String, Map<String, Map<String,List<String>>>> constraintMap;
	Map<String, Map<String,List<String>>> constraint;
	public CDLListenerImpl(Map<String, Map<String, Map<String,List<String>>>> c) {
		constraintMap = c;
	}
	
	public Map<String, Map<String,List<String>>> generateConstraint() {
		Map<String, Map<String,List<String>>> constraint = new LinkedHashMap<String, Map<String,List<String>>>();
		List<String> nametList = new ArrayList();
		Map<String, List<String>> info = new LinkedHashMap<String, List<String>>();
		info.put("constraintName",nametList);
		constraint.put("constraintINFO", info);
		
		List<String> objectList = new ArrayList();
		List<String> processEntityList = new ArrayList();
		List<String> contextList = new ArrayList();
		Map<String, List<String>> contextualize = new LinkedHashMap<String, List<String>>();
		contextualize.put("Object", objectList);
		contextualize.put("ProcessEntity", processEntityList);
		contextualize.put("Context", contextList);
		constraint.put("Contextualize", contextualize);
		
		List<String> conditionList = new ArrayList();
		Map<String,Integer> value = new LinkedHashMap<String,Integer>();
		Map<String, List<String>> evaluate = new LinkedHashMap<String, List<String>>();
		evaluate.put("Condition", conditionList);
		constraint.put("Evaluate", evaluate);
		
		List<String> formulaList = new ArrayList();
		List<String> formulaNameList = new ArrayList();
		Map<String, List<String>> acq = new LinkedHashMap<String, List<String>>();
		acq.put("formulaNameList",formulaNameList);
		acq.put("formulaList",formulaList);
		constraint.put("Acquire", acq);
		return constraint;
	}
	
	@Override public void enterConstraintName(CDLParser.ConstraintNameContext ctx) { 
		constraint = this.generateConstraint();
        String constraintName = ctx.getText().substring(1,ctx.getText().length()-1);;
        constraint.get("constraintINFO").get("constraintName").add(constraintName);   
    }
	@Override public void enterObjectName(CDLParser.ObjectNameContext ctx) { 
		String object = ctx.getText().substring(1,ctx.getText().length()-1);
		constraint.get("Contextualize").get("Object").add(object);
    }
	@Override public void enterEntityName(CDLParser.EntityNameContext ctx) { 
		String entity = ctx.getText().substring(1,ctx.getText().length()-1);
		constraint.get("Contextualize").get("ProcessEntity").add(entity);
    }
	@Override public void enterRelationalPredicate(CDLParser.RelationalPredicateContext ctx) { 
		String condition = ctx.getText();
		List<String> conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
		Iterator itr = conditionList.iterator(); 
        while (itr.hasNext()) 
        { 
            String s = (String) itr.next(); 
            if (s.contains("ACQUIRED")) 
                itr.remove(); 
        }
        String reunion = conditionList.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(","));
		constraint.get("Evaluate").get("Condition").add(reunion);
    }
	
	@Override public void enterBinaryPredicate(CDLParser.BinaryPredicateContext ctx) { 
		String condition = ctx.getText();
		List<String>conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
		System.out.println(conditionList);
		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
		conditionList.set(2,conditionList.get(2).substring(1,conditionList.get(2).length()-1));
		Iterator itr = conditionList.iterator(); 
        String reunion = conditionList.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(","));
		constraint.get("Evaluate").get("Condition").add(reunion);
    }
	
	@Override public void enterSetPredicate(CDLParser.SetPredicateContext ctx) { 
		String condition = ctx.getText();
		List<String>conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
		
		System.out.println(conditionList);
		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
		conditionList.set(2,conditionList.get(2).substring(1,conditionList.get(2).length()-1));
		Iterator itr = conditionList.iterator(); 
        String reunion = conditionList.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(","));
		constraint.get("Contextualize").get("Context").add(reunion);
    }
	
	@Override public void enterAcquireQuery(CDLParser.AcquireQueryContext ctx) { 
		String expr = ctx.getText();
		String formulaName = expr.split("\"")[1];
		String formula = expr.split("\"")[3];
		constraint.get("Acquire").get("formulaNameList").add(formulaName);
		constraint.get("Acquire").get("formulaList").add(formula);
    }
	@Override public void enterEos(CDLParser.EosContext ctx) { 
		constraintMap.put(constraint.get("constraintINFO").get("constraintName").get(0),constraint);
    }
	
}
