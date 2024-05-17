package org.example.Lumos.gui.showProgramWindow;

import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.gui.newShowProgramWindow.NewShowProgramWindowController;
import org.example.Lumos.gui.newShowProgramWindow.NewShowProgramWindowView;
import org.example.Lumos.gui.peopleWindow.PeopleWindowController;
import org.example.Lumos.gui.peopleWindow.PeopleWindowView;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowProgramWindowController {
    private final ShowProgramServiceImpl showProgramService = new ShowProgramServiceImpl();
    private JTable showProgramTable;
    private ShowProgramTableModel showProgramTableModel;
    private ShowProgramWindowView showProgramWindowView;
    private JFrame parentFrame;
    public void execut(ShowProgramWindowView showProgramWindowView){
        this.showProgramWindowView = showProgramWindowView;

        parentFrame = showProgramWindowView.getParentFrame();
        showProgramWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                showProgramWindowView.dispose();
            }
        });

        //Устанавливаем модель для таблицы
        showProgramTable = showProgramWindowView.getShowProgramTable();
        showProgramTableModel = new ShowProgramTableModel(showProgramService);
        showProgramTable.setModel(showProgramTableModel);

        JButton seeArtistsButton = showProgramWindowView.getSeeArtistsButton();
        SeeArtistsActionListener seeArtistsActionListener = new SeeArtistsActionListener(showProgramWindowView);
        seeArtistsButton.addActionListener(seeArtistsActionListener);

        JButton seeTechniciansButton = showProgramWindowView.getSeeTechniciansButton();
        SeeTechniciansActionListener seeTechniciansActionListener = new SeeTechniciansActionListener(showProgramWindowView);
        seeTechniciansButton.addActionListener(seeTechniciansActionListener);

        JButton seeTransferButton = showProgramWindowView.getSeeTransferButton();
        SeeTransferActionListener seeTransferActionListener = new SeeTransferActionListener(showProgramWindowView);
        seeTransferButton.addActionListener(seeTransferActionListener);

        JButton addShowProgramButton = showProgramWindowView.getAddShowProgramButton();
        AddShowProgramActionListener addShowProgramActionListener = new AddShowProgramActionListener(showProgramWindowView);
        addShowProgramButton.addActionListener(addShowProgramActionListener);

        JButton delShowProgramButton = showProgramWindowView.getDelShowProgramButton();
        DelShowProgramActionListener delShowProgramActionListener = new DelShowProgramActionListener();
        delShowProgramButton.addActionListener(delShowProgramActionListener);
    }
    private class SeeArtistsActionListener implements ActionListener {
        private final ShowProgramWindowView showProgramWindowView;
        SeeArtistsActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Открыть окно просмотра артистов
                showProgramWindowView.dispose();
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Артисты"));
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }
    private class SeeTechniciansActionListener implements ActionListener {
        private final ShowProgramWindowView showProgramWindowView;
        SeeTechniciansActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Открыть окно просмотра техников
                showProgramWindowView.dispose();
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Техники"));
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }
    private class SeeTransferActionListener implements ActionListener {
        private final ShowProgramWindowView showProgramWindowView;
        SeeTransferActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Открыть окно просмотра трансфера
                showProgramWindowView.dispose();
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Трансфер"));
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }
    private static class AddShowProgramActionListener implements ActionListener{
        private final ShowProgramWindowView showProgramWindowView;
        AddShowProgramActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открыть окно добавления шоу-программы
            NewShowProgramWindowController newShowProgramWindowController = new NewShowProgramWindowController();
            newShowProgramWindowController.execut(new NewShowProgramWindowView(showProgramWindowView));
            showProgramWindowView.dispose();
        }
    }
    private class DelShowProgramActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Удалить выбранную строку из таблицы с шоу-программами
                showProgramTableModel.delete(showProgramTable.getSelectedRow());
                showProgramWindowView.dispose();
                execut(new ShowProgramWindowView(parentFrame));
            }catch (IndexOutOfBoundsException ignored){
            }
        }
    }
}
