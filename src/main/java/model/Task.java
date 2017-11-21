package model;

import java.util.ArrayList;

public class Task {
	
	private String name;
	private int effort;
	private int people;
	private ArrayList<Task> requiredBefore;
	private ArrayList<Skills> requiredSkills;
	
	public Task(String name, int effort, int people, ArrayList<Task> rB, ArrayList<Skills> rS){
		this.setName(name);
		this.setEffort(effort);
		this.setPeople(people);
		this.setRequiredBefore(rB);
		this.setRequiredSkills(rS);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public ArrayList<Task> getRequiredBefore() {
		return requiredBefore;
	}

	public void setRequiredBefore(ArrayList<Task> requiredBefore) {
		this.requiredBefore = requiredBefore;
	}

	public ArrayList<Skills> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(ArrayList<Skills> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	
}
