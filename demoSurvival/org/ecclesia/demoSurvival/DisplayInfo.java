package org.ecclesia.demoSurvival;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DisplayInfo {

	public static void display() {

		Runnable r = new Runnable() {
			public void run() {
				String pt1 = "<html><body width='";
				String pt2 = "'><h1>Demonstration Info</h1>"
						+ "<p>This demo presents you with the training of a neural network."
						+ " In this presentation we have created creatures dubbed <font color=\"green\">Malishes (the green circles)</font>."
						+ " If a piece of <font color=\"blue\">food (the squares)</font> is in a <font color=\"green\">Malish's</font> field of view, its relative postion is passed into a <font color=\"green\">Malish's</font> neural network."
						+ " These neural networks outputs whether the <font color=\"green\">Malish</font> turns left, right, or continues straight."
						+ " The Malishes that find <font color=\"blue\">food</font> reproduced and pass on their networks to their children in which random mutations can improve them."
						+ " If a <font color=\"green\">Malish</font> fails to find food it turns <font color=\"yellow\">yellow,</font> then <font color=\"red\">red,</font> and eventually dies."
						+ " Thus the <font color=\"green\">Malish's</font> that have improved neural networks will survive longer and reproduce more offspring with the improved neural networks. This shows our neural network software toolkit"
						+ " and its powerful capabilities when implemented within a project";
						

				// JPanel p = new JPanel( new BorderLayout() );

				int width = 300;
				String s = pt1 + width + pt2;

				JOptionPane.showMessageDialog(null, s);
			}
		};
		SwingUtilities.invokeLater(r);
	}
}