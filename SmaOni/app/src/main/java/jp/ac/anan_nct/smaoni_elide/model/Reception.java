package jp.ac.anan_nct.smaoni_elide.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by skriulle on 2015/03/10.
 */
public class Reception {

    Player[] players;
    boolean start;
    Date startTime, endTime;

    public Reception(JSONArray jsonArray) {
        startTime = new Date();
        players = new Player[jsonArray.length()-3];
        int i = 0;
        for (i = 0; i < jsonArray.length()-3; i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Player player = new Player();
                player.setName(jsonObject.getString("name"));
                player.setAccount(jsonObject.getString("account"));
                player.setPos(new Position(jsonObject.getInt("x"), jsonObject.getInt("y")));
                players[i] = player;
            } catch (JSONException e){
                Log.e("ERROR", e.toString());
            }
        }
        try{
            start = jsonArray.getBoolean(i++);
            startTime = new Date(jsonArray.getLong(i++));
            endTime = new Date(jsonArray.getLong(i++));
        }catch(JSONException e){
            Log.e("ERROR", e.toString());
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean canStart(){
        return start;
    }

    public Date startAt(){
        return startTime;
    }

    public Date endAt(){
        return endTime;
    }
}
