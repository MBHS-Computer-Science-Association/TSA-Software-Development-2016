package org.ecclesia.demoGenetics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.BoxLayout;
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

		protected char[] ntSequence;

		public ContentPanel() {
			createComponents();
			ntSequence = new char[10];
		}

		private void createComponents() {
//			this.setLayout(new BorderLayout(0, 15));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JLabel norm = new JLabel("Cancer Genetic Analysis", JLabel.CENTER);
			norm.setFont(norm.getFont().deriveFont(Font.BOLD, 20f));
			add(norm/*, BorderLayout.NORTH*/);

			add(new JComponent() {
				{

					setLayout(new SpringLayout());
					JLabel fixed = new JLabel("Non-mutated EGFR gene:");
					add(fixed);

					for (Character c : "ATGCGACCCT".toCharArray()) {
						JLabel label = new JLabel(c.toString(), JLabel.CENTER);
						label.setFont(label.getFont().deriveFont(Font.BOLD));
						add(label);
					}

					JRadioButton[][] buttonArray = new JRadioButton[4][10];
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
									//geneticsLogic.changeList(ntSequence);
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

					buttonArray[0][0].setSelected(true);
					buttonArray[3][1].setSelected(true);
					buttonArray[2][2].setSelected(true);
					buttonArray[1][3].setSelected(true);
					buttonArray[2][4].setSelected(true);
					buttonArray[0][5].setSelected(true);
					buttonArray[1][6].setSelected(true);
					buttonArray[1][7].setSelected(true);
					buttonArray[1][8].setSelected(true);
					buttonArray[3][9].setSelected(true);

					SpringUtilities.makeCompactGrid(this, 5, 11, 3, 3, 3, 3);
				}
			}/*, BorderLayout.CENTER*/);
			
			add(new JComponent() {
				{
					setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

					JLabel fixed = new JLabel("Non-mutated EGFR gene:");
					fixed.setFont(fixed.getFont().deriveFont(Font.BOLD, 20f));
					this.add(fixed, JLabel.CENTER);
					
					JLabel label2 = new JLabel("ATGCGACCCT");
					label2.setFont(fixed.getFont().deriveFont(Font.BOLD, 20f));
					this.add(label2, JLabel.CENTER);
					
					JLabel results = new JLabel("Results: ");
					results.setFont(fixed.getFont().deriveFont(Font.BOLD, 20f));
					this.add(results);
					
					JButton generate = new JButton("Generate");
					generate.setFont(fixed.getFont().deriveFont(Font.BOLD, 20f));
					this.add(generate);
					
					generate.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
//							String s = "s";
							results.setText("Results: " + (geneticsLogic.changeList(ntSequence)[0] > .5F
									? "mutated genes" : "non-mutated genes"));
						}
					});
				}
				
			}/*, BorderLayout.SOUTH*/);
			
		}
	}

}
