package fr.mrwormsy.busnetwork.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.mrwormsy.busnetwork.BusNetwork;
import fr.mrwormsy.busnetwork.graph.Graph;
import fr.mrwormsy.busnetwork.node.Node;


public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel pan;
	
	@SuppressWarnings("rawtypes")
	private static JComboBox stopsBefore;
	@SuppressWarnings("rawtypes")
	private static JComboBox stopsAfter;
	@SuppressWarnings("rawtypes")
	private static JComboBox times;
	
	private static JButton searchButton;
	private static JLabel stopsBeforeLabel;
	private static JLabel stopsAfterLabel;
	
	private static JLabel pathString;
	
	public JPanel getPan() {
		return pan;
	}

	public void setPan(JPanel pan) {
		this.pan = pan;
	}

	public static JLabel getPathString() {
		return pathString;
	}

	public static void setPathString(JLabel pathString) {
		Gui.pathString = pathString;
	}

	@SuppressWarnings("rawtypes")
	public static JComboBox getTimes() {
		return times;
	}

	@SuppressWarnings("rawtypes")
	public static void setTimes(JComboBox times) {
		Gui.times = times;
	}

	public static ArrayList<Object> shapes;
	
	private static ArrayList<GraphicNode> gNodesList;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gui() {   
		
		setShapes(new ArrayList<Object>());
		
		setgNodesList(new ArrayList<GraphicNode>());
		
		this.setTitle("Projet SIBRA");
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);		
		    
		this.setLayout(new BorderLayout());  
		
		this.setBackground(Color.PINK);
		
		pathString = new JLabel("");
		pathString.setBounds(50, 600, this.getWidth() - 150, 200);
		
		pan = new GPanel();
		pan.setPreferredSize(this.getMaximumSize());
		
		pan.setBackground(Color.PINK);    
		pan.setLayout(null);
		
		String[] stopsList = BusNetwork.getTheGraph().getStopsName();

		stopsBefore = new JComboBox(stopsList);
		stopsAfter = new JComboBox(stopsList);
		JLabel stopsBeforeLabel = new JLabel("From where ?");
		JLabel stopsAfterLabel = new JLabel("To ?");
		
		stopsBefore.setName("stopsBefore");
		stopsBefore.setSelectedIndex(0);
		stopsBefore.addActionListener(this);
		stopsBefore.setBounds(50, 40, 150, 25);
		stopsBeforeLabel.setBounds(50, 15, 150, 25);
		
		stopsAfter.setName("stopsAfter");
		stopsAfter.setSelectedIndex(0);
		stopsAfter.addActionListener(this);
		stopsAfter.setBounds(((int) this.getBounds().getWidth()) - 200, 40, 150, 25);
		stopsAfterLabel.setBounds(((int) this.getBounds().getWidth()) - 200, 15, 150, 25);
		
		
		times = new JComboBox();
		times.addItem("Depart time");
		for(int i = 0; i < 24; i++) {
			for(int j = 0; j < 60; j+=5) {
				times.addItem(String.format("%02d", i) + ":" + String.format("%02d", j));
			}
		}
		times.setSelectedIndex(0);
		times.setName("times");
		times.addActionListener(this);
		times.setBounds(this.getWidth()/2 - 200/2, 50, 200, 30);
		
		searchButton = new JButton("Search path");
		searchButton.setName("searchButton");
		searchButton.addActionListener(this);
		searchButton.setBounds(this.getWidth()/2 - 200/2, 100, 200, 30);
		
		
		pan.add(stopsBefore);
		pan.add(stopsAfter);
		pan.add(stopsBeforeLabel);
		pan.add(stopsAfterLabel);
		
		pan.add(times);
		
		pan.add(searchButton);
		
		pan.add(pathString);
		
		this.setContentPane(pan);
		
		this.setVisible(true);
		
		//this.drawGraph();
	}

	public static ArrayList<Object> getShapes() {
		return shapes;
	}

	public static void setShapes(ArrayList<Object> shapes) {
		Gui.shapes = shapes;
	}

	@SuppressWarnings("rawtypes")
	public static JComboBox getStopsBefore() {
		return stopsBefore;
	}

	@SuppressWarnings("rawtypes")
	public static void setStopsBefore(JComboBox stopsBefore) {
		Gui.stopsBefore = stopsBefore;
	}

	@SuppressWarnings("rawtypes")
	public static JComboBox getStopsAfter() {
		return stopsAfter;
	}

	@SuppressWarnings("rawtypes")
	public static void setStopsAfter(JComboBox stopsAfter) {
		Gui.stopsAfter = stopsAfter;
	}

	public static JLabel getStopsBeforeLabel() {
		return stopsBeforeLabel;
	}

	public static void setStopsBeforeLabel(JLabel stopsBeforeLabel) {
		Gui.stopsBeforeLabel = stopsBeforeLabel;
	}

	public static JLabel getStopsAfterLabel() {
		return stopsAfterLabel;
	}

	public static void setStopsAfterLabel(JLabel stopsAfterLabel) {
		Gui.stopsAfterLabel = stopsAfterLabel;
	}

	public static JButton getSearchButton() {
		return searchButton;
	}

	public static void setSearchButton(JButton searchButton) {
		Gui.searchButton = searchButton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static ArrayList<GraphicNode> getgNodesList() {
		return gNodesList;
	}

	public static void setgNodesList(ArrayList<GraphicNode> gNodesList) {
		Gui.gNodesList = gNodesList;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) source;
			
			if (comboBox.getName().equalsIgnoreCase(getStopsBefore().getName())) {
				BusNetwork.setStopBefore((String) comboBox.getSelectedItem());
			} else if (comboBox.getName().equalsIgnoreCase(getStopsAfter().getName())) {
				BusNetwork.setStopAfter((String) comboBox.getSelectedItem());
			} else if (comboBox.getName().equalsIgnoreCase(getTimes().getName())) {
				BusNetwork.setDepartTime((String) comboBox.getSelectedItem());
			}
		} else if (source instanceof JButton) {
			JButton button = (JButton) source;
			
			if (button.getName().equalsIgnoreCase("searchButton")) {
				if (!(BusNetwork.getStopAfter() == null || BusNetwork.getStopBefore() == null || BusNetwork.getDepartTime() == null)) {
					Graph theGraph = BusNetwork.getTheGraph();
					//theGraph.findPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()), theGraph.getNodeFromString(BusNetwork.getStopAfter()));
					this.displayPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()), theGraph.getNodeFromString(BusNetwork.getStopAfter()));

					this.repaint();
				}
			}
		}
	}
	
	public void displayPath(Node nodeA, Node nodeB) {
		pathString.setText("<html>" + "The path will be : " + BusNetwork.getTheGraph().findPath(nodeA, nodeB) + "</html>");
	}
}


class GPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GPanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
        for (Object s : Gui.shapes) {
            if (s instanceof GraphicNode) {
                ((GraphicNode) s).draw(g, false);
            } else if (s instanceof GraphicArc) {
                ((GraphicArc) s).draw(g);
            }
        }
        
        if (!(BusNetwork.getStopAfter() == null || BusNetwork.getStopBefore() == null)) {
        	this.drawGraph(g, getArrayListStopsFromPathString(BusNetwork.getTheGraph().findPath(BusNetwork.getTheGraph().getNodeFromString(BusNetwork.getStopBefore()), BusNetwork.getTheGraph().getNodeFromString(BusNetwork.getStopAfter()))));
		} else {
			this.drawGraph(g, null);
			//this.drawFormatedGraph(g, null);
		}
    }
    
    //Draws the graph without being formated by the bus line
	public void drawGraph(Graphics g, List<String> stopsList) {
    	Graph theGraph = BusNetwork.getTheGraph();
		
		//We can draw only y = between 150 and the max height - 50 and x = 50 and the max weight - 50
		int maxLineLengh = theGraph.getNodeList().size();
		
		int x = 50;
		int y = 300;
		int count = 0;
		
		int xx = -1;
		int yy = -1;
		
		GraphicNode gNode;
		
		for(int i = 0; i < maxLineLengh; i++) {
			
			if (count >= 10) {
				y += 100;
				x = 50;
				count = 0;
			}
			
			gNode = new GraphicNode(x, y, theGraph.getNodeList().get(i).getName().toLowerCase());
			
			if (stopsList != null) {
				if (containsStopString(stopsList, gNode.getName())) {
					gNode.draw(g, true);
					
					if (xx != -1 && yy != -1) {
						g.drawLine(xx, yy, x + 5, y + 5);
					}
					
					xx = x + 5;
					yy = y + 5;
					
				} else {
					gNode.draw(g, false);
				}
			} else {
				gNode.draw(g, false);
			}
			
			
			this.add(gNode.getLabel());
			
			x += ((this.getWidth() - 100) / 10);
			count++;
		}  
	}
	
	//TODO TOO HARD FOR NOW
	
	//Draw the graph according to the bus lines. We first draw the first bus line as a line, then we draw the second one in order to put the connection if it exists (with a certain rotation)
	public void drawFormatedGraph(Graphics g, List<String> stopsList) {
		
		Graph theGraph = BusNetwork.getTheGraph();
		
		HashMap<Integer, ArrayList<GraphicNode>> hashMapGraphicNodes = new HashMap<Integer, ArrayList<GraphicNode>>();
		
		//We first need to draw the first bus line in a line (no rotation)
		
		//We want to split the graph in n graphs which are straight
		for(Node node : theGraph.getNodeList()) {
			for(Integer i : node.getBusLines()) {				
				if (hashMapGraphicNodes.containsKey(i)) {
					hashMapGraphicNodes.get(i).add(new GraphicNode(node.getName()));
				} else {
					hashMapGraphicNodes.put(i, new ArrayList<GraphicNode>());
					hashMapGraphicNodes.get(i).add(new GraphicNode(node.getName()));
				}				
			}
		}
			
		//Now as our hash map is built, we must choose one line (the first one for instance, and draw it, with a y which is the middle of the window).			
		int x = 50;
		int y = 450;
		
		int maxSize = hashMapGraphicNodes.get(1).size();
			
		for(GraphicNode graphicNode : hashMapGraphicNodes.get(1)) {
			graphicNode.setX(x);
			graphicNode.setY(y);
			
			graphicNode.draw(g, false);
			graphicNode.getLabel().setBounds(x - 40, y - 20, 80, 20);
			this.add(graphicNode.getLabel());
			
			x += ((this.getWidth() - 50) / maxSize);
		}
		
		//If there is only one graph we have finished
		if (hashMapGraphicNodes.size() == 1) {
			return;
		}
		
		int count = 1;
		
		x = -1;
		y = -1;
		
		for(int i = 2; i < hashMapGraphicNodes.size(); i++) {
			//We first need to get the node in common
			GraphicNode gNodeInCommon = GraphicNode.getGraphicNodeInCommon(hashMapGraphicNodes.get(i - 1), hashMapGraphicNodes.get(i));
			
		}
		
	}
	
	public List<String> getArrayListStopsFromPathString(String string) {		
		String theString = string.replaceAll("[ ]+", "");		
		return Arrays.asList(theString.split("(-->)"));
		
	}
	
	public boolean containsStopString(List<String> list, String string) {
		for(String s : list) {
			if (s.equalsIgnoreCase(string)) {
				return true;
			}
		}
		
		return false;
	}
}