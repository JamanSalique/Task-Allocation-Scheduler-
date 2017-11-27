package model;

import java.util.Observable;

public class FrameModel extends Observable{
	private int methodNum;
	/**
	 * Calls the update method in the main window so that
	 * the user can move to the panel to the left of the current panel.
	 */
	public void moveLeft(){
		methodNum = 1;
		setChanged();
		notifyObservers();
	}

	/**
	 * Calls the update method in the main window so that
	 * the user can move to the panel to the right of the current panel.
	 */
	public void moveRight(){
		methodNum = 2;
		setChanged();
		notifyObservers();
	}

	public int getMethodNum() {
		return methodNum;
	}
}
