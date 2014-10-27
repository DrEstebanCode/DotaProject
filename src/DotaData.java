import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.net.URL;
import java.net.HttpURLConnection;


// This class was created just to test how to connect to the Steam Dota 2 API
// using java.
public class DotaData{


    //  main function that tests getting match details
    public static void main(String [] args){
        DotaData myDota = new DotaData();
        String apiKey;
        apiKey = myDota.getAPIkey();
        System.out.println(apiKey);
        myDota.getEndGameStats(975378350);
    }

    // Function that reads in an api key stored within a text file within the src file
    // apikey.txt should never be pushed to and should be included in the .gitignore file
    public static String getAPIkey(){
        BufferedReader br = null;
        String apiKey = null;
        try{
        br = new BufferedReader(new FileReader("src\\apikey.txt"));
            apiKey = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return apiKey;
    }

    // Function that given a Dota 2 Match ID returns data about the match
    // There is more data I can pull but for now I am only returning the players and their end game stats
    // In the future I want really want to pull and store all the data. For now the goal is to get all the
    // data that you would see on a DotaBuff match page.
    public static void getEndGameStats(long id){
        String matchId = String.valueOf(id);
        String apiKey = getAPIkey();
        String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=" + apiKey + "&match_id=" + matchId;

        try {
            URL matchRequest = new URL(url);
            HttpURLConnection con = (HttpURLConnection) matchRequest.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());

            JSONObject data = new JSONObject(response.toString());
            System.out.println(data.toString());
            JSONObject resultData = data.getJSONObject("result");
            System.out.println(resultData.toString());
            JSONArray playerData = resultData.getJSONArray("players");
            System.out.println(playerData.toString());
            for (int i = 0; i < playerData.length(); i++){
                JSONObject playerInfo = playerData.getJSONObject(i);
                int playerId = playerInfo.getInt("account_id");
                int heroId = playerInfo.getInt("hero_id");
                int kills = playerInfo.getInt("kills");
                int deaths = playerInfo.getInt("deaths");
                int assists = playerInfo.getInt("assists");
                int gold = playerInfo.getInt("gold");
                int goldSpent = playerInfo.getInt("gold_spent");
                int GPM = playerInfo.getInt("gold_per_min");
                int XPM = playerInfo.getInt("xp_per_min");
                int denies = playerInfo.getInt("denies");
                int heroDamage = playerInfo.getInt("hero_damage");
                int towerDamage = playerInfo.getInt("tower_damage");
                int heroHealing = playerInfo.getInt("hero_healing");
                int level = playerInfo.getInt("level");

                System.out.println("Player ID: " + playerId);
                System.out.println("Hero ID: " + heroId);
                System.out.println("Level ID: " + level);
                System.out.println("Kills: " + kills);
                System.out.println("Deaths: " + deaths);
                System.out.println("Assists: " + assists);
                System.out.println(playerId);
                System.out.println(gold);
                System.out.println(goldSpent);
                System.out.println(GPM);
                System.out.println(XPM);
                System.out.println(denies);
                System.out.println(heroDamage);
                System.out.println(towerDamage);
                System.out.println(heroHealing);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    // Converts a steam ID from 32-bit to 64-bit
    // 64-bit steam ID is needed to get users steam profile
    // This is probably a very unsafe way to convert steam ID's lol
    // Look at this example later:
    // (fuck i can't find it)
    public static String convertSteamId(int id){
        long steam_id = (long)id;
        if(id != 17){
            steam_id = steam_id + 76561197960265728l;
        }
        return String.valueOf(steam_id);
    }

}