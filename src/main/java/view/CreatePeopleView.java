package view;

import javax.swing.*;

import control.PeopleController;
import control.TaskListener;
import model.PersonModel;
import model.Skills;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CreatePeopleView extends JPanel implements Observer{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField nameInput;
	private JList<Skills> skills;
    private PersonModel model;

    private PeopleController controller;


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
		jpCenter.setLayout(new GridLayout(2, 2));
		
		//Adding Task Name
		jpCenter.add(new JLabel("Person Name:"));
		nameInput = new JTextField();
		jpCenter.add(nameInput);	
		
		//Adding Skills That Person Has
		jpCenter.add(new JLabel("Add Skills"));
		skills = new JList<Skills>(Skills.values());
		JScrollPane jspSkills = new JScrollPane(skills);
		jpCenter.add(jspSkills);
		
		//Create button to create task
		JButton jbCreate = new JButton("Add Team Member");
		
		controller = new PeopleController(model, nameInput, skills);
		jbCreate.addActionListener(controller);
		jbCreate.setActionCommand("create person");
		jpSouth.add(jbCreate);
    }

    @Override
    public void update(Observable o, Object arg) {
    	
    }
}
