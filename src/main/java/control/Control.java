package control;

import java.util.ArrayList;
import java.util.Arrays;

import model.Block;
import model.Person;
import model.Schedule;
import model.ScheduleTask;
import model.Skills;
/**
 * This class is the main controller class.
 * @author junaid
 *
 */
public class Control {
	
	protected static Control mainControl = new Control();
	
	private ArrayList<ScheduleTask> tasks;
	private ArrayList<Person> team;
	private Schedule schedule;
	/**
	 * Default constructor constructs the control class.
	 */
	private Control() {
		tasks = new ArrayList<ScheduleTask>();
		team = new ArrayList<Person>();
	}
	/**
	 * Constructs control class.
	 * @param tasks
	 * @param team
	 */
	private Control(ArrayList<ScheduleTask> tasks, ArrayList<Person> team){
		this.tasks = tasks;
		this.team = team;
	}
	/**
	 * This method instantiates for the schedule.
	 */
	public static void instantiate() {
        mainControl.setSchedule();
	}

	/**
	 * This method returns an instance of main control.
	 * @return
	 */
	public static Control instance(){
		return mainControl;
	}
	/**
	 * This method adds a task to the tasks ArrayList
	 * @param t
	 */
	public void addTask(ScheduleTask t){
		tasks.add(t);
	}
	/**
	 * This removes a task from the tasks ArrayList with a given name.
	 * @param taskName
	 */
	public void removeTask(String taskName){
		for(ScheduleTask t : tasks){
			if(t.getName().equals(taskName)){
				tasks.remove(t);
			}
		}
	}
	/**
	 * This method adds a Person to the team ArrayList
	 * @param p
	 */
	public void addTeamMember(Person p){
		team.add(p);
	}
	/**
	 * This method removes a Person from the team ArrayList with a given name.
	 * @param memberName
	 */
	public void removeTeamMember(String memberName){
		for(Person p : team){
			if(p.getName().equals(memberName)){
				tasks.remove(p);
			}
		}
	}

	/**
	 * Getter method that returns the tasks ArrayList
	 * @return
	 */
	public ArrayList<ScheduleTask> getTasks(){
		return this.tasks;
	}
	/**
	 * Getter method that returns the team ArrayList
	 * @return
	 */
	public ArrayList<Person> getTeam(){
		return this.team;
	}
	/**
	 * This method creates a new Schedule
	 */
	public void setSchedule(){
		schedule = new Schedule(tasks, team);
	}
	/**
	 * Getter method that returns the schedule
	 * @return
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	/**
	 * This method clears both ArrayLists and makes the schedule null.
	 */
	public void reset(){
		tasks.clear();
		team.clear();
		schedule = null;
	}
}
