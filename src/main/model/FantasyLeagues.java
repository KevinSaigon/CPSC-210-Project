package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

public class FantasyLeagues implements Writable {

    private LinkedList<League> leagues;

    //EFFECTS: make a new list of Leagues
    public FantasyLeagues() {
        leagues = new LinkedList<League>();
    }

    //MODIFIES: this
    //EFFECTS: add league to FantasyLeague
    public void addFantasyLeague(League l) {
        leagues.add(l);
    }

    //getters
    //EFFECTS: return leagues in FantasyLeagues
    public LinkedList<League> getFantasyLeagues() {
        return leagues;
    }

    //EFFECTS: return names of all leagues in FantasyLeagues
    public List<String> retrieveLeagueNames() {
        List<String> leagueNames = new LinkedList<String>();
        for (League l: leagues) {
            String name = l.getName();
            leagueNames.add(name);
        }
        return leagueNames;
    }

    @Override
    //EFFECTS: return FantasyLeagues as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("leagues", fantasyLeaguesToJson());
        return json;
    }

    //EFFECTS: return leagues as a JSONArray
    private JSONArray fantasyLeaguesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (League l: leagues) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
