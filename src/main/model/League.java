package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Represent a league which has a name and a list of teams
public class League implements Writable {

    private String name;
    private LinkedList<Team> teams;
//    private LinkedList<Player> draftable;
    private Map<String, List<Player>> draftable;

    //EFFECTS: make new league
    public League(String leagueName) {
        name = leagueName;
        teams = new LinkedList<Team>();
        draftable = new HashMap<>();
    }


    //MODIFIES: this
    //EFFECTS: add team into League's list of team
    public void addTeam(Team teamName) {
        teams.add(teamName);
    }

    //EFFECTS: return true if Player already exists within the League, false otherwise
    public boolean containsPlayer(Player p) {
        for (Team t: teams) {
            //TODO: contains player in Team class
            if (t.containsPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    //getters
    public String getName() {
        return name;
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public Map<String, List<Player>> getDraftable() {
        return draftable;
    }

    //setters
    public void setDraftable(Map<String, List<Player>> map) {
        draftable = map;
    }

    public LinkedList<String> getTeamNames() {
        LinkedList<String> listOfTeamNames = new LinkedList<String>();
        for (Team t: teams) {
            if (!listOfTeamNames.contains(t.getTeamName())) {
                listOfTeamNames.add(t.getTeamName());
            }
        }
        return listOfTeamNames;
    }

    public void addDraftablePlayer(List<List<Player>> listOfListsOfPlayers) {
        for (List<Player> playerList : listOfListsOfPlayers) {
            Player randomPlayer = playerList.get(0);
            String position = randomPlayer.getPosition();

            draftable.put(position, playerList);
        }
    }

    @Override
    public String toString() {
        return String.format(name);
    }

    //setters
//    public void setDraftable(LinkedList<Player> listOfDraftablePlayers) {
//        draftable = listOfDraftablePlayers;
//    }

    //MODIFIES: this
    //EFFECTS: add player to draftable fields
//    public void addToDraftable(Player p) {
//        draftable.add(p);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: remove player from draftable fields
//    public void removeFromDraftable(Player p) {
//        draftable.remove(p);
//    }



    @Override
    //EFFECTS: return League as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("league name", name);
        json.put("teams", teamsToJson());
        json.put("draftable", draftableToJson());
        return json;
    }

    //EFFECTS: return teams as a JSONArray
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t: teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    //maybe comment this out, it's okay if it's writing but it doesn't have to read
    //EFFECTS: return draftable as a JSONArray
//    private JSONArray draftableToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (Player p : draftable) {
//            jsonArray.put(p.toJson());
//        }
//        return jsonArray;
//    }
    private JSONObject draftableToJson() {
        JSONObject json = new JSONObject();
        for (String key : draftable.keySet()) {
            JSONArray jsonArray = new JSONArray();

            for (Player p : draftable.get(key)) {
                jsonArray.put(p.toJson());
            }
            json.put(key, jsonArray);
        }
        return json;
    }

//    map.get(position)
//    listForPosition = map.get(position)
//    listForPosition.add(p)

}
