package GUI;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ScheduleGUI {

	private  JFrame myFrame;
	private  JPanel center, west, east, south;
	private  JTable table;
	private  String[] employeesTask1 = new String[]{"Bob","Sarah"};
	private  String[] employeesTask2 = new String[]{"Jane","Chris","Steven"};
	private  String[] employeesTask3 = new String[]{"Bob","Chris","Steven"};
	private  String[] listOfTasksForTask3 = new String[]{"Task 1", "Task 2"};
	private  String[] na = new String[]{"N/A"};
	private  String[] columnNames = {"Task ID",
            "Task Name",
            "Estimate Time",
            "Start Time",
            "End Time", "Employees On Task", "List of tasks before the task can be started"};
	
	private  Object[][] data =  {
			{new Integer(1), "Task 1", new Integer(15), new Integer(0), new Integer(15), employeesTask1, na},
			{new Integer(2), "Task 2", new Integer(10), new Integer(10), new Integer(20), employeesTask2, na},
			{new Integer(3), "Task 3", new Integer(20), new Integer(20), new Integer(40), employeesTask3,listOfTasksForTask3 },
			};
	
	
	public ScheduleGUI(){
		myFrame = new JFrame();
		center = new JPanel();
		west = new JPanel();
		east = new JPanel();
		south = new JPanel();
		myFrame.setLayout(new BorderLayout());
		table = new JTable(data,columnNames);
		JScrollPane pane = new JScrollPane(createTable());
		center.add(pane);
		myFrame.add(center, BorderLayout.CENTER);
		myFrame.setVisible(true);
		myFrame.pack();
	}
	
	public JTable createTable(){
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
	    table.getColumnModel().getColumn(5).setCellRenderer(renderer);
	    table.getColumnModel().getColumn(6).setCellRenderer(renderer);
	    updateRowHeights();
	    updateColumnWidth();
		return table;
	}
	
	class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        //make multi line where the cell value is String[]
	        if (value instanceof String[]) {
	            setListData((String[]) value);
	        }

	        //cell backgroud color when selected
	        if (isSelected) {
	            setBackground(UIManager.getColor("Table.selectionBackground"));
	        } else {
	            setBackground(UIManager.getColor("Table.background"));
	        }

	        return this;
	    }
	}
	
	private void updateRowHeights()
	{
	    for (int row = 0; row < table.getRowCount(); row++)
	    {
	        int rowHeight = table.getRowHeight();

	        for (int column = 0; column < table.getColumnCount(); column++)
	        {
	            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        table.setRowHeight(row, rowHeight);
	    }
	}

	private void updateColumnWidth(){
		 TableColumn tableColumn5 = table.getColumnModel().getColumn(5);
		 TableColumn tableColumn6 = table.getColumnModel().getColumn(6);
		 TableColumn tableColumn2 = table.getColumnModel().getColumn(2);
		 tableColumn5.setPreferredWidth(150);
		 tableColumn6.setPreferredWidth(250);
		 tableColumn2.setPreferredWidth(100);
	}


}

