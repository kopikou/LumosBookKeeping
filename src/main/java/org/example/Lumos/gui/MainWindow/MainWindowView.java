package org.example.Lumos.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindowView extends JFrame {
    private JPanel mainPanel;
    private JTable incomeExpensesTable;
    private JScrollPane jcrollPane;
    private JButton addOrderButton;
    private JPanel buttonPanel;
    public MainWindowView(){
        super("Lumos Бухгалтерия");

        initComponents();
        placeComponents();

        pack();
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void initComponents(){
        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        incomeExpensesTable = new JTable();

        addOrderButton = new JButton("Добавить заказ");
    }
    private void placeComponents(){
        jcrollPane = new JScrollPane(incomeExpensesTable);

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        mainPanel.add(jcrollPane);

        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(addOrderButton);

        mainPanel.add(buttonPanel);
        //mainPanel.add(addOrderButton);

        this.add(mainPanel);
    }

    public JTable getIncomeExpensesTable(){
        return incomeExpensesTable;
    }
    public JButton getAddOrderButton(){
        return addOrderButton;
    }
}
