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
	final static float backpropagationLearningRate = 0.25f;
	final static float greedyAlgorithmRate = 0.05f;
	final static float bruteForceLearningRate = 2.5f;
	final static float weightsMax = 10;
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
			output[i] = activFunc(output[i]);
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
							totalError += error;
							totalNewError += newError;
							weights[w] = oldWeight;
						}
						if (totalNewError < totalError) {
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

	private float leastError;
	
	/**
	 * Brute force weight combinations and chooses the one with the least error
	 * 
	 * @param testCases
	 * @return true if any improvment was made, false otherwise
	 */
	public boolean bruteForceWeightImprovement(float[][][] testCases) {
		leastError = 0;
		for(int i=0; i<testCases.length; i++) {
			fillNetwork(testCases[i][0]);
			leastError += getTotalError(output, testCases[i][1]);
		}
		leastError = Float.MAX_VALUE;
		return recursiveBruteForce(testCases,0,0);
	}

	/**
	 * 
	 * @param testCases
	 * @param r
	 * @param c
	 * @return true if any improvement, false otherwise
	 */
	private boolean recursiveBruteForce(float[][][] testCases, int r, int c) {
		boolean last = r == network.length - 1 && c == network[r].length - 1;
		boolean anyImprovement = false;
		Neuron n = network[r][c];
		float[] weights = n.getWeights();
		for (int i = 0; i < weights.length; i++) {
			float oldWeight = weights[i];
			for (float w = (allowsNegativeWeights ? -weightsMax : 0); w <= weightsMax; w += bruteForceLearningRate) {
				weights[i] = w;
				if (last) {
					float newError = 0;
					for (int j = 0; j < testCases.length; j++) {
						fillNetwork(testCases[j][0]);
						newError += getTotalError(output, testCases[j][1]);
					}
					if (newError < leastError) {
						leastError = newError;
						anyImprovement = true;
						oldWeight = w;
					}else {
						weights[i] = oldWeight;
					}
				} else {
					boolean better;
					int nextR = r;
					int nextC = c + 1;
					if (nextC == network[r].length) {
						nextR++;
						nextC = 0;
					}
					better = recursiveBruteForce(testCases, nextR, nextC);
					if (better) {
						oldWeight = w;
						anyImprovement = true;
					} else {
						weights[i] = oldWeight;
					}
				}
			}
		}
		return anyImprovement;
	}

	/**
	 * 
	 * @param output
	 * @param expectedOutput
	 * @return the total error rating between the two or -1 if there is an error
	 */
	public float getTotalError(float[] output, float[] expectedOutput) {
		return getDifference(output, expectedOutput);
	}
	
	/**
	 * 
	 * @param arr
	 * @param arr2
	 * @return the total difference between the arrays
	 */
	public static float getDifference(float[] arr, float[] arr2) {
		if (arr.length != arr2.length) {
			return -1;
		}
		float error = 0;
		for (int i = 0; i < arr.length; i++) {
			error += Math.abs(arr[i] - arr2[i]);
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
		v = Math.min(v, weightsMax);
		if (allowsNegativeWeights) {
			return Math.max(-weightsMax, v);
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
