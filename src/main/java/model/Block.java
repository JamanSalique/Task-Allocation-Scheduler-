package model;

import java.util.ArrayList;

public class Block {
	
	private Task task;
	private int start;
	private int end;
	private ArrayList<Person> people;
	
	
	
	public Block(Task t, int s, int e, ArrayList<Person> p){
		this.setTask(t);
		this.setStart(s);
		this.setEnd(e);
		this.setPeople(p);
	}



	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}
	
	public ArrayList<String> getPeopleName(){
		
		ArrayList<String> s= new ArrayList<String>();
		for(Person p : getPeople())
			s.add(p.getName());
		return s;
	}

	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	
	public String toString(){
		 return ("Task name : " + this.getTask().getName() + ", Start Time : " + this.getStart() + ", End Time : " + this.getEnd() + ", People : " + this.getPeopleName().toString());
	}

}
