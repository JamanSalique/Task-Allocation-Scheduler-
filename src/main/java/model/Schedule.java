
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Schedule {
	
	private ArrayList<Block> blocks;
	
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
	
	/**
	 * Main constructor, takes a List of Tasks, and a List of people, and stores a list of schedule blocks
	 * @param ArrayList<Task> tasks : List of tasks to be included in the schedule
	 * @param ArrayList<Person> people : List of people to be included in the schedule
	 */
	public Schedule(ArrayList<ScheduleTask> tasks, ArrayList<Person> people){
		this.blocks = getSchedule(tasks, people);
	}
	
	/**
	 * @param ScheduleTask task : task to check
	 * @param ArrayList<Block> blocks : blocks to check from
	 * @return Returns true if a given task is scheduled in a given list of blocks, false otherwise
	 */
	public boolean isScheduled(ArrayList<Block> blocks, ScheduleTask task){
		for(Block b : blocks){
			if(b.getTask().equals(task))
				return true;
		}
		return false;
	}
	
	/**
	 * @param ArrayList<Block> b : The list of blocks to check from
	 * @param ScheduleTask t : The task for which the constraint is being verified
	 * @return true if all necessary tasks are done for t to be scheduled, false otherwise
	 */
	public boolean areTasksDone(ArrayList<Block> b, ScheduleTask t){
		if(t.getRequiredBefore() != (null)){
			for(ScheduleTask toDo : t.getRequiredBefore()){
				if(!isScheduled(b, toDo))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets all possible teams of people which could work on a given task
	 * @param ScheduleTask task : task for which possible teams are returned
	 * @param ArrayList<Person> : people that constitute the total pool of workers that could work on the task
	 * @return the list of all possible teams for a task, which contain all the required skills for that task
	 */
	public ArrayList<ArrayList<Person>> getPossibleTeams(ScheduleTask task, ArrayList<Person> people){
		
        ArrayList<ArrayList<Person>> possibleTeams = combination(new ArrayList<ArrayList<Person>>(), people, new ArrayList<Person>(), 0, 0, task.getPeople());
        ArrayList<ArrayList<Person>> toReturn = new ArrayList<ArrayList<Person>>();
        
        for(ArrayList<Person> possibleTeam : possibleTeams){
        	ArrayList<Skills> neededSkills = new ArrayList<Skills>(task.getRequiredSkills());
        	for(Person p : possibleTeam){
        		if(p.getSkills() != null){
	        		for(Skills s : p.getSkills()){
	        			if(neededSkills.contains(s)){
	        				neededSkills.remove(s);
	        			}
	        		}
	        		if((neededSkills.size() == 0) && !toReturn.contains(possibleTeam)){
    					toReturn.add(possibleTeam);
    				}
	        	}
        	}
        }
        return toReturn;
	}
	
	/**
	 * Recursive method which computes combinations
	 * @param ArrayList<ArrayList<T>> combinations : Keeps track of all current combinations
	 * @param ArrayList<T> arr : List that contains the elements that can be included in each combination
	 * @param ArrayList<T> data : List that contains the latest combination
	 * @param int start : Keeps track of the start index 
	 * @param int index : Keeps track of the current index
	 * @param int r: Size of each combination
	 */
	public static <T> ArrayList<ArrayList<T>> combination(ArrayList<ArrayList<T>> combinations, ArrayList<T> arr, ArrayList<T> data, int start, int index, int r){
		int end = arr.size() - 1;
		if (index == r)
			combinations.add(new ArrayList<T>(data.subList(0, r)));
	
		for (int i=start; i<=end && end-i+1 >= r-index; i++){
			data.add(index, arr.get(i));
			combination(combinations, arr, data, i+1, index+1, r);
		}
		return combinations;
	}
	
	/** 
	 * @param HashMap<Task, ArrayList<ArrayList<Person>>> input : A list of possible teams per task
	 * @return ArrayList<HashMap<Task, ArrayList<Person>>> : Returns a list of of all possible combinations of teams per task
	 */
	public ArrayList<HashMap<ScheduleTask, ArrayList<Person>>> getTeamCombinations(HashMap<ScheduleTask, ArrayList<ArrayList<Person>>> input){
		
		ArrayList<HashMap<ScheduleTask, ArrayList<Person>>> ret = new ArrayList<HashMap<ScheduleTask, ArrayList<Person>>>();	
		
		//Keeps track of which iteration we are through for each task
		HashMap<ScheduleTask, Integer> iteration = new HashMap<ScheduleTask, Integer>();
		HashMap<ScheduleTask, Integer> finalIt = new HashMap<ScheduleTask, Integer>();
		
		for(ScheduleTask t : input.keySet()){
			finalIt.put(t, input.get(t).size()-1);
			iteration.put(t, 0);
		}
		
		
		do{
			HashMap<ScheduleTask, ArrayList<Person>> cur = new HashMap<ScheduleTask, ArrayList<Person>>();
			for(ScheduleTask t : input.keySet()){
				cur.put(t, input.get(t).get(iteration.get(t)));
			}
			ret.add(cur);
			iteration = incrementMap(input, iteration);
		}while(!iteration.equals(finalIt));
		
		return ret;
	}
	
	/**
	 * Given a list of possible teams for each task, this method chooses the combination of tasks which maximizes the number of workers
	 * @param HashMap<Task, ArrayList<ArrayList<Person>>> input : Map containing a list of possible teams for each task
	 */
	public HashMap<ScheduleTask, ArrayList<Person>> getTeams(HashMap<ScheduleTask, ArrayList<ArrayList<Person>>> input){
		
		ArrayList<HashMap<ScheduleTask, ArrayList<Person>>> possibleCombinations = getTeamCombinations(input);
		HashMap<ScheduleTask, ArrayList<Person>> bestCombination = null;
		int number = 0;
		
		for(HashMap<ScheduleTask, ArrayList<Person>> cur : possibleCombinations){
			
			ArrayList<Person> allPeople = new ArrayList<Person>();
			
			for(ScheduleTask t : cur.keySet()){
				for(Person p : cur.get(t)){
					if(!allPeople.contains(p)){
						allPeople.add(p);
					}
				}
			}
			if(allPeople.size() > number){
				number = allPeople.size();
				bestCombination = cur;
			}
		}
		return bestCombination;
	}
	
	
	
	
	public HashMap<ScheduleTask, Integer> incrementMap (HashMap<ScheduleTask, ArrayList<ArrayList<Person>>> mainMap, HashMap<ScheduleTask, Integer> toIncrement){
		
		for(ScheduleTask t : mainMap.keySet()){
			if(toIncrement.get(t) < mainMap.get(t).size() - 1){
				toIncrement.put(t, toIncrement.get(t) + 1);
				mainMap.keySet().stream().skip(1);
				break;
			}else{
				toIncrement.put(t, 0);
			}
		}
		return toIncrement;
	}
	
	/**
	 * Method which schedules each block given a map of task and their corresponding teams, such that tasks cannot be scheduled simultaneously if they have team members in common
	 * @param HashMap<Task, ArrayList<Person>> teams : Map containing each task and their team
	 * @param int currentTime : time from which the tasks can be scheduled 
	 */
	public ArrayList<Block> getBlocksFromTeams(HashMap<ScheduleTask, ArrayList<Person>> teams, int currentTime){
		
		
		ArrayList<Block> ret = new ArrayList<Block>();
		
		for(ScheduleTask t : teams.keySet()){
			int lowestStart = currentTime;
			int lowestEnd = currentTime + t.getEffort();
			
			for(Block b : ret){
				if(containsAny(b.getPeople(), teams.get(t))){
					int start = b.getStart();
					int end = b.getEnd();
					
					if((start < lowestEnd) || (end > lowestStart)){
						lowestStart = end;
						lowestEnd = end + t.getEffort();
					}
				}
			}
			ret.add(new Block(t, lowestStart, lowestEnd, teams.get(t)));
		}
		return ret;	
	}
	
	
	
	/**
	 * @return true if at least one of the elements in the second list, is also in the first list, false otherwise
	 */
	public static <T> boolean containsAny(ArrayList<T> first, ArrayList<T> second){
		for(T t: second){
			if(first.contains(t))
				return true;
		}
		return false;
	}
	
	
	
	
	
	//Constraint 1: Number of people in a task (done)
	//Constraint 2: Skills in a task (done)
	//Constraint 3: previous tasks needed to be done (done)
	
	public ArrayList<Block> getSchedule(ArrayList<ScheduleTask> tasks, ArrayList<Person> people){
		
		ArrayList<ScheduleTask> inputCopy = tasks;
		ArrayList<Block> toReturn = new ArrayList<Block>();
		int time = 0;
		boolean keepGoing = true;
		int ii = 0;
		while(inputCopy.size() != 0 && keepGoing){
			keepGoing = false;
			HashMap<ScheduleTask, ArrayList<ArrayList<Person>>> possibleTeamsPerTask = new HashMap<ScheduleTask, ArrayList<ArrayList<Person>>>();
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			for(int i = 0; i < inputCopy.size(); i++){
				if(areTasksDone(toReturn, inputCopy.get(i))){
					ArrayList<ArrayList<Person>> teams =  getPossibleTeams(inputCopy.get(i), people);

					if(teams.size() != 0){
						//System.out.println(ii + " : " + inputCopy.size() + " : " + inputCopy.get(i).getName());
						possibleTeamsPerTask.put(inputCopy.get(i), teams);
						keepGoing = true;
						inputCopy.remove(i);
					}
				}
			}
			

			HashMap<ScheduleTask, ArrayList<Person>> bestTeamPerTask = getTeams(possibleTeamsPerTask);

			ArrayList<Block> newBlocks = getBlocksFromTeams(bestTeamPerTask, time);

			for(Block b : newBlocks){
				if(b.getEnd() > time){
					time = b.getEnd();
				}
				toReturn.add(b);
			}
			++ii;
		}

		return toReturn;
	}	
}
