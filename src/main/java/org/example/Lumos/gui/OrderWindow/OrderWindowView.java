package org.example.Lumos.gui.OrderWindow;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderWindowView extends JFrame {
    private JFrame parent;
    private JPanel mainPanel;
    private JPanel artistsPanel;
    private JPanel showPanel;
    private JPanel datePanel;
    private JPanel placePanel;
    private JPanel techniciansPanel;
    private JPanel transferPanel;
    private JComboBox showProgramComboBox;
    private JComboBox artistsComboBox1, artistsComboBox2, artistsComboBox3, artistsComboBox4, artistsComboBox5, artistsComboBox6, artistsComboBox7, artistsComboBox8;
    private List<JComboBox> artistsComboBoxes;
    private JComboBox techniciansComboBox1, techniciansComboBox2;
    private List<JComboBox> techniciansComboBoxes;
    private JComboBox transferComboBox1,transferComboBox2;
    private List<JComboBox> transferComboBoxes;
    private JLabel showProgramLabel;
    private JLabel dateLabel;
    private JLabel placeLabel;
    private JLabel artitsLabel;
    private JLabel techniciansLabel;
    private JLabel transferLabel;
    private JXDatePicker picker;
    private JTextField placeTextField;
    public OrderWindowView(JFrame parent){
        super("Добавить заказ");

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
        showPanel = new JPanel();
        datePanel = new JPanel();
        placePanel = new JPanel();
        artistsPanel = new JPanel();
        techniciansPanel = new JPanel();
        transferPanel = new JPanel();

        showProgramLabel = new JLabel("Шоу-программа");
        showProgramComboBox = new JComboBox<>();

        dateLabel = new JLabel("Дата");
        picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        placeLabel = new JLabel("Место");
        placeTextField = new JTextField(25);

        artistsComboBox1 = new JComboBox<>();
        artistsComboBox2 = new JComboBox<>();
        artistsComboBox3 = new JComboBox<>();
        artistsComboBox4 = new JComboBox<>();
        artistsComboBox5 = new JComboBox<>();
        artistsComboBox6 = new JComboBox<>();
        artistsComboBox7 = new JComboBox<>();
        artistsComboBox8 = new JComboBox<>();

        artitsLabel = new JLabel("Артисты");
        artistsComboBoxes = new ArrayList<>();
        artistsComboBoxes.add(artistsComboBox1);
        artistsComboBoxes.add(artistsComboBox2);
        artistsComboBoxes.add(artistsComboBox3);
        artistsComboBoxes.add(artistsComboBox4);
        artistsComboBoxes.add(artistsComboBox5);
        artistsComboBoxes.add(artistsComboBox6);
        artistsComboBoxes.add(artistsComboBox7);
        artistsComboBoxes.add(artistsComboBox8);

        techniciansComboBox1 = new JComboBox<>();
        techniciansComboBox2 = new JComboBox<>();

        techniciansLabel = new JLabel("Техники");
        techniciansComboBoxes = new ArrayList<>();
        techniciansComboBoxes.add(techniciansComboBox1);
        techniciansComboBoxes.add(techniciansComboBox2);

        transferComboBox1 = new JComboBox<>();
        transferComboBox2 = new JComboBox<>();

        transferLabel = new JLabel("Трансфер");
        transferComboBoxes = new ArrayList<>();
        transferComboBoxes.add(transferComboBox1);
        transferComboBoxes.add(transferComboBox2);
    }
    private void placeComponents(){
        //mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        showPanel.setLayout(new BoxLayout(showPanel,BoxLayout.Y_AXIS));
        datePanel.setLayout(new BoxLayout(datePanel,BoxLayout.Y_AXIS));
        placePanel.setLayout(new BoxLayout(placePanel,BoxLayout.Y_AXIS));
        artistsPanel.setLayout(new BoxLayout(artistsPanel,BoxLayout.Y_AXIS));
        techniciansPanel.setLayout(new BoxLayout(techniciansPanel,BoxLayout.Y_AXIS));
        transferPanel.setLayout(new BoxLayout(transferPanel,BoxLayout.Y_AXIS));

        showPanel.add(showProgramLabel);
        showPanel.add(showProgramComboBox);

        datePanel.add(dateLabel);
        datePanel.add(picker);

        placePanel.add(placeLabel);
        placePanel.add(placeTextField);

        artistsPanel.add(artitsLabel);
        artistsPanel.add(artistsComboBox1);
        artistsPanel.add(artistsComboBox2);
        artistsPanel.add(artistsComboBox3);
        artistsPanel.add(artistsComboBox4);
        artistsPanel.add(artistsComboBox5);
        artistsPanel.add(artistsComboBox6);
        artistsPanel.add(artistsComboBox7);
        artistsPanel.add(artistsComboBox8);

        techniciansPanel.add(techniciansLabel);
        techniciansPanel.add(techniciansComboBox1);
        techniciansPanel.add(techniciansComboBox2);

        transferPanel.add(transferLabel);
        transferPanel.add(transferComboBox1);
        transferPanel.add(transferComboBox2);

        mainPanel.add(showPanel);
        mainPanel.add(datePanel);
        mainPanel.add(placePanel);
        mainPanel.add(artistsPanel);
        mainPanel.add(techniciansPanel);
        mainPanel.add(transferPanel);

        this.add(mainPanel);
    }

    public JFrame getParentFrame(){
        return this.parent;
    }
    public JComboBox getShowProgramComboBox(){return showProgramComboBox;}
    public List<JComboBox> getArtistsComboBoxes(){
        return artistsComboBoxes;
    }
    public List<JComboBox> getTechniciansComboBoxes(){
        return techniciansComboBoxes;
    }
}
