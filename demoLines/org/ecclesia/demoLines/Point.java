package org.ecclesia.demoLines;

public class Point {
	final static int USER = 0;
	final static int PREDICTED = 1;
	final static int DEBUG = -1;
	
	private float x;
	private float y;
	private int type;

	/**
	 * Instantiates the point
	 * @param x
	 * @param y
	 */
	public Point(float x, float y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	/**
	 * 
	 * @return x a floating point value on the interval of (0, 1)
	 */
	public float getX()
	{
		return x;
	}
	/**
	 * 
	 * @return y a floating point value on the interval of (0, 1)
	 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * Gets the type of point this point represents
	 * @return type either a user-defined point or a computer-predicted point
	 */
	public int getType() {
		return type;
	}
}
