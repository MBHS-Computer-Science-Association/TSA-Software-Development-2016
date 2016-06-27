package org.ecclesia.demoLogic;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class DigitalLogicDemonstration extends Demonstration {
	XORLogicTrainer logicTrainer;
	
	public DigitalLogicDemonstration() {
		super("Digital Logic Analysis");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoLogic/introduction.txt")));
	}
	
	@Override
	public void run() {
		logicTrainer = new XORLogicTrainer();
		this.setContentPanel(new ContentPanel());
		this.setControlPanel(new ControlPanel());
	}

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		public ControlPanel() {
			createComponents();
		}
		
		public void createComponents() {			
			JLabel label = new JLabel("Control Panel");
			this.add(label);
		}
	}
	
	@SuppressWarnings("serial")
	class ContentPanel extends JPanel {
		public ContentPanel() {
			createComponents();
		}
		
		public void createComponents() {			
			JLabel label = new JLabel("Content Panel");
			this.add(label);
		}
	}

}