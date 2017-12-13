package control;

import javax.swing.*;

import model.Person;
import model.PersonModel;
import model.ScheduleTask;
import model.Skills;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Controller for people creation, implements ActionListener
 * 
 * @see PersonModel
 * @see CreatePersonView
 */
public class PeopleController implements ActionListener{

    private PersonModel model;
    private JTextField name;
    private JList<Skills> skills;

    /**
     * Constructor for PeopleController
     * 
     * @param model PersonModel object that creates new person
     * @param name Name of person
     * @param skills List of skills that person has
     * 
     * @see PersonModel
     * @see CreatePersonView
     */
    public PeopleController(PersonModel model, JTextField name, JList<Skills> skills){
        this.model = model;
        this.name = name;
        this.skills = skills;

    }

    /**
	 * Activated when button to create a new person is pressed, validates input data then creates new person if valid
	 * 
	 * @param e ActionEvent that is triggered when create person button is clicked
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(name.getText().equals("")){
    		JOptionPane.showMessageDialog(null, 
                    "No name for person selected", 
                    "Invalid Input", 
                    JOptionPane.WARNING_MESSAGE);
    	}
    	else{
    		boolean validEntry = true;
			for(Person t : Control.mainControl.getTeam()){
				if(t.getName().equals(name.getText())){
					JOptionPane.showMessageDialog(null, 
	                        "Name of person already exists", 
	                        "Invalid Input", 
	                        JOptionPane.WARNING_MESSAGE);
					validEntry = false;
					break;
				}
			}
			if(validEntry){
				Person newPerson = new Person(name.getText(), new ArrayList<Skills>(skills.getSelectedValuesList()));
				model.addToPeople(newPerson);
				Control.mainControl.addTeamMember(newPerson);
			}
    	}
	}
}
