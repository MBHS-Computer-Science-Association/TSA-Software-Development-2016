package org.ecclesia.demoGenetics;

import javax.swing.JPanel;

import org.ecclesia.demoTemplate.Demonstration;

public class CancerGenomicAnalysis extends Demonstration {
	JPanel contentPanel;
	JPanel controlPanel;

	public CancerGenomicAnalysis() {
		super("Artificial Neural Networks in Cancer Genetic Studies");
		contentPanel = new ContentPanel();
		controlPanel = new ControlPanel();
	}

	@Override
	public String getIntroduction() {
		return "Cancer. This is what it's about. Do you know what the most important"
				+ " aspect of life is? Death. It's the death due to the "
				+ "uncontrolled proliferation of life. How ironic. "
				+ "Yet, this is not an English class and we need to teach "
				+ "you how to use this software.  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam mollis venenatis lorem a faucibus. Nam eros quam, pellentesque porta rutrum vel, bibendum in augue. Duis molestie, massa at pharetra pretium, purus magna condimentum tortor, ut tincidunt quam est ut justo. Nullam finibus bibendum urna, ac dignissim quam lobortis a. Aenean fermentum nibh tincidunt lorem efficitur, iaculis malesuada justo scelerisque. Aenean molestie laoreet magna, et gravida purus fermentum sit amet. Nam vel felis malesuada, facilisis nunc et, mollis ante. Nullam sed magna mi. Quisque venenatis id ex at pretium. Nam id metus lorem. Curabitur lacinia id enim ac pharetra. Aliquam feugiat nunc leo, sed eleifend tortor pellentesque id. Morbi est nunc, hendrerit quis cursus eu, semper sed lorem. Proin sed rhoncus dui, in rhoncus lectus. Mauris aliquet lorem sed feugiat tempor. Sed et metus nisi. ";
	}

	@Override
	public JPanel getContentPanel() {
		return contentPanel;
	}

	@Override
	public JPanel getControlPanel() {
		return controlPanel;
	}

	class ContentPanel extends JPanel {

	}

	class ControlPanel extends JPanel {

	}
}
