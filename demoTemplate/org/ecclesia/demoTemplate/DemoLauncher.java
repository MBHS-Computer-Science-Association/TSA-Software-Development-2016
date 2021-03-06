package org.ecclesia.demoTemplate;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

import org.ecclesia.demoGenetics.CancerGenomicAnalysis;
import org.ecclesia.demoLines.LineExtrapolationDemo;
import org.ecclesia.demoLogic.DigitalLogicDemonstration;
import org.ecclesia.demoMedical.MedicalAnalysisDemonstration;
import org.ecclesia.demoSurvival.GameIntelligenceSimulation;

/**
 * Allows all of the demonstrations to be launched from a single window. This
 * should be the main entry point of the application and all demonstration
 * windows should be invoked from this class.
 * 
 * @author Trevor Nguyen
 *
 */
@SuppressWarnings("serial")
public final class DemoLauncher extends JFrame {
	/**
	 * Holds a list of all of the playable demonstrations that are available to
	 * try out.
	 */
	public static List<Demonstration> demoList;

	/**
	 * Statically initializes the list of demonstrations into a linked list and
	 * adds instances of all of the playable demos. From this list, the start()
	 * method of any demo can be called to instantiate a GUI for that
	 * demonstration.
	 */
	static {
		demoList = new LinkedList<>();
		demoList.add(new LineExtrapolationDemo());
		demoList.add(new GameIntelligenceSimulation());
		demoList.add(new MedicalAnalysisDemonstration());
		demoList.add(new DigitalLogicDemonstration());
		demoList.add(new CancerGenomicAnalysis());
	}

	/**
	 * Creates a new window for the demo launcher in its minimum dimensions and
	 * sets up any needed parameters.
	 */
	public DemoLauncher() {
		super("Ecclesia Launcher");
		
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Dimension screenSize = environment.getMaximumWindowBounds().getSize();
		
		Dimension windowSize = null;
		if (screenSize.getWidth() > 630 && screenSize.getHeight() > 950) {
			windowSize = new Dimension(630, 950);
			this.setResizable(false);
		} else {
			windowSize = new Dimension(975, 750);
			this.setResizable(true);
		}
		initializeWindow(windowSize);
		createComponents();
		this.setVisible(true);
	}

	/**
	 * Dynamically creates buttons for each of the demonstrations in the
	 * demoList out of JButtons.
	 */
	private void createComponents() {
		JTextComponent introTextPane = new JTextPane();
		((JTextPane) introTextPane).setContentType("text/html");
		introTextPane.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/introduction.txt")));
		introTextPane.setEditable(false);
		introTextPane.setPreferredSize(new Dimension(0, 0));
		introTextPane.setBackground(getBackground());
		introTextPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(introTextPane);

		QuadrantOrganizer quad = new QuadrantOrganizer();
		quad.setMinimumSize(new Dimension(0, 0));
		this.add(quad);

		JComponent buttonsHolder = new JComponent() {
			{
				this.setLayout(new GridLayout(demoList.size() + 1, 1));
				JLabel label = new JLabel("Select a Demonstration:", JLabel.CENTER);
				label.setFont(label.getFont().deriveFont(Font.BOLD, 16f));
				this.add(label);
				for (Demonstration demo : demoList) {
					JButton b = new JButton(demo.name);
					b.setFont(b.getFont().deriveFont(14f));

					// Links the button to the demonstration
					// start() method.
					b.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							demo.run();
							demo.startWindow();
						}
					});

					this.add(b);
				}
			}
		};

		this.add(buttonsHolder);

	}

	/**
	 * Sets up the look and feel to the system default using the built in Java
	 * UIManager which adapts to the operating system. Allows the window to be
	 * closed when the exit button is clicked. Initializes the window to certain
	 * dimensions. Layout is set to the GridBagLayout.
	 * 
	 * Note: For the most part, similar code in
	 * org.ecclesia.demoTemplate.DemoWindow
	 * 
	 * @param d
	 *            The dimensions of the frame to initialize. These dimensions
	 *            will end up being changed later should the user decide to
	 *            resize the application.
	 */
	private void initializeWindow(Dimension d) {
		// Required "boiler plate" code to make sure the frame will close
		// and operate as expected.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(d);
		// this.setMaximumSize(d);
		// this.setResizable(false);

		// BoxLayout was chosen for the demo launcher to
		// allow for an easily scalable layout to meet the
		// needs of a dynamic list of demonstrations.
		Container contentPanel = this.getContentPane();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

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

	/**
	 * Manages the quadrant of methods used in artificial intelligence.
	 * 
	 * @author Trevor Nguyen
	 *
	 */
	class QuadrantOrganizer extends JComponent {
		public QuadrantOrganizer() {
			createComponents();
		}

		/**
		 * Creates all of the components.
		 */
		public void createComponents() {
			JTextPane northwest;
			JTextPane northeast;
			JTextPane southwest;
			JTextPane southeast;
			this.setLayout(new GridLayout(2, 2, 15, 15));

			northwest = new JTextPane();
			northwest.setContentType("text/html");
			northwest.setEditable(false);
			northwest.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/backPropagationInfo.txt")));
			northwest.setBackground(new Color(245, 245, 245));
			
			northeast = new JTextPane();
			northeast.setContentType("text/html");
			northeast.setEditable(false);
			northeast.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/bruteForceInfo.txt")));
			northeast.setBackground(new Color(245, 245, 245));
			
			southwest = new JTextPane();
			southwest.setContentType("text/html");
			southwest.setEditable(false);
			southwest.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/geneticAlgorithmInfo.txt")));
			southwest.setBackground(new Color(245, 245, 245));
			
			southeast = new JTextPane();
			southeast.setContentType("text/html");
			southeast.setEditable(false);
			southeast.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/greedyAlgorithmInfo.txt")));
			southeast.setBackground(new Color(245, 245, 245));
			
			this.add(northwest);
			this.add(northeast);
			this.add(southwest);
			this.add(southeast);
		}
	}

	/**
	 * Main entry point into the application. Instantiates one instance of the
	 * DemoLauncher and any demos can be called from that window by user
	 * interaction.
	 * 
	 * @param args
	 *            command line arguments are not used nor required for
	 *            operation.
	 */
	public static void main(String[] args) {
		new DemoLauncher();
	}
}
