import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 15;

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
				g.setColor(Color.GREEN);
			}
			g.fillRect((int) malish.getX() - WIDTH / 2, (int) malish.getY() - WIDTH / 2, WIDTH, WIDTH);
		}
		for (int i = 0; i < Environment.foodList.size(); i++) {
			g.setColor(Color.magenta);
			Food food = Environment.foodList.get(i);
			g.fillRect((int) food.getX() - WIDTH / 2, (int) food.getY() - WIDTH / 2, WIDTH, WIDTH);
		}
	}

}
