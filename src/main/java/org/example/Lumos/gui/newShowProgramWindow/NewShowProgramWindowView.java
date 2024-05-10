package org.example.Lumos.gui.newShowProgramWindow;

import javax.swing.*;
import java.awt.*;

public class NewShowProgramWindowView extends JFrame {
    private JPanel mainPanel,titlePanel, pricePanel, artistsPanel,technicicansPanel,transferPanel;
    private JFrame parent;
    private JLabel titleLabel, priceLabel, artistsCntLabel,technicicansCntLabel,transferCntLabel,artistSalaryLabel,technicianSalaryLabel,transferCostLabel;
    private JTextField titleTextField;
    private JSpinner priceSpinner,artistsCntSpinner,techniciansCntSpinner,transferCntSpinner,artistSalarySpinner,technicianSalarySpinner,transferCostSpinner;
    private JButton addButton;
    public NewShowProgramWindowView(JFrame parent){
        super("Добавить шоу-программу");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        this.parent = parent;

        initComponents();
        placeComponents();

        pack();
        setSize(1000,150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(){
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        pricePanel = new JPanel();
        artistsPanel = new JPanel();
        technicicansPanel = new JPanel();
        transferPanel = new JPanel();

        titleLabel = new JLabel("Название");
        titleTextField = new JTextField(25);

        priceLabel = new JLabel("Цена");
        SpinnerModel price = new SpinnerNumberModel(10000, 10000, 100000, 1000);
        priceSpinner = new JSpinner(price);

        artistsCntLabel = new JLabel("Количество артистов");
        SpinnerModel artistCnt = new SpinnerNumberModel(2,2,8,1);
        artistsCntSpinner = new JSpinner(artistCnt);

        artistSalaryLabel = new JLabel("Зарплата артиста");
            SpinnerModel artistSalary = new SpinnerNumberModel(1250,1250,2750,50);
        artistSalarySpinner = new JSpinner(artistSalary);

        technicicansCntLabel = new JLabel("Количество техников");
        SpinnerModel techniciansCnt = new SpinnerNumberModel(1,0,2,1);
        techniciansCntSpinner = new JSpinner(techniciansCnt);

        technicianSalaryLabel = new JLabel("Зарплата техника");
        SpinnerModel technicianSalary = new SpinnerNumberModel(750,750,2750,50);
        technicianSalarySpinner = new JSpinner(technicianSalary);

        transferCntLabel = new JLabel("Количество трансфера");
        SpinnerModel transferCnt = new SpinnerNumberModel(1,1,3,1);
        transferCntSpinner = new JSpinner(transferCnt);

        transferCostLabel = new JLabel("Стоимость трансфера");
        SpinnerModel transferCost = new SpinnerNumberModel(750,750,1000,50);
        transferCostSpinner = new JSpinner(transferCost);

        addButton = new JButton("Добавить");
    }

    private void placeComponents(){
        //mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
        titlePanel.add(titleLabel);
        titlePanel.add(titleTextField);

        pricePanel.setLayout(new BoxLayout(pricePanel,BoxLayout.Y_AXIS));
        pricePanel.add(priceLabel);
        pricePanel.add(priceSpinner);

        artistsPanel.setLayout(new BoxLayout(artistsPanel,BoxLayout.Y_AXIS));
        artistsPanel.add(artistsCntLabel);
        artistsPanel.add(artistsCntSpinner);
        artistsPanel.add(artistSalaryLabel);
        artistsPanel.add(artistSalarySpinner);

        technicicansPanel.setLayout(new BoxLayout(technicicansPanel,BoxLayout.Y_AXIS));
        technicicansPanel.add(technicicansCntLabel);
        technicicansPanel.add(techniciansCntSpinner);
        technicicansPanel.add(technicianSalaryLabel);
        technicicansPanel.add(technicianSalarySpinner);

        transferPanel.setLayout(new BoxLayout(transferPanel,BoxLayout.Y_AXIS));
        transferPanel.add(transferCntLabel);
        transferPanel.add(transferCntSpinner);
        transferPanel.add(transferCostLabel);
        transferPanel.add(transferCostSpinner);

        mainPanel.add(titlePanel);
        mainPanel.add(pricePanel);
        mainPanel.add(artistsPanel);
        mainPanel.add(technicicansPanel);
        mainPanel.add(transferPanel);

        mainPanel.add(addButton);

        this.add(mainPanel);
    }
    public JFrame getParentFrame(){
        return this.parent;
    }
    public JButton getAddButton(){return addButton;}
    public JTextField getTitleTextField(){return titleTextField;}
    public JSpinner getPriceSpinner(){return priceSpinner;}
    public JSpinner getArtistsCntSpinner(){return artistsCntSpinner;}
    public JSpinner getTechniciansCntSpinner(){return techniciansCntSpinner;}
    public JSpinner getTransferCntSpinner(){return transferCntSpinner;}
    public JSpinner getArtistSalarySpinner(){return artistSalarySpinner;}
    public JSpinner getTechnicianSalarySpinner(){return technicianSalarySpinner;}
    public JSpinner getTransferCostSpinner(){return transferCostSpinner;}
}
