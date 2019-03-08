package fr.mrwormsy.busnetwork.arc;

import java.util.ArrayList;

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
		
		//Get the average time between two nodes
		//System.out.println(this.getAverageTimeOfArc(before, after));
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
	
	// === Methods ===
	
	/*
	
	private int getAverageTimeOfArc(Node A, Node B) {
		int count = 0;
		int total = 0;
		
		for(Integer i : A.getListTimeOfStopFirstWay().keySet()) {
			if (B.getListTimeOfStopFirstWay().containsKey(i)) {
				for(int j = 0; (j < A.getListTimeOfStopFirstWay().get(i).size()); j++) {				
					//for(Integer j : A.getListTimeOfStopFirstWay().get(i)) {
					if (!(A.getListTimeOfStopFirstWay().get(i).get(j) == -1 || B.getListTimeOfStopFirstWay().get(i).get(j) == -1)) {
						total += (B.getListTimeOfStopFirstWay().get(i).get(j) - A.getListTimeOfStopFirstWay().get(i).get(j));
						count++;
					}
				}
			}
		}
		
		if (count == 0) {
			return -1;
		}
		return (int) (total/count);
	}
	
	*/
	
	private int getAverageTimeOfArc(Node A, Node B) {
		
		//First we need to get the common busline of the two stops
		int commonBusLine = 0;
		
		for (Integer i : A.getBusLines()) {
			if (i != 0) {
				for(Integer j : B.getBusLines()) {
					if (i == j && commonBusLine != 0) {
						commonBusLine = i;
					}
				}
			}
		}
		
		int count = 0;
		int total = 0;
		
		ArrayList<Integer> AStops = A.getListTimeOfStopFirstWay().get(commonBusLine);
		AStops.addAll(A.getListTimeOfStopSecondWay().get(commonBusLine));
		
		ArrayList<Integer> BStops = B.getListTimeOfStopFirstWay().get(commonBusLine);
		BStops.addAll(B.getListTimeOfStopSecondWay().get(commonBusLine));
		
		
		
		for(int id = 0; id < AStops.size() || id < BStops.size(); id++) {

		}
		
		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
