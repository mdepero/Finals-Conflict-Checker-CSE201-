package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CourseData.Data;
import GUI.About;
import GUI.Edit;
import GUI.Instructions;
import GUI.Report;

public class Main extends JFrame {

	File selectedFile;
	Data dataObject;

	private JPanel mainPanel;

	private CardLayout contentSwitcher;
	private JPanel contentPanel;

	JPanel fileButtons;
	JLabel fileLabel;
	JLabel errorLabel;
	JButton submitButton;
	JButton deleteButton;
	JButton addButton;

	JLabel aboutButton;
	JPanel aboutPanel;
	JLabel instructionsButton;
	JPanel instructionsPanel;
	JLabel editButton;
	JPanel editPanel;
	JLabel searchButton;
	JPanel searchPanel;
	JLabel reportButton;
	JPanel reportPanel;

	public Main() throws Exception {

		dataObject = new Data();

		this.setTitle(Constants.WINDOW_TITLE);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ================ Make and add frame and main panel
		// ===================

		// moves the window to the middle of the screen because it's annoying
		// that it pops up in the corner
		this.setBounds(
				(int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize()
						.getWidth() / 2) - (Constants.MAIN_GUI_WIDTH / 2)),
				100, 0, 0);

		this.setPreferredSize(new Dimension(Constants.MAIN_GUI_WIDTH,
				Constants.MAIN_GUI_HEIGHT));

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Constants.CONTENT_BACKGROUND_COLOR);
		this.add(mainPanel);

		ButtonListener buttonListener = new ButtonListener();

		// =================== Make and add top row ======================
		JPanel topRow = new JPanel();
		topRow.setLayout(new BoxLayout(topRow, BoxLayout.LINE_AXIS));
		topRow.setPreferredSize(new Dimension(0, Constants.TOP_ROW_HEIGHT));
		topRow.setBackground(Constants.MENU_BACKGROUND_COLOR);
		topRow.setBorder(BorderFactory.createMatteBorder(0, 0,
				Constants.TOP_ROW_BORDER_THICKNESS, 0,
				Constants.TOP_ROW_BORDER_COLOR));

		JLabel title = new JLabel();
		Image img = new ImageIcon(Constants.MIAMI_ICON_NAME).getImage();
		img = img.getScaledInstance(Constants.TOP_ROW_HEIGHT,
				Constants.TOP_ROW_HEIGHT, Image.SCALE_SMOOTH);
		title.setIcon(new ImageIcon(img));
		title.setFont(Constants.TITLE_FONT);
		title.setText(Constants.TITLE);
		topRow.add(title);

		topRow.add(Box.createHorizontalGlue());

		JPanel buttonsGroup = new JPanel();
		buttonsGroup
				.setLayout(new BoxLayout(buttonsGroup, BoxLayout.PAGE_AXIS));
		buttonsGroup.setBackground(Constants.MENU_BACKGROUND_COLOR);
		fileButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		fileLabel = new JLabel();
		fileLabel.setFont(Constants.SMALL_FONT);
		addButton = new JButton("Add File");
		addButton.addActionListener(buttonListener);
		deleteButton = new JButton("Remove");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(buttonListener);
		submitButton = new JButton("Submit");
		submitButton.setEnabled(false);
		submitButton.addActionListener(buttonListener);

		fileButtons.add(fileLabel);
		fileButtons.add(addButton);
		fileButtons.add(deleteButton);
		fileButtons.add(submitButton);
		fileButtons.setBackground(Constants.MENU_BACKGROUND_COLOR);

		errorLabel = new JLabel();
		errorLabel.setFont(Constants.SMALL_FONT);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Constants.MENU_BACKGROUND_COLOR);

		buttonsGroup.add(fileButtons);
		buttonsGroup.add(errorLabel);

		topRow.add(buttonsGroup);

		mainPanel.add(topRow, BorderLayout.NORTH);

		// =================== Make and add side bar ======================

		MouseListener sideButtonListener = new SideButtonListener();

		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
		sideBar.setBackground(Constants.MENU_BACKGROUND_COLOR);
		// create 10 pixels of padding
		sideBar.setBorder(BorderFactory.createLineBorder(
				Constants.MENU_BACKGROUND_COLOR, 10));

//		// search button
//		searchButton = new JLabel();
//		searchButton.setFont(Constants.MAIN_FONT);
//		Image searchImg = new ImageIcon(Constants.SEARCH_ICON_NAME).getImage();
//		searchImg = searchImg.getScaledInstance(Constants.MAIN_FONT.getSize(),
//				Constants.MAIN_FONT.getSize(), Image.SCALE_SMOOTH);
//		searchButton.setIcon(new ImageIcon(searchImg));
//		searchButton.setText("Search");
//		searchButton.addMouseListener(sideButtonListener);
//		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// about button
		aboutButton = new JLabel();
		aboutButton.setFont(Constants.MAIN_FONT);
		Image aboutImg = new ImageIcon(Constants.ABOUT_ICON_NAME).getImage();
		aboutImg = aboutImg.getScaledInstance(Constants.MAIN_FONT.getSize(),
				Constants.MAIN_FONT.getSize(), Image.SCALE_SMOOTH);
		aboutButton.setIcon(new ImageIcon(aboutImg));
		aboutButton.setText("About");
		aboutButton.addMouseListener(sideButtonListener);
		aboutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// instructions button
		instructionsButton = new JLabel();
		instructionsButton.setFont(Constants.MAIN_FONT);
		Image instructionsImg = new ImageIcon(Constants.INSTRUCTIONS_ICON_NAME)
				.getImage();
		instructionsImg = instructionsImg.getScaledInstance(
				Constants.MAIN_FONT.getSize(), Constants.MAIN_FONT.getSize(),
				Image.SCALE_SMOOTH);
		instructionsButton.setIcon(new ImageIcon(instructionsImg));
		instructionsButton.setText("Instructions");
		instructionsButton.addMouseListener(sideButtonListener);
		instructionsButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));

