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
					update();
				}
				
			}).start();
		}

		private void createComponents() {
			restartButton = new JButton("Restart Ecosystem");
			restartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					environment.restart();
				}

			});

			generationLabel = new JLabel("Generation: X");
			speedSlider = new JSlider(0, 100, 50);
			speedSlider.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					int value = speedSlider.getValue();
					// TODO: Set the environment speed to the value.
					System.out.printf("The value of slider: %d%n", value);
				}
			});

			restartLabel = new JLabel("Number of Restarts: X");

			this.setLayout(new GridLayout(2, 2, 50, 50));
			this.add(restartButton);
			this.add(generationLabel);
			this.add(speedSlider);
			this.add(restartLabel);
		}
		
		private void update() {
//			if (<value of slider changed>)
//				generationLabel.setText("Number of Generations: " + <value>);
//			if (<value of restarts changed>)
//				restartLabel.setText("Number of Restarts: " + <value>);
			
			// TODO: Finish implementing this.
		}
	}
}
