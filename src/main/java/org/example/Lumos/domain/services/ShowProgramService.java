package org.example.Lumos.domain.services;

import org.example.Lumos.domain.entity.ShowProgram;

import java.util.List;

public interface ShowProgramService {
    ShowProgram findShowProgram(int id);

    void saveShowProgram(ShowProgram showProgram);

    void deleteShowProgram(ShowProgram showProgram);

    void updateShowProgram(ShowProgram showProgram);

    List<ShowProgram> findAllShowPrograms();
}
