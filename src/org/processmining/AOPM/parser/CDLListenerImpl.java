package org.processmining.AOPM.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CDLListenerImpl extends CDLBaseListener {
	Map<String, Map<String, Object>> constraintMap;
	public Map<String, Object> constraint = this.generateConstraint();
	public CDLListenerImpl(Map<String, Map<String, Object>> c) {
		this.constraintMap = c;
	} 
	
	public Map<String, Object> generateConstraint() {
		Map<String, Object> constraint = new LinkedHashMap<String, Object>();
		return constraint;
	}
	
	@Override public void enterConstraintName(CDLParser.ConstraintNameContext ctx) { 
		String constraintName = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.constraint.put("constraintName", constraintName);   
    }
	@Override public void enterEntityName(CDLParser.EntityNameContext ctx) { 
		String processEntityName = ctx.getText().substring(1,ctx.getText().length()-1);
		constraint.put("processEntityName",processEntityName);
    }
	@Override public void enterFilterName(CDLParser.FilterNameContext ctx) { 
		String filterName = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.constraint.put("filter", filterName);   
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
        this.constraint.put("evaluation", reunion);
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
        this.constraint.put("evaluation", reunion);
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
        this.constraint.put("evaluation", reunion);
    }
	
	
	@Override public void enterEos(CDLParser.EosContext ctx) { 
		constraintMap.put((String) this.constraint.get("constraintName"),this.constraint);
		this.constraint = this.generateConstraint();
    }
	
}
