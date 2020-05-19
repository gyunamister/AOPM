package org.processmining.AOPM.IS_OHP;

public class oldEvent {
	int e;
	Order order;
	Item item;
	Package pack;
	Route route;
	String act;
	Resource res;
	int start_timestamp;
	int complete_timestamp;
	
	public oldEvent(int e, String act, Resource res, int start, int complete, Order o1, Item i1, Package p1, 
			Route r1) {
		this.e = e;
		this.order = o1;
		this.item = i1;
		this.pack = p1;
		this.route = r1;
		this.act = act;
		this.res = res;
		this.start_timestamp = start;
		this.complete_timestamp = complete;
	}
	
	public String toString() {
		return this.e + "," + this.act + "," + this.res + "," + this.start_timestamp + "," + this.complete_timestamp + "," + this.order + "," + this.item + "," + this.pack + "," + this.route;  
	}
}
