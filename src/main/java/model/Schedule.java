
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Schedule {
	
	private ArrayList<Block> blocks;
	
	/**
	 * Main constructor, takes a List of Tasks, and a List of people, and stores a list of schedule blocks
	 * @param tasks : List of tasks to be included in the schedule
	 * @param people : List of people to be included in the schedule
	 */
	public Schedule(ArrayList<Task> tasks, ArrayList<Person> people){
		this.blocks = getSchedule(tasks, people);
	}
	
	/**
	 * @param task : task to check
	 * @param blocks : blocks to check from
	 * @return true if a given task is scheduled in a given list of blocks, false otherwise
	 */
	public boolean isScheduled(ArrayList<Block> blocks, Task task){
		for(Block b : blocks){
			if(b.getTask().equals(task))
				return true;
		}
		return false;
	}
	
	/**
	 * @param b : The list of blocks to check from
	 * @param t : The task for which the constraint is being verified
	 * @return true if all necessary tasks are done for t to be scheduled, false otherwise
	 */
	public boolean areTasksDone(ArrayList<Block> b, Task t){
		if(t.getRequiredBefore() != (null)){
			for(Task toDo : t.getRequiredBefore()){
				if(!isScheduled(b, toDo))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets all possible teams of people which could work on a given task
	 * @param task : task for which possible teams are returned
	 * @param people : people that constitute the total pool of workers that could work on the task
	 * @return the list of all possible teams for a task, which contain all the required skills for that task
	 */
	public ArrayList<ArrayList<Person>> getPossibleTeams(Task task, ArrayList<Person> people){
		
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
	 * @param combinations : Keeps track of all current combinations
	 * @param arr : List that contains the elements that can be included in each combination
	 * @param data : List that contains the latest combination
	 * @param start : Keeps track of the start index 
	 * @param index : Keeps track of the current index
	 * @param r int : Size of each combination
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
	 * @param input : A list of possible teams per task
	 * @return a list of of all possible combinations of teams per task
	 */
	public ArrayList<HashMap<Task, ArrayList<Person>>> getTeamCombinations(HashMap<Task, ArrayList<ArrayList<Person>>> input){
		
		ArrayList<HashMap<Task, ArrayList<Person>>> ret = new ArrayList<HashMap<Task, ArrayList<Person>>>();	
		
		//Keeps track of which iteration we are through for each task
		HashMap<Task, Integer> iteration = new HashMap<Task, Integer>();
		HashMap<Task, Integer> finalIt = new HashMap<Task, Integer>();
		
		for(Task t : input.keySet()){
			finalIt.put(t, input.get(t).size()-1);
			iteration.put(t, 0);
		}
		
		
		while(!iteration.equals(finalIt)){
			
			HashMap<Task, ArrayList<Person>> cur = new HashMap<Task, ArrayList<Person>>();
			for(Task t : input.keySet()){
				cur.put(t, input.get(t).get(iteration.get(t)));
			}
			ret.add(cur);
			iteration = incrementMap(input, iteration);
		}
		
		return ret;
	}
	
	/**
	 * Given a list of possible teams for each task, this method chooses the combination of tasks which maximizes the number of workers
	 * @param input : Map containing a list of possible teams for each task
	 */
	public HashMap<Task, ArrayList<Person>> getTeams(HashMap<Task, ArrayList<ArrayList<Person>>> input){
		
		ArrayList<HashMap<Task, ArrayList<Person>>> possibleCombinations = getTeamCombinations(input);
		HashMap<Task, ArrayList<Person>> bestCombination = null;
		int number = 0;
		
		for(HashMap<Task, ArrayList<Person>> cur : possibleCombinations){
			
			ArrayList<Person> allPeople = new ArrayList<Person>();
			
			for(Task t : cur.keySet()){
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
	
	
	
	
	public HashMap<Task, Integer> incrementMap (HashMap<Task, ArrayList<ArrayList<Person>>> mainMap, HashMap<Task, Integer> toIncrement){
		
		for(Task t : mainMap.keySet()){
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
	 * @param teams : Map containing each task and their team
	 * @param currentTime : time from which the tasks can be scheduled 
	 */
	public ArrayList<Block> getBlocksFromTeams(HashMap<Task, ArrayList<Person>> teams, int currentTime){
		
		
		ArrayList<Block> ret = new ArrayList<Block>();
		
		for(Task t : teams.keySet()){
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
	
	//To minimise time;
	// 1) Scan through all tasks that can currently be done
	// 2) For each task, get a list of possible teams that could work on that task (from getPeopleForTask) to get Map(<Task>, ArrayList<ArrayList<Person>>)
	// 3) Find the combination of teams which maximises the number of different people working to get Map(<Task>, ArrayList<Person>) (Heuristic)
	// 4) Iterate through the map and schedule all tasks simultaneously

	public ArrayList<Block> getSchedule(ArrayList<Task> tasks, ArrayList<Person> people){
		
		ArrayList<Task> inputCopy = tasks;
		ArrayList<Block> toReturn = new ArrayList<Block>();
		int time = 0;
		boolean keepGoing = true;
		int ii = 0;
		while(inputCopy.size() != 0 && keepGoing){
			keepGoing = false;
			HashMap<Task, ArrayList<ArrayList<Person>>> possibleTeamsPerTask = new HashMap<Task, ArrayList<ArrayList<Person>>>();
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			for(int i = 0; i < inputCopy.size(); i++){
				if(areTasksDone(toReturn, inputCopy.get(i))){
					ArrayList<ArrayList<Person>> teams =  getPossibleTeams(inputCopy.get(i), people);

					if(teams.size() != 0){
						System.out.println(ii + " : " + inputCopy.size() + " : " + inputCopy.get(i).getName());
						possibleTeamsPerTask.put(inputCopy.get(i), teams);
						keepGoing = true;
						inputCopy.remove(i);
					}
				}
			}
			

			HashMap<Task, ArrayList<Person>> bestTeamPerTask = getTeams(possibleTeamsPerTask);

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
	
	
	
	public static void main(String args[]){
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        int r = 2;
        int n = arr.size();
        ArrayList<Integer> data = new ArrayList<Integer>();
     
        Task t2 = new Task("2", 4, 3, null, new ArrayList<Skills>());
        Task t1 = new Task("1", 3, 3, null, new ArrayList<Skills>(Arrays.asList(Skills.CPlus, Skills.CSS, Skills.dotNet)));
        Task t3 = new Task("3", 2, 2, null, new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Task t4 = new Task("4", 2, 3, null, new ArrayList<Skills>());
        Task t5 = new Task("5", 6, 3, null, new ArrayList<Skills>());
        Task t6 = new Task("6", 4, 3, null, new ArrayList<Skills>());
        Task t7 = new Task("7", 4, 3, null, new ArrayList<Skills>());

        ArrayList<Task> tasks = new ArrayList<Task>(Arrays.asList(t1,t2,t3, t4, t5, t6, t7));

        Person one = new Person("1", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person two = new Person("2",  new ArrayList<Skills>(Arrays.asList(Skills.CPlus)));
        Person three = new Person("3", new ArrayList<Skills>(Arrays.asList(Skills.dotNet)));
        Person four = new Person("4",  new ArrayList<Skills>(Arrays.asList(Skills.CSS)));
        Person five = new Person("5", null);
        Person six = new Person("6", null);
        ArrayList<Person> team = new ArrayList<Person>(Arrays.asList(one, two, three, four, five, six));
        
        ArrayList<Block> schedule = new Schedule(tasks, team).blocks;
        
        for(Block b : schedule){
        	System.out.println(b.toString());
        }
        
	}
	
	
}
