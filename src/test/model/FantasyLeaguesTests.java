package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FantasyLeaguesTests extends JsonTest {

    private FantasyLeagues fantasyLeagues;
    private League league1;
    private League league2;
    private Team team1;
    private Team team2;
    private Player qb1;

    @BeforeEach
    void runBefore() {
        fantasyLeagues = new FantasyLeagues();
        league1 = new League("Bonjour");
        league2 = new League("Hello");
        team1 = new Team("empty set");
        team2 = new Team("null set");
        qb1 = new Quarterback("Kevin Nguyen", 14);

    }

    @Test
    public void retrieve() {
        fantasyLeagues.addFantasyLeague(league1);
        fantasyLeagues.addFantasyLeague(league2);
        assertEquals(2, fantasyLeagues.getFantasyLeagues().size());
        assertEquals("Bonjour", fantasyLeagues.retrieveLeagueNames().get(0));
    }

    @Test
    public void testyTest() {
        try {
            FantasyLeagues fantasyLeagues = new FantasyLeagues();

            qb1.setYards(200);
            qb1.setTouchDowns(2);
            qb1.setFumbles(0);
            qb1.setFantasyPoints(20);
            qb1.setGamesPlayed(1);
            qb1.setYardsPerGame(200);
            qb1.setTouchDownPerGame(2);
            qb1.setFumblesPerGame(0);


            team1.addPlayer(qb1);
            league1.addTeam(team1);
            league1.addTeam(team2);
            fantasyLeagues.addFantasyLeague(league1);
            fantasyLeagues.addFantasyLeague(league2);

            JsonWriter writer = new JsonWriter("./data/testyTest.json");
            writer.open();
            writer.write(fantasyLeagues);
            writer.close();

            JsonReader reader = new JsonReader("./data/testyTest.json");
            fantasyLeagues = reader.read();
            List<League> numberOfLeague = fantasyLeagues.getFantasyLeagues();
            assertEquals(2, numberOfLeague.size());

            League testingLeague = numberOfLeague.get(0);
            assertEquals(0, testingLeague.getDraftable().size());

            List<Team> listOfTeamsOfLeague1 = numberOfLeague.get(0).getTeams();
            assertEquals(2, listOfTeamsOfLeague1.size());

            Team teamTest = listOfTeamsOfLeague1.get(0);
            assertEquals(1,teamTest.getRoster().size());

            Player player = teamTest.getRoster().get(0);
            checkPlayerBasic("Kevin Nguyen", 14, player);
            checkPlayerOverall(1, player);
            checkPlayerPerGame(200, 2, 0, player);
            checkPlayerStats(200,2, 0, 20, player);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testyTestQuarterback() {
        try {
            FantasyLeagues fantasyLeagues = new FantasyLeagues();
            Quarterback qb = new Quarterback("Dak Prescott", 4);
            team1.addPlayer(qb);
            league1.addTeam(team1);
            league1.addTeam(team2);
//            league2.addToDraftable(player1);
//            league2.addToDraftable(qb);
            fantasyLeagues.addFantasyLeague(league1);
            fantasyLeagues.addFantasyLeague(league2);

            JsonWriter writer = new JsonWriter("./data/testyTest.json");
            writer.open();
            writer.write(fantasyLeagues);
            writer.close();

            JsonReader reader = new JsonReader("./data/testyTest.json");
            fantasyLeagues = reader.read();
            List<League> numberOfLeague = fantasyLeagues.getFantasyLeagues();
            assertEquals(2, numberOfLeague.size());

            League testingLeague = numberOfLeague.get(0);
            assertEquals(0, testingLeague.getDraftable().size());

            List<Team> listOfTeamsOfLeague1 = numberOfLeague.get(0).getTeams();
            assertEquals(2, listOfTeamsOfLeague1.size());

            Team teamTest = listOfTeamsOfLeague1.get(0);
            assertEquals(1, teamTest.getRoster().size());

            Player quarterback = teamTest.getRoster().get(0);
            checkPlayerBasic("Dak Prescott", 4, quarterback);
            checkPlayerOverall(0, quarterback);
            checkPlayerPerGame(0, 0, 0, quarterback);
            checkPlayerStats(0,0, 0, 0, quarterback);

//            LinkedList<Player> draftable = numberOfLeague.get(1).getDraftable();
//            assertEquals(2, draftable.size());

        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

    }
}
