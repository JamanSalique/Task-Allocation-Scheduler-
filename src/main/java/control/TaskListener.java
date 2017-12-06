/**
 * 
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ScheduleTask;
import model.Skills;


/**
 * @author Anttoni
 *
 */
public class TaskListener implements ActionListener {

	ScheduleTask taskModel;
	
	JTextField nameInput;
	JSlider peopleNum;
	JSlider effortNum;
	JList<Skills> skills;
	
	public TaskListener(JTextField nameInput, JSlider peopleNum, JSlider effortNum, JList<Skills> skills){
		this.nameInput = nameInput;
		this.peopleNum = peopleNum;
		this.effortNum = effortNum;
		this.skills = skills;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "create task":
			//taskModel = new ScheduleTask(nameInput.getText(), peopleNum.getValue(), effortNum.getValue(), skills.getSelectedValue())
		}
	}

}
