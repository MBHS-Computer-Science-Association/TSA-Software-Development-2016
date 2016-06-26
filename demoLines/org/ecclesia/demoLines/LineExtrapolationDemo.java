package org.ecclesia.demoLines;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class LineExtrapolationDemo extends Demonstration {
	private LinePredictor linePredictor;
	
	/**
	 * This constructor does not start anything. It only serves to create
	 * an entry in the DemoLauncher. The run() method will start the simulation.
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
	
	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		public ControlPanel() {
			createComponents();
		}

		/**
		 * Creates and adds the GUI components to the control panel.
		 * Also links up logic and event triggering to the restart button
		 * as well as the slider.
		 * 
		 * Uses the FlowLayout for simplicity and quick development.
		 */
		private void createComponents() {
			
		}
	}
}
