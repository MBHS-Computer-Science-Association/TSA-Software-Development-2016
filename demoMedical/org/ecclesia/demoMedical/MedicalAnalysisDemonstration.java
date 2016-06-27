package org.ecclesia.demoMedical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
			JLabel label = new JLabel("Content panel: Accuracy Grid!");
			this.add(label, BorderLayout.PAGE_START);
			AccuracyGrid grid = new AccuracyGrid();
			grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

				int size = (getHeight() - 15) / 20;
				
				for (int i = 0; i < accuracyGrid.length; i++) {
					for (int j = 0; j < accuracyGrid[i].length; j++) {
						g.setColor(accuracyGrid[i][j] ? Color.GREEN : Color.RED);
						g.fillRect(j * size, i * size, size-3, size-3);
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
