package org.example.domain.dao;

import org.example.domain.entity.ShowProgram;

import java.util.List;

public interface ShowProgramDao {
    ShowProgram findById(int id);
    void save(ShowProgram showProgram);
    void update(ShowProgram showProgram);
    void delete(ShowProgram showProgram);
    List<ShowProgram> findAll();
}
