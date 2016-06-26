package org.ecclesia.demoLines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Renderer extends JPanel {
	private List<Drawable> drawables = new ArrayList<>();

	LinePredictor linePredictor;
	
	/**
	 * Bases the graphics frame off of a simulation object
	 * Adds user input capabilities to JPanel
	 */
	public Renderer(LinePredictor linePredictor) {
		this.linePredictor = linePredictor;
		
		setupListener();
	}

	/**
	 * Sets up the mouse listener for the window.
	 */
	public void setupListener() {
		this.addMouseListener(new MouseAdapter() {
			/**
			 * Runs on release of either mouse button Adds a new point to the
			 * list
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				addDrawable(new UserPoint(x, y));
				linePredictor.initPoint(x, y);
			}
		});
		
		/**
		 * Refreshes at 60 FPS
		 */
		new Timer(1000 / 60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
			
		}).start();
	}

	/**
	 * Paints all drawables
	 */
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < drawables.size(); i++) {
			drawables.get(i).draw(g);
		}
	}

	/**
	 * Adds an item to the rendering list
	 * 
	 * @param d entity that will be rendered
	 */
	public void addDrawable(Drawable d) {
		drawables.add(d);
	}

	/**
	 * Removes all items from the renderingList
	 */
	public void clearDrawables() {
		drawables.clear();
	}
}