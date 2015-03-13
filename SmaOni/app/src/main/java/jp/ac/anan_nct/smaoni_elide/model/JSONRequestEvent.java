package jp.ac.anan_nct.smaoni_elide.model;

import org.json.JSONObject;

/**
 * Created by skriulle on 2015/03/13.
 */
public interface JSONRequestEvent {



    void onResponse(JSONObject jsonObject);

}
