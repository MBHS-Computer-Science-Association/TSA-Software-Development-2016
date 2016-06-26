package org.ecclesia.demoSurvival.entities;

import java.util.Random;

import org.ecclesia.neural.Network;

public class Malish extends Animal {
	static Random random = new Random();
	final static int inputWidth = 3;
	final static int intermediateWidth = 3;
	final static int numIntermediate = 1;

	private static boolean backPropagationEnabled = false;
	
	public static int getOutputwidth() {
		return outputWidth;
	}

	public Network getNet() {
		return net;
	}

	final static int outputWidth = 3;
	final static float heathDepletion = 0.001f;
	final static float turnSpeed = (float) (Math.PI / 8);
	final static float moveSpeed = 1f;
	final static int vision = 100;
	Network net;
	private int generation;

	/**
	 * Instantiates the neural network toolkit and appends it to the creatures
	 */
	public Malish(float x, float y) {
		super((float) (random.nextFloat() * Math.PI * 2), x, y);
		net = new Network(inputWidth, intermediateWidth, numIntermediate, outputWidth);
		super.setX(x);
		super.setY(y);
		super.setHealth(1f);
		generation = 1;
	}

	public Malish(Malish mother) {
		super();
		net = new Network(mother.getNet());
		super.setX(mother.getX());
		super.setY(mother.getY());
		super.adjustAngle((float) (random.nextFloat() * Math.PI * 2));
		super.setHealth(1f);
		generation = mother.generation + 1;
	}

	/**
	 * Changes the Malish's condition Ran each Tick Returns True if Dead,
	 * 
	 * @return true if dead or false if alive
	 */
	public boolean move(float[] input) {
		boolean debug = false;

		super.move(moveSpeed);
		float[] output = net.getOutput(input);
		int greatest = 0;
		float greatestValue = 0.0f;
		for (int i = 0; i < output.length; i++) {
			if (!debug) {
				if (output[i] > greatestValue) {
					greatest = i;
					greatestValue = output[i];
				}
			} else {
				if (input[i] > greatestValue) {
					greatest = i;
					greatestValue = input[i];
				}
			}
		}

		if (backPropagationEnabled)
			net.backPropagation(input, input);

		if (greatest == 0) {
			// System.out.println("left");
			super.adjustAngle((float) -turnSpeed);
		} else if (greatest == 2) {
			// System.out.println("right");
			super.adjustAngle((float) turnSpeed);
		}
		if (super.depleteHealth(heathDepletion)) {
			return true;
		}
		return false;
	}

	public int getGeneration() {
		return generation;
	}
	
	public static void setBackPropagation(boolean value) {
		backPropagationEnabled = value;
	}
	
	public static boolean getBackPropagationEnabled() {
		return backPropagationEnabled;
	}
}