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
	 * @param ItemEvent e
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox box = (JComboBox) e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected = box.getSelectedItem().toString();
			switch(box.getActionCommand()){
			case "effort":
				//set effort
			case "people":
				//set people
			}
			
	    }
	}
		
}

