package org.processmining.AOPM.models;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.processmining.AOPM.algorithms.Simulator;

public class ActionStorage { 
	public Simulator simulator;
	public ActionStorage(Simulator sm) {
		simulator = sm;
	}
	
	public String execute(String constraint, String action_condition, String action,String info) { 
		String new_message = "";
		if(action.equals("a1: alert case manager")) {
			this.alertCaseManager(constraint, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a2: alert operation director")) {
			this.alertOperationDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a3: alert managing director")) {
			this.alertManagingDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a4: alert case manager with suggestion")) {
			this.alertCaseManagerWithS(constraint, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a5: alert operation director with suggestion")) {
			this.alertOperationDirectorWithS(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a6: alert managing director with suggestion")) {
			this.alertManagingDirectorWithS(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a7: add resource")) {
			this.addResource();
			new_message = "Execute action: " + action;
		}
		return new_message;
	}
	
	public void addResource() {
		JFrame f = new JFrame();
		String resource_info=JOptionPane.showInputDialog(f,"Add resource?");
		if(resource_info!=null) {
			String[] parsed_info = resource_info.split(",");
			Resource r = new Resource(parsed_info[0]);
			for(int i=1;i<parsed_info.length;i++) {
				String task_info = parsed_info[i];
				String[] parsed_task_info = task_info.split("-");
				r.setTask(parsed_task_info[0], Integer.parseInt(parsed_task_info[1]));
			}
			simulator.resource_list.add(r);
		}
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
	
	public void alertCaseManagerWithS(String constraint, String info) {
		String message = "To case manager, below is the order hanging for more than 200. Would you assign top priority to this order? \n" + info;
//		JOptionPane.showMessageDialog(null, message, "Alert message", JOptionPane.ERROR_MESSAGE);
		JFrame f = new JFrame();
		int result=JOptionPane.showConfirmDialog(f, message, "Alert message", JOptionPane.ERROR_MESSAGE);
//		remove!
		if(result==0) {
			//check if finished already
			try {
				List<ObjectType> related_objects = this.simulator.mp.object_graph.get(info);
				for(ObjectType o:related_objects) {
					this.simulator.mp.object_list.remove(o);
				}
				this.simulator.mp.object_graph.remove(info);
			} catch(NullPointerException e) {
				System.out.println(info + " is already finished");
			}
		}
	}
	
	public void alertOperationDirectorWithS(String constraint, String action_condition, String info) {
		String m = String.format("To operation director, %s of violating %s %s %s \n Would you assign more resource?", 
				action_condition.split(",")[0], constraint, action_condition.split(",")[1], action_condition.split(",")[2]);
		JFrame fr = new JFrame();
		int result=JOptionPane.showConfirmDialog(fr, m, "Alert message", JOptionPane.ERROR_MESSAGE);
		if(result==0) {
			JFrame f = new JFrame();
			String resource_info=JOptionPane.showInputDialog(f,"Please specify resource name and task (e.g., Resource1, place_order-1)","Type here");
			if(resource_info!=null) {
				String[] parsed_info = resource_info.split(",");
				Resource r = new Resource(parsed_info[0]);
				for(int i=1;i<parsed_info.length;i++) {
					String task_info = parsed_info[i];
					String[] parsed_task_info = task_info.split("-");
					r.setTask(parsed_task_info[0], Integer.parseInt(parsed_task_info[1]));
				}
				simulator.resource_list.add(r);
			}
		}
		
	}
	
	public void alertManagingDirectorWithS(String constraint, String action_condition, String info) {
		String m = String.format("To managing director, below is the one violating %s \n - %s \n Would you adjust the permission of the resource?", constraint, info);
		JFrame fr = new JFrame();
		int result=JOptionPane.showConfirmDialog(fr, m, "Alert message", JOptionPane.ERROR_MESSAGE);
		if(result==0) {
			JFrame f = new JFrame();
			String resource_info=JOptionPane.showInputDialog(f,"Please specify the resource name and the task to withdraw a permission (e.g., Resource1, place_order)","Type here");
			if(resource_info!=null) {
				for(Resource r : simulator.resource_list) {
					if(r.resource_name.equals(info)) {
						String[] parsed_info = resource_info.split(",");
						for(int i=1;i<parsed_info.length;i++) {
							String task_info = parsed_info[i];
							System.out.println(r.resource_name + r.tasks);
							r.tasks.remove(task_info);
							System.out.println(r.resource_name + r.tasks);
						}
					}
				}
			}
		}
		
	}
}
