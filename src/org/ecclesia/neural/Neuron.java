package org.ecclesia.neural;

import java.util.Random;

/**
 * 
 * @author Sammy Shin, Trevor Thai Kim Nguyen, Christian Duffee
 *
 */
public class Neuron {
	final static float mutationChange = 0.05f;

	private float[] weights;
	private float input;

	public float[] getWeights() {
		return weights;
	}

	/**
	 * Initially generates a Random Neuron
	 * 
	 * @param width
	 */
	public Neuron(int width) {
		Random randy = new Random();
		weights = new float[width];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = randy.nextFloat();
		}
	}

	/**
	 * Reproduces a Neuron based on Parent
	 * 
	 * @param parent
	 */
	public Neuron(Neuron parent) {
		weights = parent.getWeights().clone();
		for (int i = 0; i < weights.length; i++) {
			if
		}
	}

	/**
	 * Resets Input
	 */
	private void resetInput() {
		input = 0;
	}

	/**
	 * Adds value to inputs
	 * 
	 * @param value
	 */
	public void addInput(float value) {
		input += value;
	}

	/**
	 * Gets the Neuron's output based on a sigmoid function and then resets the
	 * input
	 */
	public float[] getOutput() {
		float[] output = new float[weights.length];
		for (int i = 0; i < weights.length; i++) {
			output[i] = getSigmoidValue(input * weights[i]);
		}
		resetInput();
		return output;
	}

	/**
	 * Gets an output based on a Sigmoid function
	 * 
	 * @return Sigmoid Value of v
	 */
	float getSigmoidValue(float v) {
		return (float) (1 / (1 + Math.pow(Math.E, -v)));
	}

}