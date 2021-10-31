package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTests {

    private League notNFL;
    private Team nyg;
    private Team seattleSeahawks;
    private Player player;

    @BeforeEach
    void runBefore() {
        notNFL = new League("Definitely Not The NFL");
        nyg = new Team("New York Giants");
        seattleSeahawks = new Team("Seattle Seahawks");
        player = new Quarterback("Alan Turing", 10);
    }

    @Test
    public void constructorTest() {
        assertEquals(notNFL.getName(), "Definitely Not The NFL");
        assertEquals(0,notNFL.getTeams().size());
    }

    @Test
    public void testAddTeam() {
        notNFL.addTeam(nyg);
        assertEquals(1, notNFL.getTeams().size());

        notNFL.addTeam(seattleSeahawks);
        assertEquals(2, notNFL.getTeams().size());
    }

    @Test
    public void testContainsPlayer() {
        Player player2 = new Quarterback("blah", 39);
        assertEquals(0, notNFL.getDraftable().size());

        seattleSeahawks.addPlayer(player);
        notNFL.addTeam(seattleSeahawks);
        notNFL.addTeam(nyg);
        assertTrue(notNFL.containsPlayer(player));
        assertFalse(notNFL.containsPlayer(player2));

//        notNFL.addToDraftable(player);
//        assertEquals(1, notNFL.getDraftable().size());

        nyg.addPlayer(player2);
        assertTrue(notNFL.containsPlayer(player2));

        LinkedList<Player> playersList = new LinkedList<>();
        playersList.add(player);
        playersList.add(player);
        playersList.add(player2);
//        notNFL.setDraftable(playersList);
//        assertEquals(3, notNFL.getDraftable().size());
    }

    @Test
    public void testGetTeamNamesNoRepetition() {
        notNFL.addTeam(seattleSeahawks);
        notNFL.addTeam(nyg);

        LinkedList<String> expected = new LinkedList<>();
        expected.add("Seattle Seahawks");
        expected.add("New York Giants");

        LinkedList<String> teamNames = notNFL.getTeamNames();
        assertEquals(expected, teamNames);
        assertEquals(2, teamNames.size());

        assertEquals("Seattle Seahawks", teamNames.get(0));
        assertEquals("New York Giants", teamNames.get(1));
    }

    @Test
    public void testGetTeamNameRepetition() {
        notNFL.addTeam(nyg);
        notNFL.addTeam(nyg);

        LinkedList<String> expected = notNFL.getTeamNames();
        assertEquals(1, expected.size());
        assertEquals("New York Giants", notNFL.getTeamNames().get(0));
    }
}
