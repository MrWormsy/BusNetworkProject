package fr.mrwormsy.busnetwork.Node;

import java.util.ArrayList;

public class Node {

	// === Variables ===
	
	//The id of the bus stop
	private int id;
	
	//The name of the bus stop
	private String name;
	
	//The time when the bus stopped (in minutes from 00h00) (-1 means the bus hasn't stopped yet)
	private int timeLastStop;
	
	//List time of stop in one way (ascending)
	private ArrayList<Integer> listTimeOfStopFirstWay;
	
	//List time of stop in an another way (ascending)
	private ArrayList<Integer> listTimeOfStopSecondWay;
	
	// === Constructor ===
	
	public Node(int id, String name) {
		this.setId(id);
		this.setName(name);
		this.setTimeLastStop(-1);
	}

	// === Getters and Setters
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTimeLastStop() {
		return timeLastStop;
	}


	public void setTimeLastStop(int timeLastStop) {
		this.timeLastStop = timeLastStop;
	}

	public ArrayList<Integer> getListTimeOfStopFirstWay() {
		return listTimeOfStopFirstWay;
	}

	public void setListTimeOfStopFirstWay(ArrayList<Integer> listTimeOfStopFirstWay) {
		this.listTimeOfStopFirstWay = listTimeOfStopFirstWay;
	}

	public void AddTimeOfStopToListTimeOfStopFirstWay (int timeOfStop) {
		this.listTimeOfStopFirstWay.add(timeOfStop);
	}
	
	public ArrayList<Integer> getListTimeOfStopSecondWay() {
		return listTimeOfStopSecondWay;
	}

	public void setListTimeOfStopSecondWay(ArrayList<Integer> listTimeOfStopSecondWay) {
		this.listTimeOfStopSecondWay = listTimeOfStopSecondWay;
	}
	
	public void AddTimeOfStopToListTimeOfStopSecondWay (int timeOfStop) {
		this.listTimeOfStopSecondWay.add(timeOfStop);
	}
}
