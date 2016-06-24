package org.ecclesia.demoSurvival;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Christian Duffee
 *
 */
public class DeathNotice {
	public static void display() {

		Runnable r = new Runnable() {
			public void run() {
				String pt1 = "<html><body width='";
				String pt2 = "'><h1>All Malishes have died, restarting ecosystem.</h1>"
						+ "<p></p>";

				// JPanel p = new JPanel( new BorderLayout() );

				int width = 300;
				String s = pt1 + width + pt2;

				JOptionPane.showMessageDialog(null, s);
			}
		};
		SwingUtilities.invokeLater(r);
	}
}
