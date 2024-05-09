package org.example;

import org.example.Lumos.gui.mainWindow.MainWindowController;
import org.example.Lumos.gui.mainWindow.MainWindowView;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;

public class Main {
    public static void main(String[] args) {
        IncomeServiceImpl incomeService = new IncomeServiceImpl();
        ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
        //MainWindowView mainWindowView = new MainWindowView();
        MainWindowController mainWindowController = new MainWindowController();
        mainWindowController.execut(new MainWindowView(),incomeService,expenseService);
        /*ShowProgramService showProgramService = new ShowProgramService();
        List<ShowProgram> showPrograms = showProgramService.findAllShowPrograms();
        for(ShowProgram showProgram:showPrograms){
            System.out.println(showProgram);
        }*/
        /*PeopleService peopleService = new PeopleService();
        List<People> peoples = peopleService.findAllPeople();
        for(People human:peoples){
            System.out.println(human);
        }*/



        /*IncomeService incomeService = new IncomeService();
        List<Income> incomes = incomeService.findAllIncome();
        for(Income income:incomes){
            System.out.println(income);
        }*/
        /*ExpenseService expenseService = new ExpenseService();
        List<Expense> expenses = expenseService.findAllExpense();
        for(Expense expense:expenses){
            System.out.println(expense);
        }*/


    }
}