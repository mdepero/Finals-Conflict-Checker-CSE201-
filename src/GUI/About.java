package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Constants;

public class About extends JPanel {
	
	public About(){
		
		this.setBackground(Constants.CONTENT_BACKGROUND_COLOR);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		JLabel about = new JLabel(Constants.ABOUT);
		
		this.add(about);
		
	}
	
}
