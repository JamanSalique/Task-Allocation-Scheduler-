package model;

import java.util.ArrayList;
/**
 * This class represents the person assigned to a task. A person has a name and a set of skills
 *
 */
public class Person {
	
	private String name;
	private ArrayList<Skills> skills;
	
	/**
	 * This constructs a Person with a given name and a set of skills.
	 * @param name
	 * @param skills
	 */
	public Person(String name, ArrayList<Skills> skills){
		this.setName(name);
		this.setSkills(skills);
	}

	/**
	 * Getter method that returns the name.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for name field
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method that returns the ArrayList of skills
	 * @return
	 */
	public ArrayList<Skills> getSkills() {
		return skills;
	}

	/**
	 * Setter method for the skills ArrayList
	 * @param skills
	 */
	public void setSkills(ArrayList<Skills> skills) {
		this.skills = skills;
	}

	/**
	 * toString method to allow us to show the name and the set of skills when we print a Person object.
	 */
	public String toString(){
		String info = "Name: " + name + " Skills: ";
		for(Skills e : skills){
			info += e + " ";
		}
		return info;
	}
}
