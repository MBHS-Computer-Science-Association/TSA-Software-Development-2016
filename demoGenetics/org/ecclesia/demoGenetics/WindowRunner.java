package org.ecclesia.demoGenetics;
import org.ecclesia.demoTemplate.Demonstration;

/**
 * The running class of the Genetics demonstration
 * 
 * @author Sammy Shin
 *
 */
public class WindowRunner {
	public static void main(String[] args) {
		Demonstration demo = new CancerGenomicAnalysis().start();
	}
	
}
