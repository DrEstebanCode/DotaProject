/**
 * Created by Andrew on 10/26/2014.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.net.URL;
import java.net.HttpURLConnection;

public class Match {

    //Create the Api class to access API Key
    Api API = new Api();
    String apiKey = API.getApiKey();

    // Match Details
    //TODO: Add cluster (replay) support
    int duration;
    int lobbyType;
    int firstBloodTime;
    int towerStatusRadiant;
    int towerStatusDire;
    int humanPlayers;
    int positiveVotes;
    int negativeVotes;
    int gameMode;
    int barracksStatusRadiant;
    int barracksStatusDire;
    int startTime;
    Player[] players;

    //Team info (only available in team games)
    String radiantName;
    //picture radiantLogo;
    boolean radiantTeamComplete;
    String direName;
    //picture radiantLogo;
    boolean direTeamComplete;

    public Match(int matchId){
        getMatchDetails(matchId);
    }

    private void getMatchDetails(int matchId){
        String matchUrl = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=" + apiKey + "&match_id=" + matchId;

        try {
            URL matchRequest = new URL(matchUrl);
            HttpURLConnection con = (HttpURLConnection) matchRequest.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + matchUrl);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject data = new JSONObject(response.toString());
            System.out.println(data.toString());
            JSONObject resultData = data.getJSONObject("result");

            // Set the Match Details
            duration = resultData.getInt("duration");
            startTime = resultData.getInt("start_time");
            lobbyType = resultData.getInt("lobby_type");
            firstBloodTime = resultData.getInt("first_blood_time");
            towerStatusRadiant = resultData.getInt("tower_status_radiant");
            towerStatusDire = resultData.getInt("tower_status_dire");
            humanPlayers = resultData.getInt("human_players");
            positiveVotes = resultData.getInt("positive_votes");
            negativeVotes = resultData.getInt("negative_votes");
            gameMode = resultData.getInt("game_mode");
            barracksStatusRadiant = resultData.getInt("barracks_status_radiant");
            barracksStatusDire = resultData.getInt("barracks_status_dire");

            // Set the Player details
            JSONArray playerData = resultData.getJSONArray("players");
            players = parsePlayerData(playerData);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player[] parsePlayerData(JSONArray playerArray){
        Player[] thePlayers = new Player[playerArray.length()];
        for(int i = 0; i < playerArray.length(); i++){
            thePlayers[i] = new Player(playerArray.getJSONObject(i));
        }

        return thePlayers;
    }

    public boolean positiveGame()
    {
        return (positiveVotes >= negativeVotes);
    }

    // Gets gets gets
    public Player[] getPlayers(){
        return players;
    }

    public Player findPlayer(int Id)
    {
        for (Player player : players)
        {
            if (player.playerId == Id)
                return player;
        }

        // did not find the player with that ID
        return null;
    }


}
