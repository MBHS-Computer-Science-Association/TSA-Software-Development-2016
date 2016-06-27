package org.ecclesia.demoSurvival;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import org.ecclesia.demoSurvival.entities.Food;
import org.ecclesia.demoSurvival.entities.Malish;

/**
 * Stores all of the logic components for the simulation and interfaces with the
 * GUI only through the getter methods of the renderer and the statistics.
 * 
 * @author Christian Duffee, Sammy Shin, Thai Nguyen
 */
public class Environment {
	final static int initialMalish = 5;
	final static int initialFood = 10;
//	final static int clippingDistance = 5000;
	final static int WIDTH = 800;
	final static int HEIGHT = 600;

	final static float foodRegenerationRate = 0.01f;
//	final static int initialPopulation = 10;

	final static float fov = (float) (Math.PI / 8);
	static Random random = new Random();

	private List<Malish> malishList;
	private List<Food> foodList;
	/**
	 * The JPanel rendering object for this simulation.
	 */
	private Renderer renderer;
	/**
	 * Stores the updating clock to allow for automated simulation refreshing.
	 */
	private Timer timer;

	/**
	 * Keeps track of the number of restarts so that it can display it on the
	 * GUI.
	 */
	private int restartCount;
	/**
	 * Keeps track of the number of generations so that it can display it on the
	 * GUI.
	 */
	private int generationCount;

	/**
	 * Initializes the evolutionary simulator graphics window. Instantiates all
	 * lists and necessary objects. Loads food and Malish entities onto the
	 * simulation platform. Creates a timer to manage updates at a default of
	 * 180 ups.
	 * 
	 */
	public Environment() {
		malishList = new LinkedList<>();
		foodList = new LinkedList<>();
		renderer = new Renderer(this);

		for (int i = 0; i < initialFood; i++) {
			foodList.add(new Food(random.nextFloat() * WIDTH, random.nextFloat() * HEIGHT));
		}

		restart();
		restartCount = 0;

		// Creates auto updating cycle.
		// Refreshes 180 times each second.
		timer = new Timer(1000 / 180, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}

		});
	}

	/**
	 * Restarts the simulation with new neural networks. Increments the number
	 * of restarts counter by 1.
	 */
	public void restart() {
		malishList.clear();

		for (int i = 0; i < initialMalish; i++) {
			float x = random.nextFloat() * WIDTH;
			float y = random.nextFloat() * HEIGHT;
			Malish m = new Malish(x, y);
			malishList.add(m);
		}

		restartCount++;
		generationCount = 1;
	}

	/**
	 * Updates the simulation mechanics by calling on the neural network and
	 * also updating the environment itself (the reproduction of food)
	 */
	public void update() {

		if (malishList.isEmpty())
			restart();

		if (random.nextFloat() < foodRegenerationRate) {
			foodList.add(new Food(random.nextInt(WIDTH), random.nextInt(HEIGHT)));
		}

		for (int i = malishList.size() - 1; i >= 0; i--) {

			Malish malish = malishList.get(i);

			// wraps the screen from top to bottom and side to side, sets
			// creature position if out of bounds of JFrame
			if (malish.getX() > WIDTH)
				malish.setX(2);
			if (malish.getX() < 0)
				malish.setX(WIDTH - 2);
			if (malish.getY() > HEIGHT)
				malish.setY(2);
			if (malish.getY() < 0)
				malish.setY(HEIGHT - 2);

			/*
			 * Derivative equation to check whether Malish has "eaten" a food
			 * object and sets health to full inputs values of sensing into
			 * neural net toolkit
			 */
			int direction = -2;
			int shortestDistance = Integer.MAX_VALUE;
			for (int j = foodList.size() - 1; j >= 0; j--) {
				Food food = foodList.get(j);
				float diffX = food.getX() - malish.getX();
				float diffY = food.getY() - malish.getY();
				float theta = (float) Math.atan2(diffY, diffX);
				
				if (theta < 0) {
					theta += 2.00 * Math.PI;
				}
				
				// 3rd quadrant
				if (food.getX() < malish.getX() && food.getY() < malish.getY()) {
					theta += Math.PI;
				// 2nd quadrant
				} else if (food.getX() < malish.getX() && food.getY() >= malish.getY()) {
					theta += Math.PI;
				}

				float relativeAngle = theta - malish.getAngle();

				int newDirection = -2;
				// if (diffX * diffX + diffY * diffY < clippingDistance) {
				if (relativeAngle >= -fov && relativeAngle <= fov) {
					newDirection = 0;
				} else if (relativeAngle < -fov && relativeAngle >= -fov - 2 * fov) {
					newDirection = -1;
				} else if (relativeAngle > fov && relativeAngle <= fov + 2 * fov) {
					newDirection = 1;
				}
				// }

				if (newDirection != -2) {
					int newDist = (int) (diffX * diffX + diffY * diffY);
					if (newDist < shortestDistance) {
						shortestDistance = newDist;
						direction = newDirection;
					}
				}

				if (Math.abs(diffX) <= 10 && Math.abs(diffY) <= 10) {
					foodList.remove(j);
					malish.setHealth(1f);
					
					if (Math.random() < 0.20f) {						
						Malish newborn = new Malish(malish);
						malishList.add(newborn);
						if (newborn.getGeneration() > generationCount)
							generationCount = newborn.getGeneration();
					}
				}
			}
			float[] input = new float[3];
			if (direction != -2) {
				input[direction + 1] = 1.0f;
			}

			if (malish.move(input)) {
				malishList.remove(i);
			}
		}
	}

	/**
	 * Starts the simulation.
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Allows for access by the demonstration window to render the entities.
	 * 
	 * @return the rendering JPanel for this simulation
	 */
	public Renderer getRenderer() {
		return renderer;
	}

	/**
	 * Allows access by the renderer for all of the Malish entities.
	 * 
	 * @return a list of Malishes in the simulation environment
	 */
	public List<Malish> getMalishList() {
		return malishList;
	}

	/**
	 * Allows access by the renderer for all of the food entities.
	 * 
	 * @return a list of food in the simulation environment
	 */
	public List<Food> getFoodList() {
		return foodList;
	}

	/**
	 * Accesses the number of restarts since the start of the simulation
	 * 
	 * @return restartCount
	 */
	public int getRestartCount() {
		return restartCount;
	}

	/**
	 * Accesses the number of generations since the start of the simulation.
	 * 
	 * @return generationCount
	 */
	public int getGenerationCount() {
		return generationCount;
	}

	/**
	 * Allows external sources to increase the speed of simulation. Normalizes
	 * ups to a maximum of 1000 ups per second, making the delay only 1ms
	 * minimum.
	 * 
	 * @param ups
	 *            updates per second for the simulation
	 */
	public void setUPS(int ups) {
		if (ups <= 0)
			throw new IllegalArgumentException("Must update simulation at least once per second.");
		if (ups > 1000)
			ups = 1000;

		timer.setDelay(1000 / ups);
	}
	
	/**
	 * Enables back propagation algorithm to optimize error correction
	 * @param value true will enable back propagation
	 */
	public void setBackPropagation(boolean value) {
		Malish.setBackPropagation(value);
	}
	
	/**
	 * Determines whether the networks are being optimized
	 * @return true if the back propagation is enabled
	 */
	public boolean getBackPropagationEnabled() {
		return Malish.getBackPropagationEnabled();
	}
}
