package org.ecclesia.demoSurvival;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.DemoWindow;
import org.ecclesia.demoTemplate.Demonstration;

/**
 * 
 * @author Trevor Nguyen
 *
 */
public class GameIntelligenceSimulation extends Demonstration {
	private JPanel controlPanel;
	private JPanel contentPanel;
	private String introduction;

	Environment environment;
	
	public GameIntelligenceSimulation() {
		super("Game Intelligence Simulation");
		introduction = Demonstration.getInstructionsFromFile(new File("demoSurvival/introduction.txt"));
		
		environment = new Environment();
		
		controlPanel = new ControlPanel();
		contentPanel = environment.getRenderer();
	}

	@Override
	public Demonstration start() {
		environment.start();

		new DemoWindow(this);
		return this;
	};

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

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {

	}
}
