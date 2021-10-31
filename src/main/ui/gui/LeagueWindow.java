package ui.gui;

import model.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeagueWindow extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final int BUTTON_HEIGHT = 100;
    public static final int BUTTON_WIDTH = 200;

    private final JFrame frame;
    private final JPanel panel;
    private final JButton createTeamButton;
    private final JButton viewTeamButton;
    private final JButton backButton;

    private static League league;

    //display new window with options regarding different leagues
    public LeagueWindow(League league) {

        //TODO: is this gonna be okay?
        this.league = league;

        frame = new JFrame();
        panel = new JPanel();
        createTeamButton = new JButton("Create New Team");
        viewTeamButton = new JButton("View Teams");
        backButton = new JButton("Back");

        initializeGraphics();

        createTeamButton.setActionCommand("create team");
        createTeamButton.addActionListener(this);
        viewTeamButton.setActionCommand("view team");
        viewTeamButton.addActionListener(this);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);

    }

    //display new window with options regarding different leagues
    public LeagueWindow() {
        frame = new JFrame();
        panel = new JPanel();
        createTeamButton = new JButton("Create New Team");
        viewTeamButton = new JButton("View Teams");
        backButton = new JButton("Back");

        initializeGraphics();

        createTeamButton.setActionCommand("create team");
        createTeamButton.addActionListener(this);
        viewTeamButton.setActionCommand("view team");
        viewTeamButton.addActionListener(this);
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: initialize frame and panels
    public void initializeGraphics() {
        frame.setLayout(null);
//        startingFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setVisible(true);

        panel.setBounds(0,500, WIDTH, HEIGHT / 4);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(250,150,150));
        panel.setVisible(true);

        createTeamButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        viewTeamButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        panel.add(createTeamButton, c);
        panel.add(viewTeamButton,c);
        panel.add(backButton,c);

        frame.add(panel);

    }

    //MODIFIES: this
    //EFFECTS: handle expected behaviour of individual button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("create team")) {
            System.out.println("cool beans");
            frame.setVisible(false);
            new EnterTeamNameWindow(league);

        } else if (e.getActionCommand().equals("view team")) {
            System.out.println("cooler beans");
            frame.setVisible(false);
            new LoadWindow(league);

        } else if (e.getActionCommand().equals("back")) {
            new FantasyAppGUI();
        }
    }

    //getters
    public static League getLeague() {
        return league;
    }

//    public static void main(String[] args) {
//
//        //this should happen when you start a new League, like press enter in EnterLeagueNameWindow or somwhere
//        League newLeague = new League("Squammy");
//        List<List<Player>> totalDatabase = new LinkedList<>();
//        //TODO: add more if necessary - RunningBack, TightEnd etc etc
//        totalDatabase.add(PlayerDatabase.QUARTERBACKS);
//        newLeague.addDraftablePlayer(totalDatabase);
//
//        new LeagueWindow(newLeague);
//    }


}
