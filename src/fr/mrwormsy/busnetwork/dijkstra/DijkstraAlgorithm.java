package fr.mrwormsy.busnetwork.dijkstra;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.mrwormsy.busnetwork.arc.Arc;
import fr.mrwormsy.busnetwork.graph.Graph;
import fr.mrwormsy.busnetwork.node.Node;

public class DijkstraAlgorithm {

    @SuppressWarnings("unused")
	private final List<Node> nodes;
    private final List<Arc> Arcs;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodeList());
        this.Arcs = new ArrayList<Arc>(graph.getArcListFirstWay());
        this.Arcs.addAll(graph.getArcListSecondtWay());
    }

    public void execute(Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Node node, Node target) {
        for (Arc Arc : Arcs) {
            if (Arc.getBefore().equals(node)
                    && Arc.getAfter().equals(target)) {
                return Arc.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Arc Arc : Arcs) {
            if (Arc.getBefore().equals(node)
                    && !isSettled(Arc.getAfter())) {
                neighbors.add(Arc.getAfter());
            }
        }
        return neighbors;
    }

    private Node getMinimum(Set<Node> Nodees) {
        Node minimum = null;
        for (Node Node : Nodees) {
            if (minimum == null) {
                minimum = Node;
            } else {
                if (getShortestDistance(Node) < getShortestDistance(minimum)) {
                    minimum = Node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Node Node) {
        return settledNodes.contains(Node);
    }

    private int getShortestDistance(Node destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public ArrayList<Node> getPath(Node target) {
    	ArrayList<Node> path = new ArrayList<Node>();
        Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}