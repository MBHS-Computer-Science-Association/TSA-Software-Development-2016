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
public class Environment implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	final static int width = 1000;
	final static int height = 1000;
	final static int initialMalish = 15;
	final static int initialFood = 50;
	final static int foodDist = 15;
	final static int clippingDistance = 100;
	final static float foodRegenerationRate = 0.015f;
	static ArrayList<Malish> malishList = new ArrayList<>();
	static ArrayList<Food> foodList = new ArrayList<>();
	static Random rand = new Random();
	Renderer r;
	Timer time;

	/**
	 * Initializes the evolutionary simulator graphics window
	 * 
	 * @throws InterruptedException
	 * 
	 */

	public Environment() throws InterruptedException {
		time = new Timer(1000 / 60, this);
		JFrame frame = new JFrame("Evolutionary Simulator - Malishes");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(10, 10, width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		r = new Renderer();
		frame.add(r);

		for (int i = 0; i < initialMalish; i++) {
			malishList.add(new Malish(rand.nextFloat() * width, rand.nextFloat() * height));
		}
		for (int i = 0; i < initialFood; i++) {
			foodList.add(new Food(rand.nextFloat() * width, rand.nextFloat() * height));
		}
		time.start();
	}

	/**
	 * Updates the simulation
	 */
	public void update() {
		if (rand.nextFloat() < foodRegenerationRate) {
			foodList.add(new Food(rand.nextInt(width), rand.nextInt(height)));
		}
		for (int i = malishList.size() - 1; i >= 0; i--) {
			Malish malish = malishList.get(i);
			if (malish.getX() > width) {
				malish.setX(2);
			}
			if (malish.getX() < 0) {
				malish.setX(width - 2);
			}
			if (malish.getY() > height) {
				malish.setY(2);
			}
			if (malish.getY() < 0) {
				malish.setY(height - 2);
			}
			float[] input = new float[4];
			Vector2fNeural vector = new Vector2fNeural(0, 0);
			for (int n = foodList.size() - 1; n >= 0; n--) {
				Food food = foodList.get(n);
				float dX = malish.getX() - food.getX();
				float dY = malish.getY() - food.getY();
				if (Math.abs(dX) < foodDist && Math.abs(dY) < foodDist) {
					foodList.remove(n);
					malish.setHealth(1f);
					malishList.add(new Malish(malish));
				}
				if (Math.abs(dX) < clippingDistance && Math.abs(dY) < clippingDistance) {
					double m = dY / dX;
					double theta = Math.tan(m);
					theta -= malish.getAngle();
					if(theta <= 0- Math.PI/6 && theta > -Math.PI/2) 
					{
						System.out.println("Left");
						input[0] = 1f;
					}else if(theta > -Math.PI/6 && theta < Math.PI/6) 
					{
						System.out.println("Middle");
						input[1] = 1f;
					}else if(theta >= Math.PI/6 && theta <= Math.PI/ 2)
					{
						System.out.println("Right");
						input[2] = 1f;
					}
					/**float currentTheta = malish.getVelocity().getAngle();
					float alpha = currentTheta - (float) Math.PI / 3;
					float beta = currentTheta + (float) Math.PI / 3;
					float theta = (float) Math.atan2(food.getY() - malish.getY(), food.getX() - malish.getX());
					if (theta >= alpha && theta <= beta
							&& (realDistance = (float) Math.sqrt(dX * dX + dY * dY)) < clippingDistance) {
						input[0] = 1f;
						input[1] = (theta - malish.getAngle()) / (float) Math.PI - 0.5f;
						if(theta > malish.getAngle()) 
						{
							input[1] = input[1] * -1;
						}
						System.out.println(input[1]);
					}
					**/
				}
			}
			input[3] = malish.getHealth();
			if (malish.move(input)) {
				malishList.remove(i);
			}

		}

	}

	public static void main(String args[]) throws InterruptedException {

		Environment e = new Environment();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		r.repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("UP");
			time.setDelay(1);
			time.start();
			break;
		case KeyEvent.VK_DOWN:
			time.setDelay(100);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

/////////////////////////////////////////////////////////////////////////
class Renderer extends JPanel {
	static final int width = 15;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < Environment.malishList.size(); i++) {
			Malish malish = Environment.malishList.get(i);
			if (malish.health < 0.1f) {
				g.setColor(Color.RED);
			} else if (malish.health < 0.4f) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.CYAN);
			}
			g.fillOval((int) malish.getX() - width / 2, (int) malish.getY() - width / 2, width, width);
		}
		for (int i = 0; i < Environment.foodList.size(); i++) {
			g.setColor(Color.GREEN);
			Food food = Environment.foodList.get(i);
			g.fillRect((int) food.getX() - width / 2, (int) food.getY() - width / 2, width, width);
		}
	}

}
