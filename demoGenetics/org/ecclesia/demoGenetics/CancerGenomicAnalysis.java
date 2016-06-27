package org.ecclesia.demoGenetics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

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
			this.setLayout(new BorderLayout(0, 15));
			
			
			JLabel norm = new JLabel("Genetic Oncogene Analysis", JLabel.CENTER);
			norm.setFont(norm.getFont().deriveFont(Font.BOLD, 20f));
			add(norm, BorderLayout.NORTH);


			add(new JComponent() {
				{
					setLayout(new SpringLayout());		
					JLabel fixed = new JLabel("Wild type EGFR gene:");
					add(fixed);

					for (Character c : "ATGCGACCCT".toCharArray()) {
						JLabel label = new JLabel(c.toString(), JLabel.CENTER);
						label.setFont(label.getFont().deriveFont(Font.BOLD));
						add(label);
					}

					JLabel user = new JLabel("Sally Smith's sequence:");
					add(user);

					List<JTextField> fieldList = new ArrayList<>(11);
					
					//JRadio
					
					for (Character c : "ATGCGACCCT".toCharArray()) {
						JTextField field = new JTextField(c.toString());
						fieldList.add(field);
					}
					
					for (int i = 0; i < fieldList.size(); i++) {
						JTextField field = fieldList.get(i);
						field.addCaretListener(new CaretListener() {

							@Override
							public void caretUpdate(CaretEvent e) {
								String text = field.getText();
								if (text != null && text.length() != 0) {
									text = text.substring(0, 1);
								}

								try {
									field.getDocument().remove(1, field.getDocument().getLength());
								} catch (BadLocationException e1) {
									e1.printStackTrace();
								}
							}
							
						});
					}
					
					for (JComponent comp : fieldList)
						add(comp);
					
					SpringUtilities.makeCompactGrid(this, 2, 11, 3, 3, 3, 3);
				}
			}, BorderLayout.SOUTH);

			JTextPane modify = new JTextPane();
			modify.setContentType("text/html");
			modify.setText(Demonstration.getInstructionsFromFile(new File("demoGenetics/profile.txt")));
			modify.setEditable(false);
			modify.setBackground(this.getBackground());
//			modify.setPreferredSize(new Dimension(0, 0));
			add(modify, BorderLayout.CENTER);
		}
	}

}
