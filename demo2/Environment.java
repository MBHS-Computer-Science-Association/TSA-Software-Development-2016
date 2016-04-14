import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Christian Duffee, Sammy Shin, Thai Nguyen
 * @school McKinney Boyd High School
 * @date March 4, 2016
 */
public class Environment extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	final static int WIDTH = 1000;
	final static int HEIGHT = 1000;
	final static String title = "Demonstration 2";

	final static float foodRegenerationRate = 0.01f;
	final static int initialPopulation = 10;

	final static float fov = (float) (Math.PI / 8);

	static ArrayList<Malish> malishList = new ArrayList<>();
	static ArrayList<Food> foodList = new ArrayList<>();

	static Random random = new Random();

	Renderer r;
	Timer time;

	/**
	 * 
	 * Initializes the evolutionary simulator graphics window
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public Environment() throws InterruptedException {
		super(title);

		time = new Timer(1000 / 60, this);
		r = new Renderer();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
		setBounds(10, 10, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		add(r);

		time.start();
	}

	/**
	 * Updates the simulation mechanics by calling on the neural network and
	 * also updating the environment itself (the reproduction of food)
	 */
	public void update() {
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

				if (theta >= -fov && theta <= fov) {
					//middle
				}

				if (Math.abs(diffX) <= 1 && Math.abs(diffY) <= 1) {
					foodList.remove(j);
					malish.setHealth(1f);
					// malishList.add(new Malish(malish));
				}

				// if (Math.abs(dX) < clippingDistance && Math.abs(dY) <
				// clippingDistance) {
				// float realDistance;
				// float currentTheta = malish.getVelocity().getAngle();
				// float alpha = currentTheta - (float) Math.PI / 3;
				// float beta = currentTheta + (float) Math.PI / 3;
				// float theta = (float) Math.atan2(food.getY() - malish.getY(),
				// food.getX() - malish.getX());
				// if (theta >= alpha && theta <= beta
				// && (realDistance = (float) Math.sqrt(dX * dX + dY * dY)) <
				// clippingDistance) {
				// input[0] = 1f;
				// input[1] = (theta - malish.getAngle()) / (float) Math.PI -
				// 0.5f;
				// if(theta > malish.getAngle())
				// {
				// input[1] = input[1] * -1;
				// }
				// System.out.println(input[1]);
				// }
				// }
			}
			if (malish.move(input)) {
				malishList.remove(i);
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// switch (e.getKeyCode()) {
		// case KeyEvent.VK_UP:
		// System.out.println("UP");
		// time.setDelay(1);
		// time.start();
		// break;
		// case KeyEvent.VK_DOWN:
		// time.setDelay(100);
		// }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Runs the update and repaint method via the timer calling it repeatedly
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		r.repaint();
	}

	/**
	 * Instantiates the environment/starts the demo
	 * 
	 * @param args
	 * @throws InterruptedException
	 * 
	 */
	public static void main(String args[]) throws InterruptedException {
		Environment e = new Environment();
	}
}