package control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.GanttChartGUI;
/**
 * This is the controller for the start button for the schedule, implementing ActionListener.
 * @author junaid
 *
 */
public class ChartController implements ActionListener{

	private JPanel schedule;
	/**
	 * Constructs controller for Schedule
	 * @param schedule
	 */
	public ChartController(JPanel schedule){
		this.schedule = schedule;
	}
	/**
	 * This is activated when the start schedule button is clicked. This generates a Gantt chart showing the schedule.
	 *@param e ActionEvent that is triggered when a button in the MainWindow frame is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Control.instantiate();
		JPanel ganttdemo = new GanttChartGUI("Planning App");
		ganttdemo.setName("schedule");
		schedule.add(ganttdemo, BorderLayout.CENTER);
		schedule.revalidate();
		schedule.repaint();
		schedule.setVisible(true);
		
	}

}
