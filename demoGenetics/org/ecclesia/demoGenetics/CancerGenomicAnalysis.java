package org.ecclesia.demoGenetics;

import java.io.File;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

/**
 * The functioning class that calls the GUI template and appends the 
 * proper content to the content panel and the proper content to the control panel
 * 
 * @author Sammy Shin and Trevor Nguyen
 *
 */
public class CancerGenomicAnalysis extends Demonstration {
	public CancerGenomicAnalysis() {
		super("Artificial Neural Networks in Cancer Genetic Studies");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoGenetics/introduction.txt")));
		this.setContentPanel(new ContentPanel());
		this.setControlPanel(new ControlPanel());
	}
}
