package org.example.Lumos.gui.peopleWindow;

import org.example.Lumos.domain.entity.People;
import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.hibernate.services.PeopleServiceImpl;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class PeopleWindowController {
    private JList<String> peopleList;
    private List<People> people;
    private DefaultListModel<String> persons = new DefaultListModel<String>();
    private JTextField nameTextField;
    private JComboBox personComboBox;
    private JButton addPersonButton,delPersonButton;
    private PeopleServiceImpl peopleService;
    private ShowProgramServiceImpl showProgramService;
    private PeopleWindowView peopleWindowView;
    private ShowProgram showProgram;
    private List<People> allPersons;
    public PeopleWindowController(){}
    public PeopleWindowController(List<People> people){
        this.people = people;
    }
    public PeopleWindowController(ShowProgram showProgram, List<People> people){
        this.showProgram = showProgram;
        this.people = people;
    }
    public void execut(PeopleWindowView peopleWindowView){
        this.peopleWindowView = peopleWindowView;

        JFrame parentFrame = peopleWindowView.getParentFrame();
        peopleWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                peopleWindowView.dispose();
            }
        });

        peopleService = new PeopleServiceImpl();
        showProgramService = new ShowProgramServiceImpl();

        peopleList = peopleWindowView.getPeopleList();
        for (People value : people) {
            persons.addElement(value.getName());
        }
        peopleList.setModel(persons);

        nameTextField = peopleWindowView.getNameTextField();
        personComboBox = peopleWindowView.getPersonComboBox();

        addPersonButton = peopleWindowView.getAddPersonButton();
        addPersonButton.addActionListener(new AddPersonActionListener());

        if(peopleWindowView.getTitle().equals("Сотрудники")){
            nameTextField.setVisible(true);
            personComboBox.setVisible(false);
        }else{
            nameTextField.setVisible(false);
            personComboBox.setVisible(true);

            allPersons = peopleService.findAllPeople();
            for(int i = 0; i < allPersons.size(); i++){
                for(int j = 0; j < people.size(); j++){
                    if (allPersons.get(i).getName().equals(people.get(j).getName())) {
                        allPersons.remove(i);
                    }
                }
            }
            for (People person : allPersons) {
                personComboBox.addItem(person.getName());
            }
        }
        personComboBox.setSelectedItem(null);

        delPersonButton = peopleWindowView.getDelPersonButton();
        delPersonButton.addActionListener(new DelPeopleActionListener());
    }

    private class AddPersonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(nameTextField.isVisible()){
                if(!nameTextField.getText().isEmpty()){
                    //addPersonButton.setEnabled(true);
                    People person = new People(nameTextField.getText());
                    peopleService.savePeople(person);

                    people.add(person);

                    peopleWindowView.dispose();
                    PeopleWindowController peopleWindowController = new PeopleWindowController(people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));

                    //execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }else{
                    JOptionPane.showMessageDialog(peopleWindowView,
                            "Ошибка записи. Пожалуйста, заполните все поля.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }if (personComboBox.isVisible()){
                PeopleWindowController peopleWindowController = new PeopleWindowController();
                if(personComboBox.getSelectedItem() != null){
                    //List<People> persons = peopleService.findAllPeople();
                    switch (peopleWindowView.getTitle()){
                        case "Артисты":{
                            showProgram.addArtist(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                            break;
                        }
                        case "Техники":{
                            showProgram.addTechnician(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                            break;
                        }
                        case "Трансфер":{
                            showProgram.addTransfer(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                            break;
                        }
                    }
                    //people.add(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                    peopleList.removeAll();
                    peopleWindowView.dispose();
                    //PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));

                    //execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }else{
                    JOptionPane.showMessageDialog(peopleWindowView,
                            "Ошибка записи. Пожалуйста, заполните все поля.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }

    private class DelPeopleActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(nameTextField.isVisible()){
                try{
                    People person = peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId());
                    //person.setName(peopleList.getSelectedValue());


                    peopleService.deletePeople(person);

                    people.remove(peopleList.getSelectedIndex());

                    peopleWindowView.dispose();
                    PeopleWindowController peopleWindowController = new PeopleWindowController(people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));

                    //execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }catch (IndexOutOfBoundsException ex){
                }
            }if (personComboBox.isVisible()){
                PeopleWindowController peopleWindowController = new PeopleWindowController();
                try{
                    switch (peopleWindowView.getTitle()){
                        case "Артисты":{
                            showProgram.removeArtist(peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId()));
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                            break;
                        }
                        case "Техники":{
                            showProgram.removeTechnician(peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId()));
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                            break;
                        }
                        case "Трансфер":{
                            showProgram.removeTransfer(peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId()),peopleList.getSelectedIndex());
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                            break;
                        }
                    }
                    //people.remove(peopleList.getSelectedIndex());
                    peopleList.removeAll();

                    peopleWindowView.dispose();
                    //PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));

                    //execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }catch (IndexOutOfBoundsException ex){
                }
            }
        }
    }
}
