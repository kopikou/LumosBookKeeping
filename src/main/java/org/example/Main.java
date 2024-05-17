package org.example;

import org.example.Lumos.domain.entity.People;
import org.example.Lumos.gui.mainWindow.MainWindowController;
import org.example.Lumos.gui.mainWindow.MainWindowView;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;
import org.example.Lumos.hibernate.services.PeopleServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IncomeServiceImpl incomeService = new IncomeServiceImpl();
        ExpenseServiceImpl expenseService = new ExpenseServiceImpl();

        MainWindowController mainWindowController = new MainWindowController();
        mainWindowController.execut(new MainWindowView(),incomeService,expenseService);

        /*PeopleServiceImpl peopleService = new PeopleServiceImpl();
        List<People> list = peopleService.findAllPeople();
        for (int i = 0; i < list.size();i++){
            System.out.println(list.get(i));
        }*/
        //System.out.println(peopleService.findAllPeople());
        /*String row = "2024-05-19";
        String[] ar = row.split("-");
        System.out.println(Integer.parseInt(ar[1]));*/
        Date date = new Date();
        //System.out.println(date.getTime());//Timestamp
        //long timestamp = date.getTime();
        //date = new Date(timestamp);
        String month = new SimpleDateFormat("MMMM").format(date);//Get month string
        System.out.println(month);
    }
}