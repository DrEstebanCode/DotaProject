import org.json.JSONObject;

/**
 * Created by Marco on 2015-07-08.
 */
//Player class!!! :) :D
class Player{
    int playerId;
    int heroId;
    int kills;
    int deaths;
    int assists;
    int gold;
    int goldSpent;
    int GPM;
    int XPM;
    int denies;
    int heroDamage;
    int towerDamage;
    int heroHealing;
    int level;

    public Player(JSONObject playerInfo){
        playerId = playerInfo.getInt("account_id");
        heroId = playerInfo.getInt("hero_id");
        kills = playerInfo.getInt("kills");
        deaths = playerInfo.getInt("deaths");
        assists = playerInfo.getInt("assists");
        gold = playerInfo.getInt("gold");
        goldSpent = playerInfo.getInt("gold_spent");
        GPM = playerInfo.getInt("gold_per_min");
        XPM = playerInfo.getInt("xp_per_min");
        denies = playerInfo.getInt("denies");
        heroDamage = playerInfo.getInt("hero_damage");
        towerDamage = playerInfo.getInt("tower_damage");
        heroHealing = playerInfo.getInt("hero_healing");
        level = playerInfo.getInt("level");
    }


    // All the get functions!!!

    public int getPlayerId(){
        return playerId;
    }

    public int getHeroId(){
        return heroId;
    }

    public int getKills(){
        return kills;
    }

    public int getDeaths(){
        return deaths;
    }

    public int getAssists(){
        return assists;
    }

    public int getGold(){
        return gold;
    }

    public int getGoldSpent(){
        return goldSpent;
    }

    public int getDenies(){
        return denies;
    }

    public int getHeroDamage(){
        return heroDamage;
    }

    public int getTowerDamage(){
        return towerDamage;
    }
    public int getHeroHealing(){
        return heroHealing;
    }

    public int getGPM(){
        return GPM;
    }

    public int getXPM(){
        return XPM;
    }
}