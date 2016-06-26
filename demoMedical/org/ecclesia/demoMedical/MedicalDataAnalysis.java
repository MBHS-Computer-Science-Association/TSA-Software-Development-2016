package org.ecclesia.demoMedical;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import org.ecclesia.neural.Network;

/**
 * 
 * @author Christian Duffee
 */
public class MedicalDataAnalysis {
	boolean[][] board = new boolean[10][10];
	int[][] dat = new int[306][4];

	Network n = new Network(3, 3, 2, 1, true);

	public MedicalDataAnalysis() {
	}

	Random random = new Random();
	final int amtData = 100;

	public float[][] getData() {
		float[][] data = new float[amtData * 2 + 1][3];
		for (int i = 0; i < amtData; i++) {
			int r = random.nextInt(dat.length);
			// r = i;
			data[i][0] = (dat[r][0] - 30) / 60.0f;
			data[i][1] = (dat[r][1] - 58) / 12.0f;
			data[i][2] = (dat[r][2]) / 20.0f;
			data[i + amtData] = new float[1];
			data[i + amtData][0] = dat[r][3];
		}
		return data;
	}

	/**
	 * Fils the sucess board based on Netowrk n and the data
	 * 
	 * @param n
	 * @param data
	 */
	public void fillBoard(Network n, float[][] data) {
		for (int i = 0; i < amtData; i++) {
			float[] out = n.getOutput(data[i]);
			int r = i / 10;
			int c = i % 10;
			if ((out[0] > 0.5f) == (data[amtData + i][0] == 1.0f)) {
				board[r][c] = true;
			} else {
				board[r][c] = false;
			}
		}
	}

	/**
	 * Invoked when the user presses the train button.
	 */
	public void train() {

		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File("demoMedical/habermanData.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 306; i++) {
			for (int k = 0; k < 4; k++) {
				dat[i][k] = fileScanner.nextInt();
			}
		}

		n = new Network(3, 3, 2, 1, true);
		float[][] data = getData();
		float[][][] testCases = new float[amtData][2][3];
		for (int i = 0; i < testCases.length; i++) {
			testCases[i][0] = data[i];
			testCases[i][1] = data[i + amtData];
		}

		boolean learning = true;
		while (learning) {
			learning = n.greedyAlgorithmWeightImprovment(testCases);
		}
	}

	/**
	 * Invoked when the user presses the test button.
	 */
	public void test() {
		float[][] newData = getData();
		fillBoard(n, newData);
	}

	/**
	 * Notifies the GUI of the accuracy of the program.
	 * 
	 * @return array 100 x 100 boolean array representing accuracy
	 */
	public boolean[][] getAccuracyData() {
		return board;
	}
}
