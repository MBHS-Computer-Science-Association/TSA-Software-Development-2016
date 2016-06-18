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

	final static int initialMalish = 15;
	final static int initialFood = 10;
	final static int clippingDistance = 5000;
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
		super(title);//
		DisplayInfo.display();

		for (int i = 0; i < initialFood; i++) {
			foodList.add(new Food(random.nextFloat() * WIDTH, random.nextFloat() * HEIGHT));
		}

		for (int i = 0; i < initialMalish; i++) {
			float x = random.nextFloat() * WIDTH;
			float y = random.nextFloat() * HEIGHT;
			Malish m = new Malish(x, y);
			malishList.add(m);
		}

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
		
		if(malishList.size()<=0) 
		{
			for (int i = 0; i < initialMalish; i++) {
				float x = random.nextFloat() * WIDTH;
				float y = random.nextFloat() * HEIGHT;
				Malish m = new Malish(x, y);
				malishList.add(m);
			}
		}
		
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
