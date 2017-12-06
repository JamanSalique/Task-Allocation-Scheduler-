package view;

import javax.swing.*;

import control.PeopleController;
import model.PersonModel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CreatePeopleView extends JPanel implements Observer{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel topInputPanel;
    private JPanel bottomInputPanel;
    private JLabel nameLabel;
    private JLabel skillsLabel;
    private JLabel titleLabel;
    private JTextField nameField;
    private JTextField skillsField;
    private JList<String> skillsList;
    private JList<String> addList;
    private JScrollPane skillsPane;
    private JScrollPane addPane;
    private JButton enter;
    private PersonModel model;

    private PeopleController controller;


    public CreatePeopleView(PersonModel model){

    	this.setLayout(new BorderLayout());
        this.model = model;
        nameField = new JTextField();
        skillsField = new JTextField();
        titleLabel = new JLabel("Click enter to add people. Double click on person to delete.");
        //skillsList = new JList(model.getABC());
        controller = new PeopleController(model, nameField, skillsField);
        inputPanel = new JPanel(new BorderLayout());
        topInputPanel = new JPanel(new BorderLayout());
        bottomInputPanel = new JPanel(new BorderLayout());
        nameLabel = new JLabel("Name: ");
        skillsLabel = new JLabel("Skill Level (0-100): ");
        nameField.addActionListener(controller);

        skillsField.addActionListener(controller);



//        skillsList.setSelectionModel(new DefaultListSelectionModel() {
//            @Override
//            public void setSelectionInterval(int a, int b) {
//                if(isSelectedIndex(a)) {
//                    removeSelectionInterval(a, b);
//                }
//                else {
//                    addSelectionInterval(a, b);
//                }
//            }
//        });




        addList = new JList<String>(model.getPeople());
        addList.addMouseListener(controller);
        //skillsPane = new JScrollPane(skillsList);
        addPane = new JScrollPane(addList);
        enter = new JButton("Enter");
        enter.addActionListener(controller);

   //     skillsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
   //     skillsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        topInputPanel.add(nameLabel, BorderLayout.WEST);
        topInputPanel.add(nameField, BorderLayout.CENTER);
        bottomInputPanel.add(skillsLabel, BorderLayout.WEST);
        bottomInputPanel.add(skillsField, BorderLayout.CENTER);
        add(addPane, BorderLayout.CENTER);
        inputPanel.add(topInputPanel, BorderLayout.NORTH);
        inputPanel.add(bottomInputPanel, BorderLayout.CENTER);
        inputPanel.add(enter, BorderLayout.SOUTH);topInputPanel.add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.NORTH);
    }

    @Override
    public void update(Observable o, Object arg) {

        skillsList.revalidate();
        skillsList.repaint();

        addList.revalidate();
        addList.repaint();
    }
}
