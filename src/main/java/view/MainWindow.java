package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ButtonListener;
import control.Control;
import control.ChartController;
import model.FrameModel;
import model.PersonModel;
import model.TaskModel;

/**
 * @author Antonni Pykalisto
 * 
 * MainWindow is a JFrame which holds all the panels.
 * Two buttons are at the south of the frame which allows users to the next left or right panel.
 * MainWindow extends JFrame and implements Observer
 * 
 * @see JFrame
 * @see Observer
 * @see FrameModel
 * @see ButtonListener
 * @see CreatePeopleView
 * @see CreateTaskView
 * @see GranttChartGUI
 */

public class MainWindow extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	//Model
	private FrameModel model;
	private PersonModel personModel;
	private TaskModel taskModel;	
	
	//Control
	private ButtonListener buttonListener;
	
	//View
	private CreateTaskView createTask;
	private CreatePeopleView createPerson;
	private JPanel schedule;
	
	private static int currentCenterPanelIndex;
	
	private CardLayout centerPanelLayout;

	private JButton scheduleGenerator;
	private JButton leftButton;
	private JButton rightButton;
	private JButton reset;

	private JPanel currentCenterPanel;
	private JPanel bottomPanel;	
	
	/**
	 * MainWindow Constructor to create an instance of the class
	 * 
	 * @param model Object of the FrameModel class which holds the main functionalities of the MainWindow class
	 * @throws InterruptedException
	 * 
	 * @see FrameModel
	 * @see ButtonListener
	 */
	public MainWindow(FrameModel model) throws InterruptedException{

		super("Task Planner");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		personModel = new PersonModel();
		taskModel = new TaskModel();
				
		schedule = new JPanel();
		schedule.setLayout(new BorderLayout());
		createTask = new CreateTaskView(taskModel);
		createPerson = new CreatePeopleView(personModel);
		
		buttonListener = new ButtonListener(model);
		
		//================================ Buttons ====================================
		
		scheduleGenerator = new JButton("Start!");
        scheduleGenerator.setBounds(90,430,126,24);
        schedule.add(scheduleGenerator, BorderLayout.NORTH);
        ChartController cc = new ChartController(schedule);
        scheduleGenerator.addActionListener(cc);

		leftButton = new JButton("<");
		leftButton.addActionListener(buttonListener);
		leftButton.setActionCommand("move left");

		rightButton = new JButton(">");
		rightButton.addActionListener(buttonListener);
		rightButton.setActionCommand("move right");
		
		reset = new JButton("Reset");
		reset.addActionListener(buttonListener);
		reset.setActionCommand("reset");
		
		//================================= Panels ====================================
		
		currentCenterPanelIndex = 1;
		
		centerPanelLayout = new CardLayout();
		currentCenterPanel = new JPanel();
		currentCenterPanel.setOpaque(false);
		currentCenterPanel.setLayout(centerPanelLayout);
		currentCenterPanel.add(createPerson, "1");
		currentCenterPanel.add(createTask, "2");
		currentCenterPanel.add(schedule, "3");
					
		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(leftButton, BorderLayout.WEST);
		bottomPanel.add(rightButton, BorderLayout.EAST);
		bottomPanel.add(reset, BorderLayout.CENTER);

		//============================= Layout Manager ================================

		setLayout(new BorderLayout());
        add(currentCenterPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setSize(1200, 750);
        setLocationRelativeTo(null);
	}
	
	/**
	 * Invoked when the Observable class Model invokes the methods
	 * setChanged and notifyObservers
	 * 
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		model = (FrameModel) o;
		switch (model.getMethodNum()){
		//counting down the array storing the panels
		case 1:
			if (currentCenterPanelIndex != 1){
				currentCenterPanelIndex--;
				centerPanelLayout.show(currentCenterPanel, "" + currentCenterPanelIndex);
			} 
			break;
		//counting up the array storing the panels	
		case 2:
			if (currentCenterPanelIndex != 3){
				currentCenterPanelIndex++;
				centerPanelLayout.show(currentCenterPanel, "" + currentCenterPanelIndex);
			}
			break;
		case 3:
			try {
				init();
				this.setVisible(false);
				this.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Main method to run program
	 * 
	 * @param args
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		init();
	}
	
	/**
	 * Initializes components
	 * @throws InterruptedException 
	 */
	public static void init() throws InterruptedException{
		//create a new instance of each one.
		FrameModel model = new FrameModel();
		MainWindow view = new MainWindow(model);
		
		//adds the view to the model as an observer.
		model.addObserver(view);

		view.setVisible(true);
	}
}
