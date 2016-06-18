package org.ecclesia.neural;

import java.util.Arrays;

import org.ecclesia.neural.util.Mathematics;

/**
 * @author Christian Duffee
 *
 */
public class Network {
	/**
	 * Two dimensional array that stores the perceptron neurons of a discrete
	 * network Has package level visibility for restricted encapsulation.
	 */
	Neuron[][] network;
	float[] output;
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
		network[0] = new Neuron[inputWidth]; // creates input Neuron row
		for (int i = 1; i <= numHidden; i++) { // creates hidden Neuron rows
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
		
		output = new float[outputWidth];
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
		output = new float[network[network.length-1][0].getWeights().length];
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
		Arrays.fill(output, 0);
		fillNetwork(input);
		return output;
	}

	/**
	 * Fills the network
	 * 
	 * @param input
	 */
	public void fillNetwork(float[] input) {
		for (int i = 0; i < input.length; i++) {
			network[0][i].addInput(input[i]);
		}
		for (int i = 0; i < network.length - 1; i++) {
			for (int n = 0; n < network[i].length; n++) {
				float[] localOutput = network[i][n].getOutput();
				/**
				 * Only apply activation function if it is a hidden neuron
				 * Excludes Input neurons
				 */
				if (n != 0) {
					for (int j = 0; j < localOutput.length; j++) {
						localOutput[j] = Mathematics.getSigmoidValue(localOutput[j]);
					}
				}
				for (int j = 0; j < localOutput.length; j++) {
					network[i + 1][j].addInput(localOutput[j]);
				}
			}
		}
		
		for (int i = 0; i < network[network.length - 1].length; i++) {
			float[] localOutputs = network[network.length - 1][i].getOutput();
			for (int n = 0; n < localOutputs.length; n++) {
				output[n] += localOutputs[n];
			}
		}
		for (int i = 0; i < output.length; i++) {
			output[i] = Mathematics.getSigmoidValue(output[i]);
		}
	}

	/**
	public void backPropagation(float[] input, float[] expectedOutput) {
		for (int row = network.length - 1; row == network.length - 1; row--) {
			for (int i = 0; i < network[row].length; i++) {
				double ak = n.getOutput();
				double ai = con.leftNeuron.getOutput();
				double desiredOutput = expectedOutput[i];

				double partialDerivative = -ak * (1 - ak) * ai * (desiredOutput - ak);
				double deltaWeight = -learningRate * partialDerivative;
				double newWeight = con.getWeight() + deltaWeight;
				con.setDeltaWeight(deltaWeight);
				con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
			}
		}

	}
	**/

	/**
	 * Resets all the Neurons of the network
	 */
	public void reset() {
		for (int r = 0; r < network.length; r++) {
			for (int c = 0; c < network[r].length; c++) {
				network[r][c].resetInput();
			}
		}
	}
}
