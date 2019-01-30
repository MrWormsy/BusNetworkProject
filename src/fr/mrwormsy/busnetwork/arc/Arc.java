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
	
	//The time (in minutes from 00h00) when the bus stop must stops (The dash "-" means this is not a stop)
	private int timeOfStop;
	
	//The time the bus should arrive at the bus stop (The dash "-" means this is not a stop)
	private int timeOfArriving;
	
	// === Constructor ===
	
	public Arc(int id, Node before, Node after, int timeOfStop) {
		this.setId(id);
		this.setBefore(before);
		this.setAfter(after);
		this.setTimeOfStop(timeOfStop);
		this.setTimeOfArriving(-1);
	}
	
	public Arc(int id, Node before, Node after, int timeOfStop, int timeOfArriving) {
		this.setId(id);
		this.setBefore(before);
		this.setAfter(after);
		this.setTimeOfStop(timeOfStop);
		this.setTimeOfArriving(timeOfArriving);
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

	public int getTimeOfStop() {
		return timeOfStop;
	}

	public void setTimeOfStop(int timeOfStop) {
		this.timeOfStop = timeOfStop;
	}
	
	// === Methods ===
	
	public int getTimeOfArriving() {
		return timeOfArriving;
	}

	public void setTimeOfArriving(int timeOfArriving) {
		this.timeOfArriving = timeOfArriving;
	}

	//Get the hours of the stop
	public int getHourOfStop() {
		
		if (this.getTimeOfStop() == -1) {
			return -1;
		}
		
		return (this.getTimeOfStop() - ((int) this.getTimeOfStop()%60)) / 60;
	}
	
	//Get the minutes of the stop
	public int getMinutesOfStop() {	
		
		if (this.getTimeOfStop() == -1) {
			return -1;
		}
		
		return (int) (this.getTimeOfStop() - (60 * (this.getTimeOfStop() - ((int) this.getTimeOfStop()%60)) / 60));
	}
	
	//Get the hour:minute formated string
	public String getHMFormat() {
		
		if (this.getMinutesOfStop() == -1) {
			return "-";
		}
		
		return "".concat(String.format("%02d", this.getHourOfStop()).concat(":").concat(String.format("%02d", this.getMinutesOfStop())));
		
		//return "".concat(String.valueOf(this.getHourOfStop())).concat(":").concat(String.valueOf(this.getMinutesOfStop()));
	}
	
	//Get the estimated time of arriving
	public long getEstimatedTimeOfArriving() {
		return 0l;
	}
	
}
