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
	final float backpropagationLearningRate = 0.25f;
	final float greedyAlgorithmRate = 0.1f;
	boolean allowsNegativeWeights;

	/**
	 * Generates a new Random Neural Network
	 * 
	 * @param inputWidth
	 * @param hiddenWidth
	 * @param numHidden
	 * @param outputWidth
	 */
	public Network(int inputWidth, int hiddenWidth, int numHidden, int outputWidth) {
		this(inputWidth, hiddenWidth, numHidden, outputWidth, false);
	}

	/**
	 * 
	 * @param inputWidth
	 * @param hiddenWidth
	 * @param numHidden
	 * @param outputWidth
	 * @param allowsNegativeWeights
	 */
	public Network(int inputWidth, int hiddenWidth, int numHidden, int outputWidth, boolean allowsNegativeWeights) {
		this.allowsNegativeWeights = allowsNegativeWeights;
		network = new Neuron[numHidden + 1][0];
		network[0] = new Neuron[inputWidth]; // creates input Neuron row
		for (int i = 1; i <= numHidden; i++) { // creates hidden Neuron rows
			network[i] = new Neuron[hiddenWidth];
		}

		for (int i = 0; i < network.length - 1; i++) {
			for (int n = 0; n < network[i].length; n++) {
				network[i][n] = new Neuron(hiddenWidth, allowsNegativeWeights);
			}
		}

		for (int i = 0; i < network[network.length - 1].length; i++) {
			network[network.length - 1][i] = new Neuron(outputWidth, allowsNegativeWeights);
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
		output = new float[network[network.length - 1][0].getWeights().length];
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
		fillNetwork(input);
		return output;
	}

	/**
	 * Fills the network
	 * 
	 * @param input
	 */
	private void fillNetwork(float[] input) {
		reset();
		for (int i = 0; i < input.length; i++) {
			network[0][i].addInput(input[i]);
		}
		for (int i = 0; i < network.length - 1; i++) {
			for (int n = 0; n < network[i].length; n++) {
				float[] localOutput = network[i][n].getOutputs();
				/**
				 * Only apply activation function if it is a hidden neuron
				 * Excludes Input neurons
				 */
				if (n != 0) {
					for (int j = 0; j < localOutput.length; j++) {
						localOutput[j] = localOutput[j];
					}
				}
				for (int j = 0; j < localOutput.length; j++) {
					network[i + 1][j].addInput(localOutput[j]);
				}
			}
		}

		for (int i = 0; i < network[network.length - 1].length; i++) {
			float[] localOutputs = network[network.length - 1][i].getOutputs();
			for (int n = 0; n < localOutputs.length; n++) {
				output[n] += localOutputs[n];
			}
		}
		for (int i = 0; i < output.length; i++) {
			// output[i] = activFunc(output[i]);
			// output[i] = truent(output[i]);
		}
	}

	/**
	 * Applies the backpropagation algorithm to the data
	 * 
	 * @param input
	 * @param expectedOutput
	 */
	public void backPropagation(float[] input, float[] expectedOutput) {
		fillNetwork(input);
		int row = network.length - 1; // row of neurons before the output
		for (int c = 0; c < network[row].length; c++) {
			Neuron n = network[row][c];
			for (int o = 0; o < output.length; o++) {
				float outputError = expectedOutput[o] - output[o];
				// outputError = outputError * outputError;
				float change = backpropagationLearningRate * -outputError * n.getRawOutput() * activFunc(output[o])
						* (1 - activFunc(output[o]));
				float[] weights = n.getWeights();
				weights[o] -= change;
				weights[o] = truent(weights[o]);
			}
		}

		row = network.length - 2; // row of neurons 2 before the output
		for (int c = 0; c < network[row].length; c++) {
			Neuron n = network[row][c];
			for (int o = 0; o < output.length; o++) {
				float outputError = expectedOutput[o] - output[o];
				// outputError = outputError * outputError;
				for (int c2 = 0; c2 < n.getWeights().length; c2++) {
					float change = backpropagationLearningRate * -outputError * n.getRawOutput()
							* network[row + 1][c2].getRawOutput() * (1 - network[row + 1][c2].getRawOutput());
					float[] weights = n.getWeights();
					weights[c2] -= change;
					weights[c2] = truent(weights[c2]);
				}
			}
		}
	}

	/**
	 * Improves the Neural Network using a bruteforce algorithm
	 * 
	 * @param testCases
	 * @return true if there was an improvement, false otherwise
	 */
	public boolean greedAlhorithm(float[][][] testCases) {
		boolean anyImprovment = false;
		for (int r = 0; r < network.length; r++) {
			for (int c = 0; c < network[r].length; c++) {
				Neuron n = network[r][c];
				float[] weights = n.getWeights();
				for (int w = 0; w < weights.length; w++) {
					boolean negative = false;
					boolean solutionFound = false;
					do {
						float oldWeight = weights[w];
						float newWeight = truent(weights[w] + greedyAlgorithmRate * (negative ? -1 : 1));
						// prevents calculations if weight hasn't changed
						boolean improvement = newWeight != oldWeight;
						float totalError = 0;
						float totalNewError = 0;
						for (int t = 0; t < testCases.length && improvement; t++) {
							float[] testInput = testCases[t][0];
							float[] testOutput = testCases[t][1];
							fillNetwork(testInput);
							float error = getTotalError(output, testOutput);
							weights[w] = newWeight;
							fillNetwork(testInput);
							float newError = getTotalError(output, testOutput);
							totalError+=error;
							totalNewError +=newError;
							weights[w] = oldWeight;
						}
						if (totalNewError<totalError) {
							System.out.println("boom");
							weights[w] = newWeight;
							solutionFound = true;
							anyImprovment = true;
						}
						negative = !negative;
					} while (negative && !solutionFound);
				}
			}
		}
		return anyImprovment;
	}

	/**
	 * 
	 * @param output
	 * @param expectedOutput
	 * @return the total error rating between the two or -1 if there is an error
	 */
	public float getTotalError(float[] output, float[] expectedOutput) {
		if (output.length != expectedOutput.length) {
			return -1;
		}
		float error = 0;
		for (int i = 0; i < output.length; i++) {
			error += Math.abs(output[i] - expectedOutput[i]);
		}
		return error;
	}

	/**
	 * Resets all the Neurons of the network
	 */
	public void reset() {
		for (int r = 0; r < network.length; r++) {
			for (int c = 0; c < network[r].length; c++) {
				network[r][c].resetInput();
			}
		}
		Arrays.fill(output, 0);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public float truent(float v) {
		v = Math.min(v, 1f);
		if (allowsNegativeWeights) {
			return Math.max(-1, v);
		} else {
			return Math.max(0, v);
		}
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
}
