package org.example.Lumos.gui.showProgramWindow;

import javax.swing.*;
import java.awt.*;

public class ShowProgramWindowView extends JFrame {
    private JFrame parent;
    private JPanel mainPanel,buttonPanel;
    private JTable showProgramTable;
    private JScrollPane jcrollPane;
    private JButton seeArtistsButton,seeTechniciansButton,seeTransferButton,addShowProgramButton,delShowProgramButton;
    public ShowProgramWindowView(JFrame parent){
        super("Шоу-программы");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;

        initComponents();
        placeComponents();

        pack();
        setSize(1300,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(){
        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        showProgramTable = new JTable();

        seeArtistsButton = new JButton("Посмотреть артистов");
        seeTechniciansButton = new JButton("Посмотреть техников");
        seeTransferButton = new JButton("Посмотреть трансфер");
        addShowProgramButton = new JButton("Добавить шоу-программу");
        delShowProgramButton = new JButton("Удалить шоу-программу");
    }

    private void placeComponents(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));

        jcrollPane = new JScrollPane(showProgramTable);

        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        addShowProgramButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(addShowProgramButton);
        delShowProgramButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(delShowProgramButton);
        seeArtistsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(seeArtistsButton);
        seeTechniciansButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(seeTechniciansButton);
        seeTransferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(seeTransferButton);

        mainPanel.add(jcrollPane);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
    public JTable getShowProgramTable(){return showProgramTable;}
    public JButton getSeeArtistsButton() {
        return seeArtistsButton;
    }
    public JButton getSeeTechniciansButton() {
        return seeTechniciansButton;
    }
    public JButton getSeeTransferButton() {
        return seeTransferButton;
    }
    public JButton getAddShowProgramButton() {return addShowProgramButton;}
    public JButton getDelShowProgramButton(){return delShowProgramButton;}
}
