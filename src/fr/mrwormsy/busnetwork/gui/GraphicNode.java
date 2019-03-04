package fr.mrwormsy.busnetwork.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GraphicNode {

	 private int x, y, width, height;
	 private String name;
	 private JLabel label;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public GraphicNode(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.label = new JLabel(name);
		this.label.setBounds(x - 40, y - 20, 80, 20);
	}
	
	public GraphicNode(String name) {
		this.x = 0;
		this.y = 0;
		this.name = name;
		this.label = new JLabel(name);
		this.label.setBounds(0, 0, 80, 20);
	}
	
    public void draw(Graphics g, boolean visiting) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);

        if (visiting) {
        	g2d.setColor(Color.RED);
		} else {
			g2d.setColor(Color.GRAY);
		}
        
        g2d.fill(circle);
    }

	public static GraphicNode getGraphicNodeInCommon(ArrayList<GraphicNode> arrayList, ArrayList<GraphicNode> arrayList2) {
		for(GraphicNode gNode1 : arrayList) {
			for(GraphicNode gNode2 : arrayList2) {
				if (gNode1.getName().equalsIgnoreCase(gNode2.getName())) {
					return gNode2;
				}
			}
		}
		return null;
	}
}
