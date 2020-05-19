package org.processmining.AOPM.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ADLListenerImpl extends ADLBaseListener {
	Map<String, Map<String, Object>> actionMap;
	public Map<String, Object> action = this.generateAction();
	public ADLListenerImpl(Map<String, Map<String, Object>> actionMap) {
		this.actionMap = actionMap;
	} 
	
	public Map<String, Object> generateAction() {
		Map<String, Object> action = new LinkedHashMap<String, Object>();
		
		Map<String, Object> ccv = new LinkedHashMap<String, Object>();
		List<String> constraintList = new ArrayList<String>();
		List<String> processEntityList = new ArrayList<String>();
		List<Integer> timeRange = new ArrayList<Integer>();
		ccv.put("constraintList", constraintList);
		ccv.put("processEntityList", processEntityList);
		ccv.put("timeRange", timeRange);
		action.put("ccv", ccv);

		Map<String,Object> paramList = new LinkedHashMap<String,Object>();
		action.put("parameter", paramList);
		
		return action;
	}
	
	@Override public void enterActionName(ADLParser.ActionNameContext ctx) { 
        String actionName = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.action.put("actionName", actionName);
    }
	@Override public void enterConstraintName(ADLParser.ConstraintNameContext ctx) { 
		String constraint = ctx.getText().substring(1,ctx.getText().length()-1);
		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("constraintList")).add(constraint);
    }
	
	@Override public void enterProcessEntityName(ADLParser.ProcessEntityNameContext ctx) { 
		String processEntity = ctx.getText().substring(1,ctx.getText().length()-1);
		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("processEntityList")).add(processEntity);
    }
	
	@Override public void enterStartTime(ADLParser.StartTimeContext ctx) { 
		String startTime = ctx.getText();
		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("timeRange")).add(startTime);
    }
	
	@Override public void enterEndTime(ADLParser.EndTimeContext ctx) { 
		String endTime = ctx.getText();
		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("timeRange")).add(endTime);
    }
	
	@Override public void enterRelationalPredicate(ADLParser.RelationalPredicateContext ctx) { 
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
		this.action.put("assessment", reunion);
    }
	
	@Override public void enterActionDesc(ADLParser.ActionDescContext ctx) { 
		String actionDesc = ctx.getText().substring(1,ctx.getText().length()-1);
		this.action.put("action", actionDesc);
    }
	
	@Override public void enterParameterDetails(ADLParser.ParameterDetailsContext ctx) { 
		String[] paramDetail = ctx.getText().split("=");
		String paramName = paramDetail[0].substring(1,paramDetail[0].length()-1);
		String paramValue = paramDetail[1].substring(1,paramDetail[1].length()-1);
		((Map<String,Object>) this.action.get("parameter")).put(paramName, paramValue);
    }
	
	@Override public void enterEos(ADLParser.EosContext ctx) { 
		actionMap.put((String) this.action.get("actionName"),this.action);
		this.action = this.generateAction();
    }
	
}
