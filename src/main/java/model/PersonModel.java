package model;

import java.util.Observable;

import javax.swing.DefaultListModel;

/**
 * Model for person creation
 * 
 * @see PeopleController
 * @see CreatePeopleView
 */
public class PersonModel extends Observable{
	private DefaultListModel<String> addModel;
	
	/**
	 * Constructor
	 */
	public PersonModel(){
		addModel = new DefaultListModel<>();
	}
	
	/**
	 * Getter for addModel
	 * 
	 * @return List of people created
	 */
	public DefaultListModel<String> getPeople(){
		return addModel;
	}
	
	/**
	 * Adds new person to list
	 * 
	 * @param e Adds name of person to list
	 */
	public void addToPeople(Person e){
		addModel.addElement(e.toString());
	}
}
