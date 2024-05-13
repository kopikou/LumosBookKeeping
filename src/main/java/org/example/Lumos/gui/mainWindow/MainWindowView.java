package org.example.Lumos.gui.mainWindow;

import javax.swing.*;
import java.awt.*;

public class MainWindowView extends JFrame {
    private JPanel mainPanel;
    private JTable incomeExpensesTable;
    private JButton addOrderButton, delOrderButton, seeShowProgramButton,seeEmployeesButton;
    private JPanel buttonPanel;
    public MainWindowView(){
        super("Lumos Бухгалтерия");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

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

        delOrderButton = new JButton("Удалить заказ");

        seeShowProgramButton = new JButton("Шоу-программы");

        seeEmployeesButton = new JButton("Сотрудники");
    }
    private void placeComponents(){
        JScrollPane jcrollPane = new JScrollPane(incomeExpensesTable);

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        mainPanel.add(jcrollPane);

        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));

        addOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        delOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeShowProgramButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        seeEmployeesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(addOrderButton);
        buttonPanel.add(delOrderButton);
        buttonPanel.add(seeShowProgramButton);
        buttonPanel.add(seeEmployeesButton);

        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }

    public JTable getIncomeExpensesTable(){
        return incomeExpensesTable;
    }
    public JButton getAddOrderButton(){
        return addOrderButton;
    }
    public JButton getDelOrderButton(){return delOrderButton;}
    public JButton getSeeShowProgramButton(){return seeShowProgramButton;}
    public JButton getSeeEmployeesButton(){return seeEmployeesButton;}
}
