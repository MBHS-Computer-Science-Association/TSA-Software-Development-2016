package org.ecclesia.demoLines;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.ecclesia.demoTemplate.Demonstration;

public class LineExtrapolationDemo extends Demonstration {
	private LinePredictor linePredictor;

	/**
	 * This constructor does not start anything. It only serves to create an
	 * entry in the DemoLauncher. The run() method will start the simulation.
	 */
	public LineExtrapolationDemo() {
		super("Line Extrapolation Demonstration");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoLines/introduction.txt")));
	}

	/**
	 * Starts a fresh instance of the simulation.
	 */
	@Override
	public void run() {
		linePredictor = new LinePredictor();
		this.setContentPanel(linePredictor.getRenderer());
		this.setControlPanel(new ControlPanel());
	}

	/**
	 * Control panel that manages the user interface for the demo.
	 * 
	 * @author Trevor Nguyen
	 *
	 */
	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		JLabel d1 = new JLabel("data1", JLabel.CENTER);
		JLabel d2 = new JLabel("data2", JLabel.CENTER);
		JLabel d3 = new JLabel("data3", JLabel.CENTER);

		public ControlPanel() {
			createComponents();

			new Timer(1000 / 60, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateGUIData();
				}
			}).start();
		}

		/**
		 * Creates and formats all of the components of the points data
		 * component.
		 */
		public void createComponents() {
			this.setLayout(new GridLayout(6, 0));
			this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			JLabel l1 = new JLabel("User Point:", JLabel.CENTER);
			l1.setFont(l1.getFont().deriveFont(Font.BOLD, 12f));
			JLabel l2 = new JLabel("Predicted Point:", JLabel.CENTER);
			l2.setFont(l2.getFont().deriveFont(Font.BOLD, 12f));
			JLabel l3 = new JLabel("Actual Point:", JLabel.CENTER);
			l3.setFont(l3.getFont().deriveFont(Font.BOLD, 12f));

			this.add(l1);
			this.add(d1);
			this.add(l2);
			this.add(d2);
			this.add(l3);
			this.add(d3);
		}

		/**
		 * Grabs new point data from the line predictor demo
		 */
		public void updateGUIData() {
			float[][] data = linePredictor.getCoordsData();

			// Input coordinates
			if (data[0] != null) {
				String text = String.format("X: %.2f    Y: %.2f", data[0][0], data[0][1]);
				d1.setText(text);
			} else {
				String text = String.format("X: ---    Y: ---");
				d1.setText(text);
			}

			// Output coordinates
			if (data[1] != null) {
				String text = String.format("X: %.2f    Y: %.2f", data[1][0], data[1][1]);
				d2.setText(text);
			} else {
				String text = String.format("X: ---    Y: ---");
				d2.setText(text);
			}

			// Desired coordinates
			if (data[2] != null) {
				String text = String.format("X: %.2f    Y: %.2f", data[2][0], data[2][1]);
				d3.setText(text);
			} else {
				String text = String.format("X: ---    Y: ---");
				d3.setText(text);
			}
		}

	}
}
