package org.example.Lumos.hibernate.dao;

import org.example.Lumos.domain.dao.ExpenseDao;
import org.example.Lumos.domain.entity.Expense;
import org.example.Lumos.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    @Override
    public Expense findById(int id) {
        return HibernateUtil.getSession().get(Expense.class, id);
    }

    @Override
    public void save(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(expense);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(expense);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        Expense expense1 = (Expense) session.find(Expense.class, expense.getId());
        session.remove(expense1);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Expense> findAll() {
        List<Expense> expenses = (List<Expense>)  HibernateUtil.getSession().createQuery("From Expense").list();
        return expenses;
    }
}
