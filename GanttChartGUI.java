import java.awt.Color;
import java.awt.Dimension;
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

//dependencies -- TaskNumeric and GanttChartFactory classes.
// If we remove them the Chart will work awfully with numbers instead of an actual date.
public class GanttChartGUI extends ApplicationFrame {

	
    public GanttChartGUI(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(700, 500));
        setContentPane(jpanel);
        pack();
        setVisible(true);
    }

    // Create the chart frame.
    private static JFreeChart createChart(IntervalCategoryDataset dataset) {
        final JFreeChart chart = GanttChartFactory.createGanttChart(
            "Planning Schedule", "Task", "Time", dataset, true, true, false);
        return chart;
    }

    
    // Create a Dataset for the chart -> Tasks and a legend.
    private static IntervalCategoryDataset createDataset() {
        TaskSeries inProgress = new TaskSeries("In Progress");

        Task task = new TaskNumeric("task1", 0, 5);
       // task.setPercentComplete(0.5D);
        inProgress.add(task);
        Task task1 = new TaskNumeric("task2", 2,9);
        inProgress.add(task1);
        Task task2 = TaskNumeric.duration("task3", 6, 5);
        inProgress.add(task2);
        
        TaskSeries scheduled = new TaskSeries("Scheduled");
        Task task3 = new TaskNumeric("task4", 5, 7);
        scheduled.add(task3);

        TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
        taskseriescollection.add(inProgress);
        taskseriescollection.add(scheduled);
        return taskseriescollection;
    }

    // Create Panel -> added to the frame in the constructor.
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
        
        // Some test data.
        ArrayList<String> dayShift = new ArrayList<String>();
        dayShift.add("Mark");
        dayShift.add("John");
        dayShift.add("Adam");
        dayShift.add("George");
        ArrayList<String> nightShift = new ArrayList<String>();
        nightShift.add("Peter");
        nightShift.add("Steve");
        nightShift.add("Josh");
        nightShift.add("Dingo");
        
        // We have to override the BaseItemLabelGenerator in order to write on the time periods.
        renderer.setBaseItemLabelGenerator( new CategoryItemLabelGenerator(){
        	
        	
        	//has to be overridden
            @Override
            public String generateRowLabel(CategoryDataset dataset, int row) {
                return "Your Row Text  " + row;
            }

            // has to be overridden
            @Override
            public String generateColumnLabel(CategoryDataset dataset, int column) {
                return "Your Column Text  " + column;
            }

            // What we need -> the actual text on the time periods.
            @Override
            public String generateLabel(CategoryDataset dataset, int row, int column) {
                //return "Your Label Text  " + row + "," + column;
            	return dayShift.get(column) +  "," + nightShift.get(column);
            }


        });
        
        
        
        return chartpanel;
    }

    public static void main(String args[]) {
        GanttChartGUI ganttdemo = new GanttChartGUI("Planning App");
        RefineryUtilities.centerFrameOnScreen(ganttdemo);
    }

}