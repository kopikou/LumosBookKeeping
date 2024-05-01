package org.example.Lumos.dao;

import org.example.Lumos.entity.Income;
import org.example.Lumos.entity.ShowProgram;
import org.example.Lumos.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IncomeDaoImpl implements IncomeDao{
    @Override
    public Income findById(int id) {
        return HibernateUtil.getSession().get(Income.class, id);
    }

    @Override
    public void save(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(income);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(income);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(income);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Income> findAll() {
        List<Income> incomes = (List<Income>)  HibernateUtil.getSession().createQuery("From Income").list();
        return incomes;
    }
}
