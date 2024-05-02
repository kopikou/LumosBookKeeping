package org.example.Lumos.gui.OrderWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OrderWindowController {
    public void execut(OrderWindowView orderWindowView){
        JFrame parentFrame = orderWindowView.getParentFrame();
        orderWindowView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                orderWindowView.dispose();
            }
        });
    }
}
