package org.example.Lumos.dao;

import org.example.Lumos.entity.Income;

import java.util.List;

public interface IncomeDao {
    Income findById(int id);
    void save(Income income);
    void update(Income income);
    void delete(Income income);
    List<Income> findAll();
}
