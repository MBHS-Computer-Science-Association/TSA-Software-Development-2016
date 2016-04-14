package org.ecclesia.neural.util;

/**
 * @author Trevor Nguyen
 *
 */
public class Mathematics {

	/**
	 * Gets an output based on a Sigmoid function
	 * 
	 * @return Sigmoid Value of v
	 */
	public static float getSigmoidValue(float v) {
		return (float) ((Math.pow(Math.E, v)  + Math.pow(Math.E, v))/ (Math.pow(Math.E, v) + Math.pow(Math.E, -v)));
	}
	
}