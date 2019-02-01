package fr.mrwormsy.busnetwork.arc;

import fr.mrwormsy.busnetwork.node.Node;

public class Arc {

	// === Variables ===
	
	//The id of the link
	private int id;
	
	//The departure bus stop
	private Node before;
	
	//The arriving bus stop
	private Node after;
	
	// === Constructor ===
	
	public Arc(int id, Node before, Node after) {
		this.setId(id);
		this.setBefore(before);
		this.setAfter(after);
	}
	
	// === Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getBefore() {
		return before;
	}

	public void setBefore(Node before) {
		this.before = before;
	}

	public Node getAfter() {
		return after;
	}

	public void setAfter(Node after) {
		this.after = after;
	}
	
}
