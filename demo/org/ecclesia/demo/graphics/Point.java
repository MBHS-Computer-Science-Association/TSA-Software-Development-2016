package org.ecclesia.demo.graphics;

import java.awt.Graphics;

public class Point implements Drawable {
	final static int size = 10;
	int x;
	int y;

	/**
	 * Instantiates the point
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws the Point
	 */
	@Override
	public void draw(Graphics g) {
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}
}
