package control;

import java.util.ArrayList;
import java.util.Arrays;

import model.Block;
import model.Person;
import model.Schedule;
import model.ScheduleTask;
import model.Skills;

public class Control {
	
	protected static Control mainControl = new Control();
	
	private ArrayList<ScheduleTask> tasks;
	private ArrayList<Person> team;
	private Schedule schedule;
	
	private Control() {
		tasks = new ArrayList<ScheduleTask>();
		team = new ArrayList<Person>();
	}
	
	private Control(ArrayList<ScheduleTask> tasks, ArrayList<Person> team){
		this.tasks = tasks;
		this.team = team;
	}
	
	public static void instantiate() {
        mainControl.setSchedule();
	}

	public static Control instance(){
		return mainControl;
	}
	
	public void addTask(ScheduleTask t){
		tasks.add(t);
	}
	
	public void removeTask(String taskName){
		for(ScheduleTask t : tasks){
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

	public ArrayList<ScheduleTask> getTasks(){
		return this.tasks;
	}
	
	public ArrayList<Person> getTeam(){
		return this.team;
	}
	
	public void setSchedule(){
		schedule = new Schedule(tasks, team);
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void reset(){
		tasks.clear();
		team.clear();
		schedule = null;
	}
}
