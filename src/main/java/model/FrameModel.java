package model;

import java.util.Observable;

/**
 * Model for MainWindow to switch between panels
 * 
 * @see ButtonListener
 * @see MainWindow
 */
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
	
	/**
	 * Calls the update method in the main window so that
	 * the user can reset application
	 */
	public void reset(){
		methodNum = 3;
		setChanged();
		notifyObservers();
	}

	/**
	 * Getter for methodNum
	 * 
	 * @return Method number, left button returns 1, right returns 2
	 */
	public int getMethodNum() {
		return methodNum;
	}
}
