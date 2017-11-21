package control;

import java.util.ArrayList;

import model.Person;
import model.Schedule;
import model.Task;

public class Control {
	
	private ArrayList<Task> tasks;
	private ArrayList<Person> team;
	private Schedule schedule;
	
	public Control(ArrayList<Task> tasks, ArrayList<Person> team){
		this.tasks = tasks;
		this.team = team;
	}

	public void addTask(Task t){
		tasks.add(t);
	}
	
	public void removeTask(String taskName){
		for(Task t : tasks){
			if(t.getName().equals(taskName)){
				tasks.remove(t);
			}
		}
	}
	
	public void addTeamMember(Person p){
		team.add(p);
	}
	
	public void removeTeamMember(String memberName){
		for(Person p : team){
			if(p.getName().equals(memberName)){
				tasks.remove(p);
			}
		}
	}
	
	public Schedule setSchedule(){
		return new Schedule(tasks, team);
	}
	
	
}
