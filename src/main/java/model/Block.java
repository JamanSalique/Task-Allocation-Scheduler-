package model;

import java.util.ArrayList;
/**
 * This class represents a block on a schedule, a block has a start and end time, a task, and a list of people.
 *
 */
public class Block {
	
	private ScheduleTask task;
	private int start;
	private int end;
	private ArrayList<Person> people;
	/**
	 * This constructs a block using fields.
	 * @param t
	 * @param s
	 * @param e
	 * @param p
	 */
	public Block(ScheduleTask t, int s, int e, ArrayList<Person> p){
		this.setTask(t);
		this.setStart(s);
		this.setEnd(e);
		this.setPeople(p);
	}

	/**
	 * Getter method that returns the task for the block
	 * @return
	 */
	public ScheduleTask getTask() {
		return task;
	}
	
	/**
	 * Setter method for the task field
	 * @param task
	 */
	public void setTask(ScheduleTask task) {
		this.task = task;
	}
	/**
	 * Getter method that returns the start time
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Setter method for the start field
	 * @param start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Getter method that returns the end time
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Setter method for the end field
	 * @param end
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * Getter method that returns the ArrayList of people.
	 * @return
	 */
	public ArrayList<Person> getPeople() {
		return people;
	}
	/**
	 * This method goes through the ArrayList of people and returns the name of the Person.
	 * @return
	 */
	public ArrayList<String> getPeopleName(){
		
		ArrayList<String> s= new ArrayList<String>();
		for(Person p : getPeople())
			s.add(p.getName());
		return s;
	}

	/**
	 * Setter method for people ArrayList
	 * @param people
	 */
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	/**
	 * toString method to allow us to show the task, start time, end time and people for the block if we print the block.
	 */
	public String toString(){
		 return ("Task name : " + this.getTask().getName() + ", Start Time : " + this.getStart() + ", End Time : " + this.getEnd() + ", People : " + this.getPeopleName().toString());
	}

}
