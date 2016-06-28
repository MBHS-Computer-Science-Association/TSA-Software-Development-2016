package org.ecclesia.demoLogic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.ecclesia.demoTemplate.Demonstration;

public class DigitalLogicDemonstration extends Demonstration {
	XORLogicTrainer logicTrainer;
	ContentPanel content;
	public DigitalLogicDemonstration() {
		super("Digital Logic Analysis");
		this.setIntroduction(Demonstration.getInstructionsFromFile(new File("demoLogic/introduction.txt")));
	}

	@Override
	public void run() {
		logicTrainer = new XORLogicTrainer();
		content = new ContentPanel();
		this.setContentPanel(content);
		this.setControlPanel(new ControlPanel());
	}

	JLabel in;
	JLabel in1;
	JLabel in2;
	JLabel in3;
	JLabel in4;

	JLabel ou;
	JTextField ou1;
	JTextField ou2;
	JTextField ou3;
	JTextField ou4;

	JLabel rou;
	JTextField rou1;
	JTextField rou2;
	JTextField rou3;
	JTextField rou4;

	JLabel cor;
	JTextField cor1;
	JTextField cor2;
	JTextField cor3;
	JTextField cor4;

	@SuppressWarnings("serial")
	class ControlPanel extends JPanel {
		public ControlPanel() {
			createComponents();
		}

		public void createComponents() {
			JButton train = new JButton("Train");
			JProgressBar bar = new JProgressBar();
			this.add(train);
			this.add(bar);
			train.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("PRESSED");
					bar.setIndeterminate(true);
					
					XORLogicTrainer xor = new XORLogicTrainer();
					xor.trainXor();
					float[] data = xor.getOutput();
					System.out.println("DONE");
					ou1.setText(round(data[0]));
					ou2.setText(round(data[1]));
					ou3.setText(round(data[2]));
					ou4.setText(round(data[3]));

					rou1.setText("" + Math.round(data[0]));
					rou2.setText("" + Math.round(data[1]));
					rou3.setText("" + Math.round(data[2]));
					rou4.setText("" + Math.round(data[3]));

					if (Math.round(data[0]) == 0) {
						cor1.setText("Correct");
						System.out.println(cor1.getText());
						cor1.setForeground(Color.GREEN);
					} else {
						System.out.println("Incorrect");
						cor1.setText("Incorrect");
						cor1.setForeground(Color.RED);
					}

					if (Math.round(data[1]) == 1) {
						cor2.setText("Correct");
						cor2.setForeground(Color.GREEN);
					} else {
						cor2.setText("Incorrect");
						cor2.setForeground(Color.RED);
					}

					if (Math.round(data[2]) == 1) {
						cor3.setText("Correct");
						cor3.setForeground(Color.GREEN);
					} else {
						cor3.setText("Incorrect");
						cor3.setForeground(Color.RED);
					}

					if (Math.round(data[3]) == 0) {
						cor4.setText("Correct");
						cor4.setForeground(Color.GREEN);
					} else {
						cor4.setText("Incorrect");
						cor4.setForeground(Color.RED);
					}
					bar.setIndeterminate(false);

					//content.repaint();
				}
			});

		}
	}

	/**
	 * rounds the value to 2 decimal values
	 * 
	 * @param v
	 * @return the rounded value
	 */
	public String round(float v) {
		return String.format("%.2f", v);
	}

	@SuppressWarnings("serial")
	class ContentPanel extends JPanel {
		public ContentPanel() {
			createComponents();
		}

		public void createComponents() {
			in = new JLabel("Input:");
			in1 = new JLabel("0,0");
			in2 = new JLabel("0,1");
			in3 = new JLabel("1,0");
			in4 = new JLabel("1,1");

			ou = new JLabel("Output:");
			ou1 = new JTextField("");
			ou2 = new JTextField("");
			ou3 = new JTextField("");
			ou4 = new JTextField("");

			rou = new JLabel("Rounded Output:");
			rou1 = new JTextField("");
			rou2 = new JTextField("");
			rou3 = new JTextField("");
			rou4 = new JTextField("");

			cor = new JLabel("Correct?");
			cor1 = new JTextField("");
			cor2 = new JTextField("");
			cor3 = new JTextField("");
			cor4 = new JTextField("");
			this.add(in);
			this.add(in1);
			this.add(in2);
			this.add(in3);
			this.add(in4);

			this.add(ou);
			this.add(ou1);
			this.add(ou2);
			this.add(ou3);
			this.add(ou4);

			this.add(rou);
			this.add(rou1);
			this.add(rou2);
			this.add(rou3);
			this.add(rou4);

			this.add(cor);
			this.add(cor1);
			this.add(cor2);
			this.add(cor3);
			this.add(cor4);
		}
	}
}