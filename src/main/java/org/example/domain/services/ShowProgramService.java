package org.example.domain.services;

import org.example.domain.dao.ShowProgramDaoImpl;
import org.example.domain.entity.ShowProgram;

import java.util.List;

public class ShowProgramService {
    private ShowProgramDaoImpl showProgramDao = new ShowProgramDaoImpl();
    public ShowProgramService(){}
    public ShowProgram findShowProgram(int id) {
        return showProgramDao.findById(id);
    }

    public void saveShowProgram(ShowProgram showProgram) {
        showProgramDao.save(showProgram);
    }

    public void deleteShowProgram(ShowProgram showProgram) {
        showProgramDao.delete(showProgram);
    }

    public void updateShowProgram(ShowProgram showProgram) {
        showProgramDao.update(showProgram);
    }

    public List<ShowProgram> findAllShowPrograms() {
        return showProgramDao.findAll();
    }
}
