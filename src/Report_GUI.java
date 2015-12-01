import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import CourseData.Category;
import CourseData.Class;
import CourseData.Data;

public class Report_GUI extends JFrame {

	private JPanel contentPane;

	private JTextArea textarea;

	Data dataObject;

	/**
	 * Create the frame.
	 */
	public Report_GUI(Data dataObject) {

		this.dataObject = dataObject;

		int height = 600;
		int width = 800;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel imgLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("planB.png"))
				.getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(0, -25, 300, 200);
		contentPane.add(imgLabel);

		JLabel lblConflictReport = new JLabel("Class Report");
		lblConflictReport.setFont(new Font("American Typewriter", Font.PLAIN,
				80));
		lblConflictReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblConflictReport.setBounds(190, 30, 624, 83);
		contentPane.add(lblConflictReport);

		JLabel header = new JLabel(
				"Classes with any conflicts will be listed below:");
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		header.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		header.setBounds(130, 195, 645, 25);
		contentPane.add(header);

		textarea = new JTextArea("");
		textarea.setEditable(false);

		JScrollPane conflictPane = new JScrollPane(textarea);
		conflictPane.setBackground(SystemColor.textHighlight);
		conflictPane.setBounds(155, 232, 529, 303);
		contentPane.add(conflictPane);

		addScheduleAndConflicts();

	}

	private void addScheduleAndConflicts() {

		Category lastCat = new Category(0, 0, 0, "Na", "Na");
		for (Category cat : this.dataObject.finalsCategories) {

			// this.textarea.append(cat.toString() + "\n");

			for (Class c : cat.classesInThisCategory) {

				if (c.hasConflict) {

					if (cat != lastCat) {
						this.textarea.append(cat.toString() + "\n");
					}
					lastCat = cat;
					//this.textarea.append("        " + c.toStringNoFinal()
					//		+ "\n");
				}
				// else {
				// textarea.setForeground(Color.RED);
				// this.textarea.append("     " + c.toStringNoFinal() + "\n");
				// }

			}

		}

		this.textarea.append("\n\n");

	}

}
