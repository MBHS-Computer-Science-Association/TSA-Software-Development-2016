package org.ecclesia.demo;

public class Point {
	private int x;
	private int y;
	
	/**
	 * returns value of x
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * returns value of y
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the x and y paramenters to input
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
