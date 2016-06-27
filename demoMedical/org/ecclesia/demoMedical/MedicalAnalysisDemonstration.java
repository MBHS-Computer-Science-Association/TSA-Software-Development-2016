package org.ecclesia.demoMedical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

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
			setLayout(new BorderLayout(15, 15));
			JLabel label = new JLabel("Accuracy on 100 Test Cases", JLabel.CENTER);
			label.setFont(label.getFont().deriveFont(Font.BOLD, 20f));
			this.add(label, BorderLayout.NORTH);
			AccuracyGrid grid = new AccuracyGrid();
			this.add(grid, BorderLayout.CENTER);
		}

		class AccuracyGrid extends JPanel {
			boolean[][] accuracyGrid;

			public AccuracyGrid() {
				update();
				
				new Timer(1000 / 60, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						update();
						repaint();
					}
				}).start();
			}

			private void update() {
				accuracyGrid = medicalAnalysis.getAccuracyData();
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int offset = (getWidth() - getHeight()) / 2;
				int size = (getHeight()) / 10;
				
				for (int i = 0; i < accuracyGrid.length; i++) {
					for (int j = 0; j < accuracyGrid[i].length; j++) {
						g.setColor(accuracyGrid[i][j] ? Color.GREEN : Color.RED);
						g.fillRect(offset + j * size, i * size, size-2, size-2);
					}
				}
			}
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
			JButton testButton = new JButton("Test");
			testButton.setEnabled(false);
			testButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					medicalAnalysis.test();
				}
			});

			JButton trainButton = new JButton("Train");
			trainButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					medicalAnalysis.train();
					testButton.setEnabled(true);
				}
				
			});
			
			this.setLayout(new GridLayout(1, 2));
			this.add(trainButton);
			this.add(testButton);
		}
	}
}
