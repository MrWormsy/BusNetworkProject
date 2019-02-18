package fr.mrwormsy.busnetwork.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.mrwormsy.busnetwork.BusNetwork;

public class Gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private static JComboBox stopsBefore;
	@SuppressWarnings("rawtypes")
	private static JComboBox stopsAfter;
	private static JLabel stopsBeforeLabel;
	private static JLabel stopsAfterLabel;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gui() {   
		this.setTitle("Projet SIBRA");
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
			 
		JPanel pan = new JPanel();		
		
		pan.setBackground(Color.ORANGE);        
		this.setLayout(new BorderLayout());  
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
		
		this.setContentPane(pan);
		
		pan.add(stopsBefore);
		pan.add(stopsAfter);
		pan.add(stopsBeforeLabel);
		pan.add(stopsAfterLabel);
		
		this.setVisible(true);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) source;
			
			System.out.println(comboBox.getName());
			
			if (comboBox.getName().equalsIgnoreCase(getStopsBefore().getName())) {
				System.out.println("BEFORE --> " + comboBox.getSelectedItem());
			} else if (comboBox.getName().equalsIgnoreCase(getStopsAfter().getName())) {
				System.out.println("AFTER --> " + comboBox.getSelectedItem());
			}
		}
	} 
}
