package org.ecclesia.demoTemplate;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

import org.ecclesia.demoGenetics.CancerGenomicAnalysis;
import org.ecclesia.demoLines.LineExtrapolationDemo;
import org.ecclesia.demoLogic.DigitalLogicTrainer;
import org.ecclesia.demoMedical.MedicalAnalysisDemonstration;
import org.ecclesia.demoSurvival.GameIntelligenceSimulation;

/**
 * Allows all of the demonstrations to be launched from a single window.
 * This should be the main entry point of the application and all demonstration
 * windows should be invoked from this class.
 * 
 * @author Trevor Nguyen
 *
 */
@SuppressWarnings("serial")
public final class DemoLauncher extends JFrame {
	/**
	 * Holds a list of all of the playable demonstrations
	 * that are available to try out.
	 */
	public static List<Demonstration> demoList;
	
	/**
	 * Statically initializes the list of demonstrations  into
	 * a linked list and adds instances of all of the playable demos.
	 * From this list, the start() method of any demo can be called to
	 * instantiate a GUI for that demonstration.
	 */
	static {
		demoList = new LinkedList<>();
		demoList.add(new CancerGenomicAnalysis());
		demoList.add(new GameIntelligenceSimulation());
		demoList.add(new LineExtrapolationDemo());
		demoList.add(new DigitalLogicTrainer());
		demoList.add(new MedicalAnalysisDemonstration());
	}
	
	/**
	 * Creates a new window for the demo launcher in its
	 * minimum dimensions and sets up any needed parameters.
	 */
	public DemoLauncher() {
		super("Ecclesia Launcher");
		initializeWindow(new Dimension(600, 800));
		createComponents();
//		this.setLocationRelativeTo(null);
		this.setVisible(true);
//		this.pack();
	}
	
	/**
	 * Dynamically creates buttons for each of the
	 * demonstrations in the demoList out of JButtons.
	 */
	private void createComponents() {
		JTextComponent introTextPane = new JTextPane();
		((JTextPane) introTextPane).setContentType("text/html");
		introTextPane.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/introduction.txt")));
		introTextPane.setEditable(false);
		introTextPane.setPreferredSize(new Dimension(0,0));
		JScrollPane introScrollPane = new JScrollPane(introTextPane);
//		this.add(introTextPane);
		this.add(introScrollPane);

		
		QuadrantOrganizer quad = new QuadrantOrganizer();
		quad.setMinimumSize(new Dimension(0, 0));
		this.add(quad);
		
		JLabel label = new JLabel("Select a Demo:", JLabel.CENTER);
		this.add(label);
		
		for (Demonstration demo : demoList) {
			JButton b = new JButton(demo.name);
			
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
	
	/**
	 * Sets up the look and feel to the system default using the built in Java
	 * UIManager which adapts to the operating system. Allows the window to be
	 * closed when the exit button is clicked. Initializes the window to certain
	 * dimensions. Layout is set to the GridBagLayout.
	 * 
	 * Note: For the most part, similar code in org.ecclesia.demoTemplate.DemoWindow
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
//		this.setMaximumSize(d);
//		this.setResizable(false);

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
		
		JTextPane northwest;
		JTextPane northeast;
		JTextPane southwest;
		JTextPane southeast;
		
		/**
		 * Creates all of the components.
		 */
		public void createComponents() {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.setLayout(new GridLayout(2, 2, 15, 15));
			
			northwest = new JTextPane();
			northwest.setContentType("text/html");
			northwest.setEditable(false);
			northwest.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/backPropagationInfo.txt")));
//			northwest.setPreferredSize(new Dimension(0,0));
			
			northeast = new JTextPane();
			northeast.setContentType("text/html");
			northeast.setEditable(false);
			northeast.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/bruteForceInfo.txt")));
//			northeast.setPreferredSize(new Dimension(0,0));
			
			southwest = new JTextPane();
			southwest.setContentType("text/html");
			southwest.setEditable(false);
			southwest.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/geneticAlgorithmInfo.txt")));
//			southwest.setPreferredSize(new Dimension(0,0));
			
			southeast = new JTextPane();
			southeast.setContentType("text/html");
			southeast.setEditable(false);
			southeast.setText(Demonstration.getInstructionsFromFile(new File("demoTemplate/greedyAlgorithmInfo.txt")));
//			southeast.setPreferredSize(new Dimension(0,0));
			
			this.add(northwest);
			this.add(northeast);
			this.add(southwest);
			this.add(southeast);
		}
	}
	
	
	/**
	 * Main entry point into the application. Instantiates one instance
	 * of the DemoLauncher and any demos can be called from that window
	 * by user interaction.
	 * @param args command line arguments are not used nor required for operation.
	 */
	public static void main(String[] args) {
		new DemoLauncher();
	}
}
