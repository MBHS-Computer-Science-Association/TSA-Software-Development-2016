package org.ecclesia.demoLines;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.ecclesia.neural.Network;

public class LinePredictor {

	private Network net;

	private List<Point> userPoints;
	private Renderer renderer;

	public LinePredictor() {
		generateNetwork();
		userPoints = new LinkedList<>();
		renderer = new Renderer(this);
	}

	ArrayList<Float[][]> testCases = new ArrayList<>();

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
			Point inputPoint = userPoints.get(0);
			float[] input = { inputPoint.getX(), inputPoint.getY() };
			this.input = input;

			float[] predictedOutput = null;

			predictedOutput = net.getOutput(input);

			this.output = predictedOutput;

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
			Float[][] testCase = new Float[2][0];
			Float[] newInput = new Float[input.length];
			for(int i=0; i<input.length; i++) {
				newInput[i] = input[i];
			}
			Float[] newDesired = new Float[input.length];
			for(int i=0; i<desired.length; i++) {
				newDesired[i] = desired[i];
			}
			testCase[0] = newInput;
			testCase[1] = newDesired;
			testCases.add(testCase);
			// Feeds in the input vs the desired and will correct
			// based on the error.
			for (int i = 0; i < 1000; i++) {
				for (int k = 0; k < testCases.size(); k++) {
					try {
						Thread.sleep(0, 100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					try{
						Float[] objInput = testCases.get(k)[0];
						Float[] objOutput = testCases.get(k)[1];
						float[] in = new float[objInput.length];
						for (int j = 0; j < objInput.length; j++) {
							in[j] = objInput[j];
						}
						float[] out = new float[objOutput.length];
						for (int j = 0; j < objOutput.length; j++) {
							out[j] = objOutput[j];
						}
						net.backPropagation(in, out);						
					} catch (IndexOutOfBoundsException e) {
						// Do nothing.
						System.out.print("");
					}
				}
			}
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
	 * Generates a new neural network.
	 */
	public void generateNetwork() {
		net = new Network(2, 2, 2, 2, true);
		testCases.clear();
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
