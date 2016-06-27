package org.ecclesia.demoGenetics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.ecclesia.demoTemplate.Demonstration;

/**
 * The functioning class that calls the GUI template and appends the 
 * proper content to the content panel and the proper content to the control panel
 * 
 * @author Sammy Shin
 *
 */
public class CancerGenomicAnalysis extends Demonstration {
	private GeneticsLogic geneticsLogic;
	
	public CancerGenomicAnalysis() {
		super("Cancer Genetic Studies");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoGenetics/introduction.txt")));
	}

	@Override
	public void run() {		
		geneticsLogic = new GeneticsLogic();
		this.setContentPanel(new ContentPanel());
		this.setControlPanel(new ControlPanel());
	}
	
	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {

		
		public ControlPanel()
		{	
			createComponents();
		}
		
		private void createComponents() {

			GridLayout experimentLayout = new GridLayout(0,2);
			setLayout(experimentLayout);

			JButton train = new JButton("Train Current Neural Network");
			JButton generate = new JButton("Generate New Neural Network");
			
			train.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					geneticsLogic.train();
				}
			});
			
			generate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					geneticsLogic.setNetwork();
				}
			});
			
			add(train);
			add(generate);

		
		}
		
	}
	class ContentPanel extends JPanel {
		
		
		public ContentPanel()
		{	
			createComponents();
		}
		
		private void createComponents() {
			 
		        setLayout(new SpringLayout());
		 
		        JTextField g1 = new JTextField();
		        
		        add(g1);
		        add(new JButton("Button 2"));
		        add(new JButton("Button 3"));
		        add(new JButton("Long-Named Button 4"));
		        add(new JButton("5"));
		        
		        SpringUtilities.makeCompactGrid(this, 1,
                        getComponentCount(),
                        6, 6, 6, 6);
		 
		}
	}

}
