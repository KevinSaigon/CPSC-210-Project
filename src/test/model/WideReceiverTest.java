//package model;
//
// NOTE: this is extra, the program does not yet take a wide reciever
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import tests_ignore.PlayerTests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class WideReceiverTest extends PlayerTests {
//
//    private WideReceiver obj;
//
//    @BeforeEach
//    protected void runBefore() {
//        obj = new WideReceiver("Odell Beckham Jr.", 13);
//    }
//
//    @Override
//    @Test
//    public void constructorTest() {
//        assertEquals("Odell Beckham Jr.", obj.getName());
//        assertEquals(13, obj.getNumber());
//    }
//
//    @Override
//    @Test
//    public void testMakeStat() {
//        //week 1
//        obj.addYards(74);
//        obj.addTouchDown(1);
//        obj.addFumbles(0);
//        obj.nextGame();
//
//        //week2
//        obj.addYards(140);
//        obj.addTouchDown(3);
//        obj.addFumbles(1);
//
//        obj.makeStats();
//        assertEquals(obj.getYards() / obj.getGamesPlayed(),
//                obj.getYardPerGame());
//        assertEquals(obj.getTouchDowns() / obj.getGamesPlayed(),
//                obj.getTouchDownPerGame());
//        assertEquals(obj.getFumbles() / obj.getGamesPlayed(),
//                obj.getFumblesPerGame());
//    }
//
//    @Override
//    @Test
//    public void testGetFantasyPoint() {
//        //week 1
//        obj.addYards(140);
//        obj.addTouchDown(2);
//        obj.nextGame();
//        //points at the end of week 1
//        assertEquals(obj.getFantasyPoints(),
//                ((obj.getYards() / 10) * Player.POINT_PER_10YD)
//                        + (obj.getTouchDowns() * Player.POINT_PER_TD)
//                        + (obj.getFumbles() * Player.POINT_PER_FUMBLE));
//
//        //week2
//        obj.addYards(110);
//        obj.addTouchDown(3);
//        obj.addFumbles(1);
//
//
//        assertEquals(obj.getFantasyPoints(),
//                (obj.getYards() / 10 * Player.POINT_PER_10YD)
//                        + (obj.getTouchDowns() * Player.POINT_PER_TD)
//                        + (obj.getFumbles() * Player.POINT_PER_FUMBLE));
//        //assertEquals(obj.getFantasyPoints(), 21 + 18 - 2);
//    }
//
//
//    @Override
//    @Test
//    public void testAddYards() {
//        obj.addYards(85);
//        obj.addYards(59);
//        assertEquals(144, obj.getYards());
//        assertEquals(obj.getFantasyPoints(),
//                (obj.getYards() / 10) * Player.POINT_PER_10YD);
//    }
//
//    @Override
//    @Test
//    public void testAddTD() {
//        obj.addTouchDown(3);
//        obj.addTouchDown(2);
//        assertEquals(5,obj.getTouchDowns());
//        assertEquals(obj.getFantasyPoints(),
//                (obj.getTouchDowns() * Player.POINT_PER_TD));
//    }
//
//    @Override
//    @Test
//    public void testAddFumbles() {
//        obj.addFumbles(2);
//        assertEquals(2,obj.getFumbles());
//        assertEquals(obj.getFantasyPoints(),
//                (obj.getFumbles() * Player.POINT_PER_FUMBLE));
//    }
//
//}
