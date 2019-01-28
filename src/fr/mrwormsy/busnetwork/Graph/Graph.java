package fr.mrwormsy.busnetwork.Graph;

import java.util.ArrayList;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.BusNetwork;
import fr.mrwormsy.busnetwork.Node.Node;
import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.utils.Utils;

public class Graph {

	// === Variables ===
	
	//The bus line
	private int id;
	
	//The list of bus stops
	private ArrayList<Node> nodeList;
	
	//The list of connections between bus stops
	private ArrayList<Arc> arcList;
	
	//The list of connections between bus stops during vacations
	private ArrayList<Arc> arcListVacations;
	
	// === Constructor ===
	
	public Graph(int id) {
		this.setId(id);
		
		//We init the lists to empty
		this.setNodeList(new ArrayList<Node>());
		this.setArcList(new ArrayList<Arc>());
		this.setArcListVacations(new ArrayList<Arc>());
	}

	// === Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public ArrayList<Arc> getArcList() {
		return arcList;
	}

	public void setArcList(ArrayList<Arc> arcList) {
		this.arcList = arcList;
	}
	
	// === Methods ===
	
	public ArrayList<Arc> getArcListVacations() {
		return arcListVacations;
	}

	public void setArcListVacations(ArrayList<Arc> arcListVacations) {
		this.arcListVacations = arcListVacations;
	}

	//Get a graph from a file (return a graph)
	public static Graph getGraphFromFile(Scanner scanner) {
		
		//We need to get the id for this graph, that is to say the number of graph already existing + 1 (the first id is 1)
		Graph graph = new Graph(BusNetwork.getListOfBusLines().size() + 1);
		
		//The list of node we will add to the graph
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//We first need to gather the list of stops to create the list of nodes
		for (String str : Utils.getListOfStops(scanner)) {
			nodes.add(new Node(nodes.size() + 1, str));
		}
		
		//We continue to the next line because this should be a blank space
		scanner.nextLine();
		
		//Then we will need to gather the list of time of each stops
		Utils.setListsOfTimeOfEachStops(scanner, nodes);
		
		System.out.println("DONE");
		
		return graph;
	}
	
}
