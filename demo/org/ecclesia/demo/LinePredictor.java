package org.ecclesia.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ecclesia.demo.graphics.GraphicsFrame;
import org.ecclesia.demo.graphics.PredictedPoint;
import org.ecclesia.demo.graphics.UserPoint;
import org.ecclesia.neural.Network;

public class LinePredictor {

	static Network net = new Network(2, 2, 1, 2);

	static List<Point> points = new ArrayList<>();

	/**
	 * Initializes a Point at a given X and Y coordinate, calls processing code
	 */
	public static void initPoint(int x, int y) {
		points.add(new Point(x, y));
		processPoints();
		GraphicsFrame.addDrawable(new UserPoint(x, y));
	}

	/**
	 * If there is one Point, then calls Neural Network to predict 2nd point
	 * else, clear the point list and train Neural Network
	 */
	private static void processPoints() {
		if (points.size() == 1) {
			float[] pointer = new float[2];
			Point o = points.get(0);
			pointer[0] = (float) o.getX() / 1000;
			pointer[1] = (float) o.getY() / 1000;
			float[] predictedOutput = net.getOutput(pointer);
			int preX = (int) (predictedOutput[0] * 550);
			int preY = (int) (predictedOutput[1] * 550);

			System.out.println(
					Arrays.toString(pointer) + " " + Arrays.toString(predictedOutput) + " " + preX + " " + preY);
			GraphicsFrame.addDrawable(new PredictedPoint(preX, preY));
		} else {
			points.clear();
			GraphicsFrame.clearDrawables();
		}
	}
}
