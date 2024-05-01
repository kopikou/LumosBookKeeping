package org.example.Lumos.services;

import org.example.Lumos.dao.IncomeDaoImpl;
import org.example.Lumos.dao.PeopleDaoImpl;
import org.example.Lumos.entity.Income;

import java.util.List;

public class IncomeService {
    private IncomeDaoImpl incomeDao = new IncomeDaoImpl();
    public IncomeService(){}
    public Income findIncome(int id) {
        return incomeDao.findById(id);
    }

    public void saveIncome(Income income) {
        incomeDao.save(income);
    }

    public void deleteIncome(Income income) {
        incomeDao.delete(income);
    }

    public void updateIncome(Income income) {
        incomeDao.update(income);
    }

    public List<Income> findAllIncome() {
        return incomeDao.findAll();
    }
}
