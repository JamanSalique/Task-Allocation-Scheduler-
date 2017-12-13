package view;

import java.util.Date;

import org.jfree.data.gantt.Task;

public class TaskNumeric extends Task {

	/**
	 * Constructor
	 * 
	 * @param description - set the name of the task.
     * @param start - set the start time of the task.
     * @param duration - set the end time of the task.
	 */
    public TaskNumeric(String description, long start, long end) {
        super(description, new Date(start), new Date(end));
    }


}