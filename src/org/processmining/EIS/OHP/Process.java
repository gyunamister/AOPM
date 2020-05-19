package org.processmining.EIS.OHP;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Process {
	public String prev_act;
	public String current_act;
	public String next_act;
	public int available_at;
	public boolean finished;

	public List<String> control_flow = new ArrayList<String>();

	public abstract void proceedNextActivity();

	public int recordEvent(Map<String, Map<String, Object>> eventlog, String o1, String i1, String p1, String r1,
			String act, String res, int start, int complete, int e) {
		String event_id = "event" + e;
		String process = "order-handling-process";
		eventlog.put(event_id, new HashMap<String, Object>());
		if (o1 != null) {
			eventlog.get(event_id).put("order_id", o1);
		}
		if (i1 != null) {
			eventlog.get(event_id).put("item_id", i1);
		}
		if (p1 != null) {
			eventlog.get(event_id).put("package_id", p1);
		}
		if (r1 != null) {
			eventlog.get(event_id).put("route_id", r1);
		}
		eventlog.get(event_id).put("activity", act);
		eventlog.get(event_id).put("resource", res);
//		eventlog.get(event_id).put("start_timestamp", start);
		eventlog.get(event_id).put("complete_timestamp", complete);

		Map<String,String> omap = new LinkedHashMap<String,String>();
		omap.put("Order", o1);
		omap.put("Item", i1);
		omap.put("Package", p1);
		omap.put("Route", r1);
		OrderHandlingEvent event = new OrderHandlingEvent(event_id, process, act, res, complete, omap);
		List<String> lines = Arrays.asList(event.toString());
		if (e == 1) {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/IS_OHP/eventlog.csv");
			try {
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		} else {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/IS_OHP/eventlog.csv");
			try {
				Files.write(file, lines, StandardOpenOption.APPEND);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

		e = e + 1;
		return e;
	}
}
