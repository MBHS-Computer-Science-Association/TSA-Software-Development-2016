package org.ecclesia.neural;

public class Network {
	Neuron[][] network;

	/**
	 * Generates a new Random Neural Network
	 * 
	 * @param inputWidth
	 * @param hiddenWidth
	 * @param numHidden
	 * @param outputWidth
	 */
	public Network(int inputWidth, int hiddenWidth, int numHidden, int outputWidth) {
		network = new Neuron[numHidden][0];
		network[0] = new Neuron[inputWidth];
		for (int i = 1; i < numHidden; i++) {
			network[i] = new Neuron[hiddenWidth];
		}
		network[network.length - 1] = new Neuron[outputWidth];
	}

	/**
	 * Calculates and returns the Neural Network's output
	 * 
	 * @param input
	 * @return output
	 */
	public float[] getOutput(float[] input) {
		return input;
	}
}
