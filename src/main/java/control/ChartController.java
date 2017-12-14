package control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.GanttChartGUI;

public class ChartController implements ActionListener{

	private JPanel schedule;
	
	public ChartController(JPanel schedule){
		this.schedule = schedule;
	}
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
