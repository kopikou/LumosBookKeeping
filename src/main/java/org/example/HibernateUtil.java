package org.example;

import org.example.domain.entity.People;
import org.example.domain.entity.ShowProgram;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        File cfgFile = new File("hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(cfgFile)
                .addAnnotatedClass(People.class).addAnnotatedClass(ShowProgram.class).buildSessionFactory();
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
