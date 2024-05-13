package org.example.Lumos.gui.showProgramWindow;

import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ShowProgramTableModel extends AbstractTableModel {
    private final ShowProgramServiceImpl showProgramService;
    private final List<ShowProgram> showPrograms;
    public ShowProgramTableModel(ShowProgramServiceImpl showProgramService){
        this.showProgramService = showProgramService;
        showPrograms = showProgramService.findAllShowPrograms();
    }
    @Override
    public int getRowCount() {
        return showProgramService.findAllShowPrograms().size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Название";
            case 1: return "Цена";
            case 2: return "Количество артистов";
            case 3: return "Количество техников";
            case 4: return "Количество трансфера";
            case 5: return "Зарплата артиста";
            case 6: return "Зарплата техника";
            case 7: return "Стоимость трансфера";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return showPrograms.get(rowIndex).getTitle();
            case 1: return showPrograms.get(rowIndex).getPrice();
            case 2: return showPrograms.get(rowIndex).getArtistsCnt();
            case 3: return showPrograms.get(rowIndex).getTechniciansCnt();
            case 4: return showPrograms.get(rowIndex).getTransferCnt();
            case 5: return showPrograms.get(rowIndex).getArtistSalary();
            case 6: return showPrograms.get(rowIndex).getTechnicianSalary();
            case 7: return showPrograms.get(rowIndex).getTransferCost();
        }
        return null;
    }

    public void delete(int index){
        ShowProgram showProgram = showProgramService.findShowProgram(showPrograms.get(index).getId());
        showProgramService.deleteShowProgram(showProgram);
        fireTableDataChanged();
    }
}
