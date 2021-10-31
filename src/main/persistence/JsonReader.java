package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FantasyLeagues read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFantasyLeague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private FantasyLeagues parseFantasyLeague(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("leagues");
        FantasyLeagues leagues = new FantasyLeagues();
        for (Object json : jsonArray) {
            JSONObject nextLeague = (JSONObject) json;
            addLeagues(leagues, nextLeague);
        }
        return leagues;
    }

    private void addLeagues(FantasyLeagues leagues, JSONObject jsonObject) {
        String name = jsonObject.getString("league name");
        League league = new League(name);
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextLeague = (JSONObject) json;
            addTeams(league, nextLeague);
        }

        //maybe comment this out
//        JSONArray draftable = jsonObject.getJSONArray("draftable");
//        for (Object draftablePlayer : draftable) {
//            JSONObject nextDraftablePlayer = (JSONObject) draftablePlayer;
//            Player p = createPlayers(nextDraftablePlayer);
//            league.addToDraftable(p);
//        }
        Map<String, List<Player>> draftable = new HashMap<>();
        JSONObject draftableObject = jsonObject.getJSONObject("draftable");
        for (String key: draftableObject.keySet()) {
            LinkedList<Player> players = new LinkedList<>();

            for (Object player : draftableObject.getJSONArray(key)) {
                JSONObject nextDraftablePlayer = (JSONObject) player;
                Player p = createPlayers(nextDraftablePlayer);
                players.add(p);
            }
            draftable.put(key, players);
        }
        league.setDraftable(draftable);
        leagues.addFantasyLeague(league);
    }

    private void addTeams(League l, JSONObject jsonObject) {
        String name = jsonObject.getString("team name");
        Team t = new Team(name);
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            Player p = createPlayers(nextPlayer);
            t.addPlayer(p);

        }
        l.addTeam(t);

    }

    private Player createPlayers(JSONObject jsonObject) {
        String position = jsonObject.getString("position");

        if (position.equals("Quarterback")) {
            Quarterback quarterback = createNewQuarterback(jsonObject);
            return quarterback;
        } else if (position.equals("Running Back")) {
            RunningBack runningBack = createNewRunningBack(jsonObject);
            return runningBack;
        } else {
            throw new RuntimeException();
        }
    }

    private Quarterback createNewQuarterback(JSONObject jsonObject) {
        String name = jsonObject.getString("player name");
        int number = jsonObject.getInt("number");
        int interception = jsonObject.getInt("interceptions");
        float interceptionPerGame = jsonObject.getFloat("interceptions per game");

        Quarterback qb = new Quarterback(name, number);
        setPlayerField(qb, jsonObject);
        qb.setInterceptions(interception);
        qb.setInterceptionsPerGame(interceptionPerGame);

        return qb;
    }

    private RunningBack createNewRunningBack(JSONObject jsonObject) {
        String name = jsonObject.getString("player name");
        int number = jsonObject.getInt("number");

        RunningBack rb = new RunningBack(name, number);
        setPlayerField(rb, jsonObject);

        return rb;
    }

//    private void whichPosition(String position, JSONObject jsonObject) {
//        if (position.equals("Running Back")) {
//            createNewRunningBack(jsonObject);
//        } else {
////            throw new RuntimeException();
//            System.out.println("huh");
//        }
//    }

    private void setPlayerField(Player player, JSONObject jsonObject) {
        int gamesPlayed = jsonObject.getInt("games played");
        int yards = jsonObject.getInt("yards");
        int touchdowns = jsonObject.getInt("touchdowns");
        int fumbles = jsonObject.getInt("fumbles");
        int fantasyPoints = jsonObject.getInt("fantasy points");
        float yardsPerGame = jsonObject.getFloat("yards per game");
        float touchdownPerGame = jsonObject.getFloat("touchdowns per game");
        float fumblesPerGame = jsonObject.getFloat("fumbles per game");

        player.setGamesPlayed(gamesPlayed);

        player.setYards(yards);
        player.setTouchDowns(touchdowns);
        player.setFumbles(fumbles);
        player.setFantasyPoints(fantasyPoints);

        player.setYardsPerGame(yardsPerGame);
        player.setTouchDownPerGame(touchdownPerGame);
        player.setFumblesPerGame(fumblesPerGame);
    }

}