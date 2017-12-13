package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ScheduleTask;
import model.Skills;
import model.TaskModel;


/**
 * Controller for task creation, implements ActionListener
 * 
 * @see TaskModel
 * @see CreateTaskView
 */
public class TaskListener implements ActionListener {

	TaskModel model;
	
	JTextField nameInput;
	JSlider peopleNum;
	JSlider effortNum;
	JList<String> tasks;
	JList<Skills> skills;
	
	/**
	 * Constructor for TaskListener
	 * 
	 * @param model TaskModel object to create new task with all inputs
	 * @param nameInput Name of task
	 * @param peopleNum Number of people in the task
	 * @param effortNum Effort size to complete project
	 * @param skills List of skills needed to complete task
	 * @param tasks Previous tasks that need to be completed before this task can begin
	 * 
	 * @see TaskModel
	 * @see CreateTaskView
	 */
	public TaskListener(TaskModel model, JTextField nameInput, JSlider peopleNum, JSlider effortNum, JList<Skills> skills, JList<String> tasks){
		this.model = model;			
		this.nameInput = nameInput;
		this.peopleNum = peopleNum;
		this.effortNum = effortNum;
		this.skills = skills;
		this.tasks = tasks;
	}
	
	/**
	 * Activated when button to create a new task is pressed, validates input data then creates new task if valid
	 * 
	 * @param e ActionEvent that is triggered when create task button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "create task":
			
			boolean validEntry = true;
			
			if(effortNum.getValue() == 0 || peopleNum.getValue() == 0){
				JOptionPane.showMessageDialog(null, 
                        "Effort and People cannot have value of 0", 
                        "Invalid Input", 
                        JOptionPane.WARNING_MESSAGE);
				validEntry = false;
				break;
			}
			
			for(ScheduleTask t : Control.mainControl.getTasks()){
				if(t.getName().equals(nameInput.getText())){
					JOptionPane.showMessageDialog(null, 
	                        "Name of task already exists", 
	                        "Invalid Input", 
	                        JOptionPane.WARNING_MESSAGE);
					validEntry = false;
					break;
				}
			}
			
			ArrayList<ScheduleTask> taskList = new ArrayList<ScheduleTask>();
			if(!tasks.isSelectionEmpty()){
				for(String elem : tasks.getSelectedValuesList()){
					ArrayList<ScheduleTask> sTask = Control.mainControl.getTasks();
					for(ScheduleTask t : sTask){
						if(t.getName().equals(elem)){
							taskList.add(t);
						}
					}
				}
			}

			ArrayList<Skills> skillList = new ArrayList<Skills>();
			if(!skills.isSelectionEmpty()) skillList = new ArrayList<Skills>(skills.getSelectedValuesList());
			if(validEntry) {
				ScheduleTask newTask = new ScheduleTask(nameInput.getText(), effortNum.getValue(), peopleNum.getValue(), taskList, skillList);
				model.addTask(newTask.getName(), newTask.toString());
				Control.mainControl.addTask(newTask);
			}
		
			break;
		}
	}

}
