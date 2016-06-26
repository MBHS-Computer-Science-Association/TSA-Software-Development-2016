package org.ecclesia.demoLines;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ecclesia.neural.Network;

public class LinePredictor {

	private Network net;

	private List<Point> points;
	private Renderer renderer;
	
	public LinePredictor() {
		net = new Network(2, 2, 1, 2);
		points = new ArrayList<>();
		
		renderer = new Renderer(this);
	}
	
	/**
	 * Initializes a Point at a given X and Y coordinate, calls processing code
	 */
	public void initPoint(int x, int y) {
		points.add(new Point(x, y, Color.RED));
		processPoints();
		renderer.addDrawable(new UserPoint(x, y));
	}

	/**
	 * If there is one Point, then calls Neural Network to predict 2nd point
	 * else, clear the point list and train Neural Network
	 */
	private void processPoints() {
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
			renderer.addDrawable(new PredictedPoint(preX, preY));
		} else {
			points.clear();
			renderer.clearDrawables();
		}
	}
	
	/**
	 * Accesses this simulation's graphical renderer panel
	 * @return renderer
	 */
	protected Renderer getRenderer() {
		return renderer;
	}
}
