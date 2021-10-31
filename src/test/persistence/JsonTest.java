package persistence;
import model.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected static final double DELTA = 1e-15;

    protected void checkPlayerBasic(String name, int num, Player p) {
        assertEquals(name, p.getName());
        assertEquals(num, p.getNumber());
    }
    protected void checkPlayerOverall(int gamesPlayed, Player p) {
        //NOTE: add position later
//        assertEquals(pos, p.getPosition());
        assertEquals(gamesPlayed, p.getGamesPlayed());
    }

    protected  void checkPlayerStats(int yards, int td, int fumbles, int fantasyPoints, Player p) {
        assertEquals(yards, p.getYards());
        assertEquals(td, p.getTouchDowns());
        assertEquals(fumbles, p.getFumbles());
        assertEquals(fantasyPoints, p.getFantasyPoints());
    }

    protected void checkPlayerPerGame(float ypg, float tdPer, float fumblesPer, Player p) {
        assertEquals(ypg, p.getYardPerGame(), DELTA);
        assertEquals(tdPer, p.getTouchDownPerGame(), DELTA);
        assertEquals(fumblesPer, p.getFumblesPerGame(), DELTA);
    }
}
