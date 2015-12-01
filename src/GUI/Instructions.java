package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Constants;

public class Instructions extends JPanel {

	public Instructions() {

		this.setBackground(Constants.CONTENT_BACKGROUND_COLOR);

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel about = new JLabel(Constants.INSTRUCTIONS);// Constants.ABOUT);

		this.add(about);

	}

}
