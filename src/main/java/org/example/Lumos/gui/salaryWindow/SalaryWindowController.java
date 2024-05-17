package org.example.Lumos.gui.salaryWindow;

import org.example.Lumos.domain.entity.Expense;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class SalaryWindowController{
    private JComboBox monthComboBox,yearComboBox;
    private JTable salariesTable;
    private Map<String,Double> salaryDictionary;
    private JFrame parentFrame;
    private SalaryTableModel salaryTableModel;
    private List<Expense> allExpense;
    public void execut(SalaryWindowView salaryWindowView) {
        parentFrame = salaryWindowView.getParentFrame();

        salaryWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                salaryWindowView.dispose();
            }
        });

        ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
        allExpense = expenseService.findAllExpense();

        salariesTable = salaryWindowView.getSalariesTable();
        monthComboBox = salaryWindowView.getMonthComboBox();
        yearComboBox = salaryWindowView.getYearComboBox();

        Date date = new Date();
        String month = new SimpleDateFormat("MMMM").format(date);
        String year = new SimpleDateFormat("yyyy").format(date);

        monthComboBox.addActionListener(new SelectedMonthActionListener());
        monthComboBox.setSelectedItem(month);

        yearComboBox.addActionListener(new SelectedYearActionListener());
        yearComboBox.setSelectedItem(year);
    }

    private class SelectedMonthActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            salaryDictionary = new HashMap<String,Double>();
            salaryCalculation(allExpense);

            salaryTableModel = new SalaryTableModel(salaryDictionary);
            salariesTable.setModel(salaryTableModel);

            salaryTableModel.dataChange();
        }
    }
    private class SelectedYearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            salaryDictionary = new HashMap<String,Double>();
            salaryCalculation(allExpense);

            salaryTableModel = new SalaryTableModel(salaryDictionary);
            salariesTable.setModel(salaryTableModel);

            salaryTableModel.dataChange();
        }
    }
    private void salaryCalculation(List<Expense> allExpense){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        List<Expense> expense = new ArrayList<>();

        for (Expense value : allExpense) {
            Date dateExpense = null;
            try {
                dateExpense = formater.parse(value.getIncome().getDate());
            } catch (ParseException ignored) {
            }
            String monthExpense = new SimpleDateFormat("MMMM").format(dateExpense);
            String yearExpence = new SimpleDateFormat("yyyy").format(dateExpense);
            if (monthExpense.equals(monthComboBox.getSelectedItem()) && yearExpence.equals(yearComboBox.getSelectedItem())) {
                expense.add(value);
            }
        }

        for (Expense value : expense) {
            String name = value.getPerson().getName();
            double salary = salaryDictionary.containsKey(name) ? salaryDictionary.get(name) : 0;
            salary += value.getSalary();
            salaryDictionary.put(name, salary);
        }
    }
}