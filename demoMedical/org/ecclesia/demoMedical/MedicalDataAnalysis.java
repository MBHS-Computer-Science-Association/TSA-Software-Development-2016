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
	int[][] dat = new int[306][4];

	public MedicalDataAnalysis() {		
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
		
		Network network = new Network(3, 3, 2, 1, true);
		float[][] data = getData();
		float[][] newData = getData();
		float[][][] testCases = new float[amtData][2][3];
		for(int i=0; i<testCases.length; i++) {
			testCases[i][0] = data[i];
			testCases[i][1] = data[i+amtData];
		}
		boolean learning = true;
		while(learning) {
//			System.out.println('l');
			learning = network.greedyAlgorithmWeightImprovment(testCases);
		}
//		System.out.println(getScore(network,data));
		System.out.println(getScore(network,newData));
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

	public int getScore(Network n, float[][] data) {
		int score = 0;
		for (int i = 0; i < amtData; i++) {
			float[] out = n.getOutput(data[i]);
			if ((out[0] > 0.5f) == (data[amtData + i][0] == 1.0f)) {
				score++;
			}
		}
		return score;
	}
	
	/**
	 * Invoked when the user presses the train button.
	 */
	public void train() {
		
	}
	
	/**
	 * Invoked when the user presses the test button.
	 */
	public void test() {
		
	}
	
	/**
	 * Notifies the GUI of the accuracy of the program.
	 * @return array 100 x 100 boolean array representing accuracy
	 */
	public boolean[][] getAccuracyData() {
		boolean[][] array = null;
		return array;
	}
}
