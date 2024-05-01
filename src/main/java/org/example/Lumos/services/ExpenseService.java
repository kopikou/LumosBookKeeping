package org.example.Lumos.services;

import org.example.Lumos.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense findExpense(int id);

    void saveExpense(Expense expense);

    void deleteExpense(Expense expense);

    void updateExpense(Expense expense);

    List<Expense> findAllExpense();
}
