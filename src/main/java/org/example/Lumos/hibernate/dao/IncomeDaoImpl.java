package org.example.Lumos.hibernate.dao;

import org.example.Lumos.domain.dao.IncomeDao;
import org.example.Lumos.domain.entity.Income;
import org.example.Lumos.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IncomeDaoImpl implements IncomeDao {
    @Override
    public Income findById(int id) {
        return HibernateUtil.getSession().get(Income.class, id);
    }

    @Override
    public void save(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(income);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(income);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Income income) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        Income income1 = (Income) session.find(Income.class, income.getId());
        session.remove(income1);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Income> findAll() {
        List<Income> incomes = (List<Income>)  HibernateUtil.getSession().createQuery("From Income").list();
        return incomes;
    }
}
