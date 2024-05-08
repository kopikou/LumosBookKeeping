package org.example.Lumos.gui.MainWindow;

import org.example.Lumos.gui.OrderWindow.OrderWindowController;
import org.example.Lumos.gui.OrderWindow.OrderWindowView;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowController {
    public void execut(MainWindowView mainWindowView,IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        JTable incomeExpensesTable = mainWindowView.getIncomeExpensesTable();
        incomeExpensesTable.setModel(new IncomeExpensesTableModel(incomeService,expenseService));

        JButton addOrderButton = mainWindowView.getAddOrderButton();
        AddOrderActionListener addOrderActionListener = new AddOrderActionListener(mainWindowView);
        addOrderButton.addActionListener(addOrderActionListener);


    }

    private class AddOrderActionListener implements ActionListener{
        private MainWindowView mainWindowView;
        AddOrderActionListener(MainWindowView mainWindowView){
            this.mainWindowView = mainWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderWindowController orderWindowController = new OrderWindowController();
            orderWindowController.execut(new OrderWindowView(mainWindowView));
            mainWindowView.dispose();
        }
    }


}
