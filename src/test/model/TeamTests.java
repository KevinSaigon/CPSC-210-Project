package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTests {

    private Team nyg;
//    private RunningBack saquon;
    private Quarterback dj;


    @BeforeEach
    void runBefore() {
        dj = new Quarterback("Daniel Jones", 8);
        Quarterback mahomes = new Quarterback("patrick mahomes", 15);
        nyg = new Team("the worst sports team in a city that includes the Knicks");

    }

    @Test
    public void constructorTest() {
        assertEquals("the worst sports team in a city that includes the Knicks",
                nyg.getTeamName());
        assertEquals(0, nyg.getRoster().size());
    }

    @Test
    public void testDraftPlayersLessThanMax() {
        Team giants = new Team("New York Giants");
        assertEquals("New York Giants", giants.getTeamName());
        assertEquals(0, nyg.getRoster().size());

        nyg.addPlayer(dj);
        assertEquals(1, nyg.getRoster().size());
        //        nyg.draftPlayers(saquon);
        // assertEquals(2, nyg.getRoster().size());
    }

    @Test
    public void testDraftPlayerMoreThanMax() {
        nyg.addPlayer(dj);
        nyg.addPlayer(dj);
        nyg.addPlayer(dj);
        nyg.addPlayer(dj);
        nyg.addPlayer(dj);
        assertEquals(5, nyg.getRoster().size());

        Quarterback lamar = new Quarterback("Lamar Jackson", 15);
        nyg.addPlayer(lamar);
        //make sure lamar didn't get added to the roster
        assertEquals(5, nyg.getRoster().size());
    }

    @Test
    public void testToString() {
        assertEquals("the worst sports team in a city that includes the Knicks",nyg.toString());
    }


//    @Test
//    public void testGetRostersNamesDifferentPlayers() {
////        assertEquals(null, nyg.getRostersNames());
//        assertEquals(0, nyg.getRostersNames().size());
//
//        nyg.draftPlayers(dj);
////        nyg.draftPlayers(saquon);
//        nyg.draftPlayers(mahomes);
////        assertEquals(["Daniel Jones", "Saquon Barkley"], nyg.getRostersNames());
//        assertEquals(2, nyg.getRostersNames().size());
//    }


}

