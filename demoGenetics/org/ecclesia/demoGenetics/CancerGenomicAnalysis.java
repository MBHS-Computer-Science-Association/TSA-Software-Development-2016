package org.ecclesia.demoGenetics;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import org.ecclesia.demoTemplate.Demonstration;

/**
 * The functioning class that calls the GUI template and appends the proper
 * content to the content panel and the proper content to the control panel
 * 
 * @author Sammy Shin
 *
 */
public class CancerGenomicAnalysis extends Demonstration {
	private GeneticsLogic geneticsLogic;
	boolean trained = false;

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

		public ControlPanel() {
			createComponents();
		}

		private void createComponents() {

			GridLayout experimentLayout = new GridLayout(0, 2);
			setLayout(experimentLayout);

			JButton train = new JButton("Train Current Neural Network");
			JButton generate = new JButton("Generate New Neural Network");

			train.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					geneticsLogic.train();
					trained = true;
				}
			});

			generate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					geneticsLogic.setNetwork();
					trained = false;
				}
			});

			add(train);
			add(generate);

		}

	}

	@SuppressWarnings("serial")
	class ContentPanel extends JPanel {

		protected char[] ntSequence;

		public ContentPanel() {
			createComponents();
			ntSequence = new char[10];
		}

		private void createComponents() {

			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 0;
			//gbc.ipady = 10;
			gbc.insets = new Insets(5, 20, 0, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			JLabel title = new JLabel("Cancer Genetic Analysis:");
			title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
			add(title, gbc);
			
			JLabel explain = new JLabel("Modify the gene below and test to see if the network will predict it as mutated or not");
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(0, 20, 365, 0);
			gbc.weighty = .5;
			gbc.weightx = .5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(explain, gbc);
			
			JLabel nonmutated = new JLabel("EGFR Non-Mutated Gene: ");
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(50, 15, 0, 0);//0 150 330 0
			gbc.weighty = .5;
			gbc.weightx = .5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(nonmutated, gbc);
			
			JLabel nonmutated1 = new JLabel("A T G C G A C C C T");
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(50, 188, 0, 0);//0 150 330 0
			gbc.weighty = .5;
			gbc.weightx = .5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(nonmutated1, gbc);
			 
		
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(0, 75, 190, 0);
			gbc.weighty = .5;
			gbc.weightx = .5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			JRadioButton[][] buttonArray = new JRadioButton[4][10];

			add(new JComponent() {
			{

				setLayout(new SpringLayout());
				
				
				for (int i = 0; i < 10; i++) {
					ButtonGroup group = new ButtonGroup();
					for (int j = 0; j < 4; j++) {
						JRadioButton button = new JRadioButton();
						buttonArray[j][i] = button;

						final int index = j;
						final int seqIndex = i;

						button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								char c;

								switch (index) {
								case 0:
									c = 'A';
									break;
								case 1:
									c = 'C';
									break;
								case 2:
									c = 'G';
									break;
								case 3:
								default:
									c = 'T';
									break;
								}

								ntSequence[seqIndex] = c;
							}
						});

						group.add(button); 
						
					}
				}

				String nts = "ACGT";
				for (int i = 0; i < 4; i++) {
					JLabel label = new JLabel("                " + nts.charAt(i));
					this.add(label);

					for (int j = 0; j < 10; j++) {
						this.add(buttonArray[i][j]);
					}
				}

				SpringUtilities.makeCompactGrid(this, 4, 11, 3, 3, 3, 3);
			}
		}, gbc);
		
			JLabel gene = new JLabel("Current Modified Gene: ");

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(0, 0, 0, 390);
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
			add(gene, gbc);

			JLabel guess = new JLabel("ANN's Guess:   ");

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(150, 0, 0, 335);//210
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
			add(guess, gbc);
			
			JLabel ans = new JLabel("Real Answer:   ");

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(200, 0, 0, 329);//204
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
			add(ans, gbc);
			
			JLabel train = new JLabel("ANN Trained:   ");

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(250, 0, 0, 330); //205
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
			add(train, gbc);
			
			
			JButton results = new JButton("Results");
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(0, 330, 0, 0);
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
		
			
			results.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					guess.setText("ANN's Guess:   " + ((geneticsLogic.changeList(ntSequence)[0] > .555f)?"Mutated (Cancer)":"Non-mutated(No Cancer)"));
					geneticsLogic.train();
					ans.setText("Real Answer:   " + ((geneticsLogic.changeList(ntSequence)[0] > .555f)?"Mutated (Cancer)":"Non-mutated(No Cancer)"));
					train.setText("ANN Trained:   " + (trained?"True":"False"));
				}
			});

			add(results, gbc);
			
			JButton refresh = new JButton("Refresh");
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.ipady = 0;
			gbc.insets = new Insets(360, 500, 0, 0);
			gbc.weighty = 0;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			
			refresh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					guess.setText("ANN's Guess:   ");
					ans.setText("Real Answer:   ");
					train.setText("ANN Trained:   ");
					
				}
			});
			
			add(refresh, gbc);
			
			
		}
	}

}
