package org.ecclesia.demoTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * Provides an abstract class to interface with the demonstration window. This
 * allows the designer of the demonstration to focus on three aspects of the
 * design: the instructions, the control, and the content. By focusing on only
 * these three things, the designer does not have to worry about how the actual
 * window will appear; only the content within the panels matters.
 * 
 * <br />
 * 
 * Note: In order to work properly, the subclass must set the intruoduction,
 * control and content panels manually.
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
	 * The text that is displayed on the left sidebar.
	 */
	private String introduction;
	/**
	 * The main content panel on the right side of the application.
	 */
	private JPanel contentPanel;
	/**
	 * The bottom control panel as the main user interaction interface.
	 */
	private JPanel controlPanel;
	
	/**
	 * Creates the demonstration and meant to be called by the concrete subclasses.
	 */
	public Demonstration() {
		name = "";
	}
	
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
	 */
	public void start() {
		new DemoWindow(this);
	}
	
	/**
	 * Sets the introductory remarks of the demonstration.
	 * Meant to be called by a concrete subclass.
	 * @param introduction
	 */
	protected void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	/**
	 * Sets the content panel to a specific JPanel.
	 * Meant to be called by a concrete subclass.
	 * @param content
	 */
	protected void setContentPanel(JPanel content) {
		this.contentPanel = content;
	}
	
	/**
	 * Sets the control panel to a specific JPanel.
	 * Meant to be called by a concrete subclass.
	 * @param control
	 */
	protected void setControlPanel(JPanel control) {
		this.controlPanel = control;
	}
	
	/**
	 * Allows the demonstration's introductory text
	 * to be extracted for use in the GUI.
	 * @return the introduction text associated with the demonstration
	 */
	String getIntroduction() {
		return introduction;
	}

	/**
	 * Allows the demonstration's main content
	 * to be extracted for use in the GUI.
	 * @return the main content of the demonstration
	 */
	JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Allows the demonstration's main control panel
	 * to be extracted for use in the GUI.
	 * @return the main control interface for the demonstration
	 */
	JPanel getControlPanel() {
		return controlPanel;
	}
	
	public static String getInstructionsFromFile(File file) {
		String instructions = "Instructions.";
		
		try (BufferedReader input = new BufferedReader(new FileReader(file))) {
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = input.readLine()) != null) {
				result.append(line).append('\n');
			}
			instructions = result.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return instructions;
	}
}
