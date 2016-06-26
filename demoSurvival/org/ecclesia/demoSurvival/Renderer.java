package org.ecclesia.demoSurvival;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.ecclesia.demoSurvival.entities.Food;
import org.ecclesia.demoSurvival.entities.Malish;

/**
 * 
 * @author Sammy Shin, Christian Duffee, Trevor Nguyen
 *
 */
public class Renderer extends JPanel {
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 16;
	final static Color foodColor = Color.BLUE;
	final static Color viewColor = Color.BLUE;

	private Environment environment;
	
	public Renderer(Environment e) {
		environment = e;
		
		// Refreshing at 60 FPS
		new Timer(1000 / 60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}).start();
	}
	
	/**
	 * Paints the JPanel and is called every time the repaint() method
	 * is invoked.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int componentWidth = this.getWidth();
		int componentHeight = this.getHeight();
		
		int tempWidth = WIDTH * componentWidth / Environment.WIDTH;
		
		for (Malish malish : environment.getMalishList()) {
			if (malish.getHealth() < 0.1f) {
				g.setColor(Color.RED);
			} else if (malish.getHealth() < 0.4f) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.GREEN);
			}

			float x = malish.getX() / Environment.WIDTH * componentWidth;
			float y = malish.getY() / Environment.HEIGHT * componentHeight;
			
//			g.fillOval((int) malish.getX() - WIDTH / 2, (int) malish.getY() - WIDTH / 2, WIDTH, WIDTH);
			g.fillOval((int) x - tempWidth / 2, (int) y - tempWidth / 2, tempWidth, tempWidth);
			
			g.setColor(viewColor);
			final float mod = 8;
//			g.drawLine((int) malish.getX(), (int)malish.getY(),(int)( malish.getX() + mod * Math.cos(malish.getAngle())),
//					(int)(malish.getY() + mod * Math.sin(malish.getAngle())));

			g.drawLine((int) x, (int) y,(int)( x + mod * Math.cos(malish.getAngle())),
					(int)(y + mod * Math.sin(malish.getAngle())));
			
			
			// g.fillOval((int) (malish.getX() - WIDTH / 2 + mod *
			// Math.cos(malish.getAngle())),
			// (int) (malish.getY() - WIDTH / 2 + mod *
			// Math.sin(malish.getAngle())), headSize, headSize);
		}
		
		g.setColor(foodColor);
		for (Food food : environment.getFoodList()) {
			float x = food.getX() / Environment.WIDTH * componentWidth;
			float y = food.getY() / Environment.HEIGHT * componentHeight;
			
//			g.fillRect((int) food.getX() - WIDTH / 2, (int) food.getY() - WIDTH / 2, WIDTH, WIDTH);
			g.fillRect((int) x - tempWidth/ 2, (int) y - tempWidth / 2, tempWidth, tempWidth);

		}
	}

}
