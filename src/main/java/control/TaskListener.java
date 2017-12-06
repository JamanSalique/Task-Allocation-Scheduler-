/**
 * 
 */
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
 * @author Anttoni
 *
 */
public class TaskListener implements ActionListener {

	TaskModel model;
	
	JTextField nameInput;
	JSlider peopleNum;
	JSlider effortNum;
	JList<String> tasks;
	JList<Skills> skills;
	
	public TaskListener(TaskModel model, JTextField nameInput, JSlider peopleNum, JSlider effortNum, JList<Skills> skills, JList<String> tasks){
		this.model = model;			
		this.nameInput = nameInput;
		this.peopleNum = peopleNum;
		this.effortNum = effortNum;
		this.skills = skills;
		this.tasks = tasks;
	}
	
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
						if(sTask.contains(elem)){
							taskList.add(t);
						}
					}
				}
			}

			ArrayList<Skills> skillList = new ArrayList<Skills>();
			if(!skills.isSelectionEmpty()) skillList = new ArrayList<Skills>(skills.getSelectedValuesList());
			
			if(validEntry) model.addTask(nameInput.getText());
			if(validEntry) Control.mainControl.addTask(new ScheduleTask(nameInput.getText(), effortNum.getValue(), peopleNum.getValue(), taskList, skillList));
		
			break;
		}
	}

}
