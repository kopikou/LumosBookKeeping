package org.example.Lumos.gui.newShowProgramWindow;

import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowController;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowView;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewShowProgramWindowController {
    private ShowProgramServiceImpl showProgramService;

    public void execut(NewShowProgramWindowView newShowProgramWindowView){
        JFrame parentFrame = newShowProgramWindowView.getParentFrame();

        newShowProgramWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                newShowProgramWindowView.dispose();
            }
        });

        showProgramService = new ShowProgramServiceImpl();

        //Назначили кнопке добавления шоу-программы слушателя
        JButton addButton = newShowProgramWindowView.getAddButton();
        AddButtonActionListener addButtonActionListener = new AddButtonActionListener(newShowProgramWindowView);
        addButton.addActionListener(addButtonActionListener);
    }

    private class AddButtonActionListener implements ActionListener {
        private final ShowProgramWindowView parentFrame;
        private final NewShowProgramWindowView newShowProgramWindowView;
        AddButtonActionListener(NewShowProgramWindowView newShowProgramWindowView){
            this.newShowProgramWindowView = newShowProgramWindowView;
            parentFrame = (ShowProgramWindowView) newShowProgramWindowView.getParentFrame();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField titleTextField = newShowProgramWindowView.getTitleTextField();
            //Если указано название шоу-программы
            if(!titleTextField.getText().isEmpty()) {
                //Создаем новую шоу-программу
                ShowProgram showProgram = new ShowProgram();
                showProgram.setTitle(titleTextField.getText());
                showProgram.setPrice((int)newShowProgramWindowView.getPriceSpinner().getValue());
                showProgram.setArtistsCnt((int)newShowProgramWindowView.getArtistsCntSpinner().getValue());
                showProgram.setArtistSalary((int)newShowProgramWindowView.getArtistSalarySpinner().getValue());
                showProgram.setTechniciansCnt((int)newShowProgramWindowView.getTechniciansCntSpinner().getValue());
                showProgram.setTechnicianSalary((int)newShowProgramWindowView.getTechnicianSalarySpinner().getValue());
                showProgram.setTransferCnt((int)newShowProgramWindowView.getTransferCntSpinner().getValue());
                showProgram.setTransferCost((int)newShowProgramWindowView.getTransferCostSpinner().getValue());

                showProgramService.saveShowProgram(showProgram);

                //Возвращаемся к окну просмотра шоу-программ
                ShowProgramWindowController showProgramWindowController = new ShowProgramWindowController();
                showProgramWindowController.execut(new ShowProgramWindowView(parentFrame.getParentFrame()));
                newShowProgramWindowView.dispose();
            }else{
                JOptionPane.showMessageDialog(newShowProgramWindowView,
                        "Ошибка записи. Пожалуйста, заполните все поля.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
