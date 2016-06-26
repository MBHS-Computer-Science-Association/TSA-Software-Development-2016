package org.ecclesia.demoSurvival;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class GameIntelligenceSimulation extends Demonstration {
	private JPanel controlPanel;
	private JPanel contentPanel;
	private String introduction;

	public GameIntelligenceSimulation() {
		super("Game Intelligence Simulation");
		introduction = Demonstration.getInstructionsFromFile(new File("demoSurvival/introduction.txt"));
		controlPanel = new ControlPanel();
		contentPanel = new ContentPanel();
	}

	@Override
	public String getIntroduction() {
		return introduction;
	}

	@Override
	public JPanel getContentPanel() {
		return contentPanel;
	}

	@Override
	public JPanel getControlPanel() {
		return controlPanel;
	}
	
	class ControlPanel extends JPanel {
		
	}
	
	class ContentPanel extends JPanel {
		
	}
}
