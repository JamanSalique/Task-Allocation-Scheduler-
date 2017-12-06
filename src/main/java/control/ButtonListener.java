package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FrameModel;

public class ButtonListener implements ActionListener{
	
	private FrameModel model;
	private ScheduleTask task;
	
	/**
	 * Constructor for Main Window
	 * @param FrameModel
	 */
	public ButtonListener(FrameModel model){
		this.model = model;
	}
	
	/**
	 * Constructor for Creating a new task
	 */
	public ButtonListener(ScheduleTask task){
		this.task = task;
	}

	/**
	 *@param ActionEvent e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "move left":
			model.moveLeft();
		case "move right":
			model.moveRight();
	}

}
