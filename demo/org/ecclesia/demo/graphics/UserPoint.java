package org.ecclesia.demo.graphics;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.color.*;

public class UserPoint extends Point {
	final static int size = 10;
	final static Color color = Color.GREEN;
	int x;
	int y;

	/**
	 * Instantiates the point
	 * 
	 * @param x
	 * @param y
	 */
	public UserPoint(int x, int y) {
		super(x, y, color);
	}
}
