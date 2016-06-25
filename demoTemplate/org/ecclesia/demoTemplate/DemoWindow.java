package org.ecclesia.demoTemplate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Manages all of the Java Swing components of the demonstration including GUIs
 * and JFrames in order to have separation of logic and style.
 * 
 * @author Trevor Nguyen
 *
 */
public class DemoWindow extends JFrame {
	Demonstration demo;

	// Three main panels that constitute the JFrame
	JPanel instructions;
	JPanel control;
	JPanel content;

	// Instructions Area
	JTextArea introTextArea;
	JScrollPane introScrollPane;

	/**
	 * Constructs a demonstration window to display a GUI for any demonstration.
	 * 
	 * @param demo
	 *            A non-graphical demonstration object that contains information
	 *            in regards to text boxes and titles as well as component
	 *            information.
	 */
	public DemoWindow(Demonstration demo) {
		super(demo.name);

		this.demo = demo;

		// Initializes the platform specific style of buttons and window frames
		// to provide a native experience to the user.
		initializeWindow(new Dimension(800, 600));

		createComponents();
		fillPanels();
		lockAspectRatio();
		setVisible(true);
		formatComponents();
	}

	/**
	 * Should trigger auto-resizing to a 4:3 aspect ratio on window resizing
	 * event.
	 */
	public void lockAspectRatio() {
		// Using an anonymous subclass in order to simplify things.
		new Timer().schedule(new TimerTask() {
			public void run() {
				int width = getWidth();
				int height = getHeight();

				if (Math.abs(width * 3.0 / 4.0 - height) > 5) {
					// Run this as soon as possible without interrupting any
					// tasks.
					SwingUtilities.invokeLater(new Thread() {

						public void run() {
							if (width * 3.0 / 4.0 < height)
								setSize(width, (int) (width * 3.0 / 4.0));
							else
								setSize((int)(height * 4.0 / 3.0), height);

							formatComponents();
						}

					});
				}
			}
		}, 0, 50);
	}

	/**
	 * Creates the three component panels for the application and initializes
	 * all of the constraints and the borders.
	 */
	public void createComponents() {
		// Initializing the panels for each content pane.
		instructions = new JPanel();
		content = demo.getContentPanel();
		control = demo.getControlPanel();

		// Giving defined borders for each panel.
		instructions.setBorder(BorderFactory.createLineBorder(Color.black));
		control.setBorder(BorderFactory.createLineBorder(Color.black));
		content.setBorder(BorderFactory.createLineBorder(Color.black));

		// Using this constraints object to arrange the components
		// in the GridBagLayout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		// Setting constraints for the instructions panel.
		// Located on the left of the screen.
		constraints.gridx = 0;
		constraints.weightx = 4;
		constraints.gridwidth = 4;
		constraints.gridy = 0;
		constraints.weighty = 9;
		constraints.gridheight = 9;
		add(instructions, constraints);

		// Setting constraints for the content panel.
		// Located on the right of the screen.
		constraints.gridx = 4;
		constraints.weightx = 12;
		constraints.gridwidth = 12;
		constraints.gridy = 0;
		constraints.weighty = 9;
		constraints.gridheight = 9;
		add(content, constraints);

		// Setting up constraints for the control panel.
		// Located on the bottom of the screen.
		constraints.gridx = 0;
		constraints.weightx = 16;
		constraints.gridwidth = 16;
		constraints.gridy = 9;
		constraints.weighty = 3;
		constraints.gridheight = 3;
		add(control, constraints);
	}

	public void fillPanels() {
		introTextArea = new JTextArea(demo.getIntroduction());
		introScrollPane = new JScrollPane(introTextArea);

		instructions.setLayout(new BorderLayout());
		instructions.add(introScrollPane, BorderLayout.CENTER);

		introTextArea.setWrapStyleWord(true);
		introTextArea.setLineWrap(true);
		introTextArea.setEditable(false);

		introScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void formatComponents() {
		validate();
	}

	/**
	 * Sets up the look and feel to the system default using the built in Java
	 * UIManager which adapts to the operating system. Allows the window to be
	 * closed when the exit button is clicked. Initializes the window to certain
	 * dimensions. Layout is set to the GridBagLayout.
	 */
	public void initializeWindow(Dimension d) {
		// Required "boiler plate" code to make sure the frame will close
		// and operate as expected.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(d);
		setMinimumSize(d);

		// GridBagLayout was chosen to allow for a uniform
		// layout design while providing flexibility.
		setLayout(new GridBagLayout());

		// Creates the look and feel of the native operating system.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
