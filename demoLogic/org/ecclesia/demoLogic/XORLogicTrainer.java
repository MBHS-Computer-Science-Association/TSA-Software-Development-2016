package org.ecclesia.demoLogic;

import org.ecclesia.neural.Network;

/**
 * 1 is true, 0 is false
 * 
 * @author Christian Duffee
 *
 */
public class XORLogicTrainer {
	private Network n;
	private float[] output = new float[4];
	
	public XORLogicTrainer() {
	}
	
	public float getSucessRate() {
		float right = 0;
		for (int i = 0; i <= 1; i++) {
			for (int k = 0; k <= 1; k++) {
				float[] input = { i, k };
				float[] output = n.getOutput(input);
				System.out.println(i + " " + k + " " + output[0]);
				if (output[0] >= .5f && (i ^ k) == 1) {
					right++;
				}
				if (output[0] <= .5f && (i ^ k) == 0) {
					right++;
				}
			}
		}
		return (float) right / 4.0f;
	}

	/**
	 * Trains the network on all possible xor inputs to the network
	 * 
	 * @return the output of the network in this order (0,0), (0,1), (1,0),
	 *         (1,1)
	 */
	public void trainXor() {
		n = new Network(2, 4, 1, 1, true);
		int in = 0;
		float[][][] testCases = new float[4][2][0];
		testCases[in][0] = new float[] { 0, 0 };
		testCases[in++][1] = new float[] { 0 };

		testCases[in][0] = new float[] { 0, 1f };
		testCases[in++][1] = new float[] { 1 };

		testCases[in][0] = new float[] { 1f, 0 };
		testCases[in++][1] = new float[] { 1 };

		testCases[in][0] = new float[] { 1f, 1f };
		testCases[in++][1] = new float[] { 0 };
		n.setAllWeightsToOne();
		n.bruteForceWeightImprovement(testCases);
		for (int i = 0; i < output.length; i++) {
			output[i] = n.getOutput(testCases[i][0])[0];
		}
	}
	
	/**
	 * Returns the output based on XOR
	 * @return the output 
	 */
	public float[] getOutput() {
		return output;
	}
}
