package org.example.Lumos.dao;

import org.example.Lumos.entity.People;
import org.example.Lumos.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PeopleDaoImpl implements PeopleDao{
    @Override
    public People findById(int id) {
        return HibernateUtil.getSession().get(People.class, id);
    }

    @Override
    public void save(People people) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        //session.save(people);
        session.persist(people);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(People people) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(people);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(People people) {
        Session session = HibernateUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        //session.delete(findById(people.getId()));
        //session.delete(people);
        People person = (People) session.find(People.class, people.getId());
        session.remove(person);
        tx1.commit();
        session.close();
    }

    @Override
    public List<People> findAll() {
        List<People> people = (List<People>)  HibernateUtil.getSession().createQuery("From People").list();
        return people;
    }
}
