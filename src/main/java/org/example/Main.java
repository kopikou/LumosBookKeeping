package org.example;

import org.example.domain.entity.People;
import org.example.domain.entity.ShowProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Country country1 = new Country("Россия");
        Ministry ministry1 = new Ministry("Министерство иностранных дел",country1);
        Minister minister1 = new Minister("Лавров", "Сергей", ministry1);
        ministry1.getMinisters().add(minister1);

        Ministry ministry2 = new Ministry("Министерство культуры",country1);
        Minister minister2 = new Minister("Любимова", "Ольга", ministry2);
        ministry2.getMinisters().add(minister2);

        Ministry ministry3 = new Ministry("Министерство спорта",country1);
        Minister minister3 = new Minister("Матыцин", "Олег", ministry3);
        ministry3.getMinisters().add(minister3);

        country1.getMinistries().add(ministry1);
        country1.getMinistries().add(ministry2);
        country1.getMinistries().add(ministry3);

        Country country2 = new Country("Беларусь");
        Ministry ministry12 = new Ministry("Министерство образования",country2);
        Minister minister12 = new Minister("Иванец", "Андрей", ministry12);
        ministry12.getMinisters().add(minister12);

        Ministry ministry22 = new Ministry("Министерство финансов",country2);
        Minister minister22 = new Minister("Силиверстов", "Юрий", ministry22);
        ministry22.getMinisters().add(minister22);

        Ministry ministry32 = new Ministry("Министерство обороны",country2);
        Minister minister32 = new Minister("Хренин", "Виктор", ministry32);
        ministry32.getMinisters().add(minister32);

        country2.getMinistries().add(ministry12);
        country2.getMinistries().add(ministry22);
        country2.getMinistries().add(ministry32);

        session.save(country1);
        session.save(country2);

        transaction.commit();
        session.close();*/



        /*Session session = HibernateUtil.getSession();
        List<Country> countries = session.createQuery("from Country").list();
        for(Country country:countries){
            System.out.println(country);
        }
        session.close();*/
        /*Session session = HibernateUtil.getSession();
        List<Ministry> ministries = session.createQuery("from Ministry").list();
        for(Ministry ministry:ministries){
            System.out.println(ministry);
        }
        session.close();*/









        /*Session session = HibernateUtil.getSession();
        List<Minister> ministers = session.createQuery("from Minister").list();
        for(Minister minister:ministers){
            System.out.println(minister);
        }
        session.close();*/
        Session session = HibernateUtil.getSession();
        List<ShowProgram> artists = session.createQuery("from ShowProgram").list();
        for(ShowProgram artist:artists){
            System.out.println(artist);
        }
        session.close();












        /*Session session1= HibernateUtil.getSession();
        Transaction transaction = session1.beginTransaction();
        session1.createQuery("delete from Minister where id = 6").executeUpdate();
        transaction.commit();
        session1.close();*/


        /*Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Ministry ministry32 = new Ministry();
        List<Ministry> ministries = session.createQuery("from Ministry where title = 'Министерство обороны'").list();
        for(Ministry ministry:ministries){
            ministry32 = ministry;
        }
        Minister minister32 = new Minister("Хренин", "Виктор", ministry32);
        ministry32.getMinisters().add(minister32);
        session.save(minister32);

        transaction.commit();
        session.close();*/
    }
}