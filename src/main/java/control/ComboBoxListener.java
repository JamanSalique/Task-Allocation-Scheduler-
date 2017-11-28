package control;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import model.FrameModel;

public class ComboBoxListener implements ItemListener{
	
	FrameModel model;

	public ComboBoxListener(FrameModel model){
		this.model = model;
	}

	/**
	 * Checks which combo box has been pressed in the welcome panel (from or to), so that it knows which method
	 * from the ripley model to run (setStartDate() or setEndDate())
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox box = (JComboBox) e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected = box.getSelectedItem().toString();
			switch(box.getActionCommand()){
			case "skill":
				//set skill
			case "people":
				//set people
			}
			
	    }
	}
		
}

