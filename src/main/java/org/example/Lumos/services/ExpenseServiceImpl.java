package org.example.Lumos.services;

import org.example.Lumos.dao.ExpenseDaoImpl;
import org.example.Lumos.entity.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{
    private ExpenseDaoImpl expenseDao = new ExpenseDaoImpl();
    public ExpenseServiceImpl(){}
    @Override
    public Expense findExpense(int id) {
        return expenseDao.findById(id);
    }

    @Override
    public void saveExpense(Expense expense) {
        expenseDao.save(expense);
    }

    @Override
    public void deleteExpense(Expense expense) {
        expenseDao.delete(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        expenseDao.update(expense);
    }

    @Override
    public List<Expense> findAllExpense() {
        return expenseDao.findAll();
    }
}
