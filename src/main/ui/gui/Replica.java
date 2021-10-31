//package ui.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
//
//import model.*;
//import persistence.JsonWriter;
//
//public class Replica extends JFrame implements ActionListener {
//
//    public static final int WIDTH = 1000;
//    public static final int HEIGHT = 700;
//
//    public static final Color COLOUR_OF_CHOICE = new Color(150,150,150);
//    public static final Dimension BUTTON_DIMENSION = new Dimension(100,200);
//
//    private JButton newLeagueButton;
//    private JButton quitButton;
//    private JButton loadLeagueButton;
//    private JButton saveButton;
//    private JButton noSaveButton;
//
//    private JFrame startingFrame;
//    private JLabel welcomeMessage;
//
//    private JPanel welcomeButtonsPanel;
//    private JPanel welcomeMessagePanel;
//    private JPanel savePanel;
//
//    private static FantasyLeagues fantasyLeagues;
//
//    private JsonWriter jsonWriter;
//    private static final String JSON_STORE = "./data/fantasyLeaguesGUI.json";
//
//    //NOTE: from SimpleDrawingPlayer-Stater from CS210 repository
//    public FantasyAppGUI(FantasyLeagues madeLeague) {
//        super("Fantasy App GUI");
//        fantasyLeagues = madeLeague;
//        initializeFields();
//        initializeStartScreen();
//
//        assignButtonsRoles();
//    }
//
//    public FantasyAppGUI() {
//        super("Fantasy App GUI");
//        initializeFields();
//        initializeStartScreen();
//        assignButtonsRoles();
//    }
//
//    public void initializeFields() {
//        newLeagueButton = new JButton("Create New League");
//        quitButton = new JButton("Quit");
//        loadLeagueButton = new JButton("Load League");
//        saveButton = new JButton("Save");
//        noSaveButton = new JButton("Don't Save");
//
//        startingFrame = new JFrame();
//
//        welcomeButtonsPanel = new JPanel();
//        welcomeMessagePanel = new JPanel();
//        savePanel = new JPanel();
//
//        welcomeMessage = new JLabel("WELCOME TO MY APP");
//
//        jsonWriter = new JsonWriter(JSON_STORE);
//    }
//
//
//    public void initializeStartScreen() {
//        startingFrame.setLayout(null);
//
//        startingFrame.setSize(WIDTH, HEIGHT);
//        startingFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        startingFrame.setLocationRelativeTo(null);
//        startingFrame.setVisible(true);
//
//        makePanels();
//
//        //Note: this is to make them not be right next to each other
//        // https://www.youtube.com/watch?v=eeE44RmE1FM
//        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(10,10,10,10);
//
//        newLeagueButton.setPreferredSize(BUTTON_DIMENSION);
//        quitButton.setPreferredSize(BUTTON_DIMENSION);
//        loadLeagueButton.setPreferredSize(BUTTON_DIMENSION);
//        saveButton.setPreferredSize(BUTTON_DIMENSION);
//        noSaveButton.setPreferredSize(BUTTON_DIMENSION);
//
//        welcomeButtonsPanel.add(newLeagueButton, c);
//        welcomeButtonsPanel.add(loadLeagueButton);
//        welcomeButtonsPanel.add(quitButton, c);
//
//        savePanel.add(saveButton,c);
//        savePanel.add(noSaveButton);
//
//        startingFrame.add(welcomeButtonsPanel);
//        startingFrame.add(welcomeMessagePanel);
//        startingFrame.add(savePanel);
//    }
//
//    public void makePanels() {
//        welcomeButtonsPanel.setBounds(0, 500, WIDTH, HEIGHT / 4);
//        welcomeButtonsPanel.setLayout(new GridBagLayout());
//        welcomeButtonsPanel.setVisible(true);
//        welcomeButtonsPanel.setBackground(COLOUR_OF_CHOICE);
//
//        savePanel.setBounds(0,500, WIDTH, HEIGHT / 4);
//        savePanel.setLayout(new GridBagLayout());
//        savePanel.setVisible(false);
//        savePanel.setBackground(COLOUR_OF_CHOICE);
//
////        welcomeMessagePanel.setLayout(null);
//        welcomeMessagePanel.setBounds(0,0,WIDTH, HEIGHT - welcomeButtonsPanel.getHeight());
//        welcomeMessagePanel.setVisible(true);
//        welcomeMessagePanel.setBackground(new Color(11,77,64));
//        //TODO: make message panel bigger
//        welcomeMessage.setMinimumSize(new Dimension(200,100));
//        welcomeMessagePanel.add(welcomeMessage);
//    }
//
//    public void assignButtonsRoles() {
//        newLeagueButton.setActionCommand("create new league");
//        newLeagueButton.addActionListener(this);
//
//        loadLeagueButton.setActionCommand("load league");
//        loadLeagueButton.addActionListener(this);
//
//        quitButton.setActionCommand("quit");
//        quitButton.addActionListener(this);
//
//        saveButton.setActionCommand("save");
//        saveButton.addActionListener(this);
//
//        noSaveButton.setActionCommand("don't save");
//        noSaveButton.addActionListener(this);
//    }
//
//    public static void addToFantasyLeagues(League newLeague) {
//        fantasyLeagues.addFantasyLeague(newLeague);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("create new league")) {
//            startingFrame.setVisible(false);
//            new EnterLeagueNameWindow();
//
//        } else if (e.getActionCommand().equals("load league")) {
//            startingFrame.setVisible(false);
//            new LoadWindow(fantasyLeagues);
//
//        } else if (e.getActionCommand().equals("quit")) {
//            welcomeButtonsPanel.setVisible(false);
//            savePanel.setVisible(true);
//
//        } else if (e.getActionCommand().equals("save")) {
//            try {
//                jsonWriter.open();
//                jsonWriter.write(fantasyLeagues);
//                jsonWriter.close();
//                System.exit(0);
//                System.out.println("Saved!");
//            } catch (FileNotFoundException ee) {
//                System.out.println("Unable to write to file: " + JSON_STORE);
//            }
//
//        } else if (e.getActionCommand().equals("don't save")) {
//            System.out.println("Goodbye!");
//            System.exit(0);
//        }
//
//    }
//
//    //getters
//    public FantasyLeagues getFantasyLeagues() {
//        return fantasyLeagues;
//    }
//
//
//
////    public static void main(String[] args) {
//////        fantasyLeagues = new FantasyLeagues();
////        //if case and stuff
////        FantasyLeagues madeLeague = new FantasyLeagues();
////
////        new FantasyAppGUI(madeLeague);
////    }
//
//
//
//}
