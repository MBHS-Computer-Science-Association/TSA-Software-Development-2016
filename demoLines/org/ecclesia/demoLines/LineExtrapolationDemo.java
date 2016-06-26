package org.ecclesia.demoLines;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class LineExtrapolationDemo extends Demonstration {
	private LinePredictor linePredictor;
	
	public LineExtrapolationDemo() {
		super("Line Extrapolation Demonstration");
		
		linePredictor = new LinePredictor();
		
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoLines/introduction.txt")));
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
