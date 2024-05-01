package org.example.Lumos.gui.OrderWindow;

import javax.swing.*;

public class OrderWindowView extends JFrame {
    public OrderWindowView(JFrame parent){
        super("Добавить заказ");

        pack();
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
