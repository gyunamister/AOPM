package org.processmining.newpackage.models;

public class Item extends ObjectType {
	//public ItemProcess process;
	
	public Item(ObjectType o1, String name, int t) {
		ObjectType order = o1;
		object_name = name;
		available_at = t;
		setProcess(new ItemProcess());
	}
	
	public void setProcess(ItemProcess p) {
		process = p;
	}
}
