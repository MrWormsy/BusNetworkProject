package fr.mrwormsy.busnetwork.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.mrwormsy.busnetwork.BusNetwork;
import fr.mrwormsy.busnetwork.graph.Graph;
import fr.mrwormsy.busnetwork.node.Node;
import fr.mrwormsy.busnetwork.utils.Utils;

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

	public static ArrayList<Object> shapes;

	private static ArrayList<GraphicNode> gNodesList;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {
		setShapes(new ArrayList<Object>());

		setgNodesList(new ArrayList<GraphicNode>());

		this.setTitle("Projet SIBRA");
		this.setSize(1500, 1100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.setLayout(new BorderLayout());

		this.setBackground(Color.PINK);

		pathString = new JLabel("");
		pathString.setBounds(50, 850, this.getWidth() - 150, 200);

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
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j += 5) {
				times.addItem(String.format("%02d", i) + ":" + String.format("%02d", j));
			}
		}
		times.setSelectedIndex(0);
		times.setName("times");
		times.addActionListener(this);
		times.setBounds(this.getWidth() / 2 - 200 / 2, 50, 200, 30);

		searchButton = new JButton("Search path");
		searchButton.setName("searchButton");
		searchButton.addActionListener(this);
		searchButton.setBounds(this.getWidth() / 2 - 200 / 2, 100, 200, 30);

		pan.add(stopsBefore);
		pan.add(stopsAfter);
		pan.add(stopsBeforeLabel);
		pan.add(stopsAfterLabel);

		pan.add(times);

		pan.add(searchButton);

		pan.add(pathString);

		this.setContentPane(pan);

		this.setVisible(true);

		// this.drawGraph();
	}
	
	public Gui() {
		
	}

	public JPanel getPan() {
		return pan;
	}

	public void setPan(JPanel pan) {
		this.pan = pan;
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

	@SuppressWarnings("rawtypes")
	public static JComboBox getTimes() {
		return times;
	}

	@SuppressWarnings("rawtypes")
	public static void setTimes(JComboBox times) {
		Gui.times = times;
	}

	public static JButton getSearchButton() {
		return searchButton;
	}

	public static void setSearchButton(JButton searchButton) {
		Gui.searchButton = searchButton;
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

	public static JLabel getPathString() {
		return pathString;
	}

	public static void setPathString(JLabel pathString) {
		Gui.pathString = pathString;
	}

	public static ArrayList<Object> getShapes() {
		return shapes;
	}

	public static void setShapes(ArrayList<Object> shapes) {
		Gui.shapes = shapes;
	}

	public static ArrayList<GraphicNode> getgNodesList() {
		return gNodesList;
	}

	public static void setgNodesList(ArrayList<GraphicNode> gNodesList) {
		Gui.gNodesList = gNodesList;
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
				if (comboBox.getSelectedIndex() != 0) {
					BusNetwork.setStopBefore((String) comboBox.getSelectedItem());
				}
			} else if (comboBox.getName().equalsIgnoreCase(getStopsAfter().getName())) {
				if (comboBox.getSelectedIndex() != 0) {
					BusNetwork.setStopAfter((String) comboBox.getSelectedItem());
				}
			} else if (comboBox.getName().equalsIgnoreCase(getTimes().getName())) {

				if (comboBox.getSelectedIndex() != 0) {
					BusNetwork.setDepartTime((String) comboBox.getSelectedItem());
					BusNetwork.setCurrentTime(Utils.getTimeFromStringFormat((String) comboBox.getSelectedItem()));
				}
			}

		} else if (source instanceof JButton) {
			JButton button = (JButton) source;

			if (button.getName().equalsIgnoreCase("searchButton")) {
				if (!(BusNetwork.getStopAfter() == null || BusNetwork.getStopBefore() == null
						|| BusNetwork.getDepartTime() == null)) {
					Graph theGraph = BusNetwork.getTheGraph();
					// Print into the console the path

					// theGraph.findPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()),
					// theGraph.getNodeFromString(BusNetwork.getStopAfter()));
					this.displayPath(theGraph.getNodeFromString(BusNetwork.getStopBefore()),
							theGraph.getNodeFromString(BusNetwork.getStopAfter()));
					this.repaint();
				}
			}
		}
	}

	public void displayPath(Node nodeA, Node nodeB) {
		pathString.setText(
				"<html>" + "The path will be : " + BusNetwork.getTheGraph().findPath(nodeA, nodeB) + "</html>");
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

		if (!(BusNetwork.getStopAfter() == null || BusNetwork.getStopBefore() == null
				|| BusNetwork.getDepartTime() == null)) {
			this.drawGraphCircular(g,
					getArrayListStopsFromPathString(BusNetwork.getTheGraph().findPath(
							BusNetwork.getTheGraph().getNodeFromString(BusNetwork.getStopBefore()),
							BusNetwork.getTheGraph().getNodeFromString(BusNetwork.getStopAfter()))));
		} else {
			this.drawGraphCircular(g, null);
		}
	}

	public void drawGraphCircular(Graphics g, List<String> stopsList) {
		Graph theGraph = BusNetwork.getTheGraph();

		int centerX = this.getWidth() / 2;
		int centerY = this.getHeight() / 2;

		int radius = 375;

		// We can draw only y = between 150 and the max height - 50 and x = 50 and the
		// max weight - 50
		int nbOfStops = theGraph.getNodeList().size();

		GraphicNode gNode;

		int xx = -1;
		int yy = -1;

		int x, y;

		double increment = (2 * Math.PI) / nbOfStops;

		for (int i = 0; i < nbOfStops; i++) {
			double angle = i * increment;
			x = (int) (centerX + (radius * Math.cos(angle)));
			y = (int) (centerY + (radius * Math.sin(angle)));

			gNode = new GraphicNode(x, y, theGraph.getNodeList().get(i).getName().toLowerCase()
					+ theGraph.getNodeList().get(i).getFormatedBusLines());

			if (stopsList != null) {
				if (containsStopString(stopsList,
						gNode.getName().replaceAll("[ ]*[\\(]([0-9]*[,]?[0-9]*)*[\\)]", ""))) {
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

			if (BusNetwork.getDepartTime() == null) {
				this.add(gNode.getLabel());
			}
		}
	}

	public List<String> getArrayListStopsFromPathString(String string) {
		String theString = string.replaceAll("[ ]+", "").replaceAll("(\\([0-9]*\\))", "");
		return Arrays.asList(theString.split("(-->)"));
	}

	public boolean containsStopString(List<String> list, String string) {
		for (String s : list) {
			if (s.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}
}