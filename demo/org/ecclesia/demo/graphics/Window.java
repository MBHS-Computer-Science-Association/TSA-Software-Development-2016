package org.ecclesia.demo.graphics;

import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * Instantiates the Window
	 */
	public Window() {
		super("Line Predictor");
		setSize(750, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		add(new GraphicsFrame());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}