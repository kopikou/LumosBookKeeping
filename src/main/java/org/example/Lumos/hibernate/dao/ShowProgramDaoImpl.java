package org.example.Lumos.hibernate.dao;

import org.example.Lumos.domain.dao.ShowProgramDao;
import org.example.Lumos.hibernate.utils.HibernateUtil;
import org.example.Lumos.domain.entity.ShowProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShowProgramDaoImpl implements ShowProgramDao {

    @Override
    public ShowProgram findById(int id) {
        return HibernateUtil.getSession().get(ShowProgram.class, id);
    }

    @Override
    public void save(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(showProgram);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(showProgram);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(ShowProgram showProgram) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        ShowProgram showProgram1 = (ShowProgram) session.find(ShowProgram.class, showProgram.getId());
        session.remove(showProgram1);
        tx1.commit();
        session.close();
    }

    @Override
    public List<ShowProgram> findAll() {
        List<ShowProgram> showPrograms = (List<ShowProgram>)  HibernateUtil.getSession().createQuery("From ShowProgram").list();
        return showPrograms;
    }
}
