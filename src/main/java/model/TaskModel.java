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
	
	/**
	 * Constructor
	 */
	public TaskModel(){
		listModel = new DefaultListModel<>();
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
	 * Adds new task to list
	 * 
	 * @param e Name of new task
	 */
	public void addTask(String e){
		listModel.addElement(e);
		
		setChanged();
		notifyObservers();
	}
}
