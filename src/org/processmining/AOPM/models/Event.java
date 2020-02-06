package org.processmining.AOPM.models;

public class Event {
	int e;
	Order order;
	Item item;
	Package pack;
	Route route;
	String act;
	Resource res;
	int t;
	
	public Event(int e, String act, Resource res, int t, Order o1, Item i1, Package p1, 
			Route r1) {
		this.e = e;
		this.order = o1;
		this.item = i1;
		this.pack = p1;
		this.route = r1;
		this.act = act;
		this.res = res;
		this.t = t;
	}
	
	public String toString() {
		return this.e + "\t" + this.act + "\t" + this.res + "\t" + this.t + "\t" + this.order + "\t" + this.item + "\t" + this.pack + "\t" + this.route + "\n";  
	}
}
