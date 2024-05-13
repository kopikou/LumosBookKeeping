package org.example.Lumos.gui.peopleWindow;

import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;

public class PeopleWindowView extends JFrame {
    private final JFrame parent;
    private JPanel mainPanel,buttonPanel;
    private JList<String> people;
    private JButton addPersonButton,delPersonButton;
    private JTextField nameTextField;
    private JComboBox personComboBox;
    private final String title;
    public PeopleWindowView(JFrame parent,String title){
        super(title);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;
        this.title = title;

        initComponents();
        placeComponents();

        pack();
        setSize(400,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(){
        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        people = new JList<>();

        addPersonButton = new JButton("Добавить");
        delPersonButton = new JButton("Удалить");

        nameTextField = new JTextField(20);
        personComboBox = new JComboBox<>();
    }

    private void placeComponents(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        buttonPanel.setLayout(new VerticalLayout());

        JScrollPane jcrollPane = new JScrollPane(people);

        mainPanel.add(jcrollPane);

        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(nameTextField);
        personComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(personComboBox);

        addPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(addPersonButton);
        delPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(delPersonButton);

        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }

    public JList<String> getPeopleList() {
        return people;
    }
    public JButton getAddPersonButton(){return addPersonButton;}

    public JButton getDelPersonButton() {
        return delPersonButton;
    }

    public JComboBox getPersonComboBox() {
        return personComboBox;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public String getTitle(){return title;}
}