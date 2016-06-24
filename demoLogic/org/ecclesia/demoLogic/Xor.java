package org.ecclesia.demoLogic;

import org.ecclesia.neural.Network;

/**
 * 1 is true, 0 is false
 * 
 * @author CJ Duffee
 *
 */
public class Xor {
	static Network n;

	public static void main(String args[]) {
		n = new Network(2, 4, 2, 2, true);
		float sucessRate = 0;
		int index = 0;
		int in = 0;
		float[][][] testCases = new float[4][2][0];
		testCases[in][0] = new float[]{-1f,-1f};
		testCases[in++][1] = new float[] {1,0};
		
		testCases[in][0] = new float[]{-1f,1f};
		testCases[in++][1] = new float[]{1,0};
		
		testCases[in][0] = new float[]{-1f,1f};
		testCases[in++][1] = new float[]{0,1};
		
		testCases[in][0] = new float[]{1f,1f};
		testCases[in++][1] = new float[]{0,1};

		
		boolean learning = true;
		while (learning) {
			System.out.println("loop");
			learning = n.greedAlhorithm(testCases);
		}
		sucessRate = getSucessRate();
		System.out.println(sucessRate);

	}

	static float getSucessRate() {
		float right = 0;
		for (int i = 0; i <= 1; i++) {
			for (int k = 0; k <= 1; k++) {
				float[] input = { i, k };
				float[] output = n.getOutput(input);
				System.out.println(i + " " + k + " " + output[0] + " " +output[1]);
				if (output[1] >= output[0] && (i ^ k) == 1) {
					right++;
				}
				if (output[1] <= output[0] && (i ^ k) == 0) {
					right++;
				}
			}
		}
		return (float) right / 4.0f;
	}

}
