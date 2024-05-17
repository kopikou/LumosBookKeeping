package org.example.Lumos.gui.mainWindow;

import org.example.Lumos.domain.entity.People;
import org.example.Lumos.gui.orderWindow.OrderWindowController;
import org.example.Lumos.gui.orderWindow.OrderWindowView;
import org.example.Lumos.gui.peopleWindow.PeopleWindowController;
import org.example.Lumos.gui.peopleWindow.PeopleWindowView;
import org.example.Lumos.gui.salaryWindow.SalaryWindowController;
import org.example.Lumos.gui.salaryWindow.SalaryWindowView;
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
    public void execut(MainWindowView mainWindowView,IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.mainWindowView = mainWindowView;

        //Назначили таблице модель
        incomeExpensesTable = mainWindowView.getIncomeExpensesTable();
        incomeExpensesTableModel = new IncomeExpensesTableModel(incomeService,expenseService);
        incomeExpensesTable.setModel(incomeExpensesTableModel);

        //Назначили кнопке добавления заказа слушателя
        JButton addOrderButton = mainWindowView.getAddOrderButton();
        AddOrderActionListener addOrderActionListener = new AddOrderActionListener(mainWindowView);
        addOrderButton.addActionListener(addOrderActionListener);

        //Назначили кнопке удаления заказа слушателя
        JButton delOrderButton = mainWindowView.getDelOrderButton();
        DelOrderActionListener delOrderActionListener = new DelOrderActionListener();
        delOrderButton.addActionListener(delOrderActionListener);

        //Назначили кнопке просмотра шоу-программ слушателя
        JButton seeShowProgramButton = mainWindowView.getSeeShowProgramButton();
        SeeShowProgramActionListener seeShowProgramActionListener = new SeeShowProgramActionListener(mainWindowView);
        seeShowProgramButton.addActionListener(seeShowProgramActionListener);

        PeopleServiceImpl peopleService = new PeopleServiceImpl();
        //Назначили кнопке просмотра сотрудников слушателя
        JButton seeEmployeesButton = mainWindowView.getSeeEmployeesButton();
        SeeEmployeesActionListener seeEmployeesActionListener = new SeeEmployeesActionListener(mainWindowView, peopleService.findAllPeople());
        seeEmployeesButton.addActionListener(seeEmployeesActionListener);

        JButton seeSalaryButton = mainWindowView.getSeeSalaryButton();
        SeeSalaryActionListener seeSalaryActionListener = new SeeSalaryActionListener(mainWindowView);
        seeSalaryButton.addActionListener(seeSalaryActionListener);
    }

    private static class AddOrderActionListener implements ActionListener{
        private final MainWindowView mainWindowView;
        AddOrderActionListener(MainWindowView mainWindowView){
            this.mainWindowView = mainWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открыть окно добавления заказа
            OrderWindowController orderWindowController = new OrderWindowController();
            orderWindowController.execut(new OrderWindowView(mainWindowView));
            mainWindowView.dispose();
        }
    }

    private class DelOrderActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Удаление выбранной строки в таблице
                incomeExpensesTableModel.delete(incomeExpensesTable.getSelectedRow());
                mainWindowView.dispose();
                execut(new MainWindowView(),incomeService,expenseService);
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }

    private static class SeeShowProgramActionListener implements ActionListener{
        private final MainWindowView mainWindowView;
        SeeShowProgramActionListener(MainWindowView mainWindowView){
            this.mainWindowView = mainWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открыть окно просмотра шоу-программ
            ShowProgramWindowController showProgramWindowController = new ShowProgramWindowController();
            showProgramWindowController.execut(new ShowProgramWindowView(mainWindowView));
            mainWindowView.dispose();
        }
    }

    private static class SeeEmployeesActionListener implements ActionListener{
        private final MainWindowView mainWindowView;
        private final List<People> people;
        SeeEmployeesActionListener(MainWindowView mainWindowView,List<People> people ){
            this.mainWindowView = mainWindowView;
            this.people = people;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открыть окно просмотра сотрудников
            PeopleWindowController peopleWindowController = new PeopleWindowController(people);
            peopleWindowController.execut(new PeopleWindowView(mainWindowView,"Сотрудники"));
            mainWindowView.dispose();
        }
    }
    private static class SeeSalaryActionListener implements ActionListener{
        private final MainWindowView mainWindowView;
        SeeSalaryActionListener(MainWindowView mainWindowView){
            this.mainWindowView = mainWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открыть окно проспотра зарплат
            SalaryWindowController salaryWindowController = new SalaryWindowController();
            salaryWindowController.execut(new SalaryWindowView(mainWindowView));
            mainWindowView.dispose();
        }
    }
}
