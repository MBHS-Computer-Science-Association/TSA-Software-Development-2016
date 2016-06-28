package org.ecclesia.neural;

import java.util.Random;

import org.ecclesia.neural.util.Mathematics;
/**
 * 
 * @author Sammy Shin, Trevor Thai Kim Nguyen, Christian Duffee
 *
 */
public class Neuron {
	/**
	 * The frequency factor that the neuron will mutate
	 */
	final static float mutationChance = 0.10f;
	/**
	 * Arbitrary magnitude by which the weights will change.
	 */
	final static float changeFactor = 1.00f;
	boolean allowsNegativeWeights;

	private float[] weights;
	private float input;

	/**
	 * Initially generates a Random Neuron
	 * 
	 * @param width
	 */
	public Neuron(int width, boolean allowsNegativeWeights) {
		this.allowsNegativeWeights = allowsNegativeWeights;
		Random random = new Random();
		weights = new float[width];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = random.nextFloat() * (allowsNegativeWeights && random.nextBoolean() ? -Network.weightsMax : Network.weightsMax);
		}
	}

	/**
	 * Reproduces a Neuron based on Parent with some mutations
	 * 
	 * @param parent
	 */
	public Neuron(Neuron parent) {
		allowsNegativeWeights = parent.getAllowsNegativeWeights();
		Random random = new Random();
		weights = parent.getWeights().clone();
		for (int i = 0; i < weights.length; i++) {
			if (random.nextFloat() <= mutationChance) {
				weights[i] = weights[i] + (random.nextBoolean() ? Network.weightsMax : -Network.weightsMax) * changeFactor * random.nextFloat();
				weights[i] = truent(weights[i]);
			}
		}
	}

	/**
	 * Resets input down to zero for next iterative cycle
	 */
	public void resetInput() {
		input = 0;
	}

	/**
	 * Adds value to inputs for summation
	 * 
	 * @param value
	 */
	public void addInput(float value) {
		input += value;
	}

	/**
	 * 
	 * @return input
	 */
	public float getInput() {
		return input;
	}

	/**
	 * Gets the Neuron's outputs based on a sigmoid function and then resets the
	 * input
	 * 
	 * @return the output of the neuron including weight calculations
	 */
	public float[] getOutputs() {
		float[] output = new float[weights.length];
		for (int i = 0; i < weights.length; i++) {
			output[i] = activFunc(input) * weights[i];
		}
		return output;
	}

	public float getRawOutput() {
		return activFunc(input);
	}

	/**
	 * @return The array containing the weights of the inputs in order
	 */
	public float[] getWeights() {
		return weights;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public float truent(float v) {
		v = Math.min(v, Network.weightsMax);
		if (allowsNegativeWeights) {
			return Math.max(-Network.weightsMax, v);
		} else {
			return Math.max(0, v);
		}
	}

	/**
	 * 
	 * @return allowsNegativeWeights
	 */
	public boolean getAllowsNegativeWeights() {
		return allowsNegativeWeights;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public float activFunc(float v) {
		if (allowsNegativeWeights) {
			return Mathematics.getSigmoidValue(v);
		} else {
			return Mathematics.getSigmoidValue(v);
		}
	}
	
	/**
	 * Sets weights to zero
	 */
	public void setsWeightsToZero() {
		for(int i=0; i<weights.length; i++) {
			weights[i] = 0;
		}
		
	}
}
