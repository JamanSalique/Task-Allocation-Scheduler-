package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FrameModel;

public class ButtonListener implements ActionListener{
	
	private FrameModel model;
	
	public ButtonListener(FrameModel model){
		this.model = model;
	}

	/**
	 * Checks the action command of the button that has been pressed, using switch statements.
	 * It then calls methods from the model or the ripley model (depending in the button).
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
		}
	}

}
