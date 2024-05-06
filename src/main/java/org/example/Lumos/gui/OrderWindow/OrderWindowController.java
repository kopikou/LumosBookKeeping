package org.example.Lumos.gui.OrderWindow;

import org.example.Lumos.domain.entity.People;
import org.example.Lumos.domain.entity.ShowProgram;
import org.example.Lumos.hibernate.services.ShowProgramServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class OrderWindowController {
    private List<JComboBox> artistsComboBoxes;
    private List<JComboBox> techniciansComboBoxes;
    private ShowProgramServiceImpl showProgramService = new ShowProgramServiceImpl();
    public void execut(OrderWindowView orderWindowView){
        JFrame parentFrame = orderWindowView.getParentFrame();
        orderWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                orderWindowView.dispose();
            }
        });

        List<ShowProgram> showPrograms = showProgramService.findAllShowPrograms();
        JComboBox showProgramComboBox = orderWindowView.getShowProgramComboBox();
        for (int i = 0; i < showPrograms.size(); i++)
            showProgramComboBox.addItem(showPrograms.get(i).getTitle());

        artistsComboBoxes = orderWindowView.getArtistsComboBoxes();
        techniciansComboBoxes = orderWindowView.getTechniciansComboBoxes();

        showProgramComboBox.addActionListener(new SelectedShowProgramActionListener(showPrograms,showProgramComboBox));

    }
    private class SelectedShowProgramActionListener implements ActionListener{
        private List<ShowProgram> showPrograms;
        private JComboBox showProgramComboBox;
        SelectedShowProgramActionListener(List<ShowProgram> showPrograms, JComboBox showProgramComboBox){
            this.showPrograms = showPrograms;
            this.showProgramComboBox = showProgramComboBox;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = 0;
            for (int i = 0; i < showPrograms.size(); i++){
                if(showPrograms.get(i).getTitle() == showProgramComboBox.getSelectedItem())
                    id = i;
            }

            int artictsCnt = showPrograms.get(id).getArtistsCnt();

            for (int i = 0; i < artistsComboBoxes.size(); i++){
                artistsComboBoxes.get(i).removeAllItems();
                artistsComboBoxes.get(i).setEnabled(false);
            }

            for (int i = 0; i < showPrograms.get(id).getArtists().size(); i++){
                for (int j = 0; j < artictsCnt; j++){
                    artistsComboBoxes.get(j).addItem(showPrograms.get(id).getArtists().get(i).getName());
                    artistsComboBoxes.get(j).setEnabled(true);
                }
            }

            int techniciansCnt = showPrograms.get(id).getTechniciansCnt();

            for (int i = 0; i < techniciansComboBoxes.size(); i++){
                techniciansComboBoxes.get(i).removeAllItems();
                techniciansComboBoxes.get(i).setEnabled(false);
            }

            for (int i = 0; i < showPrograms.get(id).getTechnicians().size(); i++){
                for (int j = 0; j < techniciansCnt; j++){
                    techniciansComboBoxes.get(j).addItem(showPrograms.get(id).getTechnicians().get(i).getName());
                    techniciansComboBoxes.get(j).setEnabled(true);
                }
            }
        }
    }
}
