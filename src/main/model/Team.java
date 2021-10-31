package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

//Represent a team with a name and a roster
public class Team implements Writable {
    public static int MAX_PLAYER_ON_TEAM = 5;

    private String teamName;
    private LinkedList<Player> roster;

    //EFFECTS: initialize a new team with empty roster
    public Team(String name) {
        this.teamName = name;
        roster = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: if roster size is less than the max, add player to roster
    public void addPlayer(Player p) {
        if (roster.size() < MAX_PLAYER_ON_TEAM) {
            roster.add(p);
        }
    }

//    //EFFECTS: give name of all players in roster
      //NOTE: this is not yet used
//    public LinkedList<String> getRostersNames() {
//        LinkedList<String> listOfPlayersNames = new LinkedList<String>();
//        for (Player p: roster) {
//            if (!roster.contains(p)) {
//                listOfPlayersNames.add(p.getName());
//            }
//        }
//        return listOfPlayersNames;
//    }

    //EFFECTS: returns true if player is in roster, false otherwise
    public boolean containsPlayer(Player p) {
        return roster.contains(p);
    }

    @Override
    public String toString() {
        return String.format(teamName);
    }

    //getters
    public String getTeamName() {
        return teamName;
    }

    public LinkedList<Player> getRoster() {
        return roster;
    }


    @Override
    //EFFECTS: return team as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("team name", teamName);
        json.put("players", rosterToJson());
        return  json;
    }

    //EFFECTS: return roster as a JSONArray
    public JSONArray rosterToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : roster) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
