package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;


//NOTE: this console code was made possible through the guidance and inspiration of the Account Teller application.
// As well as the JsonSerialization starter which inspired the implementation of my JSON reader/writer. Thank you to my
// TA also who helped me reimplement some of my methods.

//Fantasy football application
public class FantasyApp extends PlayerDatabase {

    private static final String JSON_STORE = "./data/fantasyLeagues.json";
    protected Scanner input;
    private FantasyLeagues fantasyLeagues;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //TODO: question - how to prevent java from spazzing when your input is of the incorrect type --> another if
    //  statement for invalid input
    //TODO: how to exit out of 2 nested while loops
    //TODO: input issue, it's not making a new line for the input
    //TODO: why does it fail sometimes?

    //EFFECT: run the Fantasy application
    public FantasyApp() {
        runFantasy();
    }

    //MODIFIES: this
    //EFFECTS: open up the app
    private void runFantasy() {
        boolean keepGoing = true;
        //can this be an integer
        Integer command = null;
        input = new Scanner(System.in);
        fantasyLeagues = new FantasyLeagues();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command.equals(0)) {
                keepGoing = optionToSaveGame();
//                saveGame();
//                keepGoing = false;
            } else {
                processCommand(command);
            }
            //TODO: command to exit out of while loop
            //if (command == 0) {
            //    keepGoing = false;
            //}
        }

        System.out.println("\nSee you next week!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(Integer command) {
        if (command.equals(1)) {
            doNewLeague();

        } else if (command.equals(2)) {
            loadGame();
            viewLeagues();
            int selection;
            selection = input.nextInt();
            League selectedLeague = selectLeague(selection);
            viewTeams(selectedLeague);
//            processGetTeam(selectedLeague);

        } else {
            System.out.println("Selection not valid please try again");
        }
    }

    //EFFECTS: process user option to save game
    private boolean optionToSaveGame() {
        System.out.println("\nWould you like to save? ");
        System.out.println("\t1 - yes");
        System.out.println("\t2 - no");

        int command = input.nextInt();

        if (command == 1) {
            saveGame();
            return false;
        } else {
            return false;
        }
//        } else {
//            System.out.println("That was not a valid option. Please try again.");
//            optionToSaveGame();
//        }
    }

    //MODIFIES: this
    //EFFECTS: save the game
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(fantasyLeagues);
            jsonWriter.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: load the game
    private void loadGame() {
        try {
            fantasyLeagues = jsonReader.read();
            System.out.println("Loaded fantasy league!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - start a new league");
        System.out.println("\t2 - join an existing league");
        System.out.println("\t0 - quit");
    }

    //EFFECTS: display list of saved leagues
    private void viewLeagues() {
        List<String> namesOfLeagues = fantasyLeagues.retrieveLeagueNames();
        System.out.println("\nSelect from:");
        for (int i = 0; i < namesOfLeagues.size(); i++) {
            System.out.printf("\n[%d] %s", i + 1, namesOfLeagues.get(i));
        }
    }

    //EFFECTS: process user command and select desired league
    private League selectLeague(Integer selection) {
        //TODO: take command and let them view the teams within the league.
//        System.out.println("You selected League" + selection + ". This is a temporary output!");
//        System.out.println("You selected League" + selection);
        int selectedIndex = selection - 1;
        League selectedLeague = fantasyLeagues.getFantasyLeagues().get(selectedIndex);
        System.out.println("You selected " + selectedLeague.getName());
        return selectedLeague;
    }

    //MODIFIES: this and Team
    //EFFECTS: create new League with name as user input
    private void doNewLeague() {

        System.out.println("Please enter your league name");
        String leagueName = input.next();

        League newLeague = new League(leagueName);
        List<List<Player>> totalDatabase = new LinkedList<>();

        //TODO: add more if necessary - RunningBack, TightEnd etc etc
        totalDatabase.add(PlayerDatabase.QUARTERBACKS);

        newLeague.addDraftablePlayer(totalDatabase);

        fantasyLeagues.addFantasyLeague(newLeague);
        makeOrSelectTeam(newLeague);


    }

    //EFFECTS: process user input
    private void makeOrSelectTeam(League currentLeague) {
        //changed newLeague to currentLeague
        boolean keepGoing = true;
        Integer command = null;

        while (keepGoing) {
            displayTeamMenu();
            command = input.nextInt();

            //this is not quitting correctly
            //for now let's make it a feature to go back
            if (command.equals(0)) {
                keepGoing = false;
            } else {
                processTeamCommand(command, currentLeague);
            }
        }

    }

    //EFFECTS: process user command regarding team options
    private void processTeamCommand(Integer command, League currentLeague) {
        if (command.equals(1)) {
            doNewTeam(currentLeague);
        }
        if (command.equals(2)) {
            viewTeams(currentLeague);
        }
    }

    //EFFECTS: show list of teams and process user command
    private void viewTeams(League league) {
//        System.out.println("This is a beta and a temporary output!");
//        System.out.println("Select one of the following teams:");
        List<String> namesOfTeams = league.getTeamNames();
        System.out.println("\nSelect from:");
        for (int i = 0; i < namesOfTeams.size(); i++) {
            System.out.printf("\n[%d] %s", i + 1, namesOfTeams.get(i));
        }

        int command = input.nextInt();
        int index = command - 1;
        processGetTeam(league, index);
    }

    //EFFECTS: retrieve desired team by user
    private void processGetTeam(League selectedLeague, int index) {
        Team t = selectedLeague.getTeams().get(index);
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Draft Players");
        System.out.println("\t2 - View Players");

//        Map<String, List<Player>> map = selectedLeague.getDraftable();
//        Map<String, List<Integer>> map = new HashMap<>();
//        List<Integer> listOfInteger = new ArrayList<>();
//        listOfInteger.add(1);
//        listOfInteger.add(2);
//        listOfInteger.add(3);
//        map.put("testing", listOfInteger);
//        System.out.println(map);

        //TODO: this is not reading correctly, figure out why
        Integer command = input.nextInt();
        if (command.equals(1)) {
            draftingOptions(t, selectedLeague);
        } else {
            choosePlayersToView(t);
        }
    }

    private void choosePlayersToView(Team t) {
        List<Player> players = t.getRoster();
        System.out.println("\nPlease select the following players:");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("\n[%d] %s", i + 1, players.get(i).getName());
        }

        Integer command = input.nextInt() - 1;
        viewPlayer(command, players);
    }

    private void viewPlayer(int command, List<Player> players) {
        Player selectedPlayer = players.get(command);
        if (selectedPlayer.getPosition().equals("Quarterback")) {
            Quarterback qb = (Quarterback) selectedPlayer;
            System.out.println("Player name: " + qb.getName());
            System.out.println("Total Yards: " + qb.getYards());
            System.out.println("Fantasy Points: " + qb.getFantasyPoints());
            System.out.println("Total Interceptions: " + qb.getInterceptions());
        } else {
            System.out.println("Player name: " + selectedPlayer.getName());
            System.out.println("Total Yards: " + selectedPlayer.getYards());
            System.out.println("Fantasy Points: " + selectedPlayer.getFantasyPoints());
        }
    }

    //MODIFIES: this
    //EFFECTS: make a new team and call draftPlayers
    private void doNewTeam(League currentLeague) {
        System.out.println("Please enter your team's name");
        String teamName = input.next();

        Team newTeam = new Team(teamName);
        currentLeague.addTeam(newTeam);

        draftingOptions(newTeam, currentLeague);
    }

    //EFFECTS: display menu for the team portion
    private void displayTeamMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - create a new team");
        System.out.println("\t2 - select a pre-existing team");
        System.out.println("\t0 - go back");
    }

    //EFFECTS: call DraftPlayers
    //let's assume it works
    private void draftingOptions(Team newTeam, League currentLeague) {
        draftPlayer("Quarterback", newTeam, currentLeague);
        //draftPlayer("Running Back", newTeam, currentLeague); etc etc
        // TODO: draft a couple more rounds, ideally they will all use the same menu etc etc...

        //give them options to input stats or go home.
        continueToInputStatsOrNot(newTeam);
    }

    //EFFECTS: list of players add player to draftable field of leauge
    private void draftPlayer(String key, Team currentTeam, League league) {

//        List<Player> draftable = league.getDraftable();
//        if (!draftable.isEmpty()) {
//            System.out.println("Please select the following players:");
//            printPlayerDraftable(draftable);
//            System.out.println("\n");
//            draftDraftable(draftable, currentTeam);
//
//        }
        Map<String, List<Player>> map = league.getDraftable();
        List<Player> listOfPlayers = map.get(key);
        if (!listOfPlayers.isEmpty()) {
            System.out.println("Please select the following players:");
            printPlayerDraftable(listOfPlayers);
            System.out.println("\n");
            draftDraftable(listOfPlayers, currentTeam);
        }

    }

    //EFFECTS: display all PLayers left that can be drafted
    private void printPlayerDraftable(List<Player> draftable) {
        for (int i = 0; i < draftable.size(); i++) {
            System.out.printf("\n[%d] %s", i + 1, draftable.get(i).getName());
        }
    }

    //EFFECTS: add player to team
    private void draftDraftable(List<Player> draftable, Team currentTeam) {
        int selection;
        selection = input.nextInt();
        Player drafted = draftable.get(selection - 1);
        currentTeam.addPlayer(drafted);

        draftable.remove(drafted);

        printDrafted(drafted, currentTeam);
    }

    //EFFECTS: print confirmation of drafting players
    private void printDrafted(Player drafted, Team currentTeam) {
        System.out.println(currentTeam.getTeamName() + " " + "has selected" + " " + drafted.getName());
    }

    //  TODO: implement try catch clause
    // private static int scanForInt()
    //  scanForInt();
    //  while (!isValid) {
    //      try {
    //          selection = scanner.nextLine();
    //          isValid = true;
    //  }   catch (NoSuchElementException e) {
    //     // do nothing try again
    //  }

    //EFFECTS: process user input regarding inputting stats
    private void continueToInputStatsOrNot(Team currentTeam) {
        boolean keepGoing = true;
        Integer command;

        while (keepGoing) {
            displayInputOrQuitMenu();
            command = input.nextInt();

            if (command.equals(0)) {
                //NOTE: is exiting to the team menu, I want it to exit one more time
                keepGoing = false;
            }
            if (command.equals(1)) {
                choosePlayerToInputStats(currentTeam);
            }
        }
    }

    //EFFECTS: display menu with option to quit
    private void displayInputOrQuitMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Input player's stats");
        // will implement this later when you figure out how to save game;
        //System.out.println("\t2 - join a current team");
        System.out.println("\t0 - Quit to main menu");
    }

    //EFFECTS: display list of players for user to input stats and process user command
    private void choosePlayerToInputStats(Team currentTeam) {
        List<Player> roster = currentTeam.getRoster();
        printPlayersForInputStats(roster);

        int selection;
        selection = input.nextInt();
        Player selectedPlayer = roster.get(selection - 1);
        inputStats(selectedPlayer);
    }

    //EFFECTS: display message
    private void printPlayersForInputStats(List<Player> roster) {
        for (int i = 0; i < roster.size(); i++) {
            System.out.println("\nPlease select the players from your team:");
            System.out.printf("\n[%d] %s", i + 1, roster.get(i).getName());
            System.out.println("\n");
        }

    }

    //EFFECTS: add user inputted stats to player
    private void inputStats(Player player) {
        if (player.getPosition().equals("Quarterback")) {
            inputStatsForQuarterback(player);
        } else {
            System.out.println("Please enter the number of yards your" + player.getPosition() + "threw for last week");
            int yardNumber = input.nextInt();
            System.out.println("Please enter the number of touchdowns your" + player.getPosition()
                    + "scored last week");
            int tdNumbers = input.nextInt();
            System.out.println("Please enter the amount of times your" + player.getPosition() + " fumbled last week");
            int fumbleNumbers = input.nextInt();

            processStatsPlayer(player, yardNumber, tdNumbers, fumbleNumbers);
        }
    }

    //EFFECTS: add user inputted stats to quarterback
    private void inputStatsForQuarterback(Player p) {
        Quarterback qb = (Quarterback) p;
        System.out.println("Please enter the number of yards your QB threw for last week");
        int yardNumber = input.nextInt();
        System.out.println("Please enter the number of touchdowns your QB scored last week");
        int tdNumbers = input.nextInt();
        System.out.println("Please enter the amount of times your QB fumbled last week");
        int fumbleNumbers = input.nextInt();
        System.out.println("PLease enter the number of interceptions your player had last week");
        int interceptionNumbers = input.nextInt();

        processStatsQuarterback(qb, yardNumber, tdNumbers, fumbleNumbers, interceptionNumbers);
    }

    //EFFECTS: display message and confirm input with user
    private void processStatsPlayer(Player p, int yard, int td, int fumbles) {
        p.addYards(yard);
        p.addTouchDown(td);
        p.addFumbles(fumbles);

        System.out.println("Your player has " + p.getYards() + " total yard" + ", " + p.getTouchDowns()
                + " total touchdowns" + " and " + p.getFumbles() + " total fumbles. Your player has scored "
                + p.getFantasyPoints() + " total Fantasy Points!");
    }

    //MODIFIES: this
    //EFFECTS: input quarterback stats and compute fantasy points and return confirmation messages.
    private void processStatsQuarterback(Quarterback qb, int yard, int td, int fumbles, int pick) {
        qb.addYards(yard);
        qb.addTouchDown(td);
        qb.addFumbles(fumbles);
        qb.addInterceptions(pick);

        System.out.println("Your player has " + qb.getYards() + " total yard" + ", " + qb.getTouchDowns()
                            + " total touchdowns" + ", " + qb.getFumbles() + " total fumbles and "
                            + qb.getInterceptions() + " total interceptions. Your player has scored "
                            + qb.getFantasyPoints() + " total Fantasy Points!");
    }





}
