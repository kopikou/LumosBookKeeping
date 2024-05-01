package org.example.Lumos.dao;

import org.example.Lumos.entity.Expense;
import org.example.Lumos.entity.Income;

import java.util.List;

public interface ExpenseDao {
    Expense findById(int id);
    void save(Expense expense);
    void update(Expense expense);
    void delete(Expense expense);
    List<Expense> findAll();
}
