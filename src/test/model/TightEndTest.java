//package model;
//
//NOTE: this is extra, the program does not yet take a tight end
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import tests_ignore.PlayerTests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TightEndTest extends PlayerTests {
//
//    private TightEnd georgeKittle;
//
//    @BeforeEach
//    protected void runBefore() {
//        georgeKittle = new TightEnd("George Kittle", 85);
//    }
//
//    @Override
//    @Test
//    public void constructorTest() {
//        assertEquals("George Kittle", georgeKittle.getName());
//        assertEquals(85, georgeKittle.getNumber());
//    }
//
//    @Override
//    @Test
//    public void testMakeStat() {
//        //week 1
//        georgeKittle.addYards(120);
//        georgeKittle.addTouchDown(1);
//        georgeKittle.addFumbles(0);
//        georgeKittle.nextGame();
//
//        //week2
//        georgeKittle.addYards(140);
//        georgeKittle.addTouchDown(1);
//        georgeKittle.addFumbles(1);
//
//        georgeKittle.makeStats();
//        assertEquals(georgeKittle.getYards() / georgeKittle.getGamesPlayed(),
//                georgeKittle.getYardPerGame());
//        assertEquals(georgeKittle.getTouchDowns() / georgeKittle.getGamesPlayed(),
//                georgeKittle.getTouchDownPerGame());
//        assertEquals(georgeKittle.getFumbles() / georgeKittle.getGamesPlayed(),
//                georgeKittle.getFumblesPerGame());
//    }
//
//    @Override
//    @Test
//    public void testGetFantasyPoint() {
//        //week 1
//        georgeKittle.addYards(120);
//        georgeKittle.addTouchDown(1);
//        georgeKittle.nextGame();
//        //points at the end of week 1
//        assertEquals(georgeKittle.getFantasyPoints(),
//                ((georgeKittle.getYards() / 10) * Player.POINT_PER_10YD)
//                        + (georgeKittle.getTouchDowns() * Player.POINT_PER_TD)
//                        + (georgeKittle.getFumbles() * Player.POINT_PER_FUMBLE));
//
//        //week2
//        georgeKittle.addYards(140);
//        georgeKittle.addTouchDown(2);
//        georgeKittle.addFumbles(1);
//
//
//        assertEquals(georgeKittle.getFantasyPoints(),
//                (georgeKittle.getYards() / 10 * Player.POINT_PER_10YD)
//                        + (georgeKittle.getTouchDowns() * Player.POINT_PER_TD)
//                        + (georgeKittle.getFumbles() * Player.POINT_PER_FUMBLE));
//        //assertEquals(georgeKittle.getFantasyPoints(), 21 + 18 - 2);
//    }
//
//
//    @Override
//    @Test
//    public void testAddYards() {
//        georgeKittle.addYards(168);
//        georgeKittle.addYards(90);
//        assertEquals(258, georgeKittle.getYards());
//        assertEquals(georgeKittle.getFantasyPoints(),
//                (georgeKittle.getYards() / 10) * Player.POINT_PER_10YD);
//    }
//
//    @Override
//    @Test
//    public void testAddTD() {
//        georgeKittle.addTouchDown(1);
//        georgeKittle.addTouchDown(1);
//        assertEquals(2,georgeKittle.getTouchDowns());
//        assertEquals(georgeKittle.getFantasyPoints(),
//                (georgeKittle.getTouchDowns() * Player.POINT_PER_TD));
//    }
//
//    @Override
//    @Test
//    public void testAddFumbles() {
//        georgeKittle.addFumbles(3);
//        assertEquals(3,georgeKittle.getFumbles());
//        assertEquals(georgeKittle.getFantasyPoints(),
//                (georgeKittle.getFumbles() * Player.POINT_PER_FUMBLE));
//    }
//
//}
