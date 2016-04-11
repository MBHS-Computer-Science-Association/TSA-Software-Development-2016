package org.ecclesia.demo.graphics;

import javax.swing.JFrame;

public class Window extends JFrame {
	public Window() {
		super("Line Predictor");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		add(new GraphicsFrame());
		setVisible(true);
	}
}