//		// edit button
//		editButton = new JLabel();
//		editButton.setFont(Constants.MAIN_FONT);
//		Image editImg = new ImageIcon(Constants.EDIT_ICON_NAME).getImage();
//		editImg = editImg.getScaledInstance(Constants.MAIN_FONT.getSize(),
//				Constants.MAIN_FONT.getSize(), Image.SCALE_SMOOTH);
//		editButton.setIcon(new ImageIcon(editImg));
//		editButton.setText("Edit Schedule");
//		editButton.addMouseListener(sideButtonListener);
//		editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// report button
		reportButton = new JLabel();
		reportButton.setFont(Constants.MAIN_FONT);
		Image reportImg = new ImageIcon(Constants.REPORT_ICON_NAME).getImage();
		reportImg = reportImg.getScaledInstance(Constants.MAIN_FONT.getSize(),
				Constants.MAIN_FONT.getSize(), Image.SCALE_SMOOTH);
		reportButton.setIcon(new ImageIcon(reportImg));
		reportButton.setText("Full Report");
		reportButton.addMouseListener(sideButtonListener);
		reportButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel teamName = new JLabel("Made By: " + Constants.TEAM_NAME);
		teamName.setFont(Constants.SMALL_ITALIC_FONT);

//		sideBar.add(searchButton);
//		sideBar.add(Box.createVerticalStrut(Constants.SIDE_BAR_SPACING));
		sideBar.add(reportButton);
		sideBar.add(Box.createVerticalStrut(Constants.SIDE_BAR_SPACING));
		sideBar.add(instructionsButton);
		sideBar.add(Box.createVerticalStrut(Constants.SIDE_BAR_SPACING));
		sideBar.add(aboutButton);
//		sideBar.add(Box.createVerticalStrut(Constants.SIDE_BAR_SPACING));
//		sideBar.add(editButton);

		sideBar.add(Box.createVerticalGlue());
		sideBar.add(teamName);

		mainPanel.add(sideBar, BorderLayout.WEST);

		contentSwitcher = new CardLayout();
		contentPanel = new JPanel(contentSwitcher);
		contentPanel.setBorder(BorderFactory.createLineBorder(
				Constants.CONTENT_BACKGROUND_COLOR, 10));
		aboutPanel = new About();
		instructionsPanel = new Instructions();
		editPanel = new Edit();

		JPanel blank = new JPanel();
		blank.setBackground(Constants.CONTENT_BACKGROUND_COLOR);
		contentPanel.add(blank, "BLANK");
		contentPanel.add(aboutPanel, "ABOUT");
		contentPanel.add(instructionsPanel, "INSTRUCTIONS");
		contentPanel.add(editPanel, "EDIT");
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		this.pack();

		this.setVisible(true);
		
		// process initial report, which is blank
		fileInitialReport();
	}

	private void alert(String text, boolean type) {
		if (type)
			errorLabel.setForeground(Color.BLACK);
		else
			errorLabel.setForeground(Color.RED);

		errorLabel.setText(text);
	}

	private void clearAlert() {

		errorLabel.setText("");
	}
	
	int i = 0;

	private void fileReport() {
		
		if (reportPanel != null)
			contentSwitcher.removeLayoutComponent(reportPanel);
		
		reportPanel = new Report(dataObject);

		contentPanel.add(reportPanel, "REPORT");

		contentSwitcher.show(contentPanel, "REPORT");
	}
	
	private void fileInitialReport() {
		if (reportPanel != null)
			contentSwitcher.removeLayoutComponent(reportPanel);
		
		reportPanel = new Report(dataObject);

		contentPanel.add(reportPanel, "REPORT");

	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// reset error label before every action
			clearAlert();

			if (e.getSource() == addButton) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					fileLabel.setText(selectedFile.getName());
					deleteButton.setEnabled(true);
					submitButton.setEnabled(true);

				} else {

					alert("No file was selected", false);
				}

			}
			if (e.getSource() == deleteButton) {

				selectedFile = null;
				fileLabel.setText("");
				deleteButton.setEnabled(false);
				submitButton.setEnabled(false);

			}
			if (e.getSource() == submitButton) {

				if (selectedFile == null || !selectedFile.canRead()) {

					alert("No file selected or cannot read file", false);
					return;

				}

				String result = dataObject.readNewCourseData(selectedFile);

				if (!result.replace("Error", "").equals(result))
					alert(result, false);
				else{
					fileReport();
					alert(result, true);
				}

				selectedFile = null;
				fileLabel.setText("");
				deleteButton.setEnabled(false);
				submitButton.setEnabled(false);
				
			}

		}

	}

	public class SideButtonListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			// reset error label before every action
			clearAlert();

			if (e.getSource() == aboutButton) {

				contentSwitcher.show(contentPanel, "ABOUT");

			}
			if (e.getSource() == instructionsButton) {

				contentSwitcher.show(contentPanel, "INSTRUCTIONS");

			}
			if (e.getSource() == editButton) {

				contentSwitcher.show(contentPanel, "EDIT");

			}
			if (e.getSource() == reportButton) {

				contentSwitcher.show(contentPanel, "REPORT");

			}
		}

	}

	public static void main(String[] args) throws Exception {

		new Main();

	}// end main
}
