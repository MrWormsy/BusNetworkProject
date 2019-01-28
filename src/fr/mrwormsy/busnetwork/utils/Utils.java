package fr.mrwormsy.busnetwork.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.Node.Node;

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

	public static void setListsOfTimeOfEachStops(Scanner scanner, ArrayList<Node> nodes) {
		
		String line = "";
		
		do {
			line = scanner.nextLine();
			
			
		} while (!(line.replaceAll("[ ]+", " ").equalsIgnoreCase(" ") || line.equalsIgnoreCase(""))); //While the line is not empty
		
	}
	
}
