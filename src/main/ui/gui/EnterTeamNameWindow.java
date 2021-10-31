package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;

public class EnterTeamNameWindow extends JFrame implements ActionListener {
    private final JPanel panel;
    private final JFrame frame;
    private JLabel label;
    private JLabel success;
    private JButton enterButton;
    private JTextField enterNameField;
    private Team team;

    private final League league;

    //make new screen and prompt user to enter team name
    public EnterTeamNameWindow(League league) {
        this.league = league;

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
    //EFFECTS: place components onto main frame
    public void placeComponent(JPanel panel) {
        panel.setLayout(null);

        label = new JLabel("Please enter your team name");
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
    //EFFECTS: handle expected behaviour from individual button press
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = enterNameField.getText();
        success.setText("Successfully created a new team called " + text);

        team = new Team(text);

//        //TODO: NEED TO DELETE AFTER TESTINGGGGGG
//        Quarterback qb = new Quarterback("Kevin Nguyen", 17);
//        Quarterback qb2 = new Quarterback("Euler", 21);
//        Quarterback qb3 = new Quarterback("Rasheed", 20);
//        team.addPlayer(qb);
//        team.addPlayer(qb2);
//        team.addPlayer(qb3);

        league.addTeam(team);
        frame.setVisible(false);
        new TeamWindow(team);

    }
}
