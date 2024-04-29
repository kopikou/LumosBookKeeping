package org.example.Lumos.dao;

import org.example.Lumos.utils.HibernateUtil;
import org.example.Lumos.entity.ShowProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShowProgramDaoImpl implements ShowProgramDao{

    @Override
    public ShowProgram findById(int id) {
        return HibernateUtil.getSession().get(ShowProgram.class, id);
    }

    @Override
    public void save(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(showProgram);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(showProgram);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(showProgram);
        tx1.commit();
        session.close();
    }

    @Override
    public List<ShowProgram> findAll() {
        List<ShowProgram> showPrograms = (List<ShowProgram>)  HibernateUtil.getSession().createQuery("From ShowProgram").list();
        return showPrograms;
    }
}
