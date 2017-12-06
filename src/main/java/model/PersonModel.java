package model;

import java.util.Observable;

import javax.swing.DefaultListModel;

public class PersonModel extends Observable{
	private DefaultListModel<String> addModel;
	
	public PersonModel(){
		addModel = new DefaultListModel<>();
	}
	
	public DefaultListModel<String> getPeople(){
		return addModel;
	}
	
	public void addToPeople(String e){
		addModel.addElement(e);
	}
}
