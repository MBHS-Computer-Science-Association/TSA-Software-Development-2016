package org.ecclesia.demoLogic;

import org.ecclesia.neural.Network;

/**
 * 
 * @author CJ Duffee
 *
 */
public class Xor4 {
	static Network n;

	public static void main(String args[]) {
			n = new Network(1, 9, 2, 2, true);
			float[] in1 = { 8f };
			float[] in2 = { -8f };
			float[] z = { 0, 0 };
			float[] ou1 = { .99f, .2f };
			float[] ou2 = { 0.2f, .99f };
			float[][][] testCases  = new float[2][2][0];
			testCases[0][0] = in1;
			testCases[0][1] = ou1;	
			testCases[1][0] = in2;
			testCases[1][1] = ou2;
			System.out.println("*" + n.getOutput(in1)[0] + " " + n.getOutput(in1)[0]);
			boolean improving = true;
			while (improving) {
				improving = n.greedAlhorithm(testCases);
			}
			System.out.println("*" + n.getOutput(in1)[0] + " " + n.getOutput(in1)[1]);
			System.out.println("*" + n.getOutput(in2)[0] + " " + n.getOutput(in2)[1]);
	}
}
