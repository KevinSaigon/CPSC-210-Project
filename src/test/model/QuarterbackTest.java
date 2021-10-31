package model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuarterbackTest extends PlayerTests {

    @Test
    public void constructorTest() {
        assertEquals("Daniel Jones", dj.getName());
        assertEquals(8, dj.getNumber());
        assertEquals("Quarterback", dj.getPosition());
    }

    @Override
    @Test
    public void testMakeStat() {
        //week 1
        dj.addYards(120);
        dj.addTouchDown(1);
        dj.addFumbles(0);
        dj.nextGame();

        //week2
        dj.addYards(140);
        dj.addTouchDown(1);
        dj.addFumbles(1);

        dj.makeStats();
        assertEquals(dj.getYards() / dj.getGamesPlayed(),
                dj.getYardPerGame());
        assertEquals(dj.getTouchDowns() / dj.getGamesPlayed(),
                dj.getTouchDownPerGame());
        assertEquals(dj.getFumbles() / dj.getGamesPlayed(),
                dj.getFumblesPerGame());
        assertEquals(dj.getInterceptions() / dj.getGamesPlayed(),
                dj.getInterceptionsPerGame());
    }

    @Override
    @Test
    public void testGetFantasyPoint() {

        assertEquals("Daniel Jones", dj.getName());
        assertEquals(8, dj.getNumber());

        dj.setInterceptions(2);
        assertEquals(2, dj.getInterceptions());

        dj.setInterceptions(0);
        //week 1
        dj.addInterceptions(1);
        dj.addYards(250);
        dj.addTouchDown(2);
        dj.nextGame();

        //points at the end of week 1
        assertEquals((dj.getYards() / 25) * Player.POINT_PER_25YD
                + (dj.getTouchDowns() * Player.POINT_PER_TD)
                + (dj.getFumbles() * Player.POINT_PER_FUMBLE)
                + (dj.getInterceptions() * Player.POINT_PER_INT), dj.getFantasyPoints());

        //week2
        dj.addYards(400);
        dj.addTouchDown(4);
        dj.addFumbles(2);
        dj.addInterceptions(2);

        assertEquals(dj.getFantasyPoints(),
                ((dj.getYards() / 25) * Player.POINT_PER_25YD)
                        + (dj.getTouchDowns() * Player.POINT_PER_TD)
                        + (dj.getFumbles() * Player.POINT_PER_FUMBLE)
                        + (dj.getInterceptions() * Player.POINT_PER_INT));
    }


//    @Override
//    @Test
//    public void testAddYards() {
//        dj.addYards(168);
//        dj.addYards(90);
//        assertEquals(258, dj.getYards());
//        assertEquals((dj.getYards() / 25) * Player.POINT_PER_25YD, dj.getFantasyPoints());
//        //assertEquals(dj.getFantasyPoints(), 10);
//    }

//    @Override
//    @Test
//    public void testAddTD() {
//        dj.addTouchDown(1);
//        dj.addTouchDown(1);
//        assertEquals(2,dj.getTouchDowns());
//        assertEquals((dj.getTouchDowns() * Player.POINT_PER_TD), dj.getFantasyPoints());
////        assertEquals(dj.getFantasyPoints(), 12);
//    }

//    @Override
//    @Test
//    public void testAddFumbles() {
//        dj.addFumbles(3);
//        assertEquals(3,dj.getFumbles());
//        assertEquals((dj.getFumbles() * Player.POINT_PER_FUMBLE), dj.getFantasyPoints());
////        assertEquals(-6, dj.getFantasyPoints());
//    }

    @Test
    public void addInterceptions() {
        dj.addInterceptions(2);
        assertEquals(2, dj.getInterceptions());
        assertEquals(dj.getInterceptions() * Player.POINT_PER_INT, dj.getFantasyPoints());
    }



}
