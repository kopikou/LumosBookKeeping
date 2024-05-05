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


        }
    }
}
