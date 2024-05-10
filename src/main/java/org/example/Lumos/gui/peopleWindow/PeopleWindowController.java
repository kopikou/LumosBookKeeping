package org.example.Lumos.gui.peopleWindow;

import org.example.Lumos.domain.entity.People;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class PeopleWindowController {
    private JList<String> peopleList;
    private List<People> people;
    private DefaultListModel<String> persons = new DefaultListModel<String>();
    private JTextField nameTextField;
    private JComboBox personComboBox;
    private JButton addPersonButton;
    public PeopleWindowController(List<People> people){
        this.people = people;
    }
    public void execut(PeopleWindowView peopleWindowView){
        JFrame parentFrame = peopleWindowView.getParentFrame();
        peopleWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                peopleWindowView.dispose();
            }
        });

        peopleList = peopleWindowView.getPeopleList();
        for(int i = 0; i < people.size(); i++){
            persons.addElement(people.get(i).getName());
        }
        peopleList.setModel(persons);

        nameTextField = peopleWindowView.getNameTextField();
        personComboBox = peopleWindowView.getPersonComboBox();

        addPersonButton = peopleWindowView.getAddPersonButton();

        if(peopleWindowView.getTitle().equals("Сотрудники")){
            nameTextField.setVisible(true);
            personComboBox.setVisible(false);
        }else{
            nameTextField.setVisible(false);
            personComboBox.setVisible(true);
        }

    }


}
