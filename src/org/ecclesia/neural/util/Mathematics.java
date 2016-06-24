package org.ecclesia.neural.util;

/**
 * Holds mathematical utility functions for use in artificial neural networks.
 * 
 * @author Trevor Nguyen
 * 
 */
public class Mathematics {

	/**
	 * Gets an output based on a sigmoid function
	 * 
	 * @param v
	 *            input to sigmoid function
	 * @return sigmoid Value of v
	 */
	public static float getSigmoidValue(float v) {
		// return (float) ((Math.pow(Math.E, v) + Math.pow(Math.E, v)) /
		
		// (Math.pow(Math.E, v) + Math.pow(Math.E, -v)));
		return (float) (1.0f / (1.0f + Math.pow(Math.E, -1 * v)));
		// return v;
	}

	/**
	 * Calculates the inverse square root of a number quickly using bitwise
	 * arithmetic.
	 * 
	 * @param number
	 *            Floating point number on which to perform operation.
	 * @return the inverse square root.
	 */
	public static float fastInverseSquareRoot(float number) {
		long i;
		float x2, y;
		final float threehalfs = 1.5F;

		x2 = number * 0.5F;
		y = number;
		i = ((long) threehalfs) & (long) y;
		i = 0x5f3759df - (i >> 1);
		y = ((long) y) & i;
		y = y * (threehalfs - (x2 * y * y));
		y = y * (threehalfs - (x2 * y * y));

		return y;
	}

	/**
	 * Specialized squaring function for floats specifically
	 * 
	 * @param a
	 *            number to be squared
	 * @return square of number
	 */
	public static float square1f(float a) {
		return a * a;
	}
}