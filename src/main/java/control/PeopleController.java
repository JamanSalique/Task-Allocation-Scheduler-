package control;

import javax.swing.*;

import model.PersonModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PeopleController implements ActionListener, MouseListener{

    private PersonModel model;
    private JTextField f;
    private JTextField l;

    public PeopleController(PersonModel model, JTextField f, JTextField l){
        this.model = model;
        this.f = f;
        this.l = l;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!f.getText().equals("") && !l.getText().equals("")){
            model.addToPeople("Name: " + f.getText().trim() + "    Skill Level: " + l.getText().trim());
            f.setText("");
            l.setText("");
        } else if(!f.getText().equals("") && l.getText().equals("")){
            model.addToPeople("Name: " + f.getText().trim() + "    Skill Level: none ");
            f.setText("");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount()==2) {
            if (((JList<String>)e.getSource()).getModel().equals(model.getPeople())) {
                model.getPeople().remove(((JList<String>)e.getSource()).getSelectedIndex());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
