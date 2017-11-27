package control;

import java.util.ArrayList;
import java.util.Arrays;

import model.Block;
import model.Person;
import model.Schedule;
import model.ScheduleTask;
import model.Skills;

public class Control {
	
	private static Control mainControl = new Control();
	
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
		
		//TEST CASE
		
	 	ScheduleTask t2 = new ScheduleTask("2", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t1 = new ScheduleTask("1", 3, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t3 = new ScheduleTask("3", 2, 2, null, new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        ScheduleTask t4 = new ScheduleTask("4", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t5 = new ScheduleTask("5", 6, 3, new ArrayList<ScheduleTask>(Arrays.asList(t3)), new ArrayList<Skills>());
        ScheduleTask t6 = new ScheduleTask("6", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t7 = new ScheduleTask("7", 4, 3, null, new ArrayList<Skills>());

        ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3, t4, t5, t6, t7));

        Person one = new Person("1", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person two = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
        Person three = new Person("3", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person four = new Person("4",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person five = new Person("5", null);
        Person six = new Person("6", null);
        
        ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(one, two, three, four, five, six));
        
        mainControl = new Control(tasks, team); 
        mainControl.setSchedule();
        
        for(Block b : mainControl.getSchedule().getBlocks()){
        		System.out.println(b.toString());
        }

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
	
	public void setSchedule(){
		schedule = new Schedule(tasks, team);
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	
}
