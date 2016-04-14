package org.ecclesia.neural;

import org.ecclesia.neural.util.Mathematics;

/**
 * @author Trevor Nguyen
 * 
 *         Backpropagating networks are able to correct for error within the
 *         network more accurately than a basic neural network
 */
public class BackpropagatingNetwork extends Network {

	/**
	 * A backpropagating network can not be created without an existing network.
	 * 
	 * @param parent
	 *            An existing Network as a base object
	 */
	public BackpropagatingNetwork(Network parent) {
		super(parent);
	}

	/**
	 * @param input Test inputs for training
	 * @param output Desired outputs for training
	 */
	public void train(float[] input, float[] output) {
		float[] neuralOutputs = getOutput(input);
		if (output.length != neuralOutputs.length)
			throw new ArrayIndexOutOfBoundsException("Array lengths do not quite match up.");
		float cumulativeError = 0;
		for (int i = 0; i < output.length; i++) {
			cumulativeError += Mathematics.square1f(output[i] - neuralOutputs[i]);
		}
		
		BackpropagatingNeuron[][] network = (BackpropagatingNeuron[][]) getNetwork();
		for (BackpropagatingNeuron[] array : network) {
			for (BackpropagatingNeuron neuron : array) {
				neuron.feedError(cumulativeError);
			}
		}
	}
}
