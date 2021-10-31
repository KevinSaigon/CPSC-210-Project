package model;

import org.json.JSONObject;
import persistence.Writable;

// TODO: modifies effects for this?
public abstract class Player implements Writable {

    public static final int POINT_PER_10YD = 1;
    public static final int POINT_PER_TD = 6;
    public static final int POINT_PER_FUMBLE = -2;
    public static final int POINT_PER_25YD = 1;
    public static final int POINT_PER_INT = -2;

    protected String name;
    protected int number;
    protected String position;
    protected int gamesPlayed;

    protected int yards;
    protected int touchDowns;
    protected int fumbles;
    protected int fantasyPoints;

    protected float yardPerGame;
    protected float touchDownPerGame;
    protected float fumblesPerGame;


    //REQUIRES: name has non-zero length;
    //EFFECTS: name of player is set to name input, player's number is set to num, initialize player with 0 total
    //          yards, 0 total touchdowns, 0 total fumbles, 0 fantasy point, 0 games played, 0 yard per game,
    //          0 touchdown per game and 0 fumbles per game
    public Player(String name, int num) {
        this.name = name;
        number = num;
        position = "Player";

        yards = 0;
        touchDowns = 0;
        fumbles = 0;
        fantasyPoints = 0;
        gamesPlayed = 0;

        yardPerGame = 0;
        touchDownPerGame = 0;
        fumblesPerGame = 0;
    }



    //EFFECTS: calculate per game Stats
    public void makeStats() {
        yardPerGame = yards / gamesPlayed;
        touchDownPerGame = touchDowns / gamesPlayed;
        fumblesPerGame = fumbles / gamesPlayed;
    }

    //MODIFIES: this
    //EFFECTS: calculate points from players stats
    private void calculatePoints() {
        int pointByYards = (yards / 10) * POINT_PER_10YD;
        int pointsByTD = touchDowns * POINT_PER_TD;
        int pointsByFumbles = fumbles * POINT_PER_FUMBLE;

        fantasyPoints = pointByYards + pointsByTD + pointsByFumbles;
    }

    //MODIFIES: this
    //EFFECTS: add yards to players total yard count
    public void addYards(int yd) {
        yards += yd;
        calculatePoints();
    }

    //MODIFIES: this
    //EFFECTS: add touch downs to players total touchdown numbers
    public void addTouchDown(int td) {
        touchDowns += td;
        calculatePoints();
    }

    //MODIFIES: this
    //EFFECTS: add fumbles to players total fumble numbers
    public void addFumbles(int fumbles) {
        this.fumbles += fumbles;
        calculatePoints();
    }

    @Override
    public String toString() {
        return String.format(name);
    }

    //MODIFIES: this
    //EFFECTS: increments players' game played
    public void nextGame() {
        gamesPlayed++;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getYards() {
        return yards;
    }

    public int getTouchDowns() {
        return touchDowns;
    }

    public int getFumbles() {
        return fumbles;
    }

    public int getFantasyPoints() {
        return fantasyPoints;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public float getYardPerGame() {
        return yardPerGame;
    }

    public float getTouchDownPerGame() {
        return touchDownPerGame;
    }

    public float getFumblesPerGame() {
        return fumblesPerGame;
    }

//    public void setPosition(String p) {
//        position = p;
//    }

    //setters
    public void setPosition(String position) {
        this.position = position;
    }

    public void setYards(int y) {
        yards = y;
    }

    public void setTouchDowns(int td) {
        touchDowns = td;
    }

    public void setFumbles(int f) {
        fumbles = f;
    }

    public void setFantasyPoints(int fp) {
        fantasyPoints = fp;
    }

    public void setGamesPlayed(int gp) {
        gamesPlayed = gp;
    }

    public void setYardsPerGame(float ypg) {
        yardPerGame = ypg;
    }

    public void setTouchDownPerGame(float td) {
        touchDownPerGame = td;
    }

    public void setFumblesPerGame(float fpg) {
        fumblesPerGame = fpg;
    }

    //return Player as a JSONObject
    @Override
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

        json.put("yards per game", yardPerGame);
        json.put("touchdowns per game", touchDownPerGame);
        json.put("fumbles per game", fumblesPerGame);

        return json;
    }
}
