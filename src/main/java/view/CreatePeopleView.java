package view;

import javax.swing.*;

import control.PeopleController;
import control.TaskListener;
import model.PersonModel;
import model.Skills;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * GUI for creating new person, extends JPanel and implements Observer
 * 
 * @see JPanel
 * @see Observer
 * @see PeopleModel
 * @see PeopleController
 */
public class CreatePeopleView extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JTextField nameInput;
	private JList<Skills> skills;
	private JList<String> people;
    private PersonModel model;

    private PeopleController controller;

    /**
     * Constructor
     * 
     * @param model PersonModel to allow creation of new people
     * 
     * @see PeopleModel
     * @see PeopleController
     */
    public CreatePeopleView(PersonModel model){

    	this.model = model;
		
		//Create Over-arching Border Layout
		this.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpSouth = new JPanel();
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
		
		//Create Grid Layout for north layout
		jpNorth.add(new JLabel("Create a new person here"));

		//Create Grid Layout for centre layout
		jpCenter.setLayout(new BorderLayout());
		JPanel jpCCenter = new JPanel();
		JPanel jpCSouth = new JPanel();
		jpCenter.add(jpCCenter, BorderLayout.CENTER);
		jpCenter.add(jpCSouth, BorderLayout.SOUTH);
		jpCCenter.setLayout(new GridLayout(2, 2));
		
		//Adding Task Name
		jpCCenter.add(new JLabel("Person Name:"));
		nameInput = new JTextField();
		nameInput.setName("personNameInput");
		jpCCenter.add(nameInput);	
		
		//Adding Skills That Person Has
		jpCCenter.add(new JLabel("Add Skills"));
		skills = new JList<Skills>(Skills.values());
		skills.setName("skillsPeople");
		JScrollPane jspSkills = new JScrollPane(skills);
		jpCCenter.add(jspSkills);
		
		//Create button to create task
		JButton jbCreate = new JButton("Add Team Member");
		jbCreate.setName("addTeamMember");
		
		controller = new PeopleController(model, nameInput, skills);
		jbCreate.addActionListener(controller);
		jbCreate.setActionCommand("create person");
		jpCSouth.add(jbCreate);
		
		//Showing all people created
		people = new JList<String>(model.getPeople());
		people.setName("people");
		JScrollPane jspPeople = new JScrollPane(people);
		jspPeople.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jpSouth.setLayout(new GridLayout(1, 1));
		jpSouth.add(jspPeople);
    }

    /**
     * Update method
     * 
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
    	
    }
}