package org.example.Lumos.services;

import org.example.Lumos.entity.ShowProgram;

import java.util.List;

public interface ShowProgramService {
    ShowProgram findShowProgram(int id);

    void saveShowProgram(ShowProgram showProgram);

    void deleteShowProgram(ShowProgram showProgram);

    void updateShowProgram(ShowProgram showProgram);

    List<ShowProgram> findAllShowPrograms();
}
