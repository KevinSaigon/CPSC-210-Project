package ui.gui;

import model.FantasyLeagues;
import model.League;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoadWindow extends JFrame implements ActionListener, ListSelectionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static final Color COLOUR_OF_CHOICE = new Color(150,150,150);
    public static final Dimension BUTTON_DIMENSION = new Dimension(100,200);

    private FantasyLeagues loadedFantasyLeagues;
    private League loadedLeague;
    private JButton selectLeagueButton;
    private JButton leagueBackButton;
    private JButton selectTeamButton;
    private JButton teamBackButton;

    private JFrame frame;
    private JPanel buttonsPanel;
    private GridBagConstraints constraints = new GridBagConstraints();

    private JScrollPane leagueListScrollPane;
    private JScrollPane teamListScrollPane;

    private DefaultListModel listModel;
    private JList list;
    private DefaultListModel listModel2;
    private JList list2;

    //display loading window for leagues
    public LoadWindow(FantasyLeagues fantasyLeagues) {
        loadedFantasyLeagues = fantasyLeagues;
        initializeFields();
        makeButtons();
        customizeGraphics();

        makeScrollPaneForLeagues();

        constraints.insets = new Insets(10,10,10,10);
        buttonsPanel.add(selectLeagueButton);
        buttonsPanel.add(leagueBackButton);

        frame.add(leagueListScrollPane);
        frame.setVisible(true);
    }

    //display loading window for Teams
    public LoadWindow(League league) {
        loadedLeague = league;
        initializeFields();
        makeButtons();
        customizeGraphics();

        makeScrollPaneForTeams();

        constraints.insets = new Insets(10,10,10,10);
        buttonsPanel.add(selectTeamButton);
        buttonsPanel.add(teamBackButton);

        frame.add(teamListScrollPane);
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initialize frame and panels
    public void initializeFields() {
        frame = new JFrame();
        buttonsPanel = new JPanel();

        selectLeagueButton = new JButton("Select League");
        leagueBackButton = new JButton("Back");

        selectTeamButton = new JButton("Select Team");
        teamBackButton = new JButton("Back");
    }

    //MODIFIES: this
    //EFFECTS: create buttons
    public void makeButtons() {
        selectLeagueButton.setSize(BUTTON_DIMENSION);
        selectLeagueButton.setActionCommand("select league");
        selectLeagueButton.addActionListener(this);

        leagueBackButton.setSize(BUTTON_DIMENSION);
        leagueBackButton.setActionCommand("back league");
        leagueBackButton.addActionListener(this);

        selectTeamButton.setSize(BUTTON_DIMENSION);
        selectTeamButton.addActionListener(e -> newActionListener("select team"));

        teamBackButton.setSize(BUTTON_DIMENSION);
        teamBackButton.addActionListener(e -> newActionListener("back"));
    }

    //MODIFIES: this
    //EFFECTS: set panels and field to pre determined size
    public void customizeGraphics() {
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        buttonsPanel.setBounds(0,HEIGHT - (HEIGHT / 4), WIDTH, HEIGHT / 4);
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(new Color(190,150,150));
        buttonsPanel.setVisible(true);

        frame.add(buttonsPanel);
    }

    //MODIFIES: this
    //EFFECTS: initialize panel of selectable leagues
    public void makeScrollPaneForLeagues() {
        List<League> listOfLeagues = loadedFantasyLeagues.getFantasyLeagues();
        listModel = new DefaultListModel();
        for (League l : listOfLeagues) {
            listModel.addElement(l);
        }
        list = new JList(listModel);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        leagueListScrollPane = new JScrollPane(list);
        leagueListScrollPane.setBounds(0,0, buttonsPanel.getWidth(),
                HEIGHT - buttonsPanel.getHeight());
    }

    //MODIFIES: this
    //EFFECTS: initialize panel of selectable teams
    public void makeScrollPaneForTeams() {
        List<Team> listOfTeams = loadedLeague.getTeams();
        listModel2 = new DefaultListModel();
        for (Team t : listOfTeams) {
            listModel2.addElement(t);
        }
        list2 = new JList(listModel2);

        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setSelectedIndex(0);
        list2.addListSelectionListener(this);
        list2.setVisibleRowCount(5);
        teamListScrollPane = new JScrollPane(list2);
        teamListScrollPane.setBounds(0,0, buttonsPanel.getWidth(),
                HEIGHT - buttonsPanel.getHeight());
    }

    //MODIFIES: this
    //EFFECTS: handle button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        League selectedLeague = (League) list.getSelectedValue();
//        Team selectedTeam = (Team) list2.getSelectedValue();

        if (e.getActionCommand().equals("back league")) {
            System.out.println("Pressed back");

        } else if (e.getActionCommand().equals("select league")) {
            System.out.println("Pressed select");
            frame.setVisible(false);
            new LeagueWindow(selectedLeague);

//        } else if (e.getActionCommand().equals("select team")) {
//            frame.setVisible(false);
//            new TeamWindow(selectedTeam);
//
//        } else if (e.getActionCommand().equals("back team")) {
//            //NOTE: this is a demo not sure about the implementation just yet
//            frame.setVisible(false);
//            new LeagueWindow();
//            System.out.println("pressed back, this might not be the correct expected menu");
        }
    }

    //MODIFIES: this
    //EFFECTS: handle button presses
    public void newActionListener(String command) {
        Team selectedTeam = (Team) list2.getSelectedValue();
        if (command.equals("select team")) {
            frame.setVisible(false);
            new TeamWindow(selectedTeam);
        } else if (command.equals("back")) {
            frame.setVisible(false);
            new LeagueWindow();
            System.out.println("pressed back, this might not be the correct expected menu");
        }
    }

//    public static void main(String[] args) {
//        League league1 = new League("league 1");
//        League league2 = new League("League 2");
//        FantasyLeagues leagues = new FantasyLeagues();
//        Team team1 = new Team("team1");
//        Team team2 = new Team("team 2");
//
//        league1.addTeam(team1);
//        league1.addTeam(team2);

//        leagues.addFantasyLeague(league1);
//        leagues.addFantasyLeague(league2);
//
////        new LoadWindow(leagues);
//        new LoadWindow(league1);
//
//    }


    //required by ListListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
