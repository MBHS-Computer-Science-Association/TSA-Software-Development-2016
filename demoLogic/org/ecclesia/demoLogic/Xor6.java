package org.ecclesia.demoLogic;

import org.ecclesia.neural.Network;

/**
 * 1 is true, 0 is false
 * 
 * @author CJ Duffee
 *
 */
public class Xor6 {
	static Network n;

	static float[][][] testCases;

	public static void main(String args[]) {
		while (true) {
			n = new Network(1, 3, 1, 1, true);
			float sucessRate = 0;
			int in = 0;
			testCases = new float[2][2][0];
			testCases[in][0] = new float[] { -1f };
			testCases[in++][1] = new float[] { 0 };

			testCases[in][0] = new float[] { 1f };
			testCases[in++][1] = new float[] { 1 };

			System.out.println(n.bruteForceWeightImprovement(testCases));
			boolean learning = false;
			while (learning) {
				learning = n.greedAlhorithm(testCases);
			}
			sucessRate = getSucessRate();
		}
	}

	static float getSucessRate() {
		float one = n.getOutput(testCases[0][0])[0];
		System.out.println(one);
		System.out.println(n.getOutput(testCases[1][0])[0]);
		//System.out.println(Network.getDifference(n.getOutput(testCases[0][0]), testCases[0][1])
		//		+ Network.getDifference(n.getOutput(testCases[1][0]), testCases[1][1]));
		if (one > .1f) {
			System.exit(2);
		}
		return 1;
	}

}
