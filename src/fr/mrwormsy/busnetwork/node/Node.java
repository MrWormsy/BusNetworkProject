package fr.mrwormsy.busnetwork.node;

import java.util.ArrayList;

import fr.mrwormsy.busnetwork.utils.Utils;

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
	
	//List time of stop in one way (ascending) (vacations)
	private ArrayList<Integer> listTimeOfStopFirstWayVacations;
		
	//List time of stop in an another way (ascending) (vacations)
	private ArrayList<Integer> listTimeOfStopSecondWayVacations;
	
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
	
	public ArrayList<Integer> getListTimeOfStopSecondWay() {
		return listTimeOfStopSecondWay;
	}

	public void setListTimeOfStopSecondWay(ArrayList<Integer> listTimeOfStopSecondWay) {
		this.listTimeOfStopSecondWay = listTimeOfStopSecondWay;
	}

	public ArrayList<Integer> getListTimeOfStopFirstWayVacations() {
		return listTimeOfStopFirstWayVacations;
	}

	public void setListTimeOfStopFirstWayVacations(ArrayList<Integer> listTimeOfStopFirstWayVacations) {
		this.listTimeOfStopFirstWayVacations = listTimeOfStopFirstWayVacations;
	}

	public ArrayList<Integer> getListTimeOfStopSecondWayVacations() {
		return listTimeOfStopSecondWayVacations;
	}

	public void setListTimeOfStopSecondWayVacations(ArrayList<Integer> listTimeOfStopSecondWayVacations) {
		this.listTimeOfStopSecondWayVacations = listTimeOfStopSecondWayVacations;
	}	
	
	//Get id of the closest time of a specific listOfTimeOfStop (the next one), returns -1 if the last bus has already left the stop and there is no other time
	public int getClosestIdOfListOfTime(ArrayList<Integer> arrayListOfStop, String hhmmFormat) {
		
		int timeUnformated = Utils.getTimeFromStringFormat(hhmmFormat);
		
		if (arrayListOfStop.get(0) >= timeUnformated) {
			return 0;
		}
		
		for(int i = 0; i < arrayListOfStop.size() - 1; i++) {
			if (arrayListOfStop.get(i) < timeUnformated && arrayListOfStop.get(i + 1) >= timeUnformated) {
				return i + 1;
			}
		}
		return -1;
	}
	
	
	
}