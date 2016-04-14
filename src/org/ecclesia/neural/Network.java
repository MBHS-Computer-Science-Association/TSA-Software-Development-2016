package org.ecclesia.neural;

import org.ecclesia.neural.util.Mathematics;

/**
 * @author s201500
 *
 */
public class Network {
	/**
	 * Two dimensional array that stores the perceptron neurons of a discrete network
	 * Has package level visibility for restricted encapsulation.
	 */
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
		network = new Neuron[numHidden + 1][0];
		network[0] = new Neuron[inputWidth];
		for (int i = 1; i < numHidden+1; i++) {
			network[i] = new Neuron[hiddenWidth];
		}

		for (int i = 0; i < network.length - 1; i++) {

			for (int n = 0; n < network[i].length; n++) {
				network[i][n] = new Neuron(hiddenWidth);
			}
		}
		
		for (int i = 0; i < network[network.length - 1].length; i++) {
			network[network.length - 1][i] = new Neuron(outputWidth);
		}
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
		for (int i = 0; i < input.length; i++) {
			network[0][i].addInput(input[i]);
		}
		for (int i = 0; i < network.length - 1; i++) {
			for (int n = 0; n < network[i].length; n++) {
				float[] localOutput = network[i][n].getOutput();
				for (int j = 0; j < localOutput.length; j++) {
					network[i + 1][j].addInput(localOutput[j]);
				}
			}
		}

		float[] output = new float[network[network.length - 1][0].getWeights().length];
		for (int i = 0; i < network[network.length - 1].length; i++) {
			float[] localOutputs = network[network.length - 1][i].getOutput();
			for (int n = 0; n < localOutputs.length; n++) {
				output[n] += localOutputs[n];
			}
		}
		for (int i = 0; i < output.length; i++) {
			output[i] = Mathematics.getSigmoidValue(output[i]);
		}

		return output;
	}
}
