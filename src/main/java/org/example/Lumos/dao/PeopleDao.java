package org.example.Lumos.dao;

import org.example.Lumos.entity.People;

import java.util.List;

public interface PeopleDao {
    People findById(int id);
    void save(People people);
    void update(People people);
    void delete(People people);
    List<People> findAll();
}
