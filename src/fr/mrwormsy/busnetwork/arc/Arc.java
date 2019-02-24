package fr.mrwormsy.busnetwork.arc;

import fr.mrwormsy.busnetwork.node.Node;

public class Arc {

	// === Variables ===
	
	//The departure bus stop
	private Node before;
	
	//The arriving bus stop
	private Node after;
	
	//The weight
	private int weight;
	
	// === Constructor ===
	
	public Arc(Node before, Node after) {
		this.setBefore(before);
		this.setAfter(after);
		this.setWeight(0);
	}
	
	// === Getters and Setters ===

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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	// == Methods ==
}
