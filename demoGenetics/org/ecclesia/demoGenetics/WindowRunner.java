package org.ecclesia.demoGenetics;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.ecclesia.demoLines.CancerGenomicAnalysis;
import org.ecclesia.demoTemplate.Demonstration;

public class WindowRunner {
	public static void main(String[] args) {
		Demonstration demo = new CancerGenomicAnalysis().start();
	}
	
}
