package org.example.Lumos.gui.peopleWindow;

import org.example.Lumos.hibernate.services.PeopleServiceImpl;

import javax.swing.*;
import java.awt.*;

public class PeopleWindowView extends JFrame {
    private JFrame parent;
    private JPanel mainPanel;
    private JScrollPane jcrollPane;
    private JList<String> people;
    public PeopleWindowView(JFrame parent,String title){
        super(title);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;

        initComponents();
        placeComponents();

        pack();
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(){
        mainPanel = new JPanel();

        people = new JList<>();
    }

    private void placeComponents(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));

        jcrollPane = new JScrollPane(people);

        this.add(jcrollPane);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }

    public JList<String> getPeopleList() {
        return people;
    }
}