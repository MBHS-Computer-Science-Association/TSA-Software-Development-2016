package org.ecclesia.demo;

import java.util.ArrayList;
import java.util.List;

import org.ecclesia.demo.graphics.GraphicsFrame;
import org.ecclesia.demo.graphics.UserPoint;

public class LinePredictor {

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
			// TODO call Neural Network Prediction Code
		} else {
			// TODO Train Neural Network
			points.clear();
			GraphicsFrame.clearDrawables();
		}
	}
}
