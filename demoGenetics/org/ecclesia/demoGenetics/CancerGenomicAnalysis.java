package org.ecclesia.demoGenetics;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

		public ContentPanel() {
			createComponents();
		}

		private void createComponents() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			
			
			JLabel norm = new JLabel("Genetic Analysis Pro V2", JLabel.CENTER);
			norm.setFont(norm.getFont().deriveFont(Font.BOLD, 20f));
			add(norm);

			JTextArea modify = new JTextArea(
					"Modify these nucleotides to and see what the Neural network will predict: Whether or not this is mutated");
			
			modify.setEditable(false);
			modify.setLineWrap(true);
			modify.setBackground(this.getBackground());
			add(modify);

			add(new JComponent() {
				{
					SpringLayout layout = new SpringLayout();
					setLayout(layout);
					layout.putConstraint(SpringLayout.WEST, norm, 5, SpringLayout.WEST, this);
					layout.putConstraint(SpringLayout.NORTH, norm, 5, SpringLayout.NORTH, this);

					JLabel fixed = new JLabel("Normal EGFR gene:");
					add(fixed);

					JLabel geneSeq = new JLabel("A T G C G A C C C T");
					for (Character c : "ATGCGACCCT".toCharArray()) {
						JLabel label = new JLabel(c.toString(), JLabel.CENTER);
						label.setFont(label.getFont().deriveFont(Font.BOLD));
						add(label);
					}

					JLabel user = new JLabel("Editable nucleotide sequence:");
					JTextField g1 = new JTextField("A");
					JTextField g2 = new JTextField("T");
					JTextField g3 = new JTextField("G");
					JTextField g4 = new JTextField("C");
					JTextField g5 = new JTextField("G");
					JTextField g6 = new JTextField("A");
					JTextField g7 = new JTextField("C");
					JTextField g8 = new JTextField("C");
					JTextField g9 = new JTextField("C");
					JTextField g0 = new JTextField("T");

					add(user);
					add(g1);
					add(g2);
					add(g3);
					add(g4);
					add(g5);
					add(g6);
					add(g7);
					add(g8);
					add(g9);
					add(g0);
					SpringUtilities.makeCompactGrid(this, 2, 11, 3, 3, 3, 3);
				}
			});

		}
	}

}
