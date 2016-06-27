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
	private Environment environment;

	/**
	 * This constructor does not start anything. It only serves to create
	 * an entry in the DemoLauncher. The run() method will start the simulation.
	 */
	public GameIntelligenceSimulation() {
		super("Ecological Survival Simulation");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoSurvival/introduction.txt")));
	}
	
	/**
	 * Starts a fresh instance of this simulation.
	 */
	@Override
	public void run() {
		environment = new Environment();
		this.setContentPanel(environment.getRenderer());
		this.setControlPanel(new ControlPanel());
		environment.start();
	}

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		JButton restartButton;
		JButton optimizeButton;
		JLabel sliderLabel;
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

			optimizeButton = new JButton("Load Optimized NNs");
			optimizeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean enabled = environment.getBackPropagationEnabled();
					environment.setBackPropagation(!enabled);
					
					// Was enabled before
					if (enabled)
						optimizeButton.setText("Load Optimized NNs");
					else
						optimizeButton.setText("Disable Optimized NNs");
				}
			});
			
			sliderLabel = new JLabel("Simulation Speed", JLabel.CENTER);
			
			generationLabel = new JLabel("Generation: 0", JLabel.CENTER);
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
				}
			});
			speedSlider.setMajorTickSpacing(100);
			speedSlider.setPaintTicks(true);

			restartLabel = new JLabel("Number of Restarts: 0", JLabel.CENTER);

			this.setLayout(new GridLayout(3, 2, 10, 10));
			this.add(restartButton);
			this.add(optimizeButton);
			this.add(sliderLabel);
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
