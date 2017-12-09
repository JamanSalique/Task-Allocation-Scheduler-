package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.IntervalCategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import control.Control;
import model.Block;

//dependencies -- TaskNumeric and GanttChartFactory classes.
public class GanttChartGUI extends JPanel {

	/**
	 * Constructor
	 * 
	 * @param s - set a name for the chart.
	 * Inside the constructor the preferred layout and size are set.
	 */    
    public GanttChartGUI(String s) {
        setLayout(new GridLayout());
        add(createDemoPanel());
        setPreferredSize(new Dimension(700, 500));
        setVisible(true);
    }

    /**
     * Create the actual chart, setting the actual attributes inside the method.
     * @param dataset - represents all the tasks, their names and durations.
     * 
     */
    private static JFreeChart createChart(IntervalCategoryDataset dataset) {
        final JFreeChart chart = GanttChartFactory.createGanttChart(
            "Planning Schedule", "Task", "Time", dataset, true, true, false);
        return chart;
    }

    
    /**
     * Create a Dataset for the chart.
     * Each Task must have a name, start time and end time.
     */
    private static IntervalCategoryDataset createDataset() {
        TaskSeries inProgress = new TaskSeries("In Progress");       

        for(Block b : Control.instance().getSchedule().getBlocks()) {
                inProgress.add(new TaskNumeric(b.getTask().getName(), b.getStart(), b.getEnd()));
        }
        
        TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
        taskseriescollection.add(inProgress);
        return taskseriescollection;
    }

    /**
     * Create the panel with the GanttChart.
     */
    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setMouseWheelEnabled(true);
        
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setBaseItemLabelGenerator( new IntervalCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(Color.BLACK);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
        ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER));
        
       
        
        // We have to override the BaseItemLabelGenerator in order to write on the time periods.
        renderer.setBaseItemLabelGenerator( new CategoryItemLabelGenerator(){
            
            
            @Override
            public String generateRowLabel(CategoryDataset dataset, int row) {
                return "Your Row Text  " + row;
            }

            @Override
            public String generateColumnLabel(CategoryDataset dataset, int column) {
                return "Your Column Text  " + column;
            }

            @Override
            public String generateLabel(CategoryDataset dataset, int row, int column) {
                return Control.instance().getSchedule().getBlocks().get(column).getPeople().toString();
            }


        });
        
        
        
        return chartpanel;
    }

    public static void main(String args[]) {
        Control.instantiate();
        GanttChartGUI ganttdemo = new GanttChartGUI("Planning App");
    }

}