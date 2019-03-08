package fr.mrwormsy.busnetwork;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.graph.Graph;
import fr.mrwormsy.busnetwork.gui.Gui;
import fr.mrwormsy.busnetwork.node.Node;

public class BusNetwork {

	// === Static Variables ===
	
	private static ArrayList<Graph> listOfBusLines = null;
	private static Graph theGraph = null;
	
	private static String stopBefore = null;
	private static String stopAfter = null;
	
	private static String departTime = null;
	private static int currentTime = 0;
	
	// === Static Getters and Setters ===
	
	public static ArrayList<Graph> getListOfBusLines() {
		return listOfBusLines;
	}

	public static void setListOfBusLines(ArrayList<Graph> listOfBusLines) {
		BusNetwork.listOfBusLines = listOfBusLines;
	}

	public static Graph getTheGraph() {
		return theGraph;
	}

	public static void setTheGraph(Graph theGraph) {
		BusNetwork.theGraph = theGraph;
	}

	public static String getStopBefore() {
		return stopBefore;
	}

	public static void setStopBefore(String stopBefore) {
		BusNetwork.stopBefore = stopBefore;
	}

	public static String getStopAfter() {
		return stopAfter;
	}

	public static void setStopAfter(String stopAfter) {
		BusNetwork.stopAfter = stopAfter;
	}

	public static String getDepartTime() {
		return departTime;
	}

	public static void setDepartTime(String departTime) {
		BusNetwork.departTime = departTime;
	}

	public static int getCurrentTime() {
		return currentTime;
	}

	public static void setCurrentTime(int currentTime) {
		BusNetwork.currentTime = currentTime;
	}

	// === Main method ===
	
	public static void main(String[] args) {
		
		//The gui variable
		Gui gui;
		
		//The file variable that will contain a bus line
		FileInputStream file = null;
		
		//We init the list of bus stop to empty
		setListOfBusLines(new ArrayList<Graph>());
		
		try {		
			
			//We will use a scanner which is easier to use to read string values
			
			//The id is auto increment
			
			//We open the file
			file = new FileInputStream("2_Piscine-Patinoire_Campus.txt");
			
			//We create a scanner to scan the different lines
			Scanner scanner = new Scanner(file);
			
			//The graph is built according to the file read by the scanner
			Graph graph = Graph.getGraphFromFile(scanner);
			
			//We then make the arcs
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			file = new FileInputStream("1_Poisy-ParcDesGlaisins.txt");
			scanner = new Scanner(file);
			graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			file = new FileInputStream("3_Test.txt");
			scanner = new Scanner(file);
			graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			file = new FileInputStream("4_Test2.txt");
			scanner = new Scanner(file);
			graph = Graph.getGraphFromFile(scanner);
			graph.buildArcs();
			
			listOfBusLines.add(graph);
			
			//The id 0 is for the fused graph
			
			//When we have all the bus lines, we fuse them into one big graph
			theGraph = Graph.fuseGraphs(listOfBusLines);
			
			//We create a Gui and then init them
			gui = new Gui();
			gui.init();
			
			//This is for console only, the GUI version is in the Gui Class
			//askTheClient();
			
			//Close the file at the end
			if (file != null) {
				file.close();
			}
	      } catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//Console only, this should not be working anymore
	
	//Ask the client where he wants to go and when (optional, if this option is not set we return all the trips with the times related)
	@SuppressWarnings("resource")
	public static void askTheClient() {
		
		//First we need to display where the person wants to go
		System.out.print("\nWhere do you want to go ? ");
		Scanner scanner = new Scanner(System.in);
		
		Node nodeB = null;
		nodeB = theGraph.getNodeFromString(scanner.nextLine());
		
		//Then from where
		System.out.print("\nFrom Where ? ");
		scanner = new Scanner(System.in);
		
		Node nodeA = null;
		nodeA = theGraph.getNodeFromString(scanner.nextLine());
		
		if (nodeA == null || nodeB == null) {
			System.out.println("ERROR : One of the stops you entered is not valid...");
			return;
		}
		
		System.out.println("\nYou want to go from " + nodeA.getName() + " to " + nodeB.getName());
		
		//And ask after with hours and minute with the hh:mm format (if the client sets -1)
		System.out.print("\nDo you want to start your trip in a certain time ? Thus just type the hour and minute (eg 12:45). Else you let empty... ");
		scanner = new Scanner(System.in);
		
		//Informations
		
		/*
		
		String hourChoice = scanner.nextLine();
		if (hourChoice.equalsIgnoreCase("")) {
			
			//We must print all of the stops
			System.out.println("\nThere are all differents times...\n");
			
			//TODO UNDO THAT
			//theGraph.printTimeTableAToB(nodeA, nodeB);
		
		} else {
			
			if (!hourChoice.matches("[0-9][0-9]:[0-9][0-9]")) {
				System.out.println("ERROR : The hh:mm format is incorect");
				return;
			}
			
			if (theGraph.isTheBusTravelingOnTheFirstWay(nodeA, nodeB)) {
				System.out.println(Utils.getTImeHMFormat(nodeA.getListTimeOfStopFirstWay().get(nodeA.getClosestIdOfListOfTime(nodeA.getListTimeOfStopFirstWay(), hourChoice))));
			} else {
				System.out.println(Utils.getTImeHMFormat(nodeA.getListTimeOfStopSecondWay().get(nodeA.getClosestIdOfListOfTime(nodeA.getListTimeOfStopSecondWay(), hourChoice))));
			}
			
		}
		
		*/
		
		//Path 
		
		//We first need to run the Dijkstra algorithm and then do things
		/*
		
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(theGraph);
        dijkstra.execute(nodeA);
        ArrayList<Node> path = dijkstra.getPath(nodeB);
        
        if (path == null) {
			
        	System.out.println("ERROR : You cannot reach this bus stop from " + nodeA.getName());
        	return;
		}
        
        
        System.out.print("\nThe path will be : ");
        for(int i = 0; i < path.size() - 1; i++) {
        	System.out.print(path.get(i).getName() + " --> ");
        }
        System.out.println(path.get(path.size() - 1).getName());
		
		*/
		
		theGraph.findPath(nodeA, nodeB);
		
	}
}
