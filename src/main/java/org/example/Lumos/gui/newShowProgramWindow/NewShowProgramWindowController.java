package org.example.Lumos.gui.newShowProgramWindow;

import org.example.Lumos.domain.entity.Expense;
import org.example.Lumos.domain.entity.Income;
import org.example.Lumos.domain.entity.People;
import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.gui.mainWindow.MainWindowController;
import org.example.Lumos.gui.mainWindow.MainWindowView;
import org.example.Lumos.gui.orderWindow.OrderWindowView;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowController;
import org.example.Lumos.gui.showProgramWindow.ShowProgramWindowView;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewShowProgramWindowController {
    private ShowProgramServiceImpl showProgramService;
    private JButton addButton;
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

        addButton = newShowProgramWindowView.getAddButton();
        AddButtonActionListener addButtonActionListener = new AddButtonActionListener(newShowProgramWindowView);
        addButton.addActionListener(addButtonActionListener);
    }

    private class AddButtonActionListener implements ActionListener {
        private ShowProgramWindowView parentFrame;
        private NewShowProgramWindowView newShowProgramWindowView;
        AddButtonActionListener(NewShowProgramWindowView newShowProgramWindowView){
            this.newShowProgramWindowView = newShowProgramWindowView;
            parentFrame = (ShowProgramWindowView) newShowProgramWindowView.getParentFrame();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField titleTextField = newShowProgramWindowView.getTitleTextField();
            if(!titleTextField.getText().isEmpty()) {
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
