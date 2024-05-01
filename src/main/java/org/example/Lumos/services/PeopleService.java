package org.example.Lumos.services;

import org.example.Lumos.entity.People;

import java.util.List;

public interface PeopleService {
    People findPeople(int id);

    void savePeople(People people);

    void deletePeople(People people);

    void updatePeople(People people);

    List<People> findAllPeople();
}
