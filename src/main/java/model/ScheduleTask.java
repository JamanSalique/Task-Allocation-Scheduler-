package model;

import java.util.ArrayList;

/**
 * This class represents a task in a schedule. A schedule task has a name, an effort estimate, 
 * a certain number of people that can be assigned to a task, a list of tasks that are required to be done before this task is done,
 * and a list of skills required for the task to be completed.
 *
 */
public class ScheduleTask {
	
	private String name;
	private int effort;
	private int people;
	private ArrayList<ScheduleTask> requiredBefore;
	private ArrayList<Skills> requiredSkills;
	
	/**
	 * This constructs a task with a given name, effort, number of people, a list of tasks required before and a 
	 * list of skills required.
	 * @param name
	 * @param effort
	 * @param people
	 * @param rB
	 * @param rS
	 */
	public ScheduleTask(String name, int effort, int people, ArrayList<ScheduleTask> rB, ArrayList<Skills> rS){
		this.setName(name);
		this.setEffort(effort);
		this.setPeople(people);
		this.setRequiredBefore(rB);
		this.setRequiredSkills(rS);
	}

	/**
	 * Getter method that returns the name of the task.
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
	 * Getter method that returns the effor estimate for the task
	 * @return
	 */
	public int getEffort() {
		return effort;
	}

	/**
	 * Setter method for the effort field
	 * @param effort
	 */
	public void setEffort(int effort) {
		this.effort = effort;
	}

	/**
	 * Getter method for the number of people required for a task.
	 * @return
	 */
	public int getPeople() {
		return people;
	}

	/**
	 * Setter method for the people field
	 * @param people
	 */
	public void setPeople(int people) {
		this.people = people;
	}

	/**
	 * Getter method that returns the required tasks before
	 * @return
	 */
	public ArrayList<ScheduleTask> getRequiredBefore() {
		return requiredBefore;
	}

	/**
	 * Setter method for requiredBefore field
	 * @param requiredBefore
	 */
	public void setRequiredBefore(ArrayList<ScheduleTask> requiredBefore) {
		this.requiredBefore = requiredBefore;
	}

	/**
	 * Getter method that returns the requiredSkills for a task
	 * @return
	 */
	public ArrayList<Skills> getRequiredSkills() {
		return requiredSkills;
	}

	/**
	 * Setter method for the requiredSkills field.
	 * @param requiredSkills
	 */
	public void setRequiredSkills(ArrayList<Skills> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	
	/**
	 * toString method to allow us to show the name, effort, number of people and required skills when we print a ScheduleTask object.
	 */
	public String toString(){
		String info = "Name: " + name + " Effort: " + effort + " Number of People: " + people + " Skills: ";
		if(requiredSkills.isEmpty()) info += "None ";
		else{
			for(Skills e : requiredSkills){
				info += e + " ";
			}
		}
		info += "Tasks required: ";
		if(requiredBefore.isEmpty()) info += "None";
		else{
			for(ScheduleTask e : requiredBefore){
				info += e.getName() + " ";
			}
		}
		return info;
	}
}
