package org.example.Lumos.gui.ShowProgramWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowProgramWindowController {
    public void execut(ShowProgramWindowView showProgramWindowView){
        JFrame parentFrame = showProgramWindowView.getParentFrame();
        showProgramWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                showProgramWindowView.dispose();
            }
        });
    }
}
