package org.ecclesia.demoGenetics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The management and creation of the Control Panel
 * 
 * @author Sammy Shin
 *
 */

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	
	public ControlPanel()
	{	
		createComponents();
	}
	
	private void createComponents() {
//		GridBagConstraints gbc = new GridBagConstraints();
//		JButton b1;
//		JLabel train;
//		JTextArea txt;
//		setLayout(new GridBagLayout());
//		
//		gbc.fill = GridBagConstraints.BOTH;
//		
//		b1 = new JButton("Generate");
//		train = new JLabel("Training Neural Networks");
//		gbc.gridheight = 1;
//		gbc.gridwidth = 1;
//		gbc.weightx = 0;
//		gbc.weighty = 0;
//		gbc.gridx = 2;
//		gbc.gridy = 4;
//		add(b1, gbc);
//		
//		gbc.gridheight = 1;
//		gbc.gridwidth = 2;
//		gbc.weightx = 0.2;
//		gbc.weighty = 0.2;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		
//		add(train, gbc);
		GridLayout experimentLayout = new GridLayout(0,2);
		setLayout(experimentLayout);

		JButton train = new JButton("Train Current Neural Network");
		JButton generate = new JButton("Generate New Neural Network");
		
		train.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//DO SOMETHING
			}
		});
		
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		add(train);
		add(generate);

	
	}
	
}