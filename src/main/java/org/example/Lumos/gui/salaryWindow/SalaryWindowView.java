package org.example.Lumos.gui.salaryWindow;

import javax.swing.*;
import java.awt.*;

public class SalaryWindowView  extends JFrame {
    private final JFrame parent;
    private JPanel mainPanel, datePanel;
    private JComboBox monthComboBox,yearComboBox;
    private JTable salariesTable;
    public SalaryWindowView(JFrame parent){
        super("Расчёт зарплаты");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;

        initComponents();
        placeComponents();

        pack();
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initComponents(){
        mainPanel = new JPanel();
        datePanel = new JPanel();

        String[] months = new String[]{"январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
        monthComboBox = new JComboBox<>(months);

        String[] years = new String[101];
        int k = 0;
        int i = 2024;
        while (i < 2125){
            years[k] = Integer.toString(i);
            i++;
            k++;
        }
        yearComboBox = new JComboBox<>(years);

        salariesTable = new JTable();
    }
    private void placeComponents(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.X_AXIS));

        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);

        mainPanel.add(datePanel);

        JScrollPane jcrollPane = new JScrollPane(salariesTable);
        mainPanel.add(jcrollPane);

        this.add(mainPanel);
    }

    public JFrame getParentFrame(){
        return this.parent;
    }
    public JComboBox getMonthComboBox() {
        return monthComboBox;
    }
    public JComboBox getYearComboBox() {
        return yearComboBox;
    }
    public JTable getSalariesTable() {
        return salariesTable;
    }
}
