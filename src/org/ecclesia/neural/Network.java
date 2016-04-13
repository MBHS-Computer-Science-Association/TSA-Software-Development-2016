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
	 * Generates a new Neural network based on a parent Neural Network with some
	 * mutations
	 * 
	 * @param parent
	 */
	public Network(Network parent) {
		Neuron[][] parentNetwork = parent.getNetwork();
		network = new Neuron[parentNetwork.length][0];
		for (int i = 0; i < network.length; i++) {
			network[i] = new Neuron[parentNetwork[i].length];
			for (int n = 0; n < network[i].length; n++) {
				network[i][n] = new Neuron(parentNetwork[i][n]);
			}
		}
	}

	/**
	 * returns the matrix of Neurons
	 * 
	 * @return network
	 */
	public Neuron[][] getNetwork() {
		return network;
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
