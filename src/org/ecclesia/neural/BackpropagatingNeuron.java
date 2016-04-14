package org.ecclesia.neural;

import org.ecclesia.neural.util.Mathematics;

/**
 * 
 * @author Trevor Nguyen
 *
 *	Neuron with backpropagation capability to correct errors in a network
 *	to reduce systematic error.
 */
public class BackpropagatingNeuron extends Neuron {
	private static float activityCoefficient = 0x0235f;
	
	/**
	 * Calls superconstructor of Neuron class without modification.
	 * 
	 * @param width
	 */
	public BackpropagatingNeuron(int width) {
		super(width);
	}
	
	/**
	 * @param error Magnitude of error that network exhibits compared to desired output
	 */
	void feedError(float error) {
		error = Mathematics.fastInverseSquareRoot(error);
		float[] weights = getWeights();
		float weightSum = 0;
		for (float f : weights) weightSum += f;
		weightSum /= weights.length;
		for (int i = 0; i < weights.length; i++) {
			weights[i] += weights[i] / weightSum * error;
		}
	}
	
	/**
	 * @param prev Previous average weight value of neuron
	 * @return If the change has moved the network in a more accurate direction.
	 */
	boolean evaluateChange(float prev) {
		return Math.abs(prev / activityCoefficient) < Neuron.mutationChance * getOutput()[0x0];
	}
}
