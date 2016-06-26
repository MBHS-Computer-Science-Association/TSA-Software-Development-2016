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
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

/**
 * Manages all of the Java Swing components of the demonstration including GUIs
 * and JFrames in order to have separation of logic and style. Reused code for
 * all demonstrations and provides main framework for rendering the demonstration
 * within a graphical window interface.
 * 
 * @author Trevor Nguyen
 *
 */
@SuppressWarnings("serial")
public class DemoWindow extends JFrame {
	/**
	 * Stores the graphical information specific to the demonstration
	 * and allows reuse of code through abstraction. Demo contains information
	 * about the introductory text and 
	 */
	Demonstration demo;

	/**
	 * One of the three main panels that constitutes the JFrame.
	 * Holds the instructions on how to use the demo. Sits on the left
	 * side of the demonstration window.
	 */
	JPanel instructions;
	/**
	 * One of the three main panels that constitutes the JFrame.
	 * Holds the control buttons that allow the user to interface
	 * with the content panel. Main software-user interface that resides
	 * on the bottom of the demonstration window.
	 */
	JPanel control;
	/**
	 * One of the three main panels that constitutes the JFrame.
	 * Sits in the top right corner and dominates the center of the demonstration
	 * window. Displays the main content of the demonstration.
	 */
	JPanel content;

	
	/**
	 * The instructions text on the side bar of every demonstration.
	 */
	JTextComponent introTextArea;
	/**
	 * The scrolling pane in which the instructions text resides.
	 * Located within the instructions JPanel.
	 */
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
	}

	/**
	 * Should trigger auto-resizing to a 4:3 aspect ratio every ms in order to
	 * ensure aspect ratio. Method needs to be only called once.
	 */
	public void lockAspectRatio() {
		// Using an anonymous subclass in order to simplify things.
		new Timer().schedule(new TimerTask() {
			public void run() {
				int width = getWidth();
				int height = getHeight();

				if (Math.abs(width * 3.0 / 4.0 - height) > 5) {
					// Invoking later will execute after pending
					// AWT threads have been processed, reducing
					// potential problems.
					SwingUtilities.invokeLater(new Thread() {

						public void run() {
							int desiredHeight = (int) (width * 3.0 / 4.0);
							int desiredWidth = (int) (height * 4.0 / 3.0);
							
							// If the actual height is greater than
							// the desired height, then the application
							// will choose to change the height;
							if (height > desiredHeight)
								setSize(width, desiredHeight);
							
							// If the actual width is greater than
							// the desired width, then the application
							// will choose to change the width;
							else if (width > desiredWidth)
								setSize(desiredWidth, height);

							// The JFrame will layout all of the components
							// and subcomponents again after a resizing event.
							validate();
						}

					});
				}
			}
		}, 0, 1);
	}

	/**
	 * Creates the three component panels for the application and initializes
	 * all of the constraints and the borders. Constrains the components on a
	 * grid system to ensure they occupy the right proportions of the
	 * application frame.
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
		// Makes so that the instruction size does not flexibly
		// expand into other regions.
		instructions.setPreferredSize(new Dimension(0, 0));

		// Setting constraints for the content panel.
		// Located on the right of the screen.
		constraints.gridx = 4;
		constraints.weightx = 12;
		constraints.gridwidth = 12;
		constraints.gridy = 0;
		constraints.weighty = 9;
		constraints.gridheight = 9;
		add(content, constraints);
		// Makes so that the content size does not flexibly
		// expand into other regions.
		content.setPreferredSize(new Dimension(0, 0));

		// Setting up constraints for the control panel.
		// Located on the bottom of the screen.
		constraints.gridx = 0;
		constraints.weightx = 16;
		constraints.gridwidth = 16;
		constraints.gridy = 9;
		constraints.weighty = 3;
		constraints.gridheight = 3;
		add(control, constraints);
		// Makes so that the control size does not flexibly
		// expand into other regions.
		control.setPreferredSize(new Dimension(0, 0));
	}

	/**
	 * Adds content to the panels. Separates the content from the formatting
	 * of the three main panels.
	 */
	public void fillPanels() {
		introTextArea = new JTextPane();
		((JTextPane) introTextArea).setContentType("text/html");
		introTextArea.setText(demo.getIntroduction());
		
		// Want to have styling here.
		introScrollPane = new JScrollPane(introTextArea);
		introScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		introScrollPane.setBorder(BorderFactory.createEmptyBorder());

		instructions.setLayout(new BorderLayout());
		instructions.add(introScrollPane, BorderLayout.CENTER);

		introTextArea.setEditable(false);
		introTextArea.setBorder(BorderFactory.createEmptyBorder());
		introTextArea.setBackground(instructions.getBackground());
	}

	/**
	 * Sets up the look and feel to the system default using the built in Java
	 * UIManager which adapts to the operating system. Allows the window to be
	 * closed when the exit button is clicked. Initializes the window to certain
	 * dimensions. Layout is set to the GridBagLayout.
	 * 
	 * @param d
	 *            The dimensions of the frame to initialize. These dimensions
	 *            will end up being changed later should the user decide to
	 *            resize the application.
	 */
	public void initializeWindow(Dimension d) {
		// Required "boiler plate" code to make sure the frame will close
		// and operate as expected.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
