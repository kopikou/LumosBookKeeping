package org.example.Lumos.services;

import org.example.Lumos.dao.ExpenseDaoImpl;
import org.example.Lumos.entity.Expense;

import java.util.List;

public class ExpenseService {
    private ExpenseDaoImpl expenseDao = new ExpenseDaoImpl();
    public ExpenseService(){}
    public Expense findExpense(int id) {
        return expenseDao.findById(id);
    }

    public void saveExpense(Expense expense) {
        expenseDao.save(expense);
    }

    public void deleteExpense(Expense expense) {
        expenseDao.delete(expense);
    }

    public void updateExpense(Expense expense) {
        expenseDao.update(expense);
    }

    public List<Expense> findAllExpense() {
        return expenseDao.findAll();
    }
}
