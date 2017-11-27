package model;

import java.util.ArrayList;

public class ScheduleTask {
	
	private String name;
	private int effort;
	private int people;
	private ArrayList<ScheduleTask> requiredBefore;
	private ArrayList<Skills> requiredSkills;
	
	public ScheduleTask(String name, int effort, int people, ArrayList<ScheduleTask> rB, ArrayList<Skills> rS){
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

	public ArrayList<ScheduleTask> getRequiredBefore() {
		return requiredBefore;
	}

	public void setRequiredBefore(ArrayList<ScheduleTask> requiredBefore) {
		this.requiredBefore = requiredBefore;
	}

	public ArrayList<Skills> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(ArrayList<Skills> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	
}
