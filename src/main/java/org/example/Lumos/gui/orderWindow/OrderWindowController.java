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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderWindowController {
    private IncomeServiceImpl incomeService = new IncomeServiceImpl();
    private ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
    private List<JComboBox> artistsComboBoxes,techniciansComboBoxes,transferComboBoxes;
    private JComboBox showProgramComboBox;
    private  List<ShowProgram> showPrograms;
    private ShowProgramServiceImpl showProgramService = new ShowProgramServiceImpl();
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
        for (int i = 0; i < showPrograms.size(); i++)
            showProgramComboBox.addItem(showPrograms.get(i).getTitle());

        showProgramComboBox.setSelectedItem(null);

        artistsComboBoxes = orderWindowView.getArtistsComboBoxes();
        for (int i = 0; i < artistsComboBoxes.size(); i++){
            artistsComboBoxes.get(i).setEnabled(false);
        }
        techniciansComboBoxes = orderWindowView.getTechniciansComboBoxes();
        for (int i = 0; i < techniciansComboBoxes.size(); i++){
            techniciansComboBoxes.get(i).setEnabled(false);
        }
        transferComboBoxes = orderWindowView.getTransferComboBoxes();
        for (int i = 0; i < transferComboBoxes.size(); i++){
            transferComboBoxes.get(i).setEnabled(false);
        }

        showProgramComboBox.addActionListener(new SelectedShowProgramActionListener(showPrograms,showProgramComboBox));

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
        private List<ShowProgram> showPrograms;
        private JComboBox showProgramComboBox;
        SelectedShowProgramActionListener(List<ShowProgram> showPrograms, JComboBox showProgramComboBox){
            this.showPrograms = showPrograms;
            this.showProgramComboBox = showProgramComboBox;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            placeTextField.setEnabled(true);
            addButton.setEnabled(true);
            picker.setEnabled(true);

            int id = 0;
            for (int i = 0; i < showPrograms.size(); i++){
                if(showPrograms.get(i).getTitle() == showProgramComboBox.getSelectedItem())
                    id = i;
            }

            int artictsCnt = showPrograms.get(id).getArtistsCnt();
            cleanComboBox(artistsComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getArtists().size(); i++){
                for (int j = 0; j < artictsCnt; j++){
                    artistsComboBoxes.get(j).addItem(showPrograms.get(id).getArtists().get(i).getName());
                    artistsComboBoxes.get(j).setEnabled(true);
                }
            }

            int techniciansCnt = showPrograms.get(id).getTechniciansCnt();
            cleanComboBox(techniciansComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getTechnicians().size(); i++){
                for (int j = 0; j < techniciansCnt; j++){
                    techniciansComboBoxes.get(j).addItem(showPrograms.get(id).getTechnicians().get(i).getName());
                    techniciansComboBoxes.get(j).setEnabled(true);
                }
            }

            int transferCnt = showPrograms.get(id).getTransferCnt();
            cleanComboBox(transferComboBoxes);
            for (int i = 0; i < showPrograms.get(id).getTransfers().size(); i++){
                for (int j = 0; j < transferCnt; j++){
                    transferComboBoxes.get(j).addItem(showPrograms.get(id).getTransfers().get(i).getName());
                    transferComboBoxes.get(j).setEnabled(true);
                }
            }
        }
        private void cleanComboBox(List<JComboBox> comboBoxes){
            for (int i = 0; i < comboBoxes.size(); i++){
                comboBoxes.get(i).removeAllItems();
                comboBoxes.get(i).setEnabled(false);
            }
        }
    }

    private class AddButtonActionListener implements ActionListener{
        private JFrame parentFrame;
        private OrderWindowView orderFrame;
        private JTextField placeTextField;
        AddButtonActionListener(JFrame parentFrame,OrderWindowView orderFrame){
            this.parentFrame = parentFrame;
            this.orderFrame = orderFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            placeTextField = orderFrame.getPlaceTextField();
            if(showProgramComboBox.getSelectedItem() != null && !placeTextField.getText().isEmpty()) {
                Income income = new Income();

                int id = 0;
                for (int i = 0; i < showPrograms.size(); i++) {
                    if (showProgramComboBox.getSelectedItem() == showPrograms.get(i).getTitle()) {
                        income.setShowProgram(showPrograms.get(i));
                        id = i;
                    }
                }
                //JTextField placeTextField = orderFrame.getPlaceTextField();
                income.setPlace(placeTextField.getText());
                JXDatePicker picker = orderFrame.getPicker();
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                income.setDate(formater.format(picker.getDate()));
                incomeService.saveIncome(income);

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
                            boolean flag = false;
                            for (int k = 0; k < people.size(); k++) {
                                if (showPrograms.get(id).getTechnicians().get(i) == people.get(k)) {
                                    salary.set(k, salary.get(k) + showPrograms.get(id).getTechnicianSalary());
                                    flag = true;
                                    break;
                                } else {
                                    flag = false;
                                }
                            }
                            if (!flag) {
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
                                } else {
                                    flag = false;
                                }
                            }
                            if (!flag) {
                                people.add(showPrograms.get(id).getTransfers().get(i));
                                salary.add(showPrograms.get(id).getTransferCost());
                            }
                        }
                    }
                }

                List<Expense> expenses = new ArrayList<>();
                for (int i = 0; i < people.size(); i++) {
                    Expense expense = new Expense(salary.get(i), people.get(i), income);
                    expense.setIncome(income);
                    expense.setPerson(people.get(i));
                    expense.setSalary(salary.get(i));
                    expenses.add(expense);
                    expenseService.saveExpense(expense);
                }
            }

            MainWindowController mainWindowController = new MainWindowController();
            mainWindowController.execut(new MainWindowView(),incomeService,expenseService);
            orderFrame.dispose();
        }
    }
}