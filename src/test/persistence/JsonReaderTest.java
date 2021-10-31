package persistence;

import model.FantasyLeagues;
import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FantasyLeagues leagues = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFantasyLeagues() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFantasyLeagues.json");
        try {
            FantasyLeagues leagues = reader.read();
            assertEquals(0, leagues.getFantasyLeagues().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFantasyLeagues() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterGeneralFantasyLeagues.json");
            FantasyLeagues fantasyLeagues = reader.read();
            List<League> numberOfLeague = fantasyLeagues.getFantasyLeagues();
            assertEquals(2, numberOfLeague.size());

            List<Team> listOfTeamsOfLeague1 = numberOfLeague.get(0).getTeams();
            assertEquals(2, listOfTeamsOfLeague1.size());

            Team teamTest = listOfTeamsOfLeague1.get(0);
            assertEquals(2,teamTest.getRoster().size());

            Player player = teamTest.getRoster().get(0);
            checkPlayerBasic("John Doe", 14, player);
            checkPlayerOverall(1, player);
            checkPlayerPerGame(200, 2, 0, player);
            checkPlayerStats(200,2, 0, 20, player);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
