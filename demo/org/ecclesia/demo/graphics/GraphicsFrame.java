package org.ecclesia.demo.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsFrame extends JPanel {
	static List<Drawable> drawables = new ArrayList<>();

	/**
	 * Adds UserInput to JPanel 
	 */
	public GraphicsFrame() {
		addMouseListener(new UserInput());
	}

	@Override
	/**
	 * Paints all drawables
	 */
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < drawables.size(); i++) {
			drawables.get(i).draw(g);
		}
		repaint();
	}

	/**
	 * Adds Drawable d to drawables list
	 * @param d
	 */
	public static void addDrawable(Drawable d) {
		drawables.add(d);
	}
}