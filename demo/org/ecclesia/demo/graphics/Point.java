package org.ecclesia.demo.graphics;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Point implements Drawable {
	private final static int size = 10;
	private Color color;
	private int x;
	private int y;

	/**
	 * Instantiates the point
	 * @param x
	 * @param y
	 */
	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * Draws the Point
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}
}
