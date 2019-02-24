package fr.mrwormsy.busnetwork.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class GraphicNode {

	 int x, y, width, height;

	    public GraphicNode(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }
	
}
