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
	/**
	 * Stores all of the points in the simulation. Periodically cleared for each
	 * test case.
	 */
	private List<Point> points = new ArrayList<>();

	/**
	 * Stores the simulation logic and variables
	 */
	private LinePredictor linePredictor;

	/**
	 * Bases the graphics frame off of a simulation object Adds user input
	 * capabilities to JPanel
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
			 * Detects mouse input and creates normalized points based on the
			 * relative mouse location within the renderer panel.
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				// Mouse positions.
				int mouseX = e.getX();
				int mouseY = e.getY();

				// Actual dimensions of the component and
				// needed for scaling after resizing.
				int componentWidth = getWidth();
				int componentHeight = getHeight();

				// Creates coordinates by normalizing them on the interval [0,
				// 1]
				// This allows the coordinates to be portable and fed
				// directly into the neural network.
				float normX = (float) mouseX / (float) componentWidth;
				float normY = (float) mouseY / (float) componentHeight;

				// Creates points based on normalized x and y coordinates
				Point userPoint = new Point(normX, normY, Point.USER);

				addPoint(userPoint);

				new Thread(new Runnable() {

					@Override
					public void run() {
						linePredictor.inputPoint(userPoint);
					}

				}).start();
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
	 * Paints all points on the screen.
	 */
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);

		int componentWidth = this.getWidth();
		int componentHeight = this.getHeight();

		for (Point p : points) {
			int x = (int) (p.getX() * componentWidth);
			int y = (int) (p.getY() * componentHeight);
			int diameter = 10;

			switch (p.getType()) {
			case Point.USER:
				g.setColor(Color.BLUE);
				break;
			case Point.PREDICTED:
				g.setColor(Color.RED);
				break;
			case Point.DEBUG:
				g.setColor(Color.MAGENTA);
				break;
			}

			g.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
		}
	}

	/**
	 * Adds an item to the rendering list
	 * 
	 * @param point
	 *            entity that will be rendered
	 */
	public void addPoint(Point point) {
		points.add(point);
	}

	/**
	 * Removes all items from the rendering list
	 */
	public void clearDrawables() {
		points.clear();
	}
}