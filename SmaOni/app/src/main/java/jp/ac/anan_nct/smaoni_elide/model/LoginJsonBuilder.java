package jp.ac.anan_nct.smaoni_elide.model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by skriulle on 2015/03/13.
 */
public class LoginJsonBuilder implements JSONRequestParams{

    Player player;

    public LoginJsonBuilder(){
        player = new Player();
    }

    public JSONObject buildJSON(Player player){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("account", player.getAccount());
            jsonObject.put("password", player.getPassword());
            jsonObject.put("x", player.getPos().getX());
            jsonObject.put("y", player.getPos().getY());

        }catch (Exception e){
            Log.e("ERROR:LoginJsonBuilder", e.toString());
        }

        return jsonObject;
    }






}
