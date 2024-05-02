package org.example.Lumos.gui.OrderWindow;

import javax.swing.*;

public class OrderWindowView extends JFrame {
    private JFrame parent;
    public OrderWindowView(JFrame parent){
        super("Добавить заказ");

        this.parent = parent;

        pack();
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
}
