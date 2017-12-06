import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CreatePeopleView extends JFrame implements Observer{

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
    private Model model;

    private Controller controller;


    public CreatePeopleView(Model model){

        setSize(700, 400);

        this.model = model;
        nameField = new JTextField();
        skillsField = new JTextField();
        titleLabel = new JLabel("Click enter to add people. Double click on person to delete.");
        //skillsList = new JList(model.getABC());
        controller = new Controller(model, nameField, skillsField);
        mainPanel = new JPanel(new BorderLayout());
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
        mainPanel.add(addPane, BorderLayout.CENTER);
        inputPanel.add(topInputPanel, BorderLayout.NORTH);
        inputPanel.add(bottomInputPanel, BorderLayout.CENTER);
        inputPanel.add(enter, BorderLayout.SOUTH);topInputPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.NORTH);


        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void update(Observable o, Object arg) {

        skillsList.revalidate();
        skillsList.repaint();

        addList.revalidate();
        addList.repaint();
    }
}
