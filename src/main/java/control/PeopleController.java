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

public class PeopleController implements ActionListener{

    private PersonModel model;
    private JTextField name;
    private JList<Skills> skills;

    public PeopleController(PersonModel model, JTextField name, JList<Skills> skills){
        this.model = model;
        this.name = name;
        this.skills = skills;

    }

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
			if(validEntry) Control.mainControl.addTeamMember(new Person(name.getText(), new ArrayList<Skills>(skills.getSelectedValuesList())));
    	}
	}
}
