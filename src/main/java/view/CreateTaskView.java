package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

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
import model.TaskModel;

public class CreateTaskView extends JPanel implements Observer{
	
	/**
	 * Default serial version added
	 */
	private static final long serialVersionUID = 1L;

	private TaskListener taskListener;
	
	private JTextField nameInput;
	private JSlider effortNum;
	private JSlider peopleNum;
	private JList<Skills> skills;
	private JList<String> taskList;
	
	private TaskModel model;
	
	/**
	 * Constructs panel that allows manager to create new tasks, based on JPanel class
	 */
	public CreateTaskView(TaskModel model) {	
		
		this.model = model;
		
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
		jpCenter.setLayout(new GridLayout(5, 2));
		
		//Adding Task Name
		jpCenter.add(new JLabel("Task Name:"));
		nameInput = new JTextField();
		jpCenter.add(nameInput);	
		
		//Adding Effort Estimate
		jpCenter.add(new JLabel("Effort Estimate:"));
		effortNum = new JSlider(0, 20, 1);
		effortNum.setMinorTickSpacing(1);
		effortNum.setMajorTickSpacing(10);
		effortNum.setPaintTicks(true);
		effortNum.setSnapToTicks(true);
		effortNum.setPaintLabels(true);
		jpCenter.add(effortNum);
		
		//Adding Number Of People
		jpCenter.add(new JLabel("Number Of People:"));
		peopleNum = new JSlider(0, 20, 1);
		peopleNum.setMinorTickSpacing(1);
		peopleNum.setMajorTickSpacing(10);
		peopleNum.setPaintTicks(true);
		peopleNum.setSnapToTicks(true);
		peopleNum.setPaintLabels(true);
		jpCenter.add(peopleNum);
		
		//Adding Skills Required
		jpCenter.add(new JLabel("Add Skills"));
		skills = new JList<Skills>(Skills.values());
		JScrollPane jspSkills = new JScrollPane(skills);
		jpCenter.add(jspSkills);
		
		//Adding Tasks Required
		jpCenter.add(new JLabel("Add Required Tasks"));
		taskList = new JList<String>(model.getTasks());
        JScrollPane taskPane = new JScrollPane(taskList);
        jpCenter.add(taskPane);
		
		//Create button to create task
		JButton jbCreate = new JButton("Create Task");
		
		taskListener = new TaskListener(model, nameInput, peopleNum, effortNum, skills, taskList);
		jbCreate.addActionListener(taskListener);
		jbCreate.setActionCommand("create task");
		jpSouth.add(jbCreate);
	}
	
    @Override
    public void update(Observable o, Object arg) {

    	model = (TaskModel) o;
    	
    	nameInput.setText("");
    	effortNum.setValue(0);
    	peopleNum.setValue(0);    	
        skills.revalidate();
        skills.repaint();
        taskList.revalidate();
        taskList.repaint();
    }
}