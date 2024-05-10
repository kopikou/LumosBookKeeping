package org.example.Lumos.hibernate.services;

import org.example.Lumos.domain.services.PeopleService;
import org.example.Lumos.hibernate.dao.PeopleDaoImpl;
import org.example.Lumos.domain.entity.People;

import java.util.List;

public class PeopleServiceImpl implements PeopleService {
    private PeopleDaoImpl peopleDao = new PeopleDaoImpl();
    public PeopleServiceImpl(){}
    @Override
    public People findPeople(int id) {
        return peopleDao.findById(id);
    }

    @Override
    public void savePeople(People people) {
        peopleDao.save(people);
    }

    @Override
    public void deletePeople(People people) {
        peopleDao.delete(people);
    }

    @Override
    public void updatePeople(People people) {
        peopleDao.update(people);
    }

    @Override
    public List<People> findAllPeople() {
        return peopleDao.findAll();
    }

}
