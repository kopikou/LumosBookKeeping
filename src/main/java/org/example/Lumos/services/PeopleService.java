package org.example.Lumos.services;

import org.example.Lumos.dao.PeopleDaoImpl;
import org.example.Lumos.dao.ShowProgramDaoImpl;
import org.example.Lumos.entity.People;
import org.example.Lumos.entity.ShowProgram;

import java.util.List;

public class PeopleService {
    private PeopleDaoImpl peopleDao = new PeopleDaoImpl();
    public PeopleService(){}
    public People findPeople(int id) {
        return peopleDao.findById(id);
    }

    public void savePeople(People people) {
        peopleDao.save(people);
    }

    public void deletePeople(People people) {
        peopleDao.delete(people);
    }

    public void updatePeople(People people) {
        peopleDao.update(people);
    }

    public List<People> findAllPeople() {
        return peopleDao.findAll();
    }
}
