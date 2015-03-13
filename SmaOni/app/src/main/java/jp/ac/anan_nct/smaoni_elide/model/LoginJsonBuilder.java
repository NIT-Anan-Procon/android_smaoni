package jp.ac.anan_nct.smaoni_elide.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by skriulle on 2015/03/13.
 */
public class LoginJsonBuilder {

    public JSONObject buildJSON(Player player){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("account", player.getAccount());
            jsonObject.put("password", player.getPassword());
            jsonObject.put("x", player.getPos().getX());
            jsonObject.put("y", player.getPos().getY());

        }catch (JSONException e){}

        return jsonObject;
    }






}
