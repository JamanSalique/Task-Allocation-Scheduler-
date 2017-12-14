package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import model.Block;
import model.Person;
import model.Schedule;
import model.ScheduleTask;
import model.Skills;

public class ScheduleTest {

	@Test(timeout = 10000)
	public void testFiveSuccesiveTasks() {
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("5", 1, 1, null, new ArrayList<Skills>());

		Person p1 = new Person("1",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1));
		Schedule schedule = new Schedule(tasks, team);

		if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
		else if(getScheduleEnd(schedule) != 5) fail("Tasks have not been successively scheduled");
	}

	@Test(timeout = 10000)
	public void testFiveSimultaneous() {

		//The maximum effort estimate is 4, hence the schedule should finish at 4
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 2, 1, null, new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 3, 1, null, new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 4, 1, null, new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("4", 3, 1, null, new ArrayList<Skills>());

		Person p1 = new Person("1", new ArrayList<Skills>());
		Person p2 = new Person("2", new ArrayList<Skills>());
		Person p3 = new Person("3", new ArrayList<Skills>());
		Person p4 = new Person("4", new ArrayList<Skills>());
		Person p5 = new Person("5", new ArrayList<Skills>());

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1,p2,p3,p4,p5));
		Schedule schedule = new Schedule(tasks, team);

		if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
		else if(getScheduleEnd(schedule) != 4) fail("Tasks have not been simultaneously scheduled");

	}

	@Test(timeout = 10000)
	public void testFivePreviousTasksConstraint() {
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 1, 1, new ArrayList<ScheduleTask>(Arrays.asList(t1)), new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 1, 1, new ArrayList<ScheduleTask>(Arrays.asList(t2)), new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 1, 1, new ArrayList<ScheduleTask>(Arrays.asList(t3)), new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("5", 1, 1, new ArrayList<ScheduleTask>(Arrays.asList(t4)), new ArrayList<Skills>());

		//Five people available such that it should be possible to schedule tasks simultaneously without the previous task constraint
		Person p1 = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
		Person p2 = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
		Person p3 = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
		Person p4 = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
		Person p5 = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1,p2,p3,p4,p5));
		Schedule schedule = new Schedule(tasks, team);
		
		if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
		else if(getScheduleEnd(schedule) != 5) fail("Tasks have not been successively scheduled");
	}
	
	//Tests of schedules where all tasks should be able to be scheduled
	@Test(timeout = 10000)
	public void test20Tasks6People() {
		ScheduleTask t1 = new ScheduleTask("1", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t2 = new ScheduleTask("2", 3, 5, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t3 = new ScheduleTask("3", 2, 2, null, new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        ScheduleTask t4 = new ScheduleTask("4", 2, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        ScheduleTask t5 = new ScheduleTask("5", 6, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        ScheduleTask t6 = new ScheduleTask("6", 4, 5, null, new ArrayList<Skills>());
        ScheduleTask t7 = new ScheduleTask("7", 4, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.dotNet)));
        ScheduleTask t8 = new ScheduleTask("8", 8, 6, null, new ArrayList<Skills>());
        ScheduleTask t9 = new ScheduleTask("9", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t10= new ScheduleTask("10", 8, 6, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t11 = new ScheduleTask("11", 3, 3, null, new ArrayList<Skills>());
        ScheduleTask t12 = new ScheduleTask("12", 5, 6, null, new ArrayList<Skills>());
        ScheduleTask t13 = new ScheduleTask("13", 4, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.CSS, Skills.dotNet)));
        ScheduleTask t14 = new ScheduleTask("14", 4, 5, null, new ArrayList<Skills>());
        ScheduleTask t15 = new ScheduleTask("15", 1, 3, null, new ArrayList<Skills>());
        ScheduleTask t16 = new ScheduleTask("16", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t17 = new ScheduleTask("17", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t18 = new ScheduleTask("18", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t19 = new ScheduleTask("19", 4, 3, new ArrayList<ScheduleTask>(Arrays.asList(t11, t16)), new ArrayList<Skills>());
        ScheduleTask t20 = new ScheduleTask("20", 7, 4, new ArrayList<ScheduleTask>(Arrays.asList(t19, t12)), new ArrayList<Skills>(Arrays.asList(Skills.Go)));

        ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17,  t18, t19, t20));

        Person one = new Person("1", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person two = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
        Person three = new Person("3", new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        Person four = new Person("4",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person five = new Person("5", new ArrayList<Skills>(Arrays.asList(Skills.Go)));
        Person six = new Person("6", new ArrayList<Skills>(Arrays.asList(Skills.HTML)));

        ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(one, two, three, four, five, six));
        Schedule schedule = new Schedule(tasks, team);
        
        if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}
	
	@Test(timeout = 10000)
	public void test20Tasks10People() {
		ScheduleTask t1 = new ScheduleTask("1", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t2 = new ScheduleTask("2", 3, 5, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t3 = new ScheduleTask("3", 2, 2, null, new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        ScheduleTask t4 = new ScheduleTask("4", 2, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        ScheduleTask t5 = new ScheduleTask("5", 6, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        ScheduleTask t6 = new ScheduleTask("6", 4, 5, null, new ArrayList<Skills>());
        ScheduleTask t7 = new ScheduleTask("7", 4, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.dotNet)));
        ScheduleTask t8 = new ScheduleTask("8", 8, 6, null, new ArrayList<Skills>());
        ScheduleTask t9 = new ScheduleTask("9", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t10= new ScheduleTask("10", 8, 6, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t11 = new ScheduleTask("11", 3, 3, null, new ArrayList<Skills>());
        ScheduleTask t12 = new ScheduleTask("12", 5, 6, null, new ArrayList<Skills>());
        ScheduleTask t13 = new ScheduleTask("13", 4, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.CSS, Skills.dotNet)));
        ScheduleTask t14 = new ScheduleTask("14", 4, 5, null, new ArrayList<Skills>());
        ScheduleTask t15 = new ScheduleTask("15", 1, 3, null, new ArrayList<Skills>());
        ScheduleTask t16 = new ScheduleTask("16", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t17 = new ScheduleTask("17", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t18 = new ScheduleTask("18", 4, 3, null, new ArrayList<Skills>());
        ScheduleTask t19 = new ScheduleTask("19", 4, 3, new ArrayList<ScheduleTask>(Arrays.asList(t11, t16)), new ArrayList<Skills>());
        ScheduleTask t20 = new ScheduleTask("20", 7, 4, new ArrayList<ScheduleTask>(Arrays.asList(t19, t12)), new ArrayList<Skills>(Arrays.asList(Skills.Go)));

        ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17,  t18, t19, t20));

        Person one = new Person("1", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person two = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
        Person three = new Person("3", new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        Person four = new Person("4",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person five = new Person("5", new ArrayList<Skills>(Arrays.asList(Skills.Go)));
        Person six = new Person("6", new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        Person seven = new Person("7",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person eight = new Person("8", new ArrayList<Skills>(Arrays.asList(Skills.Go)));
        Person nine = new Person("9", new ArrayList<Skills>(Arrays.asList(Skills.HTML)));

        ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine));
        Schedule schedule = new Schedule(tasks, team);
        
        if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}
	
	
	@Test(timeout = 10000)
	public void test10Tasks10People() {
		ScheduleTask t1 = new ScheduleTask("1", 2, 6, null, new ArrayList<Skills>());
        ScheduleTask t2 = new ScheduleTask("2", 4, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        ScheduleTask t3 = new ScheduleTask("3", 2, 7, null, new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        ScheduleTask t4 = new ScheduleTask("4", 3, 4, null, new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        ScheduleTask t5 = new ScheduleTask("5", 6, 2, null, new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        ScheduleTask t6 = new ScheduleTask("6", 3, 4, null, new ArrayList<Skills>());
        ScheduleTask t7 = new ScheduleTask("7", 4, 8, null, new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.dotNet)));
        ScheduleTask t8 = new ScheduleTask("8", 3, 6, null, new ArrayList<Skills>());
        ScheduleTask t9 = new ScheduleTask("9", 2, 3, null, new ArrayList<Skills>());
        ScheduleTask t10= new ScheduleTask("10", 8, 10, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        
        ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3, t4, t5, t6, t7, t8, t9, t10));

        Person one = new Person("1", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person two = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
        Person three = new Person("3", new ArrayList<Skills>(Arrays.asList(Skills.JavaScript)));
        Person four = new Person("4",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person five = new Person("5", new ArrayList<Skills>(Arrays.asList(Skills.Go)));
        Person six = new Person("6", new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        Person seven = new Person("7",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person eight = new Person("8", new ArrayList<Skills>(Arrays.asList(Skills.Go)));
        Person nine = new Person("9", new ArrayList<Skills>(Arrays.asList(Skills.HTML)));
        Person ten = new Person("10", new ArrayList<Skills>(Arrays.asList(Skills.Go, Skills.CSS)));


        ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(one, two, three, four, five, six, seven, eight, nine, ten));
        Schedule schedule = new Schedule(tasks, team);
        
        if(schedule.getBlocks().size() != tasks.size()) fail("Not all tasks have been scheduled");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}
	
	
	
	//Tests if schedules successfully schedules all tasks but the ones which cause constraints
	
	// Test for a tasks requiring a skill that noone has
	@Test(timeout = 10000)
	public void testSkillsConstraint() {
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("5", 1, 1, null, new ArrayList<Skills>(Arrays.asList(Skills.CSS)));

		Person p1 = new Person("1",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1));
		Schedule schedule = new Schedule(tasks, team);
				
		if((schedule.getBlocks().size() != tasks.size()-1)) fail("Wrong allocation of tasks");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}

	// Test for a task asking more people than are available
	@Test(timeout = 10000)
	public void testPeopleConstraint() {
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("5", 1, 2, null, new ArrayList<Skills>());

		Person p1 = new Person("1",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1));
		Schedule schedule = new Schedule(tasks, team);
		
		if((schedule.getBlocks().size() != tasks.size()-1)) fail("Wrong allocation of tasks");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}
	
	// Test for a tasks requiring a non existing task to be scheduled
	@Test(timeout = 10000)
	public void testPreviousTasksConstraint() {
		ScheduleTask t1 = new ScheduleTask("1", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t2 = new ScheduleTask("2", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t3 = new ScheduleTask("3", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t4 = new ScheduleTask("4", 1, 1, null, new ArrayList<Skills>());
		ScheduleTask t5 = new ScheduleTask("5", 1, 2, new ArrayList<ScheduleTask>(Arrays.asList(new ScheduleTask("", 1, 1, null, null))), new ArrayList<Skills>());

		Person p1 = new Person("1",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));

		ArrayList<ScheduleTask> tasks = new ArrayList<ScheduleTask>(Arrays.asList(t1,t2,t3,t4,t5));
		ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(p1));
		Schedule schedule = new Schedule(tasks, team);
		
		if((schedule.getBlocks().size() != tasks.size()-1)) fail("Wrong allocation of tasks");
		else if(!correctTimes(schedule)) fail("Some tasks are not scheduled for the right amount of time");
		else if(!correctTeamSizes(schedule)) fail("Some tasks do not contain the correct number of people working");
		else if(!correctTaskAllocation(schedule)) fail("Some tasks are scheduled and do not respect the constraints of previous tasks");
		else if(!correctSkillAllocation(schedule)) fail("Some tasks do not have team members which have all necesary skills");
		else if(!correctPeopleAllocation(schedule)) fail("Some people may be scheduled to work for more than one task at once");
	}
	
	/**
	 * Returns the time at which a given schedule ends
	 * @param given schedule
	 * @return time at which the last task ends
	 */
	public static int getScheduleEnd(Schedule s) {
		int ret = 0;
		for(Block b : s.getBlocks()) if(b.getEnd() > ret) ret = b.getEnd();
		return ret;
	}

	/**
	 * 
	 * @param given schedule
	 * @return true if each scheduled task has the correct amount of team members
	 */
	public static boolean correctTeamSizes(Schedule c) {
		for(Block b : c.getBlocks()) if(b.getPeople().size() != b.getTask().getPeople()) return false;
		return true;
	}

	/**
	 * 
	 * @param given schedule
	 * @return true if each scheduled task is scheduled for the correct amount of time
	 */
	public static boolean correctTimes(Schedule c) {
		for(Block b : c.getBlocks()) if((b.getEnd() - b.getStart()) != b.getTask().getEffort()) return false;
		return true;
	}

	/**
	 * 
	 * @param given schedule
	 * @return true if each scheduled task has all required skills at least once in the team
	 */
	public static boolean correctSkillAllocation(Schedule c) {

		for(Block b : c.getBlocks()) {
			ArrayList<Skills> requiredSkills = new ArrayList<Skills>();
			if(b.getTask().getRequiredSkills() != null) requiredSkills = new ArrayList<Skills>(b.getTask().getRequiredSkills());

			for(Person p : b.getPeople()) {
				if(p.getSkills() != (null)) {
					for(Skills s : p.getSkills()) {
						if(requiredSkills.contains(s)) {
							requiredSkills.remove(s);
						}
					}
				}
			}
			if(requiredSkills.size() != 0) return false;
		}
		return true;
	}

	/**
	 * 
	 * @param given schedule
	 * @return true if each scheduled task has the previous required tasks already scheduled
	 */
	public static boolean correctTaskAllocation(Schedule c) {

		for(Block b : c.getBlocks()) {

			ArrayList<Block> allTasksMinusB = new ArrayList<Block>(c.getBlocks());
			allTasksMinusB.remove(b);

			for(Block b1 : allTasksMinusB) {
				//If any other block contains a task that is required by b which ends after b starts, then the constraint is not respected
				if(b.getTask().getRequiredBefore() != null) {
					if(b.getTask().getRequiredBefore().contains(b1.getTask())) {
						if(b1.getEnd() > b.getStart()) {
							return false;
						}
					}
				}
			}

		}

		return true;

	}

	/**
	 * 
	 * @param given schedule
	 * @return true if no team member is sceduled to work on more than one task at once
	 */
	public static boolean correctPeopleAllocation(Schedule c) {

		//Contains all team members
		ArrayList<Person> workers = new ArrayList<Person>();

		for(Block b : c.getBlocks()) {
			for(Person p : b.getPeople()) if(!workers.contains(p)) workers.add(p);
		}

		for(Person p : workers) {
			//Contains all tasks person p works for
			ArrayList<Block> pWorksFor = new ArrayList<Block>();
			for(Block b : c.getBlocks()) if(b.getPeople().contains(p)) pWorksFor.add(b);

			for(Block b : pWorksFor) {
				ArrayList<Block> workersMinusB = new ArrayList<Block>(pWorksFor);
				workersMinusB.remove(b);
				//Iterate through all remaining tasks and check if there are any overlapping
				for(Block b1 : workersMinusB) {
					//Simultaneous if b1 starts after start of b but starts before end of b
					//Simultaneous if b1 starts before start of b but ends after start of b
					if((b1.getStart() > b.getStart() && b1.getStart() < b.getEnd()) || (b1.getStart() <= b.getStart() && b1.getEnd() > b.getStart())) {
						return false;
					}
				}

			}

		}

		return true;
	}

}
