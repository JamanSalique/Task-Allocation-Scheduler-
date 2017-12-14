package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FrameModel;

/**
 * Controller for panel switching buttons in MainWindow, implementing ActionListener
 */
public class ButtonListener implements ActionListener{
	
	private FrameModel model;
	
	/**
	 * Constructs controller for MainWindow
	 * 
	 *@param model Uses a FrameModel object to perform the switching between panels
	 *@see FrameModel
	 *@see MainWindow
	 */
	public ButtonListener(FrameModel model){
		this.model = model;
	}

	/**
	 * Activated when a button in the MainWindow is activated, gets the action command to check whether to switch to the right or left panel
	 * 
	 *@param e ActionEvent that is triggered when a button in the MainWindow frame is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "move left":
			model.moveLeft();
			break;
		case "move right":
			model.moveRight();
			break;
		case "reset":
			model.reset();
			break;
		}
	}

}
