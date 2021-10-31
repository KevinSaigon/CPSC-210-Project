package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import model.*;
import ui.PlayerDatabase;

//note: cite https://beginnersbook.com/2015/07/java-swing-tutorial/
public class EnterLeagueNameWindow extends JFrame implements ActionListener {

    private final JPanel panel;
    private final JFrame frame;
    private JLabel label;
    private JLabel success;
    private JButton enterButton;
    private JTextField enterNameField;

    //CITE: geekforgeek and alex lee
    //show screen and prompt user to enter league name
    public EnterLeagueNameWindow() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(panel);
        placeComponent(panel);

        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: place components onto Frame
    public void placeComponent(JPanel panel) {
        panel.setLayout(null);
//        panel.setBackground(new Color(108,200,230));

        label = new JLabel("Please enter your league name");
        label.setBounds(10,20,200,25);
        success = new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(label);
        panel.add(success);

        enterNameField = new JTextField(20);
        enterNameField.setBounds(10,50,165,25);
        enterNameField.addActionListener(this);
        panel.add(enterNameField);

        enterButton = new JButton("enter");
        enterButton.setBounds(10,80,80,25);
        enterButton.addActionListener(this);
        panel.add(enterButton);
    }

    //MODIFIES: this
    //EFFECTS: handle input and behaviours inputted by buttons
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (FantasyAppGUI.getFantasyLeagues() == null) {
//            FantasyAppGUI.processNullStatus();
//        }
        String text = enterNameField.getText();
        success.setText("Successfully created a new league called " + text);
        League newLeague = new League(text);


        //this is okay because you would never go to this screen unless you're starting fresh
        List<List<Player>> totalDatabase = new LinkedList<>();
        //TODO: add more if necessary - RunningBack, TightEnd etc etc


        totalDatabase.add(PlayerDatabase.QUARTERBACKS);
        newLeague.addDraftablePlayer(totalDatabase);
        FantasyAppGUI.addToFantasyLeagues(newLeague);
        frame.setVisible(false);
        new LeagueWindow(newLeague);
    }


//    public static void main(String[] args) {
//        new EnterLeagueNameWindow();
//    }


}
