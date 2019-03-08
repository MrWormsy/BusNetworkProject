package fr.mrwormsy.busnetwork.graph;

import java.util.ArrayList;
import java.util.Scanner;

import fr.mrwormsy.busnetwork.BusNetwork;
import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.dijkstra.DijkstraAlgorithm;
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
	
	//The list of connections between bus stops
	private ArrayList<Arc> arcListSecondtWay;
	
	// === Constructor ===
	
	public Graph(int id) {
		this.setId(id);
		
		//We init the lists to empty
		this.setNodeList(new ArrayList<Node>());
		
		this.setArcListFirstWay(new ArrayList<Arc>());
		this.setArcListSecondtWay(new ArrayList<Arc>());
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

	public ArrayList<Arc> getArcListSecondtWay() {
		return arcListSecondtWay;
	}

	public void setArcListSecondtWay(ArrayList<Arc> arcListSecondtWay) {
		this.arcListSecondtWay = arcListSecondtWay;
	}

	//Get a graph from a file (return a graph)
	public static Graph getGraphFromFile(Scanner scanner) {
		
		//We need to get the id for this graph, that is to say the number of graph already existing + 1 (the first id is 1)
		Graph graph = new Graph(BusNetwork.getListOfBusLines().size() + 1);
		
		//The list of node we will add to the graph
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//We first need to gather the list of stops to create the list of nodes
		for (String str : Utils.getListOfStops(scanner)) {
			nodes.add(new Node(str, graph.getId()));
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
		
		//We loop all the nodes without the last one with a variable and then create the nodes
		for (int i = 0; i < nodes.size() - 1; i++) {
			this.getArcListFirstWay().add(new Arc(nodes.get(i), nodes.get(i + 1))); 
		}
		
		//We loop all the nodes without the last one with a variable and then create the nodes
		for (int i = nodes.size() - 1; i > 0; i--) {
			this.getArcListSecondtWay().add(new Arc(nodes.get(i), nodes.get(i - 1))); 
		}		
	}
	
	//Get the Node from a String, if the Node does not exist we return null, which means this Node doesn't belong to the graph
	public Node getNodeFromString(String nodeStr) {
		for(Node n : this.getNodeList()) {
			if (n.getName().equalsIgnoreCase(nodeStr)) {
				return n;
			}
		}
		return null;
	}
	
	//Fuse all the graphs in a Graph ArrayList
	public static Graph fuseGraphs(ArrayList<Graph> graphs) {
		
		//The id 0 is reserved by the fused graph
		Graph graphToReturn = new Graph(0);
		
		//First we need to gather all the stops and then fuse the same stops and add all the arcs...
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Arc> arcsFW = new ArrayList<Arc>();
		ArrayList<Arc> arcsSW = new ArrayList<Arc>();
		//ArrayList<Arc> arcsFWV = new ArrayList<Arc>();
		//ArrayList<Arc> arcsSWV = new ArrayList<Arc>();
		for(Graph graph : graphs) {
			for(Node n : graph.getNodeList()) {
				if (!Node.nodeNameExistsInList(nodes, n.getName())) {
					Node node = new Node(n.getName(), 0);
					nodes.add(node);
				}
				
				getNodeFromList(nodes, n.getName()).getBusLines().add(n.getBusLines().get(0));
				getNodeFromList(nodes, n.getName()).getListTimeOfStopFirstWay().put(graph.getId(), n.getListTimeOfStopFirstWay().get(graph.getId()));
				getNodeFromList(nodes, n.getName()).getListTimeOfStopSecondWay().put(graph.getId(), n.getListTimeOfStopSecondWay().get(graph.getId()));
				
			}
		}	
		
		graphToReturn.setNodeList(nodes);
		
		//Here we are dealing with the arcs
		for(Graph graph : graphs) {
			for(Arc a : graph.getArcListFirstWay()) {
				arcsFW.add(new Arc(graphToReturn.getNodeFromString(a.getBefore().getName()), graphToReturn.getNodeFromString(a.getAfter().getName())));
			}
			
			for(Arc a : graph.getArcListSecondtWay()) {
				arcsSW.add(new Arc(graphToReturn.getNodeFromString(a.getBefore().getName()), graphToReturn.getNodeFromString(a.getAfter().getName())));
			}
		}
		
		graphToReturn.setArcListFirstWay(arcsFW);
		graphToReturn.setArcListSecondtWay(arcsSW);
		
		//graphToReturn.calculateAverageTimeOfArcs();
		
		return graphToReturn;
	}
	
	public static Node getNodeFromList(ArrayList<Node> nodes, String name) {
		for(Node node : nodes) {
			if (node.getName().equalsIgnoreCase(name)) {
				return node;
			}
		}
		
		return null;
	}
	
	public String[] getStopsName() {
		ArrayList<String> stopsNames = new ArrayList<String>();
		stopsNames.add("-----");
		for(Node node : this.getNodeList()) {
			stopsNames.add(node.getName().toLowerCase());
		}
		
		return stopsNames.toArray(new String[0]);
		
	}
	
	public String findPath(Node nodeA, Node nodeB) {
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this);
        dijkstra.execute(nodeA);
        ArrayList<Node> path = dijkstra.getPath(nodeB);
        
        if (path == null) {
			
        	System.out.println("ERROR : You cannot reach this bus stop from " + nodeA.getName());
        	return "ERROR : You cannot reach this bus stop from " + nodeA.getName();
		}
        
        String string = "";
        
        //System.out.print("\nThe path will be : ");
        for(int i = 0; i < path.size() - 1; i++) {
        	//System.out.print(path.get(i).getName() + " --> ");
        	string = string.concat(path.get(i).getName() + " (" + String.valueOf(path.get(i).getBusLines().get(1)) + ") " + " --> ");
        }
        
        string = string.concat(path.get(path.size() - 1).getName() + " (" + String.valueOf(path.get(path.size() - 1).getBusLines().get(1)) + ")");
        //System.out.println(path.get(path.size() - 1).getName());
        
        return string;
	}
	
	//We try to get the average time bewteen two nodes (arcs) to set this to the weight of the arc
	public int getAverageTimeBetweenTwoNodes(Arc arc) {
		Node nodeA = arc.getBefore();
		Node nodeB = arc.getAfter();
		
		int time = 0;
		int nbOfTimes = nodeA.getListTimeOfStopFirstWay().size();
		
		for(int i = 0; i < nbOfTimes - 1; i++) {
			time += nodeB.getListTimeOfStopFirstWay().get(0).get(i) - nodeA.getListTimeOfStopFirstWay().get(0).get(i);
		}	
		
		return (int) time/nbOfTimes;
	}
	
	
	public void calculateAverageTimeOfArcs() {
		for(Arc arc : this.getArcListFirstWay()) {
			arc.setWeight(this.averageTimeBewteenTwoNodes(arc.getBefore(), arc.getAfter()));
			System.out.println(this.averageTimeBewteenTwoNodes(arc.getBefore(), arc.getAfter()));
		}
		
		for(Arc arc : this.getArcListSecondtWay()) {
			arc.setWeight(this.averageTimeBewteenTwoNodes(arc.getBefore(), arc.getAfter()));
			System.out.println(this.averageTimeBewteenTwoNodes(arc.getBefore(), arc.getAfter()));
		}
	}
	
	public int averageTimeBewteenTwoNodes(Node A, Node B) {
		
		int count = 0;
		int total = 0;
		
		for(Integer i : A.getListTimeOfStopFirstWay().keySet()) {
			if (B.getListTimeOfStopFirstWay().containsKey(i)) {
				for(int j = 0; (j < A.getListTimeOfStopFirstWay().get(i).size()); j++) {				
					//for(Integer j : A.getListTimeOfStopFirstWay().get(i)) {
					if (!(A.getListTimeOfStopFirstWay().get(i).get(j) == -1 || B.getListTimeOfStopFirstWay().get(i).get(j) == -1)) {
						total += (B.getListTimeOfStopFirstWay().get(i).get(j) - A.getListTimeOfStopFirstWay().get(i).get(j));
						count++;
					}
				}
			}
		}
		
		if (count == 0) {
			return 0;
		}
		return (int) (total/count);
	}
	
	
	
}