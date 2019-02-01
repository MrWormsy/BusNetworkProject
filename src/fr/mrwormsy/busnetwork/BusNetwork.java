package fr.mrwormsy.busnetwork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.graph.Graph;
import fr.mrwormsy.busnetwork.node.Node;
import fr.mrwormsy.busnetwork.utils.Utils;

public class BusNetwork {

	private static Connection connection;
	private static ArrayList<Graph> listOfBusLines;
	
	public static void main(String[] args) {
		
		//First we open the SQL Stream
		//connection = BusNewtorkSQL.connect();
		
		//The file
		FileInputStream file = null;
		
		//We init the list of bus stop to empty
		setListOfBusLines(new ArrayList<Graph>());
		
		try {			
			//We will use a scanner which is easier to use
			file = new FileInputStream("2_Piscine-Patinoire_Campus.txt");
			Scanner scanner = new Scanner(file);
			Graph graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			//We will use a scanner which is easier to use
			file = new FileInputStream("1_Poisy-ParcDesGlaisins.txt");
			scanner = new Scanner(file);
			graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			askTheClient();
			
			//Close the file at the end
			if (file != null) {
				file.close();
			}
	      } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ask the client where he wants to go and when (optional, if this option is not set we return all the trips with the times related)
	public static void askTheClient() {
		
		//First we need to display where the person wants to go
		System.out.print("\nWhere do you want to go ? ");
		Scanner scanner = new Scanner(System.in);
		
		Node nodeB = null;
		
		for(Graph graph : getListOfBusLines()) {
			nodeB = graph.getNodeFromString(scanner.nextLine());
			if (nodeB != null) {
				break;
			}
		}
		
		//Then from where
		System.out.print("\nFrom Where ? ");
		scanner = new Scanner(System.in);
		
		Node nodeA = null;
		
		for(Graph graph : getListOfBusLines()) {
			nodeA = graph.getNodeFromString(scanner.nextLine());
			if (nodeA != null) {
				break;
			}
		}
		
		if (nodeA == null || nodeB == null) {
			System.out.println("ERROR : One of the stops you entered is not valid...");
			return;
		}
		
		System.out.println("\nYou want to go from " + nodeA.getName() + " to " + nodeB.getName());
		
		//And ask after with hours and minute with the hh:mm format (if the client sets -1)
		System.out.print("\nDo you want to start your trip in a certain time ? Thus just type the hour and minute (eg 12:45). Else you let empty... ");
		scanner = new Scanner(System.in);
		
		String hourChoice = scanner.nextLine();
		if (hourChoice.equalsIgnoreCase("")) {
			
			//We must print all of the stops
			System.out.println("\nThere are all differents times...\n");
			getListOfBusLines().get(0).printTimeTableAToB(nodeA, nodeB);
		
		} else {
			
			if (!hourChoice.matches("[0-9][0-9]:[0-9][0-9]")) {
				System.out.println("ERROR : The hh:mm format is incorect");
				return;
			}
			
			if (getListOfBusLines().get(0).isTheBusTravelingOnTheFirstWay(nodeA, nodeB)) {
				System.out.println(Utils.getTImeHMFormat(nodeA.getListTimeOfStopFirstWay().get(nodeA.getClosestIdOfListOfTime(nodeA.getListTimeOfStopFirstWay(), hourChoice))));
			} else {
				System.out.println(Utils.getTImeHMFormat(nodeA.getListTimeOfStopSecondWay().get(nodeA.getClosestIdOfListOfTime(nodeA.getListTimeOfStopSecondWay(), hourChoice))));
			}
			
		}
		
	}
	
	//Get the SQL connection
	public static Connection getConnection() {
		return connection;
	}

	public static ArrayList<Graph> getListOfBusLines() {
		return listOfBusLines;
	}

	public static void setListOfBusLines(ArrayList<Graph> listOfBusLines) {
		BusNetwork.listOfBusLines = listOfBusLines;
	}
	
	public static void addBusLineToListOfBusLines(Graph BusLines) {
		BusNetwork.listOfBusLines.add(BusLines);
	}

}
