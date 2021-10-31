//package model;
//
// NOTE: this is extra, the program does not yet take a RunningBack
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import tests_ignore.PlayerTests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class RunningBackTest {
//
//    private RunningBack saquon;
//
//    @BeforeEach
//    protected void runBefore() {
//        saquon = new RunningBack("Saquon Barkley", 26);
//    }
//
////    @Override
//    @Test
//    public void constructorTest() {
//        assertEquals("Saquon Barkley", saquon.getName());
//        assertEquals(26, saquon.getNumber());
//    }
//
////    @Override
//    @Test
//    public void testMakeStat() {
//        //week 1
//        saquon.addYards(165);
//        saquon.addTouchDown(2);
//        saquon.addFumbles(1);
//        saquon.nextGame();
//
//        //week2
//        saquon.addYards(45);
//        saquon.addTouchDown(1);
//
//        saquon.makeStats();
//        assertEquals(saquon.getYards() / saquon.getGamesPlayed(), saquon.getYardPerGame());
//        assertEquals(saquon.getTouchDowns() / saquon.getGamesPlayed(), saquon.getTouchDownPerGame());
//        assertEquals(saquon.getFumbles() / saquon.getGamesPlayed(), saquon.getFumblesPerGame());
//    }
//
////    @Override
//    @Test
//    public void testGetFantasyPoint() {
//        //week 1
//        saquon.addYards(165);
//        saquon.addTouchDown(2);
//        saquon.addFumbles(1);
//        saquon.nextGame();
//        //points at the end of week 1
//        assertEquals(saquon.getFantasyPoints(),
//                (saquon.getYards() / 10 * Player.POINT_PER_10YD) + (saquon.getTouchDowns() * Player.POINT_PER_TD)
//                        + (saquon.getFumbles() * Player.POINT_PER_FUMBLE));
//
//        //week2
//        saquon.addYards(45);
//        saquon.addTouchDown(1);
//
//
//        assertEquals(saquon.getFantasyPoints(),
//                (saquon.getYards() / 10 * Player.POINT_PER_10YD) + (saquon.getTouchDowns() * Player.POINT_PER_TD)
//                        + (saquon.getFumbles() * Player.POINT_PER_FUMBLE));
//        //assertEquals(saquon.getFantasyPoints(), 21 + 18 - 2);
//    }
//
//
////    @Override
//    @Test
//    public void testAddYards() {
//        saquon.addYards(165);
//        saquon.addYards(45);
//        assertEquals(210, saquon.getYards());
//        assertEquals(saquon.getFantasyPoints(),
//                (saquon.getYards() / 10) * Player.POINT_PER_10YD);
//    }
//
////    @Override
//    @Test
//    public void testAddTD() {
//        saquon.addTouchDown(2);
//        saquon.addTouchDown(1);
//        assertEquals(3,saquon.getTouchDowns());
//        assertEquals(saquon.getFantasyPoints(),
//                (saquon.getTouchDowns() * Player.POINT_PER_TD));
//    }
//
////    @Override
//    @Test
//    public void testAddFumbles() {
//        saquon.addFumbles(1);
//        assertEquals(1,saquon.getFumbles());
//        assertEquals(saquon.getFantasyPoints(),
//                (saquon.getFumbles() * Player.POINT_PER_FUMBLE));
//    }
//
//}
