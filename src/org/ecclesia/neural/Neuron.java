package org.ecclesia.neural;

import java.util.Random;

/**
 * 
 * @author Sammy Shin, Trevor Thai Kim Nguyen, Christian Duffee
 *
 */
public class Neuron {
	Axom[] output;

	/**
	 * Initially Generates Network
	 * 
	 * @param width
	 */
	public Neuron(int width, int levels, int outputWidth) {
		if (levels != 0) {
			output = new Axom[width];
		} else {
			output = new Axom[outputWidth];
		}
		Random randy = new Random();
		for (int i = 0; i < output.length; i++) {
			output[i] = new Axom(randy.nextFloat(),
					outputWidth != 0 ? new Neuron(width, levels - 1, outputWidth) : null);
		}
	}
}