package org.ecclesia.demo.graphics;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

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