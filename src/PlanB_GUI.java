import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import CourseData.Data;

public class PlanB_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane2;
	private ArrayList<File> selectedFiles;
	private JButton chsFile;
	private JButton rmFile;
	private JButton submitBttn;
	private JLabel fileName;
	private boolean submitted = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanB_GUI frame = new PlanB_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// end main

	public PlanB_GUI() {
		selectedFiles = new ArrayList<File>();

		mainGUI();
	}// end constructor PlanB_GUI

	// Inner listener class
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Event when a file is added
			if (event.getSource() == chsFile) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFiles.add(fileChooser.getSelectedFile());
					fileName.setText("<html>");

					for (int i = 0; i < selectedFiles.size(); i++) {
						fileName.setText(fileName.getText()
								+ selectedFiles.get(i).getName() + "<br>");
					}

					fileName.setText(fileName.getText() + "</html>");
				}// end if

				if (!(selectedFiles.isEmpty())) {
					contentPane.add(rmFile);
					contentPane.repaint();
				}// end if
				
			}// end if

			// event when a file is removed
			if (event.getSource() == rmFile) {
				selectedFiles = new ArrayList<File>();
				fileName.setText(" ");
				contentPane.remove(rmFile);
				contentPane.repaint();
			}

			// event when a file is submitted
			if (event.getSource() == submitBttn) {

				if (!selectedFiles.isEmpty()) {

					Data dataObject = new Data();
					try {
						for (int i = 0; i < selectedFiles.size(); i++) {
							dataObject.readNewCourseData(selectedFiles.get(i));
						}// end for

						dispose();
						
						dataObject.setCategories();
						
						dataObject.findConflicts();
						
						Report_GUI report = new Report_GUI(dataObject);
						report.setVisible(true);
					} // end try
					catch (Exception e) {
						fileName.setText("INVALID FILE");
						// makes it so that there are no files if it is invalid
						selectedFiles = new ArrayList<File>();
						e.printStackTrace();
					}// end catch
				}// end if
			}// end if

		}// end actionPerformed
	}// end inner class Listenener

	// Create the frame.
	public void mainGUI() {
		setBackground(SystemColor.textHighlight);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		this.add(contentPane);

		JLabel mainHead = new JLabel("Exam Overlap");
		mainHead.setFont(new Font("American Typewriter", Font.PLAIN, 77));
		mainHead.setBounds(255, 30, 590, 99);
		contentPane.add(mainHead);

		JLabel description = new JLabel(
				"<html>"
						+ "-Submit a file containing the course data<br>"
						+ "-You will then be prompted to select which courses pretain to you<br>"
						+ "-After selecting the courses, we will report any schedule conflicts<br>"
						+ "<html>");
		description.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		description.setVerticalAlignment(SwingConstants.TOP);
		description.setBounds(195, 170, 420, 125);
		contentPane.add(description);

		chsFile = new JButton("Add a File");
		chsFile.setBounds((800 / 2) - 132, 299, 125, 50);
		ActionListener fileListener = new Listener();
		chsFile.addActionListener(fileListener);
		contentPane.add(chsFile);

		submitBttn = new JButton("Submit");
		submitBttn.setBounds(408, 299, 125, 50);
		submitBttn.addActionListener(fileListener);
		contentPane.add(submitBttn);

		fileName = new JLabel();
		fileName.setVerticalAlignment(SwingConstants.TOP);
		fileName.setBounds(268, 353, 145, 200);
		contentPane.add(fileName);

		rmFile = new JButton("Remove Files");
		rmFile.setBounds(408, 350, 125, 50);
		ActionListener rmListener = new Listener();
		rmFile.addActionListener(rmListener);

		JLabel imgLabel = new JLabel("");
		Image img = new ImageIcon("img/planB[old].png")
				.getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(0, -25, 300, 200);
		contentPane.add(imgLabel);
		
	}// end window1
}
