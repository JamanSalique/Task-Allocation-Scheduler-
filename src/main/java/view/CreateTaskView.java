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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.TaskListener;
import model.ScheduleTask;
import model.Skills;

public class CreateTaskView extends JPanel{
	
	/**
	 * Default serial version added
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleTask taskModel;
	private TaskListener taskListener;
	
	/**
	 * Constructs panel that allows manager to create new tasks, based on JPanel class
	 */
	public CreateTaskView() {	
		//Create Over-arching Border Layout
		this.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
		
		//Create Grid Layout for north layout
		jpNorth.add(new JLabel("Create a new task here"));

		//Create Grid Layout for centre layout
		jpCenter.setLayout(new GridLayout(7, 2));
		
		//Adding Task Name
		jpCenter.add(new JLabel("Task Name:"));
		JTextField nameInput = new JTextField();
		jpCenter.add(nameInput);
		jpCenter.add(new JLabel());
		jpCenter.add(new JLabel());		
		
		//Adding Effort Estimate
		jpCenter.add(new JLabel("Effort Estimate:"));
		JSlider effortNum = new JSlider(0, 20, 0);
//		JComboBox effortNum = new JComboBox();
//		for(int i = 1; i < 20; i++){
//			effortNum.addItem(new Integer(i));
//		}
		jpCenter.add(effortNum);
		jpCenter.add(new JLabel());
		jpCenter.add(new JLabel());
		
		//Adding Number Of People
		jpCenter.add(new JLabel("Number Of People:"));
//		JComboBox peopleNum = new JComboBox();
//		for(int i = 1; i < 20; i++){
//			peopleNum.addItem(new Integer(i));
//		}
		JSlider peopleNum = new JSlider(0, 20, 0);
		jpCenter.add(peopleNum);
		jpCenter.add(new JLabel());
		jpCenter.add(new JLabel());
		
		//Adding Skills Required
		jpCenter.add(new JLabel("Add Skills"));
		JList<Skills> skills = new JList<Skills>(Skills.values());
		JScrollPane jspSkills = new JScrollPane(skills);
		jpCenter.add(jspSkills);

		//Create Border Layout for south layout
		jpSouth.setLayout(new BorderLayout());
		JPanel jpSNorth = new JPanel();
		JPanel jpSCenter = new JPanel();
		JPanel jpSSouth = new JPanel();
		jpSouth.add(jpSNorth, BorderLayout.NORTH);
		jpSouth.add(jpSCenter, BorderLayout.CENTER);
		jpSouth.add(jpSSouth, BorderLayout.SOUTH);
		
		//Create button to create task
		JButton jbCreate = new JButton("Create Task");
		
		taskListener = new TaskListener(nameInput, peopleNum, effortNum, skills);
		jbCreate.addActionListener(taskListener);
		jbCreate.setActionCommand("create task");
		jpSCenter.add(jbCreate);
	}
}