package org.example.Lumos.gui.orderWindow;

import org.example.Lumos.domain.entity.Expense;
import org.example.Lumos.domain.entity.Income;
import org.example.Lumos.domain.entity.People;
import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.gui.mainWindow.MainWindowController;
import org.example.Lumos.gui.mainWindow.MainWindowView;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderWindowController {
    private final IncomeServiceImpl incomeService = new IncomeServiceImpl();
    private final ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
    private List<JComboBox> artistsComboBoxes,techniciansComboBoxes,transferComboBoxes;
    private JComboBox showProgramComboBox;
    private  List<ShowProgram> showPrograms;
    private final ShowProgramServiceImpl showProgramService = new ShowProgramServiceImpl();
    private JTextField placeTextField;
    private JButton addButton;
    private JXDatePicker picker;
    public void execut(OrderWindowView orderWindowView){
        JFrame parentFrame = orderWindowView.getParentFrame();

        orderWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                orderWindowView.dispose();
            }
        });

        showPrograms = showProgramService.findAllShowPrograms();
        showProgramComboBox = orderWindowView.getShowProgramComboBox();
        //Заполняем выпадающий список шоу-программ
        for (ShowProgram showProgram : showPrograms) showProgramComboBox.addItem(showProgram.getTitle());
        showProgramComboBox.setSelectedItem(null);

        //Делаем все выпадающие списки недоступными
        artistsComboBoxes = orderWindowView.getArtistsComboBoxes();
        for (JComboBox artistsComboBox : artistsComboBoxes) {
            artistsComboBox.setEnabled(false);
        }
        techniciansComboBoxes = orderWindowView.getTechniciansComboBoxes();
        for (JComboBox techniciansComboBox : techniciansComboBoxes) {
            techniciansComboBox.setEnabled(false);
        }
        transferComboBoxes = orderWindowView.getTransferComboBoxes();
        for (JComboBox transferComboBox : transferComboBoxes) {
            transferComboBox.setEnabled(false);
        }

        //Добавляем обработчик на выбор какой-либо шоу-программы из выпадающего списка
        showProgramComboBox.addActionListener(new SelectedShowProgramActionListener(showPrograms,showProgramComboBox));

        //Добавляем обработчик нажатия кнопки добавления
        addButton = orderWindowView.getAddButton();
        AddButtonActionListener addButtonActionListener = new AddButtonActionListener(parentFrame,orderWindowView);
        addButton.addActionListener(addButtonActionListener);
        addButton.setEnabled(false);

        placeTextField = orderWindowView.getPlaceTextField();
        placeTextField.setEnabled(false);

        picker = orderWindowView.getPicker();
        picker.setEnabled(false);
    }

    private class SelectedShowProgramActionListener implements ActionListener{
        private final List<ShowProgram> showPrograms;
        private final JComboBox showProgramComboBox;
        SelectedShowProgramActionListener(List<ShowProgram> showPrograms, JComboBox showProgramComboBox){
            this.showPrograms = showPrograms;
            this.showProgramComboBox = showProgramComboBox;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //При выборе какой-то шоу-программы открываем доступ к остальным полям формы
            placeTextField.setEnabled(true);
            addButton.setEnabled(true);
            picker.setEnabled(true);

            int id = 0;//Запоминаем id шоу-программы из БД
            for (int i = 0; i < showPrograms.size(); i++){
                if(showPrograms.get(i).getTitle() == showProgramComboBox.getSelectedItem())
                    id = i;
            }

            //Заполняем выпадающие списки артистами
            int artictsCnt = showPrograms.get(id).getArtistsCnt();
            cleanComboBox(artistsComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getArtists().size(); i++){
                for (int j = 0; j < artictsCnt; j++){
                    artistsComboBoxes.get(j).addItem(showPrograms.get(id).getArtists().get(i).getName());
                    artistsComboBoxes.get(j).setEnabled(true);
                    artistsComboBoxes.get(j).setSelectedItem(null);
                }
            }

            //Заполняем выпадающие списки техниками
            int techniciansCnt = showPrograms.get(id).getTechniciansCnt();
            cleanComboBox(techniciansComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getTechnicians().size(); i++){
                for (int j = 0; j < techniciansCnt; j++){
                    techniciansComboBoxes.get(j).addItem(showPrograms.get(id).getTechnicians().get(i).getName());
                    techniciansComboBoxes.get(j).setEnabled(true);
                    techniciansComboBoxes.get(j).setSelectedItem(null);
                }
            }

            //Заполняем выпадающие списки трансфера
            int transferCnt = showPrograms.get(id).getTransferCnt();
            cleanComboBox(transferComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getTransfers().size(); i++){
                for (int j = 0; j < transferCnt; j++){
                    transferComboBoxes.get(j).addItem(showPrograms.get(id).getTransfers().get(i).getName());
                    transferComboBoxes.get(j).setEnabled(true);
                    transferComboBoxes.get(j).setSelectedItem(null);
                }
            }
        }
        private void cleanComboBox(List<JComboBox> comboBoxes){
            for (JComboBox comboBox : comboBoxes) {
                comboBox.removeAllItems();
                comboBox.setEnabled(false);
            }
        }
    }

    private class AddButtonActionListener implements ActionListener{
        private final OrderWindowView orderFrame;

        AddButtonActionListener(JFrame parentFrame,OrderWindowView orderFrame){
            this.orderFrame = orderFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = 0;//Запоминаем id шоу-программы из БД
            for (int i = 0; i < showPrograms.size(); i++) {
                if (showProgramComboBox.getSelectedItem() == showPrograms.get(i).getTitle()) {
                    id = i;
                }
            }

            JTextField placeTextField = orderFrame.getPlaceTextField();

            boolean emptyArtists = false;
            boolean emptyTechnicians = false;
            boolean emptyTransfer = false;
            boolean sameArtists = false;
            boolean sameTechnicians = false;
            boolean sameTransfer = false;
            //Есть ли незаполненые поля
            for(int i = 0; i < showPrograms.get(id).getArtistsCnt();i++){
                if(artistsComboBoxes.get(i).getSelectedItem() == null){
                    emptyArtists = true;
                }
            }
            //Есть ли дублирующиеся поля
            for(int i = 0; i < showPrograms.get(id).getArtistsCnt()-1;i++){
                if(artistsComboBoxes.get(i).getSelectedItem() == artistsComboBoxes.get(i+1).getSelectedItem()){
                    sameArtists = true;
                }
            }

            //Есть ли незаполненые поля
            for(int i = 0; i < showPrograms.get(id).getTechniciansCnt();i++){
                if(techniciansComboBoxes.get(i).getSelectedItem() == null){
                    emptyTechnicians = true;
                }
            }//Есть ли дублирующиеся поля
            for(int i = 0; i < showPrograms.get(id).getTechniciansCnt()-1;i++){
                if(techniciansComboBoxes.get(i).getSelectedItem() == techniciansComboBoxes.get(i+1).getSelectedItem()){
                    sameTechnicians = true;
                }
            }

            //Есть ли незаполненые поля
            for(int i = 0; i < showPrograms.get(id).getTransferCnt();i++){
                if(transferComboBoxes.get(i).getSelectedItem() == null){
                    emptyTransfer = true;
                }
            }//Есть ли дублирующиеся поля
            for(int i = 0; i < showPrograms.get(id).getTransferCnt()-1;i++){
                if(transferComboBoxes.get(i).getSelectedItem() == transferComboBoxes.get(i+1).getSelectedItem()){
                    sameTransfer = true;
                }
            }

            //Если выбрана какая-то шоу-программа из списка, указано место проведения заказа, выбраны сотрудники
            if(showProgramComboBox.getSelectedItem() != null && !placeTextField.getText().isEmpty() && !emptyArtists && !emptyTechnicians && !emptyTransfer && !sameArtists && !sameTechnicians && !sameTransfer) {
                Income income = new Income();

                income.setShowProgram(showPrograms.get(id));
                income.setPlace(placeTextField.getText());

                JXDatePicker picker = orderFrame.getPicker();
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                income.setDate(formater.format(picker.getDate()));

                incomeService.saveIncome(income);

                //Рассчитываем зарплату сотрудников
                List<People> people = new ArrayList<>();
                List<Integer> salary = new ArrayList<>();
                int artictsCnt = showPrograms.get(id).getArtistsCnt();
                for (int i = 0; i < showPrograms.get(id).getArtists().size(); i++) {
                    for (int j = 0; j < artictsCnt; j++) {
                        if (artistsComboBoxes.get(j).getSelectedItem() == showPrograms.get(id).getArtists().get(i).getName()) {
                            people.add(showPrograms.get(id).getArtists().get(i));
                            salary.add(showPrograms.get(id).getArtistSalary());
                        }
                    }
                }
                int techniciansCnt = showPrograms.get(id).getTechniciansCnt();
                for (int i = 0; i < showPrograms.get(id).getTechnicians().size(); i++) {
                    for (int j = 0; j < techniciansCnt; j++) {
                        if (techniciansComboBoxes.get(j).getSelectedItem() == showPrograms.get(id).getTechnicians().get(i).getName()) {
                            boolean flag = false;//Если человек уже занесен в список тех, кто работал этот заказ
                            for (int k = 0; k < people.size(); k++) {
                                if (showPrograms.get(id).getTechnicians().get(i) == people.get(k)) {
                                    salary.set(k, salary.get(k) + showPrograms.get(id).getTechnicianSalary());//Увеличить зарплату
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {//Если человек ранее не был в списке сотрудников, добавить его в список
                                people.add(showPrograms.get(id).getTechnicians().get(i));
                                salary.add(showPrograms.get(id).getTechnicianSalary());
                            }
                        }
                    }
                }
                int transferCnt = showPrograms.get(id).getTransferCnt();
                for (int i = 0; i < showPrograms.get(id).getTransfers().size(); i++) {
                    for (int j = 0; j < transferCnt; j++) {
                        if (transferComboBoxes.get(j).getSelectedItem() == showPrograms.get(id).getTransfers().get(i).getName()) {
                            boolean flag = false;
                            for (int k = 0; k < people.size(); k++) {
                                if (showPrograms.get(id).getTransfers().get(i) == people.get(k)) {
                                    salary.set(k, salary.get(k) + showPrograms.get(id).getTransferCost());
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                people.add(showPrograms.get(id).getTransfers().get(i));
                                salary.add(showPrograms.get(id).getTransferCost());
                            }
                        }
                    }
                }

                for (int i = 0; i < people.size(); i++) {
                    Expense expense = new Expense(salary.get(i), people.get(i), income);
                    expense.setIncome(income);
                    expense.setPerson(people.get(i));
                    expense.setSalary(salary.get(i));
                    expenseService.saveExpense(expense);
                }
                //Открываем главное окно программы
                MainWindowController mainWindowController = new MainWindowController();
                mainWindowController.execut(new MainWindowView(),incomeService,expenseService);
                orderFrame.dispose();
            }else{
                JOptionPane.showMessageDialog(orderFrame,
                        "Ошибка записи. Пожалуйста, заполните все поля.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
