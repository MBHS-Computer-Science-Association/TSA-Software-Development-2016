package org.ecclesia.demoTemplate;

import javax.swing.JPanel;

public abstract class Demonstration {
	final String name;
	String introductionText;
	
	public Demonstration(String name) {
		this.name = name;
	}
	
	public Demonstration start() {
		new DemoWindow(this);
		return this;
	}
	
	public abstract String getIntroduction();
	public abstract JPanel getContentPanel();
	public abstract JPanel getControlPanel();
}
