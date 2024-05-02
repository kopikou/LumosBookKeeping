package org.example.Lumos.hibernate.services;

import org.example.Lumos.domain.services.ShowProgramService;
import org.example.Lumos.hibernate.dao.ShowProgramDaoImpl;
import org.example.Lumos.domain.entity.ShowProgram;

import java.util.List;

public class ShowProgramServiceImpl implements ShowProgramService {
    private ShowProgramDaoImpl showProgramDao = new ShowProgramDaoImpl();
    public ShowProgramServiceImpl(){}
    @Override
    public ShowProgram findShowProgram(int id) {
        return showProgramDao.findById(id);
    }

    @Override
    public void saveShowProgram(ShowProgram showProgram) {
        showProgramDao.save(showProgram);
    }

    @Override
    public void deleteShowProgram(ShowProgram showProgram) {
        showProgramDao.delete(showProgram);
    }

    @Override
    public void updateShowProgram(ShowProgram showProgram) {
        showProgramDao.update(showProgram);
    }

    @Override
    public List<ShowProgram> findAllShowPrograms() {
        return showProgramDao.findAll();
    }
}
