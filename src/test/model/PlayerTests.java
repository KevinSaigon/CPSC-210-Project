package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class PlayerTests {

    protected Player player1;
    protected Quarterback dj;

    @BeforeEach
    public void runBefore() {
        dj = new Quarterback("Daniel Jones", 8);
        //Figure out testing situation
        player1 = new RunningBack("Alan Turing", 27);

    }

    @Test
    public abstract void constructorTest();

    @Test
    public void testMakeStat() {
        player1.addYards(20);
        player1.addTouchDown(2);
        player1.addFumbles(1);
        player1.nextGame();

        assertEquals("Alan Turing", player1.getName());
        assertEquals(27, player1.getNumber());
        assertEquals("Player", player1.getPosition());
        player1.setPosition("Cornerback");
        assertEquals("Cornerback", player1.getPosition());

        player1.makeStats();
        assertEquals(player1.getYards() / player1.getGamesPlayed(),
                player1.getYardPerGame());
        assertEquals(player1.getTouchDowns() / player1.getGamesPlayed(),
                player1.getTouchDownPerGame());
        assertEquals(player1.getFumbles() / player1.getGamesPlayed(),
                player1.getFumblesPerGame());
    }

    @Test
    public abstract void testGetFantasyPoint();

    @Test
    public  void testAddYards() {
        Quarterback dj2 = new Quarterback("Daniel Jones", 8);
        Player player2 = new RunningBack("Alan Turing", 420);

        dj2.addYards(168);
        dj2.addYards(90);
        assertEquals(258, dj2.getYards());
        assertEquals((dj2.getYards() / 25) * Player.POINT_PER_25YD, dj2.getFantasyPoints());

        player2.addYards(20);
        player2.addYards(40);
        assertEquals(60, player2.getYards());

        dj2.setPosition("Bench warmer");
        assertEquals("Bench warmer", dj2.getPosition());
    }


    @Test
    public void testAddTD() {
        dj.addTouchDown(1);
        dj.addTouchDown(1);
        assertEquals(2,dj.getTouchDowns());
        assertEquals((dj.getTouchDowns() * Player.POINT_PER_TD), dj.getFantasyPoints());

        player1.addTouchDown(2);
        player1.addTouchDown(3);
        assertEquals(5, player1.getTouchDowns());
        assertEquals((player1.getTouchDowns() * Player.POINT_PER_TD), player1.getFantasyPoints());
    }

    @Test
    public void testAddFumbles() {
        dj.addFumbles(3);
        assertEquals(3,dj.getFumbles());
        assertEquals((dj.getFumbles() * Player.POINT_PER_FUMBLE), dj.getFantasyPoints());

        player1.addFumbles(1);
        assertEquals(1, player1.getFumbles());
        assertEquals((player1.getFumbles() * Player.POINT_PER_FUMBLE), player1.getFantasyPoints());
    }

    @Test
    public void testSetters() {
        player1.setYards(200);
        player1.setTouchDowns(1);
        player1.setFumbles(2);
        player1.setFantasyPoints(60);
        player1.setGamesPlayed(2);
        player1.setYardsPerGame(22);
        player1.setTouchDownPerGame(3);
        player1.setFumblesPerGame(2);
//        player1.setPosition("Player");

        assertEquals(200, player1.getYards());
        assertEquals(1, player1.getTouchDowns());
        assertEquals(2, player1.getFumbles());
        assertEquals(60, player1.getFantasyPoints());
        assertEquals(2, player1.getGamesPlayed());
        assertEquals(22, player1.getYardPerGame());
        assertEquals(3, player1.getTouchDownPerGame());
        assertEquals(2, player1.getFumblesPerGame());
//        assertEquals("Player", player1.getPosition());
    }

    @Test
    public void testToString() {
        assertEquals("Daniel Jones", dj.toString());
    }

}
