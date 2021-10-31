package model;

import org.json.JSONObject;
import persistence.Writable;

public class Quarterback extends Player implements Writable {

    private int interceptions;
    private float interceptionsPerGame;

    //REQUIRES: name has non-zero length;
    //EFFECTS: name of quarterback is set to name input, number is set to num, set position to "Quarterback"
    //          initialize quarterback with 0 total yards, 0 total touchdowns, 0 total fumbles, 0 total interceptions,
    //          0 fantasy point, 0 games played,0 yard per game, 0 touchdown per game and 0 fumbles per game
    public Quarterback(String name, int num) {
        super(name, num);
        interceptions = 0;
        interceptionsPerGame = 0;

        position = "Quarterback";
    }

    //EFFECTS: super(makeStats), add calculation of interceptions per game to quarterback's field
    @Override
    public void makeStats() {
        super.makeStats();
        interceptionsPerGame = interceptions / gamesPlayed;
    }

    //EFFECTS: super(calculatePoints), calculate fantasy points with taking into account points deduction from
    //          interceptions
    private void calculatePoints() {
        int pointsByYard = (yards / 25) * POINT_PER_25YD;
        int pointsByTD = touchDowns * POINT_PER_TD;
        int pointsByFumble = fumbles * POINT_PER_FUMBLE;
        int pointsByInt = interceptions * POINT_PER_INT;

        fantasyPoints = pointsByYard + pointsByTD + pointsByFumble + pointsByInt;
    }

    @Override
    //MODIFIES: this
    //EFFECTS: add yards to quarterback
    public void addYards(int yd) {
        yards += yd;
        this.calculatePoints();
    }

    @Override
    //MODIFIES: this
    //EFFECTS: add touchdowns to quarterback
    public void addTouchDown(int td) {
        touchDowns += td;
        this.calculatePoints();
    }

    @Override
    //MODIFIES: this
    //EFFECTS: add fumbles to quarterback
    public void addFumbles(int fumbles) {
        this.fumbles = fumbles;
        this.calculatePoints();
    }

    //MODIFIES: this
    //EFFECTS: add interception to Quarterback stats
    public void addInterceptions(int interceptions) {
        this.interceptions += interceptions;
        this.calculatePoints();
    }

    //getters
    public int getInterceptions() {
        return interceptions;
    }

    public float getInterceptionsPerGame() {
        return interceptionsPerGame;
    }

    //setters
    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public void setInterceptionsPerGame(float interceptionsPerGame) {
        this.interceptionsPerGame = interceptionsPerGame;
    }

    @Override
    //EFFECTS: return Quarterback as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player name", name);
        json.put("number", number);
        json.put("position", position);
        json.put("games played", gamesPlayed);

        json.put("yards", yards);
        json.put("touchdowns", touchDowns);
        json.put("fumbles", fumbles);
        json.put("fantasy points", fantasyPoints);
        json.put("interceptions", interceptions);

        json.put("yards per game", yardPerGame);
        json.put("touchdowns per game", touchDownPerGame);
        json.put("fumbles per game", fumblesPerGame);
        json.put("interceptions per game", interceptionsPerGame);

        return json;
    }
}
