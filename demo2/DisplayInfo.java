import java.awt.*;
import javax.swing.*;

public class DisplayInfo {

	public static void display() {

		Runnable r = new Runnable() {
			public void run() {
				String pt1 = "<html><body width='";
				String pt2 = "'><h1>Demonstration Info</h1>"
						+ "<p>This demo presents you with the training of a neural network."
						+ " Each <font color=\"green\">Malish</font> (the green circles) is looking a certain way <font color=\"green\">(the blue lines)</font>."
						+ " If a piece of <font color=\"magenta\">food</font> (the squares) is in a <font color=\"green\">Malish's</font> field of view, its relative postion is passed into the <font color=\"green\">Malish's</font> neural network."
						+ " These neural networks outputs whether the <font color=\"green\">Malish</font> turns left, right, or continues straight."
						+ " The Malishes that find <font color=\"magenta\">food</font> reproduced and pass on their networks to their children in which random mutations can improve them."
						+ " If a <font color=\"green\">Malish</font> fails to find food it turns <font color=\"yellow\">yellow,<font> then <font color=\"red\">red,</font> and eventually dies."
						+ " Thus the <font color=\"green\">Malish's</green> that have networks that can better find food are selected for and modified in a process simular to Darwinian evolution.";

				// JPanel p = new JPanel( new BorderLayout() );

				int width = 300;
				String s = pt1 + width + pt2;

				JOptionPane.showMessageDialog(null, s);
			}
		};
		SwingUtilities.invokeLater(r);
	}
}