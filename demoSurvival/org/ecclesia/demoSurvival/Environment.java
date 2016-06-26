package org.ecclesia.demoSurvival;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import org.ecclesia.demoSurvival.entities.Food;
import org.ecclesia.demoSurvival.entities.Malish;

/**
 * @author Christian Duffee, Sammy Shin, Thai Nguyen
 */
public class Environment {

	private static final long serialVersionUID = 1L;

	final static int initialMalish = 15;
	final static int initialFood = 10;
	final static int clippingDistance = 5000;
	final static int WIDTH = 800;
	final static int HEIGHT = 600;

	final static float foodRegenerationRate = 0.01f;
	final static int initialPopulation = 10;

	final static float fov = (float) (Math.PI / 8);
	static Random random = new Random();

	private List<Malish> malishList;
	private List<Food> foodList;
	private Renderer renderer;
	private Timer timer;

	/**
	 * 
	 * Initializes the evolutionary simulator graphics window
	 * 
	 */
	public Environment() {
		malishList = new LinkedList<>();
		foodList = new LinkedList<>();

		renderer = new Renderer(this);

		for (int i = 0; i < initialFood; i++) {
			foodList.add(new Food(random.nextFloat() * WIDTH, random.nextFloat() * HEIGHT));
		}

		for (int i = 0; i < initialMalish; i++) {
			float x = random.nextFloat() * WIDTH;
			float y = random.nextFloat() * HEIGHT;
			Malish m = new Malish(x, y);
			malishList.add(m);
		}

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
	 * Restarts the simulation with new neural networks.
	 */
	public void restart() {
		malishList.clear();
		
		for (int i = 0; i < initialMalish; i++) {
			float x = random.nextFloat() * WIDTH;
			float y = random.nextFloat() * HEIGHT;
			Malish m = new Malish(x, y);
			malishList.add(m);
		}
	}
	
	/**
	 * Updates the simulation mechanics by calling on the neural network and
	 * also updating the environment itself (the reproduction of food)
	 */
	public void update() {

		if (malishList.size() <= 0)
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
				float diffX = malish.getX() - food.getX();
				float diffY = malish.getY() - food.getY();
				float theta = (float) Math.atan(diffY / diffX);
				if (theta < 0) {
					theta += 2.00 * Math.PI;
				}
				if (food.getX() < malish.getX() && food.getY() < malish.getY()) { // if
																					// in
																					// 3rd
																					// quadrant
					theta += Math.PI;
				} else if (food.getX() < malish.getX() && food.getY() >= malish.getY()) { // if
																							// in
																							// 2nd
																							// quadrant
					theta += Math.PI;
				}

				float relativeAngle = theta -= malish.getAngle();

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
					malishList.add(new Malish(malish));
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

	public Renderer getRenderer() {
		return renderer;
	}

	public List<Malish> getMalishList() {
		return malishList;
	}

	public List<Food> getFoodList() {
		return foodList;
	}
}
