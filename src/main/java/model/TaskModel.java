package model;

import java.util.Observable;

import javax.swing.DefaultListModel;

/**
 * Model for task creation
 * 
 * @see TaskListener
 * @see CreateTaskView
 */
public class TaskModel extends Observable{
	private DefaultListModel<String> listModel;
	private DefaultListModel<String> taskModel;
	
	/**
	 * Constructor
	 */
	public TaskModel(){
		listModel = new DefaultListModel<>();
		taskModel = new DefaultListModel<>();
	}
	
	/**
	 * Getter for task list
	 * 
	 * @return List of all tasks created
	 */
	public DefaultListModel<String> getTasks(){
		return listModel;
	}
	
	/**
	 * Getter for task list
	 * 
	 * @return List of info of all tasks created
	 */
	public DefaultListModel<String> getInfo(){
		return taskModel;
	}
	
	/**
	 * Adds new task to list
	 * 
	 * @param e Name of new task
	 */
	public void addTask(String e, String info){
		listModel.addElement(e);
		taskModel.addElement(info);
		setChanged();
		notifyObservers();
	}
}
