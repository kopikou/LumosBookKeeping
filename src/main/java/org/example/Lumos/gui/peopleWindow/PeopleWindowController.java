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
import java.util.ArrayList;
import java.util.List;

public class PeopleWindowController {
    private JList<String> peopleList;
    private List<People> people;
    private final DefaultListModel<String> persons = new DefaultListModel<String>();
    private JTextField nameTextField;
    private JComboBox personComboBox;
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

        //Заполняем список сотрудников
        peopleList = peopleWindowView.getPeopleList();
        for (People value : people) {
            persons.addElement(value.getName());
        }
        peopleList.setModel(persons);

        nameTextField = peopleWindowView.getNameTextField();
        personComboBox = peopleWindowView.getPersonComboBox();

        JButton addPersonButton = peopleWindowView.getAddPersonButton();
        addPersonButton.addActionListener(new AddPersonActionListener());//Добавляем слушателя кнопке добавления сотрудника

        if(peopleWindowView.getTitle().equals("Сотрудники")){//В случае общего списка сотрудников, оставляем только текстовое поле для добавления новго сотрудника
            nameTextField.setVisible(true);
            personComboBox.setVisible(false);
        }else{//Иначе добавляем из уже занесенных в базу сотрудников
            nameTextField.setVisible(false);
            personComboBox.setVisible(true);

            allPersons = peopleService.findAllPeople();//Заполняем выпадающий список
            for(int i = 0; i < allPersons.size(); i++){
                for (People person : people) {
                    if (allPersons.get(i).getName().equals(person.getName())) {//Удалить из выпадающего списка тех сотрудников, которые уже работают
                        allPersons.remove(i);
                    }
                }
            }
            for (People person : allPersons) {
                personComboBox.addItem(person.getName());
            }
        }
        personComboBox.setSelectedItem(null);

        JButton delPersonButton = peopleWindowView.getDelPersonButton();
        delPersonButton.addActionListener(new DelPeopleActionListener());//Добавили слушателя для кнопки удаления сотрудника
    }

    private class AddPersonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(nameTextField.isVisible()){//Если окно с общим списком сотрудников
                if(!nameTextField.getText().isEmpty()){//Если введено имя
                    People person = new People(nameTextField.getText());
                    peopleService.savePeople(person);

                    people.add(person);//Добавить нового сотрудника в список

                    peopleWindowView.dispose();
                    PeopleWindowController peopleWindowController = new PeopleWindowController(people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }else{
                    JOptionPane.showMessageDialog(peopleWindowView,
                            "Ошибка записи. Пожалуйста, заполните все поля.",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }if (personComboBox.isVisible()){//Если список сотрудников определенной шоу-программы
                PeopleWindowController peopleWindowController = new PeopleWindowController();
                if(personComboBox.getSelectedItem() != null){
                    switch (peopleWindowView.getTitle()){
                        case "Артисты":{
                            if(showProgram.getArtists().size() == 1){//Если в списке есть уже один сотрудник, удалить его и заполнить список двумя сотрудниками
                                List<People> list = new ArrayList<>(showProgram.getArtists());
                                showProgram.removeArtist(peopleService.findPeople(showProgram.getArtists().get(0).getId()));
                                list.add(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgram.setArtists(list);
                                showProgramService.updateShowProgram(showProgram);
                            }else{//Просто обновить артистов шоу-программы
                                showProgram.addArtist(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgramService.updateShowProgram(showProgram);
                            }

                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                            break;
                        }
                        case "Техники":{
                            if(showProgram.getTechnicians().size() == 1){
                                List<People> list = new ArrayList<>(showProgram.getTechnicians());
                                showProgram.removeTechnician(peopleService.findPeople(showProgram.getTechnicians().get(0).getId()));
                                list.add(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgram.setTechnicians(list);
                                showProgramService.updateShowProgram(showProgram);
                            }else{
                                showProgram.addTechnician(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgramService.updateShowProgram(showProgram);
                            }

                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                            break;
                        }
                        case "Трансфер":{
                            if(showProgram.getTransfers().size() == 1){
                                List<People> list = new ArrayList<>(showProgram.getTransfers());
                                showProgram.removeTransfer(0);
                                list.add(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgram.setTransfers(list);
                                showProgramService.updateShowProgram(showProgram);
                            }else{
                                showProgram.addTransfer(peopleService.findPeople(allPersons.get(personComboBox.getSelectedIndex()).getId()));
                                showProgramService.updateShowProgram(showProgram);
                            }

                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                            break;
                        }
                    }

                    peopleList.removeAll();

                    peopleWindowView.dispose();
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
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
            if(nameTextField.isVisible()){//Если общий список всех сотрудников
                try{//Удалить выделенного из списка
                    People person = peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId());

                    peopleService.deletePeople(person);

                    people.remove(peopleList.getSelectedIndex());

                    peopleWindowView.dispose();
                    PeopleWindowController peopleWindowController = new PeopleWindowController(people);
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }catch (IndexOutOfBoundsException ignored){
                }
            }if (personComboBox.isVisible()){
                PeopleWindowController peopleWindowController = new PeopleWindowController();
                try{
                    switch (peopleWindowView.getTitle()){
                        case "Артисты":{
                            //showProgram.removeArtist(peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId()));
                            showProgram.removeArtist(peopleList.getSelectedIndex());
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                            break;
                        }
                        case "Техники":{
                            //showProgram.removeTechnician(peopleService.findPeople(people.get(peopleList.getSelectedIndex()).getId()));
                            showProgram.removeTechnician(peopleList.getSelectedIndex());
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                            break;
                        }
                        case "Трансфер":{
                            showProgram.removeTransfer(peopleList.getSelectedIndex());
                            showProgramService.updateShowProgram(showProgram);
                            peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                            break;
                        }
                    }
                    peopleList.removeAll();

                    peopleWindowView.dispose();
                    peopleWindowController.execut(new PeopleWindowView(peopleWindowView.getParentFrame(), peopleWindowView.getTitle()));
                }catch (IndexOutOfBoundsException ignored){
                }
            }
        }
    }
}
