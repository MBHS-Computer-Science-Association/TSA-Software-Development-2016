package org.ecclesia.demoLines;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.ecclesia.neural.Network;

public class LinePredictor {

	private Network net;

	private List<Point> userPoints;
	private Renderer renderer;

	public LinePredictor() {
		net = new Network(2, 2, 3, 2);
		userPoints = new LinkedList<>();
		renderer = new Renderer(this);
	}

	/**
	 * Initializes a Point at a given X and Y coordinate, calls processing code
	 */
	public void inputPoint(Point userPoint) {
		userPoints.add(userPoint);
		processPoints();
	}

	/**
	 * If there is one Point, then calls Neural Network to predict 2nd point
	 * 
	 * else, clear the point list and train Neural Network
	 */
	private void processPoints() {
		if (userPoints.size() == 1) {
			Point one = userPoints.get(0);
			float[] input = { one.getX(), one.getY() };
			this.input = input;
			float[] predictedOutput = net.getOutput(input);
			this.output = predictedOutput;

			System.out.println("Processing Points:");
			System.out.println("\tInput Point: ");
			System.out.println("\t\t" + Arrays.toString(input));
			System.out.println("\tOutput Point:");
			System.out.println("\t\t" + Arrays.toString(predictedOutput));

			// Predictions for the point.
			float predX = predictedOutput[0];
			float predY = predictedOutput[1];
			Point prediction = new Point(predX, predY, Point.PREDICTED);

			// Tells the renderer to render the predicted point.
			renderer.addPoint(prediction);

		} else if (userPoints.size() == 2) {
			// Receives point and loads into the float array.
			Point two = userPoints.get(1);
			float[] desired = { two.getX(), two.getY() };
			this.desired = desired;

			// Helps with debugging for now.
			System.out.println("\tDesired Point:");
			System.out.println("\t\t" + Arrays.toString(desired));

			// Feeds in the input vs the desired and will correct
			// based on the error.
			net.backPropagation(input, desired);
		} else {
			// Set all coordinate arrays to null to
			// prepare for a new case of input.
			input = null;
			output = null;
			desired = null;

			userPoints.clear();
			renderer.clearDrawables();
		}
	}

	/**
	 * Accesses this simulation's graphical renderer panel
	 * 
	 * @return renderer
	 */
	protected Renderer getRenderer() {
		return renderer;
	}

	private float[] input;
	private float[] output;
	private float[] desired;

	/**
	 * Allows data statistics to be displayed on the control panel and allows
	 * for analysis during debugging.
	 * 
	 * @return data two-dimensional array holding three coordinate arrays:
	 *         input, output, and desired. Each coordinate array holds two
	 *         floats: [x, y].
	 */
	public float[][] getCoordsData() {
		float[][] data = { input, output, desired };
		return data;
	}
}
