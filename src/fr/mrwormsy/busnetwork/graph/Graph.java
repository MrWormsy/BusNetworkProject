package fr.mrwormsy.busnetwork.graph;

import java.util.ArrayList;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.BusNetwork;
import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.node.Node;
import fr.mrwormsy.busnetwork.utils.Utils;

public class Graph {

	// === Variables ===
	
	//The bus line
	private int id;
	
	//The list of bus stops
	private ArrayList<Node> nodeList;
	
	//The list of connections between bus stops
	private ArrayList<Arc> arcListFirstWay;
	
	//The list of connections between bus stops during vacations
	private ArrayList<Arc> arcListFirstWayVacations;
	
	//The list of connections between bus stops
	private ArrayList<Arc> arcListSecondtWay;
	
	//The list of connections between bus stops during vacations
	private ArrayList<Arc> arcListSecondWayVacations;
	
	// === Constructor ===
	
	public Graph(int id) {
		this.setId(id);
		
		//We init the lists to empty
		this.setNodeList(new ArrayList<Node>());
		
		this.setArcListFirstWay(new ArrayList<Arc>());
		this.setArcListSecondtWay(new ArrayList<Arc>());
		this.setArcListFirstWayVacations(new ArrayList<Arc>());
		this.setArcListSecondWayVacations(new ArrayList<Arc>());
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

	public ArrayList<Arc> getArcListFirstWay() {
		return arcListFirstWay;
	}

	public void setArcListFirstWay(ArrayList<Arc> arcListFirstWay) {
		this.arcListFirstWay = arcListFirstWay;
	}

	public ArrayList<Arc> getArcListFirstWayVacations() {
		return arcListFirstWayVacations;
	}

	public void setArcListFirstWayVacations(ArrayList<Arc> arcListFirstWayVacations) {
		this.arcListFirstWayVacations = arcListFirstWayVacations;
	}

	public ArrayList<Arc> getArcListSecondtWay() {
		return arcListSecondtWay;
	}

	public void setArcListSecondtWay(ArrayList<Arc> arcListSecondtWay) {
		this.arcListSecondtWay = arcListSecondtWay;
	}

	public ArrayList<Arc> getArcListSecondWayVacations() {
		return arcListSecondWayVacations;
	}

	public void setArcListSecondWayVacations(ArrayList<Arc> arcListSecondWayVacations) {
		this.arcListSecondWayVacations = arcListSecondWayVacations;
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
		
		graph.setNodeList(nodes);
		
		return graph;
	}
	
	public void buildArcs() {
		
		ArrayList<Node> nodes = this.getNodeList();
		
		//First Way when not in vacation
		for (int j = 0; j < nodes.get(0).getListTimeOfStopFirstWay().size(); j++) {
		
			//We loop all the nodes without the last one with a variable and then create the nodes
			for (int i = 0; i < nodes.size() - 1; i++) {
				this.getArcListFirstWay().add(new Arc(i, nodes.get(i), nodes.get(i + 1), nodes.get(i).getListTimeOfStopFirstWay().get(j))); 
			}
			
		}

		//Second Way when not in vacation
		for (int j = 0; j < nodes.get(0).getListTimeOfStopSecondWay().size(); j++) {
		
			//We loop all the nodes without the last one with a variable and then create the nodes
			for (int i = 0; i < nodes.size() - 1; i++) {
				this.getArcListSecondtWay().add(new Arc(i, nodes.get(i), nodes.get(i + 1), nodes.get(i).getListTimeOfStopSecondWay().get(j))); 
			}
			
		}
		
		//First Way when in vacation
		for (int j = 0; j < nodes.get(0).getListTimeOfStopFirstWayVacations().size(); j++) {
		
			//We loop all the nodes without the last one with a variable and then create the nodes
			for (int i = 0; i < nodes.size() - 1; i++) {
				this.getArcListFirstWayVacations().add(new Arc(i, nodes.get(i), nodes.get(i + 1), nodes.get(i).getListTimeOfStopFirstWayVacations().get(j))); 
			}
			
		}
		
		//Second Way when in vacation
		for (int j = 0; j < nodes.get(0).getListTimeOfStopSecondWayVacations().size(); j++) {
		
			//We loop all the nodes without the last one with a variable and then create the nodes
			for (int i = 0; i < nodes.size() - 1; i++) {
				this.getArcListSecondWayVacations().add(new Arc(i, nodes.get(i), nodes.get(i + 1), nodes.get(i).getListTimeOfStopSecondWayVacations().get(j))); 
			}
			
		}
		
		System.out.println("-------------------- All the arcs has been built --------------------");
		
	}
	
}