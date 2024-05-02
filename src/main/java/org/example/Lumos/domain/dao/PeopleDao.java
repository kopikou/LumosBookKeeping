package org.example.Lumos.domain.dao;

import org.example.Lumos.domain.entity.People;

import java.util.List;

public interface PeopleDao {
    People findById(int id);
    void save(People people);
    void update(People people);
    void delete(People people);
    List<People> findAll();
}
