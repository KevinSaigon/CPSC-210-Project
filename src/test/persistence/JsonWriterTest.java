package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.PlayerDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    private League league1;
    private League league2;
    private Team team1;
    private Team team2;
    private Player player1;
    private Player player2;

    @BeforeEach
    void runBefore(){
        league1 = new League("League 1");
        league2 = new League("League 2");

        team1 = new Team("Team 1");
        team2 = new Team("Team 2");

        player1 = new Quarterback("John Doe", 14);
        player1.setYards(200);
        player1.setTouchDowns(2);
        player1.setFumbles(0);
        player1.setFantasyPoints(20);
        player1.setGamesPlayed(1);
        player1.setYardsPerGame(200);
        player1.setTouchDownPerGame(2);
        player1.setFumblesPerGame(0);

        player2 = new RunningBack("Saquad", 16);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            FantasyLeagues leagues = new FantasyLeagues();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFantasyLeagues() {
        try {
            FantasyLeagues leagues = new FantasyLeagues();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFantasyLeagues.json");
            writer.open();
            writer.write(leagues);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFantasyLeagues.json");
            leagues = reader.read();
            assertEquals(0, leagues.getFantasyLeagues().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFantasyLeagues() {
        try {
            FantasyLeagues fantasyLeagues = new FantasyLeagues();

            team1.addPlayer(player1);
            team1.addPlayer(player2);
            league1.addTeam(team1);
            league1.addTeam(team2);

            fantasyLeagues.addFantasyLeague(league1);
            fantasyLeagues.addFantasyLeague(league2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFantasyLeagues.json");
            writer.open();
            writer.write(fantasyLeagues);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFantasyLeagues.json");
            fantasyLeagues = reader.read();
            List<League> numberOfLeague = fantasyLeagues.getFantasyLeagues();
            assertEquals(2, numberOfLeague.size());

            League testingLeague = numberOfLeague.get(0);
            assertEquals(0, testingLeague.getDraftable().size());

            List<Team> listOfTeamsOfLeague1 = numberOfLeague.get(0).getTeams();
            assertEquals(2, listOfTeamsOfLeague1.size());

            Team teamTest = listOfTeamsOfLeague1.get(0);
            assertEquals(2,teamTest.getRoster().size());

            Player player1 = teamTest.getRoster().get(0);
            checkPlayerBasic("John Doe", 14, player1);
            checkPlayerOverall(1, player1);
            checkPlayerPerGame(200, 2, 0, player1);
            checkPlayerStats(200,2, 0, 20, player1);

            Player player2 = teamTest.getRoster().get(1);
            checkPlayerBasic("Saquad", 16, player2);
            checkPlayerPerGame(0,0,0, player2);
            checkPlayerStats(0,0,0,0, player2);

//            LinkedList<Player> draftable = numberOfLeague.get(1).getDraftable();
//            assertEquals(1, draftable.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testDraftable() {
        try {
            FantasyLeagues fantasyLeagues = new FantasyLeagues();

            team1.addPlayer(player2);
            league1.addTeam(team1);
            List<List<Player>> totalDatabase = new LinkedList<>();
            //TODO: add more if necessary - RunningBack, TightEnd etc etc
            totalDatabase.add(PlayerDatabase.QUARTERBACKS);
            league1.addDraftablePlayer(totalDatabase);

            fantasyLeagues.addFantasyLeague(league1);

            JsonWriter writer = new JsonWriter("./data/testWriterDraftable.json");
            writer.open();
            writer.write(fantasyLeagues);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDraftable.json");
            fantasyLeagues = reader.read();

            assertEquals(1, fantasyLeagues.getFantasyLeagues().size());
            assertEquals(1, fantasyLeagues.getFantasyLeagues().get(0).getDraftable().size());
            assertEquals(13,
                    fantasyLeagues.getFantasyLeagues().get(0).getDraftable().get("Quarterback").size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
