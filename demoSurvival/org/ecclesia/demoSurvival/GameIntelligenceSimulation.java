package org.ecclesia.demoSurvival;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.ecclesia.demoTemplate.Demonstration;

/**
 * This simulates what it would be like to use Ecclesia in games or advanced
 * ecology studies.
 * 
 * @author Trevor Nguyen
 *
 */
public class GameIntelligenceSimulation extends Demonstration {
	Environment environment;

	public GameIntelligenceSimulation() {
		super("Game Intelligence Simulation");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoSurvival/introduction.txt")));

		environment = new Environment();

		this.setControlPanel(new ControlPanel());
		this.setContentPanel(environment.getRenderer());
	}

	@Override
	public void start() {
		environment.start();
		super.start();
	}

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		JButton restartButton;
		JLabel generationLabel;
		JSlider speedSlider;
		JLabel restartLabel;

		public ControlPanel() {
			createComponents();
			
			// Refreshes the components at 60 FPS
			new Timer(1000 / 60, new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					updateLabels();
				}
				
			}).start();
		}

		/**
		 * Creates and adds the GUI components to the control panel.
		 * Also links up logic and event triggering to the restart button
		 * as well as the slider.
		 * 
		 * Uses the GridLayout for simplicity and quick development.
		 */
		private void createComponents() {
			restartButton = new JButton("Restart Ecosystem");
			restartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					environment.restart();
				}

			});

			generationLabel = new JLabel("Generation: 0");
			speedSlider = new JSlider(5, 1000, 180);
			
			speedSlider.addChangeListener(new ChangeListener() {
				/**
				 * Sets the environment's updates per second to the 
				 * value of the slider.
				 */
				@Override
				public void stateChanged(ChangeEvent e) {
					int ups = speedSlider.getValue();
					environment.setUPS(ups);
					System.out.printf("The value of slider: %d%n", ups);
				}
			});
			speedSlider.setMajorTickSpacing(100);
			speedSlider.setPaintTicks(true);

			restartLabel = new JLabel("Number of Restarts: 0");

			this.setLayout(new GridLayout(2, 2, 50, 50));
			this.add(restartButton);
			this.add(generationLabel);
			this.add(speedSlider);
			this.add(restartLabel);
			
		}
		
		private int prevGenerations = 0;
		private int prevRestarts = 0;
		
		/**
		 * Updates the values of the two label components:
		 * the generations and the number of restarts.
		 * 
		 * Checks to make sure that the values have been changed
		 * before making updates to the GUI to reduce overhead.
		 */
		private void updateLabels() {
			if (environment.getGenerationCount() != prevGenerations) {
				prevGenerations = environment.getGenerationCount();
				generationLabel.setText("Number of Generations: " + prevGenerations);
			}
			
			if (environment.getRestartCount() != prevRestarts) {
				prevRestarts = environment.getRestartCount();
				restartLabel.setText("Number of Restarts: " + prevRestarts);
			}
		}
	}
}
