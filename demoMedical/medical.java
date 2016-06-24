import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import org.ecclesia.neural.Network;

public class medical {
	static int[][] dat = new int[306][4];

	public static void main(String args[]) throws FileNotFoundException {
		Scanner scanny = new Scanner(new File("./demo3/haberman.data.txt"));
		for (int i = 0; i < 306; i++) {
			for (int k = 0; k < 4; k++) {
				dat[i][k] = scanny.nextInt();
			}
		}
		Network n = new Network(3, 3, 2, 2);
		int index = 1;
		while (true) {
			float[][] data = getData();
			int score = getScore(n, data);
			for (int i = 0; i < 1; i++) {
				// Network n2 = new Network(3,3,2,2);
				Network n2 = new Network(n);
				int score2 = getScore(n2, data);
				if (score2 > score) {
					n = n2;
				}
			}
			float[][] newData = getData();
			System.out.println(index + ": " + getScore(n, newData));
			index++;
		}
	}

	static Random randy = new Random();
	final static int amtData = 100;

	public static float[][] getData() {
		float[][] data = new float[amtData * 2 + 1][3];
		for (int i = 0; i < amtData; i++) {
			int r = randy.nextInt(dat.length);
			//r = i;
			data[i][0] = (dat[r][0] - 30) / 60.0f;
			data[i][1] = (dat[r][1] - 58) / 12.0f;
			data[i][2] = (dat[r][2]) / 20.0f;
			data[i + amtData] = new float[1];
			data[i + amtData][0] = dat[r][3];
		}
		return data;
	}

	public static int getScore(Network n, float[][] data) {
		int score = 0;
		for (int i = 0; i < amtData; i++) {
			float[] out = n.getOutput(data[i]);
			if ((out[0] > out[1]) == (data[amtData + 1][0] == 1.0f)) {
				score++;
			}
		}
		return score;
	}
}
