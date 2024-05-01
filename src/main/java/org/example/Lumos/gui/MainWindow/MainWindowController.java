package org.example.Lumos.gui.MainWindow;

import org.example.Lumos.gui.OrderWindow.OrderWindowController;
import org.example.Lumos.gui.OrderWindow.OrderWindowView;
import org.example.Lumos.services.ExpenseServiceImpl;
import org.example.Lumos.services.IncomeServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowController {
    //private IncomeServiceImpl incomeService;
    //private ExpenseServiceImpl expenseService;
    public void execut(MainWindowView mainWindowView,IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        JTable incomeExpensesTable = mainWindowView.getIncomeExpensesTable();
        incomeExpensesTable.setModel(new IncomeExpensesTableModel(incomeService,expenseService));

        JButton addOrderButton = mainWindowView.getAddOrderButton();
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderWindowController orderWindowController = new OrderWindowController();
                orderWindowController.execut(new OrderWindowView(mainWindowView));
            }
        });
    }
}
