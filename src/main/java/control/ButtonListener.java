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
	 *@param ActionEvent e
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
