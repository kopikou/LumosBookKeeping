package org.example.Lumos.dao;

import org.example.Lumos.entity.Expense;
import org.example.Lumos.entity.Income;
import org.example.Lumos.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao{
    @Override
    public Expense findById(int id) {
        return HibernateUtil.getSession().get(Expense.class, id);
    }

    @Override
    public void save(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(expense);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(expense);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Expense expense) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(expense);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Expense> findAll() {
        List<Expense> expenses = (List<Expense>)  HibernateUtil.getSession().createQuery("From Expense").list();
        return expenses;
    }
}
