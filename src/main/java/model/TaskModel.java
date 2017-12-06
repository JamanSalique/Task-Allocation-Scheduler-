package model;

import java.util.Observable;

import javax.swing.DefaultListModel;

public class TaskModel extends Observable{
	private DefaultListModel<String> listModel;
	
	public TaskModel(){
		listModel = new DefaultListModel<>();
	}
	
	public DefaultListModel<String> getTasks(){
		return listModel;
	}
	
	public void addTask(String e){
		listModel.addElement(e);
		
		setChanged();
		notifyObservers();
	}
}
