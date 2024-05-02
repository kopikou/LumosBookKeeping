package org.example.Lumos.domain.dao;

import org.example.Lumos.domain.entity.ShowProgram;

import java.util.List;

public interface ShowProgramDao {
    ShowProgram findById(int id);
    void save(ShowProgram showProgram);
    void update(ShowProgram showProgram);
    void delete(ShowProgram showProgram);
    List<ShowProgram> findAll();
}
