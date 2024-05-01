package org.example.Lumos.gui.MainWindow;

import org.example.Lumos.services.ExpenseServiceImpl;
import org.example.Lumos.services.IncomeServiceImpl;

import javax.swing.*;

public class MainWindowController {
    //private IncomeServiceImpl incomeService;
    //private ExpenseServiceImpl expenseService;
    public void execut(MainWindowView mainWindowView,IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        JTable incomeExpensesTable = mainWindowView.getIncomeExpensesTable();
        incomeExpensesTable.setModel(new IncomeExpensesTableModel(incomeService,expenseService));
    }
}
