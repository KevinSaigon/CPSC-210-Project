package ui.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


public class FantasyAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/fantasyLeaguesGUI.json";

    private static JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static final Color COLOUR_OF_CHOICE = new Color(150,200,170);
    public static final Dimension BUTTON_DIMENSION = new Dimension(100,200);

    private JButton newLeagueButton;
    private JButton quitButton;
    private JButton loadButton;
    private JButton loadLeagueButton;
    private JButton saveButton;
    private JButton noSaveButton;

    private JFrame startingFrame;
    private JLabel errorMessage;

    private JPanel buttonsPanel;
    private JPanel errorMessagePanel;
    private JPanel savePanel;

    private static FantasyLeagues fantasyLeagues;


    //create the GUI
    public FantasyAppGUI() {
        super("Fantasy App GUI");
//        fantasyLeagues = null;
        initializeFields();
        initializeStartScreen();
        assignButtonsRoles();
    }

    //MODIFIES: this
    //EFFECTS: initialize all fields
    public void initializeFields() {
        newLeagueButton = new JButton("Create New League");
        quitButton = new JButton("Quit");
        loadLeagueButton = new JButton("View Leagues");
        saveButton = new JButton("Save");
        noSaveButton = new JButton("Don't Save");
        loadButton = new JButton("Load");

        startingFrame = new JFrame();

        buttonsPanel = new JPanel();
        errorMessagePanel = new JPanel();
        savePanel = new JPanel();

        errorMessage = new JLabel("");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: initialize the first frame
    public void initializeStartScreen() {
        startingFrame.setLayout(null);

        startingFrame.setSize(WIDTH, HEIGHT);
        startingFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startingFrame.setLocationRelativeTo(null);
        startingFrame.setVisible(true);

        makePanels();

        //Note: this is to make them not be right next to each other
        // citation: https://www.youtube.com/watch?v=eeE44RmE1FM
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        newLeagueButton.setPreferredSize(BUTTON_DIMENSION);
        quitButton.setPreferredSize(BUTTON_DIMENSION);
        loadLeagueButton.setPreferredSize(BUTTON_DIMENSION);
        saveButton.setPreferredSize(BUTTON_DIMENSION);
        noSaveButton.setPreferredSize(BUTTON_DIMENSION);

        buttonsPanel.add(newLeagueButton, c);
        buttonsPanel.add(loadLeagueButton, c);
        buttonsPanel.add(loadButton, c);
        buttonsPanel.add(quitButton, c);


        savePanel.add(saveButton,c);
        savePanel.add(noSaveButton);

        startingFrame.add(buttonsPanel);
        startingFrame.add(errorMessagePanel);
        startingFrame.add(savePanel);
    }

    //MODIFIES: this
    //EFFECTS: create and customize panels
    public void makePanels() {
        buttonsPanel.setBounds(0, 500, WIDTH, HEIGHT / 4);
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setVisible(true);
        buttonsPanel.setBackground(COLOUR_OF_CHOICE);

        savePanel.setBounds(0,500, WIDTH, HEIGHT / 4);
        savePanel.setLayout(new GridBagLayout());
        savePanel.setVisible(false);
        savePanel.setBackground(COLOUR_OF_CHOICE);

        errorMessagePanel.setBounds(0,500 - 40,WIDTH, 40);
        errorMessagePanel.setVisible(true);
        errorMessagePanel.setBackground(COLOUR_OF_CHOICE);
        errorMessagePanel.add(errorMessage);
    }

    //MODIFIES: this
    //EFFECTS: assign designated roles to buttons
    public void assignButtonsRoles() {
        newLeagueButton.setActionCommand("create new league");
        newLeagueButton.addActionListener(this);

        loadLeagueButton.setActionCommand("load league");
        loadLeagueButton.addActionListener(this);

        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);

        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        noSaveButton.setActionCommand("don't save");
        noSaveButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: add League to FantasyLeagues field
    public static void addToFantasyLeagues(League newLeague) {
        fantasyLeagues.addFantasyLeague(newLeague);
    }

    //MODIFIES: this
    //EFFECTS: handle commands of buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("create new league")) {
            if (fantasyLeagues == null) {
                fantasyLeagues = new FantasyLeagues();
            }
            startingFrame.setVisible(false);
            new EnterLeagueNameWindow();

        } else if (e.getActionCommand().equals("load league")) {
            if (fantasyLeagues == null) {
                errorMessage.setText("There are no loaded league, please click load button or create a new team");
            } else {
                startingFrame.setVisible(false);
                new LoadWindow(fantasyLeagues);
            }

        } else {
            actionPerformed2(e);
        }

    }

    public void actionPerformed2(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            buttonsPanel.setVisible(false);
            savePanel.setVisible(true);
            playSound("21.wav");

        } else if (e.getActionCommand().equals("save")) {
            saveGame();

        } else if (e.getActionCommand().equals("don't save")) {
             //System.out.println("Goodbye!);
            System.exit(0);

        } else if (e.getActionCommand().equals("load")) {
            processLoadLeague();
            playSound("son.wav");
        }
    }

    //MODIFIES: this
    //EFFECTS: load league from json file
    public void processLoadLeague() {
        try {
            FantasyLeagues loadedLeague = jsonReader.read();
            errorMessage.setText("Loaded League Successfully!");
            System.out.println("Loaded fantasy leagues!");
            fantasyLeagues = loadedLeague;

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            //can we make this catch case better, like instead of of just printing it'll do smt else
            //NOTE: set the panel to output "there's not load file" please start fresh"
            errorMessage.setText("There was no save file found, did you want to start a new league?");
            FantasyLeagues newlyMadeLeague = new FantasyLeagues();
            fantasyLeagues = newlyMadeLeague;
        }
    }

    //MODIFIES: this
    //EFFECTS: save fantasyLeagues to json file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(fantasyLeagues);
            jsonWriter.close();
            errorMessage.setText("Thanks for playing!");
            System.exit(0);
            System.out.println("Saved!");
        } catch (FileNotFoundException ee) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
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


    //getters
    public static FantasyLeagues getFantasyLeagues() {
        return fantasyLeagues;
    }

//    public static void main(String[] args) {
//        new FantasyAppGUI();
//    }



}
