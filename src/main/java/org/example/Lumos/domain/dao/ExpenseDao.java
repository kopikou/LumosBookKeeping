package org.example.Lumos.domain.dao;

import org.example.Lumos.domain.entity.Expense;

import java.util.List;

public interface ExpenseDao {
    Expense findById(int id);
    void save(Expense expense);
    void update(Expense expense);
    void delete(Expense expense);
    List<Expense> findAll();
}
