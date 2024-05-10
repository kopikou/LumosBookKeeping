package org.example.Lumos.gui.mainWindow;

import org.example.Lumos.domain.entity.Expense;
import org.example.Lumos.domain.entity.Income;
import org.example.Lumos.hibernate.services.ExpenseServiceImpl;
import org.example.Lumos.hibernate.services.IncomeServiceImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class IncomeExpensesTableModel extends AbstractTableModel {
    private List<Income> incomes;
    private List<Expense> expenses;
    private IncomeServiceImpl incomeService;
    private ExpenseServiceImpl expenseService;
    public IncomeExpensesTableModel(IncomeServiceImpl incomeService, ExpenseServiceImpl expenseService){
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        incomes = incomeService.findAllIncome();
        expenses = expenseService.findAllExpense();
    }
    @Override
    public int getRowCount() {
        return expenses.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Дата";
            case 1: return "Сумма";
            case 2: return "Шоу-программа";
            case 3: return "Место";
            case 4: return "Кому";
            case 5: return "Зарплата";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: {
                if(rowIndex == 0 || expenses.get(rowIndex).getIncome() != expenses.get(rowIndex-1).getIncome())//rowIndex == expenses.get(rowIndex).getIncome().getExpenses().size())
                    return expenses.get(rowIndex).getIncome().getDate();
                else return null;
            }
            case 1: {
                if(rowIndex == 0 || expenses.get(rowIndex).getIncome() != expenses.get(rowIndex-1).getIncome())
                    return expenses.get(rowIndex).getIncome().getShowProgram().getPrice();
                else return null;
            }
            case 2: {
                if(rowIndex == 0 || expenses.get(rowIndex).getIncome() != expenses.get(rowIndex-1).getIncome())//rowIndex == expenses.get(rowIndex).getIncome().getExpenses().size())
                    return expenses.get(rowIndex).getIncome().getShowProgram().getTitle();
                else return null;
            }
            case 3: {
                if(rowIndex == 0 || expenses.get(rowIndex).getIncome() != expenses.get(rowIndex-1).getIncome())//rowIndex == expenses.get(rowIndex).getIncome().getExpenses().size())
                    return expenses.get(rowIndex).getIncome().getPlace();
                else return null;
            }
            case 4: return expenses.get(rowIndex).getPerson().getName();
            case 5: return expenses.get(rowIndex).getSalary();
        }
        return null;
    }

    public void delete(int index){
        Income income = incomeService.findIncome(expenses.get(index).getIncome().getId());
        for (int i = 0; i < expenses.size(); i++){
            if (expenses.get(i).getIncome() == income){
                expenseService.deleteExpense(expenses.get(i));
            }
        }
        incomeService.deleteIncome(income);
        fireTableDataChanged();
    }
}
