package fr.mrwormsy.busnetwork.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private static JButton searchButton;
	private static JLabel stopsBeforeLabel;
	private static JLabel stopsAfterLabel;
	
	private static JLabel pathString;
	
	public static ArrayList<Object> shapes;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gui() {   
		
		setShapes(new ArrayList<Object>());
		
		this.setTitle("Projet SIBRA");
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);		
		    
		this.setLayout(new BorderLayout());  
		
		this.setBackground(Color.PINK);
		
		pathString = new JLabel("");
		pathString.setBounds(50, 300, this.getWidth() - 150, 200);
		
		pan = new GPanel();
		pan.setPreferredSize(this.getMaximumSize());
		
		pan.setBackground(Color.PINK);    
		pan.setLayout(null);
		
		//String[] petStrings = { "---", "Bird", "Cat", "Dog", "Rabbit", "Pig" };
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
		
		searchButton = new JButton("Search path");
		searchButton.setName("searchButton");
		searchButton.addActionListener(this);
		searchButton.setBounds(this.getWidth()/2 - 200/2, 100, 200, 30);
		
		
		pan.add(stopsBefore);
		pan.add(stopsAfter);
		pan.add(stopsBeforeLabel);
		pan.add(stopsAfterLabel);
		
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
			}
		} else if (source instanceof JButton) {
			JButton button = (JButton) source;
			
			if (button.getName().equalsIgnoreCase("searchButton")) {
				if (!(BusNetwork.getStopAfter() == null || BusNetwork.getStopBefore() == null)) {
					Graph theGraph = BusNetwork.getTheGraph();
					//theGraph.findPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()), theGraph.getNodeFromString(BusNetwork.getStopAfter()));
					this.displayPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()), theGraph.getNodeFromString(BusNetwork.getStopAfter()));
				}
			}
		}
	}
	
	public void drawGraph() {
		
		
	}
	
	public void drawPath(Node nodeA, Node nodeB) {
		
		
	}
	
	public void displayPath(Node nodeA, Node nodeB) {
		pathString.setText("<html>" + BusNetwork.getTheGraph().findPath(nodeA, nodeB) + "</html>");
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
                ((GraphicNode) s).draw(g);
            } else if (s instanceof GraphicArc) {
                ((GraphicArc) s).draw(g);
            }
        }
    	
        /*
        
    	Graph theGraph = BusNetwork.getTheGraph();
		
		//We can draw only y = between 150 and the max height - 50 and x = 50 and the max weight - 50
		int maxLineLengh = theGraph.getNodeList().size();
		
		int x = 50;
		
		GraphicNode gNode;
		
		for(int i = 0; i < maxLineLengh; i++) {
			
			gNode = new GraphicNode(x, 300);
			gNode.draw(g);
			
			//g.drawOval(x, 300, 10, 10);
			
			x += ((this.getWidth() - 100) / maxLineLengh);
		}
		
		*/
    	
    }
}