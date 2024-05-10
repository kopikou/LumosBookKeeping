package org.example.Lumos.gui.mainWindow;

import org.example.Lumos.domain.entity.People;
import org.example.Lumos.gui.orderWindow.OrderWindowController;
import org.example.Lumos.gui.orderWindow.OrderWindowView;
import org.example.Lumos.gui.peopleWindow.PeopleWindowController;
import org.example.Lumos.gui.peopleWindow.PeopleWindowView;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowController;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowView;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;
import org.example.Lumos.hibernate.services.PeopleServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindowController {
    private IncomeExpensesTableModel incomeExpensesTableModel;
    private JTable incomeExpensesTable;
    private IncomeServiceImpl incomeService;
    private ExpenseServiceImpl expenseService;
    private MainWindowView mainWindowView;
    private PeopleServiceImpl peopleService;
    public void execut(MainWindowView mainWindowView,IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.mainWindowView = mainWindowView;

        incomeExpensesTable = mainWindowView.getIncomeExpensesTable();
        incomeExpensesTableModel = new IncomeExpensesTableModel(incomeService,expenseService);
        incomeExpensesTable.setModel(incomeExpensesTableModel);

        JButton addOrderButton = mainWindowView.getAddOrderButton();
        AddOrderActionListener addOrderActionListener = new AddOrderActionListener(mainWindowView);
        addOrderButton.addActionListener(addOrderActionListener);

        JButton delOrderButton = mainWindowView.getDelOrderButton();
        DelOrderActionListener delOrderActionListener = new DelOrderActionListener();
        delOrderButton.addActionListener(delOrderActionListener);

        JButton seeShowProgramButton = mainWindowView.getSeeShowProgramButton();
        SeeShowProgramActionListener seeShowProgramActionListener = new SeeShowProgramActionListener(mainWindowView);
        seeShowProgramButton.addActionListener(seeShowProgramActionListener);

        peopleService = new PeopleServiceImpl();
        JButton seeEmployeesButton = mainWindowView.getSeeEmployeesButton();
        SeeEmployeesActionListener seeEmployeesActionListener = new SeeEmployeesActionListener(mainWindowView,peopleService.findAllPeople());
        seeEmployeesButton.addActionListener(seeEmployeesActionListener);
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

    private class DelOrderActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                incomeExpensesTableModel.delete(incomeExpensesTable.getSelectedRow());
                mainWindowView.dispose();
                execut(new MainWindowView(),incomeService,expenseService);
            }catch (IndexOutOfBoundsException ex){
            }
        }
    }

    private class SeeShowProgramActionListener implements ActionListener{
        private MainWindowView mainWindowView;
        SeeShowProgramActionListener(MainWindowView mainWindowView){
            this.mainWindowView = mainWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowProgramWindowController showProgramWindowController = new ShowProgramWindowController();
            showProgramWindowController.execut(new ShowProgramWindowView(mainWindowView));
            mainWindowView.dispose();
        }
    }

    private class SeeEmployeesActionListener implements ActionListener{
        private MainWindowView mainWindowView;
        private List<People> people;
        SeeEmployeesActionListener(MainWindowView mainWindowView,List<People> people ){
            this.mainWindowView = mainWindowView;
            this.people = people;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            PeopleWindowController peopleWindowController = new PeopleWindowController(people);
            peopleWindowController.execut(new PeopleWindowView(mainWindowView,"Сотрудники"));
            mainWindowView.dispose();
        }
    }
}
