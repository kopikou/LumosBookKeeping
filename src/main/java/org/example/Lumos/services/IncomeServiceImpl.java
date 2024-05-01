package org.example.Lumos.services;

import org.example.Lumos.dao.IncomeDaoImpl;
import org.example.Lumos.entity.Income;

import java.util.List;

public class IncomeServiceImpl implements IncomeService{
    private IncomeDaoImpl incomeDao = new IncomeDaoImpl();
    public IncomeServiceImpl(){}
    @Override
    public Income findIncome(int id) {
        return incomeDao.findById(id);
    }

    @Override
    public void saveIncome(Income income) {
        incomeDao.save(income);
    }

    @Override
    public void deleteIncome(Income income) {
        incomeDao.delete(income);
    }

    @Override
    public void updateIncome(Income income) {
        incomeDao.update(income);
    }

    @Override
    public List<Income> findAllIncome() {
        return incomeDao.findAll();
    }
}
