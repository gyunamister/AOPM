package org.processmining.AOPM.real;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class RealActionStorage {
	public RealSimulator simulator;
	public RealActionStorage(RealSimulator sm) {
		simulator = sm;
	}

	public String execute(int t, int executeInterval, String constraint, String processEntity, int time, String action, String action_condition,String info) {
		String new_message = "";
		if(action.equals("a1: alert case manager")) {
			//this.alertCaseManager(constraint, info);
//			new_message = "Execute action: " + action;
			List<String> ongoingObjectList = simulator.objectList;
			List<RealEvent> upcomingEvent = simulator.eventlog.stream().filter(x -> ongoingObjectList.contains(x.getCaseid()) && x.getTime() > t).collect(Collectors.toList());
			for (RealEvent e : upcomingEvent) {
				e.time -= 24;
			}
			new_message = constraint + "," + action + "," + time + "," + processEntity;
		}else if(action.equals("a2: alert operation director")) {
			this.alertOperationDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a3: alert managing director")) {
			this.alertManagingDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}
		List<String> lines = Arrays.asList(new_message);
		if(t/executeInterval==1) {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/experiment/real-action-log.csv");
			try {
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}else {
			Path file = Paths.get("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/experiment/real-action-log.csv");
			try {
				Files.write(file, lines, StandardOpenOption.APPEND);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		return new_message;
	}
	public void alertCaseManager(String constraint, String info) {
		String m = String.format("To case manager, below is the one violating %s \n - %s", constraint, info);
		JOptionPane.showMessageDialog(null, m, "Alert message", JOptionPane.ERROR_MESSAGE);

	}

	public void alertOperationDirector(String constraint, String action_condition, String info) {
		String m = String.format("To operation director, %s of violating %s %s %s",
				action_condition.split(",")[0], constraint, action_condition.split(",")[1], action_condition.split(",")[2]);
		JOptionPane.showMessageDialog(null, m, "Alert message", JOptionPane.ERROR_MESSAGE);

	}

	public void alertManagingDirector(String constraint, String action_condition, String info) {
		String m = "";
		if(!action_condition.equals("empty")) {
			m = String.format("To managing director, %s of violating %s %s %s",
					action_condition.split(",")[0], constraint, action_condition.split(",")[1], action_condition.split(",")[2]);
		}else {
			m = String.format("To managing director, below is the one violating %s \n - %s", constraint, info);
		}
		JOptionPane.showMessageDialog(null, m, "Alert message", JOptionPane.ERROR_MESSAGE);

	}
}