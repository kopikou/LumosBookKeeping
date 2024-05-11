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
    private ShowProgramServiceImpl showProgramService = new ShowProgramServiceImpl();
    private JTable showProgramTable;
    private JButton seeArtistsButton,seeTechniciansButton,seeTransferButton,addShowProgramButton,delShowProgramButton;
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

        showProgramTable = showProgramWindowView.getShowProgramTable();
        showProgramTableModel = new ShowProgramTableModel(showProgramService);
        showProgramTable.setModel(showProgramTableModel);

        seeArtistsButton = showProgramWindowView.getSeeArtistsButton();
        SeeArtistsActionListener seeArtistsActionListener = new SeeArtistsActionListener(showProgramWindowView);
        seeArtistsButton.addActionListener(seeArtistsActionListener);

        seeTechniciansButton = showProgramWindowView.getSeeTechniciansButton();
        SeeTechniciansActionListener seeTechniciansActionListener = new SeeTechniciansActionListener(showProgramWindowView);
        seeTechniciansButton.addActionListener(seeTechniciansActionListener);

        seeTransferButton = showProgramWindowView.getSeeTransferButton();
        SeeTransferActionListener seeTransferActionListener = new SeeTransferActionListener(showProgramWindowView);
        seeTransferButton.addActionListener(seeTransferActionListener);

        addShowProgramButton = showProgramWindowView.getAddShowProgramButton();
        AddShowProgramActionListener addShowProgramActionListener = new AddShowProgramActionListener(showProgramWindowView);
        addShowProgramButton.addActionListener(addShowProgramActionListener);

        delShowProgramButton = showProgramWindowView.getDelShowProgramButton();
        DelShowProgramActionListener delShowProgramActionListener = new DelShowProgramActionListener();
        delShowProgramButton.addActionListener(delShowProgramActionListener);
    }
    private class SeeArtistsActionListener implements ActionListener {
        private ShowProgramWindowView showProgramWindowView;
        SeeArtistsActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getArtists());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Артисты"));
                showProgramWindowView.dispose();
            }catch (IndexOutOfBoundsException ex){
            }
        }
    }
    private class SeeTechniciansActionListener implements ActionListener {
        private ShowProgramWindowView showProgramWindowView;
        SeeTechniciansActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTechnicians());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Техники"));
                showProgramWindowView.dispose();
            }catch (IndexOutOfBoundsException ex){
            }
        }
    }
    private class SeeTransferActionListener implements ActionListener {
        private ShowProgramWindowView showProgramWindowView;
        SeeTransferActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ShowProgram showProgram = showProgramService.findShowProgram(showProgramService.findAllShowPrograms().get(showProgramTable.getSelectedRow()).getId());
                PeopleWindowController peopleWindowController = new PeopleWindowController(showProgram,showProgram.getTransfers());
                peopleWindowController.execut(new PeopleWindowView(showProgramWindowView, "Трансфер"));
                showProgramWindowView.dispose();
            }catch (IndexOutOfBoundsException ex){
            }
        }
    }
    private class AddShowProgramActionListener implements ActionListener{
        private ShowProgramWindowView showProgramWindowView;
        AddShowProgramActionListener(ShowProgramWindowView showProgramWindowView){
            this.showProgramWindowView = showProgramWindowView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            NewShowProgramWindowController newShowProgramWindowController = new NewShowProgramWindowController();
            newShowProgramWindowController.execut(new NewShowProgramWindowView(showProgramWindowView));
            showProgramWindowView.dispose();
        }
    }
    private class DelShowProgramActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                showProgramTableModel.delete(showProgramTable.getSelectedRow());
                showProgramWindowView.dispose();
                execut(new ShowProgramWindowView(parentFrame));
            }catch (IndexOutOfBoundsException ex){
            }
        }
    }
}
