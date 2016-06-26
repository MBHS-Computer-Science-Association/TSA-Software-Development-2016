package org.ecclesia.demoTemplate;

import javax.swing.JPanel;

/**
 * Provides an abstract class to interface with the demonstration window. This
 * allows the designer of the demonstration to focus on three aspects of the
 * design: the instructions, the control, and the content. By focusing on only
 * these three things, the designer does not have to worry about how the actual
 * window will appear; only the content within the panels matters.
 * 
 * @author Trevor Nguyen
 *
 */
public abstract class Demonstration {
	/**
	 * The name that appears at the top of the window.
	 */
	final String name;
	/**
	 * The text that is displayed at the top of the window.
	 */
	String introductionText;

	/**
	 * Creates the demonstration and meant to be called by concrete subclasses.
	 * 
	 * @param name
	 *            The name that appears at the top of the window.
	 */
	public Demonstration(String name) {
		this.name = name;
	}

	/**
	 * Creates a demonstration window off of the template window class.
	 * 
	 * @return the object to allow the simultaneous declaration, creation, and
	 *         initiation of the demonstration.
	 */
	public Demonstration start() {
		new DemoWindow(this);
		return this;
	}

	/**
	 * Allows the demonstration's introductory text
	 * to be extracted for use in the GUI.
	 * @return the introduction text associated with the demonstration
	 */
	public abstract String getIntroduction();

	/**
	 * Allows the demonstration's main content
	 * to be extracted for use in the GUI.
	 * @return the main content of the demonstration
	 */
	public abstract JPanel getContentPanel();

	/**
	 * Allows the demonstration's main control panel
	 * to be extracted for use in the GUI.
	 * @return the main control interface for the demonstration
	 */
	public abstract JPanel getControlPanel();
}
