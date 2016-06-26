package org.ecclesia.demoMedical;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class MedicalAnalysisDemonstration extends Demonstration {

	public MedicalAnalysisDemonstration() {
		super("Medical Data Analysis");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoMedical/introduction.txt")));
	}
	
	@Override
	public void run() {
		new MedicalDataAnalysis();
		this.setContentPanel(new ContentPanel());
		this.setControlPanel(new ControlPanel());
	}

	@SuppressWarnings("serial")
	class ContentPanel extends JPanel {
		public ContentPanel() {
			createComponents();
		}

		/**
		 * Creates Java Swing components and adds them to the panel.
		 */
		public void createComponents() {
			JLabel label = new JLabel("Content panel");
			this.add(label);
		}
	}
	
	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		public ControlPanel() {
			createComponents();
		}
		
		/**
		 * Creates Java Swing components and adds them to the panel.
		 */
		public void createComponents() {
			JLabel label = new JLabel("Control panel");
			this.add(label);
		}
	}
}
