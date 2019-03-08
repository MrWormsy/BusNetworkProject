package fr.mrwormsy.busnetwork.node;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {

	// === Variables ===
	
	//The name of the bus stop
	private String name;
	
	//The list of bus lines the stop belongs to
	private ArrayList<Integer> busLines;
	
	//List time of stop in one way (ascending)
	private HashMap<Integer, ArrayList<Integer>> listTimeOfStopFirstWay;
	
	//List time of stop in an another way (ascending)
	private HashMap<Integer, ArrayList<Integer>> listTimeOfStopSecondWay;
	
	//List time of stop in one way (ascending) (vacations)
	private HashMap<Integer, ArrayList<Integer>> listTimeOfStopFirstWayVacations;
		
	//List time of stop in an another way (ascending) (vacations)
	private HashMap<Integer, ArrayList<Integer>> listTimeOfStopSecondWayVacations;
	
	// === Constructor ===
	
	public Node(String name, int busLine) {
		this.setName(name);
		this.setBusLines(new ArrayList<Integer>());
		this.busLines.add(busLine);
		this.setListTimeOfStopFirstWay(new HashMap<Integer, ArrayList<Integer>>());
		this.setListTimeOfStopFirstWayVacations(new HashMap<Integer, ArrayList<Integer>>());
		this.setListTimeOfStopSecondWay(new HashMap<Integer, ArrayList<Integer>>());
		this.setListTimeOfStopSecondWayVacations(new HashMap<Integer, ArrayList<Integer>>());
	}

	// === Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getBusLines() {
		return busLines;
	}

	public void setBusLines(ArrayList<Integer> busLines) {
		this.busLines = busLines;
	}

	public HashMap<Integer, ArrayList<Integer>> getListTimeOfStopFirstWay() {
		return listTimeOfStopFirstWay;
	}

	public void setListTimeOfStopFirstWay(HashMap<Integer, ArrayList<Integer>> listTimeOfStopFirstWay) {
		this.listTimeOfStopFirstWay = listTimeOfStopFirstWay;
	}
	
	public HashMap<Integer, ArrayList<Integer>> getListTimeOfStopSecondWay() {
		return listTimeOfStopSecondWay;
	}

	public void setListTimeOfStopSecondWay(HashMap<Integer, ArrayList<Integer>> listTimeOfStopSecondWay) {
		this.listTimeOfStopSecondWay = listTimeOfStopSecondWay;
	}

	public HashMap<Integer, ArrayList<Integer>> getListTimeOfStopFirstWayVacations() {
		return listTimeOfStopFirstWayVacations;
	}

	public void setListTimeOfStopFirstWayVacations(HashMap<Integer, ArrayList<Integer>> listTimeOfStopFirstWayVacations) {
		this.listTimeOfStopFirstWayVacations = listTimeOfStopFirstWayVacations;
	}

	public HashMap<Integer, ArrayList<Integer>> getListTimeOfStopSecondWayVacations() {
		return listTimeOfStopSecondWayVacations;
	}

	public void setListTimeOfStopSecondWayVacations(HashMap<Integer, ArrayList<Integer>> listTimeOfStopSecondWayVacations) {
		this.listTimeOfStopSecondWayVacations = listTimeOfStopSecondWayVacations;
	}	
	
	/*
	
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
	
	*/

	public static boolean nodeNameExistsInList(ArrayList<Node> nodes, String name) {
		for(Node node : nodes ) {
			if (node.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public int getCommonBusLine(Node target) {
		
		for(Integer i : target.getBusLines()) {
			if (target.getBusLines().contains(i) && i != 0) {
				return i;
			}
		}
		return -1;
	}
	
	public int getClosestIdOfListOfTime(int time, int busLineId) {
		int id = 0;
		int lastTime = -1;
		
		if (this.getListTimeOfStopFirstWay().get(busLineId) == null) {
			return 0;
		}
		
		for(Integer i : this.getListTimeOfStopFirstWay().get(busLineId)) {
			
			if (lastTime != -1 && i != -1) {
				if (time <= i && time > lastTime) {
					return id;
				}
			}
			
			lastTime = i;
			
			id++;
		}
		
		return 0;
	}

	public String getFormatedBusLines() {
		
		String strToReturn = " (";
		
		for(int i = 1; i < this.getBusLines().size(); i++) {
			strToReturn = strToReturn.concat(String.valueOf(this.getBusLines().get(i)));
			
			if (i + 1 != this.getBusLines().size()) {
				strToReturn = strToReturn.concat(",");
			}
		}
		
		strToReturn = strToReturn.concat(")");
		
		return strToReturn;
	}
}