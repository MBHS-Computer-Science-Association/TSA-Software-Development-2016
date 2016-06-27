package org.ecclesia.demoMedical;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

/**
 * 
 * @author Thai Nguyen
 *
 */
public class MedicalAnalysisDemonstration extends Demonstration {
	MedicalDataAnalysis medicalAnalysis;

	public MedicalAnalysisDemonstration() {
		super("Medical Data Analysis");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoMedical/introduction.txt")));
	}

	@Override
	public void run() {
		medicalAnalysis = new MedicalDataAnalysis();
		this.setContentPanel(new ContentPanel());
		this.setControlPanel(new ControlPanel());
	}

	@SuppressWarnings("serial")
	class ContentPanel extends JPanel {
		public ContentPanel() {
			createComponents();
		}

		/**
		 * Creates Java Swing components and adds them to the panel.
		 */
		public void createComponents() {
			JLabel label = new JLabel("Content panel");
			this.add(label);
		}
	}

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		public ControlPanel() {
			createComponents();
		}

		/**
		 * Creates Java Swing components and adds them to the panel.
		 */
		public void createComponents() {
			JButton trainButton = new JButton("Train");
			trainButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					medicalAnalysis.train();
				}

			});
			JButton testButton = new JButton("Test");
			testButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					medicalAnalysis.test();
				}
			});
			
			this.setLayout(new GridLayout(1, 2));
			this.add(trainButton);
			this.add(testButton);
		}
	}
}
