package fr.mrwormsy.busnetwork.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.node.Node;

public class Utils {

	//Get the first line of the txt file containing the list of stops (return an ArrayList of strings)
	public static List<String> getListOfStops(Scanner scanner) {
		String lineContainingStops = null;
		
		//First we get the first line containing " N "
		while (scanner.hasNextLine()) {
			lineContainingStops = scanner.nextLine();
			//If this line contains " N ", we know that the line is the one containing the stops
			if (lineContainingStops.contains(" N ")) {
				break;
			}
		}
		return Arrays.asList(lineContainingStops.split(" N "));
	}

	//Returns the value of the time from the String format of the time (if the string we return -1 which means the bus do not stop here)
	public static int getTimeFromStringFormat(String string) {
		
		if (string.equalsIgnoreCase("-")) {
			return -1;
		}
		
		return Integer.valueOf(string.split(":")[0]) * 60 + Integer.valueOf(string.split(":")[1]);
	}
	
	public static String getTImeHMFormat(int time) {
		
		if (time == -1) {
			return "-";
		}

		int hours = (time - ((int) time%60)) / 60;
		int minutes = (int) (time - (60 * hours));
		
		return "".concat(String.format("%02d", hours).concat(":").concat(String.format("%02d", minutes)));
		
		//return "".concat(String.valueOf(hours)).concat(":").concat(String.valueOf(minutes));
	}
	
	public static void setListsOfTimeOfEachStops(Scanner scanner, ArrayList<Node> nodes) {
		
		
		//First way
		String line = "";
		int i = 0;
		line = scanner.nextLine();
		do {			
			String[] lineArray = line.split(" ");
			
			String stopName = null;
			ArrayList<Integer> timeStops = new ArrayList<Integer>();
			
			for(String l : lineArray) {
				
				//If this is the first iteration this means this is the name of the stop
				if (stopName == null) {
					stopName = l;
				} else {					
					timeStops.add(Utils.getTimeFromStringFormat(l));
				}
			}
			
			//When we reach here we do know that the list of time is full			
			nodes.get(i).setListTimeOfStopFirstWay(timeStops);		
			i++;
			
			line = scanner.nextLine();
		} while (!(line.replaceAll("[ ]+", " ").equalsIgnoreCase(" ") || line.equalsIgnoreCase(""))); //While the line is not empty (End of the time table)
		
		//Blank space has been read...
		
		//Second way
		line = "";
		i = nodes.size() - 1;
		line = scanner.nextLine();
		do {		
			String[] lineArray = line.split(" ");
			
			String stopName = null;
			ArrayList<Integer> timeStops = new ArrayList<Integer>();
			
			for(String l : lineArray) {
				
				//If this is the first iteration this means this is the name of the stop
				if (stopName == null) {
					stopName = l;
				} else {
					timeStops.add(Utils.getTimeFromStringFormat(l));
				}
			}
			
			//When we reach here we do know that the list of time is full
			nodes.get(i).setListTimeOfStopSecondWay(timeStops);		
			i--;
			
			line = scanner.nextLine();
		} while (!(line.replaceAll("[ ]+", " ").equalsIgnoreCase(" ") || line.equalsIgnoreCase(""))); //While the line is not empty (End of the time table)
		
		//Blank space has been read...
		
		//We have to read two lines because of the file format
		scanner.nextLine();
		scanner.nextLine();
		
		//First way
		line = "";
		i = 0;
		line = scanner.nextLine();
		do {			
			String[] lineArray = line.split(" ");
			
			String stopName = null;
			ArrayList<Integer> timeStops = new ArrayList<Integer>();
			
			for(String l : lineArray) {
				
				//If this is the first iteration this means this is the name of the stop
				if (stopName == null) {
					stopName = l;
				} else {					
					timeStops.add(Utils.getTimeFromStringFormat(l));
				}
			}
			
			//When we reach here we do know that the list of time is full			
			nodes.get(i).setListTimeOfStopFirstWayVacations(timeStops);		
			i++;
			
			line = scanner.nextLine();
		} while (!(line.replaceAll("[ ]+", " ").equalsIgnoreCase(" ") || line.equalsIgnoreCase(""))); //While the line is not empty (End of the time table)
		
		//Blank space has been read...
		
		//Second way when vacations
		line = "";
		i = nodes.size() - 1;
		line = scanner.nextLine();
		do {		
			String[] lineArray = line.split(" ");
			
			String stopName = null;
			ArrayList<Integer> timeStops = new ArrayList<Integer>();
			
			for(String l : lineArray) {
				
				//If this is the first iteration this means this is the name of the stop
				if (stopName == null) {
					stopName = l;
				} else {
					timeStops.add(Utils.getTimeFromStringFormat(l));
				}
			}
			
			//When we reach here we do know that the list of time is full
			nodes.get(i).setListTimeOfStopSecondWayVacations(timeStops);		
			i--;
			
			//If we reach the end of the file we break the loop and we return
			if (!scanner.hasNextLine()) {
				break;
			}
			
			line = scanner.nextLine();
		} while (!(line.replaceAll("[ ]+", " ").equalsIgnoreCase(" ") || line.equalsIgnoreCase(""))); //While the line is not empty (End of the time table)
		
	}

	
}



