package model;

import java.util.ArrayList;

public class Person {
	
	private String name;
	private ArrayList<Skills> skills;
	
	public Person(String name, ArrayList<Skills> skills){
		this.setName(name);
		this.setSkills(skills);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Skills> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skills> skills) {
		this.skills = skills;
	}

	public String toString(){
		String info = "Name: " + name + " Skills: ";
		for(Skills e : skills){
			info += e + " ";
		}
		return info;
	}
}
