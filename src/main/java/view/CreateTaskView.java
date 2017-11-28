package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class CreateTaskView extends JPanel{
	
	/**
	 * Default serial version added
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs panel that allows manager to create new tasks, based on JPanel class
	 */
	public CreateTaskView() {
		// Create Over-arching Border Layout
		this.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
		
		//Create Grid Layout for north layout
		jpNorth.add(new JLabel("Create a new task here"));

		// Create Grid Layout for centre layout
		jpCenter.setLayout(new GridLayout(4, 2));
		jpCenter.add(new JLabel("Task Name:"));
		jpCenter.add(new JTextArea());
		jpCenter.add(new JLabel("Effort Estimate:"));
		jpCenter.add(new JComboBox());
		jpCenter.add(new JLabel("Number Of People:"));
		jpCenter.add(new JComboBox());
		jpCenter.add(new JLabel("Add Skills"));
		jpCenter.add(new JComboBox());
		//jpCenter.add(jspDisplay);

		// Create Border Layout for south layout
		jpSouth.setLayout(new BorderLayout());
		JPanel jpSNorth = new JPanel();
		JPanel jpSCenter = new JPanel();
		JPanel jpSSouth = new JPanel();
		jpSouth.add(jpSNorth, BorderLayout.NORTH);
		jpSouth.add(jpSCenter, BorderLayout.CENTER);
		jpSouth.add(jpSSouth, BorderLayout.SOUTH);

	}
}
