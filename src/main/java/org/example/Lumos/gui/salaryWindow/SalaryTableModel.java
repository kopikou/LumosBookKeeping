package org.example.Lumos.gui.salaryWindow;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalaryTableModel extends AbstractTableModel {
    private final Map<String,Double> salaryDictionary;
    public SalaryTableModel(Map<String,Double> salaryDictionary){
        this.salaryDictionary = salaryDictionary;
    }
    @Override
    public int getRowCount() {
        return salaryDictionary.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0: return "Сотрудник";
            case 1: return "Зарплата";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<HashMap.Entry> entries = new ArrayList<>(salaryDictionary.entrySet());
        switch (columnIndex){
            case 0: return entries.get(rowIndex).getKey();
            case 1: return entries.get(rowIndex).getValue();

        }
        return null;
    }
    public void dataChange(){
        fireTableDataChanged();
    }
}
