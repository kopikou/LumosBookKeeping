package org.example.Lumos.gui.ShowProgramWindow;

import javax.swing.*;
import java.awt.*;

public class ShowProgramWindowView extends JFrame {
    private JFrame parent;
    private JPanel mainPanel,tablePanel,buttonPanel;
    private JComboBox showProgramComboBox;
    private JTable showProgramTable;
    private JLabel showProgramLabel;
    private JScrollPane jcrollPane;
    private JButton seeArtistsButton,seeTechniciansButton;
    public ShowProgramWindowView(JFrame parent){
        super("Шоу программы");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;

        initComponents();
        placeComponents();

        pack();
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(){
        mainPanel = new JPanel();
        tablePanel = new JPanel();
        buttonPanel = new JPanel();

        showProgramLabel = new JLabel("Шоу-программа");
        showProgramComboBox = new JComboBox<>();
        //showProgramComboBox.setPreferredSize(new Dimension(100, 50));

        showProgramTable = new JTable();

        seeArtistsButton = new JButton("Посмотреть артистов");
        seeTechniciansButton = new JButton("Посмотреть техников");
    }

    private void placeComponents(){
        jcrollPane = new JScrollPane(showProgramTable);

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));

        tablePanel.setLayout(new BoxLayout(tablePanel,BoxLayout.Y_AXIS));
        //tablePanel.add(showProgramLabel);
        //tablePanel.add(showProgramComboBox);
        tablePanel.add(jcrollPane);

        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(showProgramLabel);
        buttonPanel.add(showProgramComboBox);
        buttonPanel.add(seeArtistsButton);
        buttonPanel.add(seeTechniciansButton);

        mainPanel.add(tablePanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
    public JComboBox getShowProgramComboBox(){return showProgramComboBox;}
    public JTable getShowProgramTable(){return showProgramTable;}
}
