package ui.gui;

import model.Player;
import model.Quarterback;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

//this window should be display the names of all players already on the team and players who can be added to the team;
public class TeamWindow extends JFrame implements ListSelectionListener, ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private Team team;

    private JFrame frame;
    private JFrame statsFrame;
    private JPanel buttonsPanel;
    private JPanel statsPanel;
    private JPanel statsDisplayPanel;

    private JLabel yardsLabel;
    private JLabel touchDownsLabel;
    private JLabel fumblesLabel;
    private JLabel interceptionsLabel;
    private JLabel displayStatsLabel;

    private JTextField yardsInput;
    private JTextField touchDownsInput;
    private JTextField fumblesInput;
    private JTextField interceptionsInput;

    private GridBagConstraints constraints = new GridBagConstraints();

    private JButton selectButton;
    private JButton backButton;
    private JButton viewStatsButton;
    private JButton inputStatsButton;
    private JButton enterButton;

    private DefaultListModel listModel;
    private JList list;
    private DefaultListModel listModel2;
    private JList rosterList;

    //make a window with options for a selected team
    public TeamWindow(Team team) {
        this.team = team;
        initializeFields();
        makeStatsFrameAndPanel();
        makeButtons();
        initializeGraphics();

        List<Player> listOfQB = LeagueWindow.getLeague().getDraftable().get("Quarterback");
        listModel = new DefaultListModel();
        for (Player p : listOfQB) {
            listModel.addElement(p);
        }
        list = new JList(listModel);

        //NOTE: this is a fragment of code from ListDemo project from Oracle website
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBounds(0,0, buttonsPanel.getWidth(),
                HEIGHT - buttonsPanel.getHeight() - statsDisplayPanel.getHeight());

        JScrollPane sidePanelScrollPane = makeSideScrollPane();

        frame.add(listScrollPane);
        frame.add(sidePanelScrollPane);
    }

    //MODIFIES: this
    //EFFECTS: make selectable list of players that are on the team
    public JScrollPane makeSideScrollPane() {
        List<Player> roster = team.getRoster();
        listModel2 = new DefaultListModel();
        for (Player p : roster) {
            listModel2.addElement(p);
        }
        rosterList = new JList(listModel2);

        rosterList.setSelectedIndex(0);
        rosterList.addListSelectionListener(this);
        rosterList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(rosterList);
        listScrollPane.setBounds(buttonsPanel.getWidth(),0, WIDTH - buttonsPanel.getWidth(),
                HEIGHT);

        return listScrollPane;
    }

    //MODIFIES: this
    //EFFECTS: initialize graphics
    public void initializeGraphics() {
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonsPanel.setBounds(0,HEIGHT - (HEIGHT / 5), WIDTH - 350, HEIGHT / 5);
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(new Color(190,150,150));
        buttonsPanel.setVisible(true);

        statsDisplayPanel.setBounds(0,HEIGHT - 180, buttonsPanel.getWidth(), 40);
        statsDisplayPanel.setLayout(new GridBagLayout());
        statsDisplayPanel.setBackground(new Color(250,150,150));
        displayStatsLabel.setSize(new Dimension(100, 30));
        statsDisplayPanel.add(displayStatsLabel);

        constraints.insets = new Insets(10,10,40,10);

        buttonsPanel.add(selectButton, constraints);
        buttonsPanel.add(inputStatsButton, constraints);
        buttonsPanel.add(viewStatsButton, constraints);
        buttonsPanel.add(backButton, constraints);

        frame.add(buttonsPanel);
        frame.add(statsDisplayPanel);
    }

    //MODIFIES: this
    //EFFECTS: initialize fields
    public void initializeFields() {
        frame = new JFrame();
        buttonsPanel = new JPanel();
        statsDisplayPanel = new JPanel();
        displayStatsLabel = new JLabel("");
    }

    //MODIFIES: this
    //EFFECTS: initialize frame for inputting stats
    public void makeStatsFrameAndPanel() {
        statsFrame = new JFrame();
        statsPanel = new JPanel();
        statsFrame.setSize(WIDTH, HEIGHT);
        statsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        statsFrame.setLocationRelativeTo(null);

        statsFrame.add(statsPanel);
        placeComponent(statsPanel);

        statsFrame.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: place component
    public void placeComponent(JPanel statsPanel) {
        statsPanel.setLayout(null);
        statsPanel.setBackground(new Color(80,150,150));

        yardsLabel = new JLabel("Input the number of yards your player ran for last week");
        yardsLabel.setBounds(40,50,WIDTH,35);
        touchDownsLabel = new JLabel("Input the number of touchdowns your player scored last week");
        touchDownsLabel.setBounds(40,150,WIDTH,35);
        fumblesLabel = new JLabel("Input the number of fumbles your player had");
        fumblesLabel.setBounds(40,250, WIDTH, 35);
        //TODO: only add this on if it's a quarterback
        interceptionsLabel = new JLabel("Input the number of interceptions your player had");
        interceptionsLabel.setBounds(40,350, WIDTH, 35);
        statsPanel.add(yardsLabel);
        statsPanel.add(touchDownsLabel);
        statsPanel.add(fumblesLabel);

        makeTextFields();

        enterButton = new JButton("Enter");
        enterButton.setBounds(40,500, 100,50);
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(this);

        statsPanel.add(yardsInput);
        statsPanel.add(touchDownsInput);
        statsPanel.add(fumblesInput);
        statsPanel.add(enterButton);
    }

    //MODIFIES: this
    //EFFECTS: initialize text fields for inputting stats
    public void makeTextFields() {
        yardsInput = new JTextField(10);
        yardsInput.setBounds(40, 100, 165,25);

        touchDownsInput = new JTextField(10);
        touchDownsInput.setBounds(40,200,165,25);

        fumblesInput = new JTextField(10);
        fumblesInput.setBounds(40,300,165,25);
        fumblesInput.setActionCommand("enter");
        fumblesInput.addActionListener(this);

        interceptionsInput = new JTextField(10);
        interceptionsInput.setBounds(40,400,165,25);
        interceptionsInput.setActionCommand("enter");
        interceptionsInput.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: set buttons to desired size and commands
    public void makeButtons() {
        selectButton = new JButton("Select");
        selectButton.setActionCommand("Select");
        selectButton.addActionListener(new SelectListener());

        viewStatsButton = new JButton("View Player");
        viewStatsButton.setActionCommand("View Player");
        viewStatsButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);

        inputStatsButton = new JButton("Input Stats");
        inputStatsButton.setActionCommand("Input Stats");
        inputStatsButton.addActionListener(this);
    }

    //NOTE - cite: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //MODIFIES: this
    //EFFECTS: play sound
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: handle expected response from buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        Player selectedPlayer = (Player) rosterList.getSelectedValue();

        if (e.getActionCommand().equals("Back")) {
            pressBack();
        } else if (e.getActionCommand().equals("View Player")) {
            processViewStats(selectedPlayer);
            playSound("checkthestatistics.wav");

        } else if (e.getActionCommand().equals("Input Stats")) {
            if (selectedPlayer.getPosition().equals("Quarterback")) {
                statsPanel.add(interceptionsLabel);
                statsPanel.add(interceptionsInput);
            }
            playSound("omg.wav");
            flipStates();

        } else if (e.getActionCommand().equals("enter")) {
            if (selectedPlayer.getPosition().equals("Quarterback")) {
                processInputStatsQuarterback(selectedPlayer);
            } else {
                processInputStats(selectedPlayer);
            }
            flipStates();
            playSound("its-lit.wav");
        }
    }

    //MODIFIES: this
    //EFFECTS: go back to last screen and play sound
    public void pressBack() {
        frame.setVisible(false);
        new LeagueWindow();
        playSound("omg.wav");
    }

    //MODIFIES: this
    //EFFECTS: flips the visibility of frame and statsFrame
    public void flipStates() {
        frame.setVisible(!frame.isVisible());
        statsFrame.setVisible(!statsFrame.isVisible());
    }

    //MODIFIES: this
    //EFFECTS: show stats of player on label
    public void processViewStats(Player p) {

        if (p.getPosition().equals("Quarterback")) {
            Quarterback qb = (Quarterback) p;
            displayStatsLabel.setText("<html>Your player has " + qb.getYards() + " total yard" + ", "
                    + qb.getTouchDowns() + " total touchdowns" + ", " + qb.getFumbles() + " total fumbles and "
                    + qb.getInterceptions() + " total interceptions. <br> Your player has scored "
                    + qb.getFantasyPoints() + " Fantasy Points!</html>");


        } else {
            displayStatsLabel.setText("<html>Your player has " + p.getYards() + " total yard" + ", "
                    + p.getTouchDowns() + " total touchdowns" + ", " + p.getFumbles() + " total fumbles. <br>"
                    + "Your player has scored " + p.getFantasyPoints() + " Fantasy Points!</html>");
        }
    }

    //NOTE - cite: https://stackoverflow.com/questions/17914979/how-to-get-int-from-jtextfield-with-a-jbutton/17915203
    //MODIFIES: this
    //EFFECTS: get stats from a given player
    public void processInputStats(Player selectedPlayer) {
        try {
            int yards = Integer.parseInt(yardsInput.getText());
            int touchdowns = Integer.parseInt(touchDownsInput.getText());
            int fumbles = Integer.parseInt(fumblesInput.getText());

            selectedPlayer.addYards(yards);
            selectedPlayer.addTouchDown(touchdowns);
            selectedPlayer.addFumbles(fumbles);

        } catch (NumberFormatException ex) {
            System.out.println("Exception : " + ex);
        }
    }

    //MODIFIES: this
    //EFFECTS: get stats from a given quarterback
    public void processInputStatsQuarterback(Player selectedPlayer) {
        try {
            Quarterback qb = (Quarterback) selectedPlayer;
            int yards = Integer.parseInt(yardsInput.getText());
            int touchdowns = Integer.parseInt(touchDownsInput.getText());
            int fumbles = Integer.parseInt(fumblesInput.getText());
            int interceptions = Integer.parseInt(interceptionsInput.getText());

            qb.addYards(yards);
            qb.addTouchDown(touchdowns);
            qb.addFumbles(fumbles);
            qb.addInterceptions(interceptions);

        } catch (NumberFormatException ex) {
            System.out.println("Exception : " + ex);
        }
    }

    //required by ListListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    //for the select button
    class SelectListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: select desired player, add it to our team roster and delete it from viewable list
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Player selectedPlayer = (Player) list.getSelectedValue();
            team.addPlayer(selectedPlayer);

            //TODO: remove this mf
            // make an if case and check for position of his, from there you can know what key to remove from
            LeagueWindow.getLeague().getDraftable().get("Quarterback").remove(selectedPlayer);

            listModel.remove(index);

            for (Player p : team.getRoster()) {
                if (!listModel2.contains(p)) {
                    listModel2.addElement(p);
                }
            }

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                selectButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
                playSound("coin.wav");
            }
        }

    }

}
