package org.ecclesia.demo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ecclesia.demo.graphics.GraphicsFrame;
import org.ecclesia.demo.graphics.UserPoint;
import org.ecclesia.neural.Network;

public class LinePredictor {

	static Network net = new Network(2,2,1,2);
	
	static List<Point> points = new ArrayList<>();

	/**
	 * Initializes a Point at a given X and Y coordinate, calls processing code
	 */
	public static void initPoint(int x, int y) {
		points.add(new Point(x, y));
		processPoints();
		GraphicsFrame.addDrawable(new UserPoint(x, y));
		processPoints();
	}

	/**
	 * If there is one Point, then calls Neural Network to predict 2nd point
	 * else, clear the point list and train Neural Network
	 */
	private static void processPoints() {
		if (points.size() == 1) {
			float[] pointss= new float[2];
			for(Point o : points)
			{
			pointss[0] = (float)o.getX();	
			}
			float[] predictedOutput = net.getOutput(pointss);
			System.out.println(Arrays.toString(predictedOutput));
		} else {
			// TODO Train Neural Network
			points.clear();
			GraphicsFrame.clearDrawables();
		}
	}
}
