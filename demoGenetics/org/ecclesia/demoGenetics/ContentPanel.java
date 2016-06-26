package org.ecclesia.demoGenetics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * The layout and creation of the Content panel 
 * 
 * @author Sammy Shin
 *
 */
public class ContentPanel extends JPanel {
	
	GridBagConstraints gbc = new GridBagConstraints();
	JButton b1;
	
	public ContentPanel()
	{	
		setLayout(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.NONE;
		
		b1 = new JButton("this");
		gbc.gridwidth = 4;
		gbc.gridx = 2;
		gbc.gridy = 5;
		add(b1, gbc);

	}
}
