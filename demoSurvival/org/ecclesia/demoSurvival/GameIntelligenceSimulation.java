package org.ecclesia.demoSurvival;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.DemoWindow;
import org.ecclesia.demoTemplate.Demonstration;

/**
 * This simulates what it would be like to use Ecclesia in games or advanced ecology studies.
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

	}
}
