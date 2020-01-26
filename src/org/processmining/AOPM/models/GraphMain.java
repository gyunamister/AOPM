package org.processmining.AOPM.models;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphMain {
	public static void main (String[] args){
		Map<Integer, Map<String, String>> MONTHS = new HashMap<Integer, Map<String, String>>();
		Map<String, String> mp1 = new HashMap<String, String>();
		mp1.put(new String("act"), new String("a"));
		Map<String, String> mp2 = new HashMap<String, String>();
		mp2.put(new String("act"), new String("b"));
	    MONTHS.put(new Integer(1), mp1);
	    MONTHS.put(new Integer(2), mp1);
	    MONTHS.put(new Integer(3), mp1);
	    MONTHS.put(new Integer(4), mp1);
	    MONTHS.put(new Integer(5), mp1);
	    MONTHS.put(new Integer(6), mp2);
	    MONTHS.put(new Integer(7), mp2);
	    MONTHS.put(new Integer(8), mp2);
	    MONTHS.put(new Integer(9), mp2);
	    MONTHS.put(new Integer(10), mp2);
	    
	    
//	    Map<Integer, Map<String, String>> result = MONTHS.entrySet();
	    System.out.println(MONTHS.entrySet().stream()
	    		.filter(x -> x.getValue().get("act").equals("a"))
	    		.map(x -> x.getKey()).collect(Collectors.toList()));
	    
	}
}
