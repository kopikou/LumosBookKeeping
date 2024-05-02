package org.example.Lumos.domain.services;

import org.example.Lumos.domain.entity.Income;

import java.util.List;

public interface IncomeService {
    Income findIncome(int id);

    void saveIncome(Income income);

    void deleteIncome(Income income);

    void updateIncome(Income income);

    List<Income> findAllIncome();
}
