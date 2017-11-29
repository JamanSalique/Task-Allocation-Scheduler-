package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ButtonListener;
import model.FrameModel;

/**
 * 
 * MainWindow is a JFrame which holds all the panels
 * Two buttons are at the south of the frame which allows users to the next left or right panel.
 */

public class MainWindow extends JFrame implements Observer{

	/**
	 * Default serial version added
	 */
	private static final long serialVersionUID = 1L;
	
	
	private FrameModel model;
	private ButtonListener buttonListener;
	
	private CreateTaskView createTask;
	private JPanel createPerson;
	private JPanel schedule;
	
	private int currentCenterPanelIndex;
	
	private CardLayout centerPanelLayout;

	private JButton leftButton;
	private JButton rightButton;

	private JPanel topPanel;
	private JPanel currentCenterPanel;
	private JPanel bottomPanel;	
	
	/**
	 * MainWindow Constructor to create an instance of the class
	 * 
	 * @param model an object of the FrameModel class which holds the main functionalities of the MainWindow class
	 * @throws InterruptedException
	 */
	
	public MainWindow(FrameModel model) throws InterruptedException{

		super("Task Planner");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		schedule = new JPanel();
		schedule.add(new JLabel("Schedule here"));
		createTask = new CreateTaskView();
		createPerson = new JPanel();
		createPerson.add(new JLabel("Create person here"));

		buttonListener = new ButtonListener(model);
		
		//================================ Buttons ====================================
		
		leftButton = new JButton("<");
		leftButton.addActionListener(buttonListener);
		leftButton.setActionCommand("move left");

		rightButton = new JButton(">");
		rightButton.addActionListener(buttonListener);
		rightButton.setActionCommand("move right");

		//================================= Panels ====================================
		
		currentCenterPanelIndex = 1;
		
		topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.add(new JLabel("Task Planner"));
		
		centerPanelLayout = new CardLayout();
		currentCenterPanel = new JPanel();
		currentCenterPanel.setOpaque(false);
		currentCenterPanel.setLayout(centerPanelLayout);
		currentCenterPanel.add(schedule, "1");
		currentCenterPanel.add(createTask, "2");
		currentCenterPanel.add(createPerson, "3");
					
		bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(leftButton, BorderLayout.WEST);
		bottomPanel.add(rightButton, BorderLayout.EAST);

		//============================= Layout Manager ================================

		setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(currentCenterPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setSize(1200, 750);
        setLocationRelativeTo(null);
	}
	
	/**
	 * Invoked when the Observable class Model invokes the methods
	 * setChanged and notifyObservers
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
		//counting up the array storing the panels	
		case 2:
			if (currentCenterPanelIndex != 3){
				currentCenterPanelIndex++;
				centerPanelLayout.show(currentCenterPanel, "" + currentCenterPanelIndex);
			}
		}
	}
	
	/**
	 * Main method starts program
	 * @throws InterruptedException, IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		
		//create a new instance of each one.
		FrameModel model = new FrameModel();
		MainWindow view = new MainWindow(model);
		

		//adds the view to the model as an observer.
		model.addObserver(view);

		view.setVisible(true);
	}
}

