package jp.ac.anan_nct.smaoni_elide.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by skriulle on 2015/03/13.
 */
public class JSONRequest {

    JSONRequestEvent jsonRecEv;

    public JSONRequest(JSONRequestEvent jsonRecEv){
        this.jsonRecEv = jsonRecEv;
    }

    public void send(JSONRequestParams jrp){
        send(jrp.buildJSON(new Player()));
    }
    public void send(JSONObject jsonObject){
        //jsonObjectを送る

        //行って帰ってきたJSONObjectのダミー
        JSONObject j = new JSONObject();
        try {

            j.put("name", "もりりん");
            jsonRecEv.onResponse(j);

        }catch(JSONException e){
            Log.e("ERROR", e.toString());
        }

    }

}
