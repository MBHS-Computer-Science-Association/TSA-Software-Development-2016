package org.ecclesia.demoLines;

import java.awt.Color;

public class UserPoint extends Point {
	final static Color color = Color.GREEN;

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
