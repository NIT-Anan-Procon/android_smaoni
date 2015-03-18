package jp.ac.anan_nct.smaoni_elide.model;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jp.ac.anan_nct.smaoni_elide.activity.ReceptionActivity;
import jp.ac.anan_nct.smaoni_elide.view.MapView;

/**
 * Created by skriulle on 2015/03/18.
 */
public class Communication2 extends AsyncTask {


    HttpPost post;
    HttpClient httpClient;

    GameData gameData;
    public static boolean conect;


    MapView mapView;
    JSONArray playerArray;


    public Communication2(GameData gameData, MapView mapView) {
        super();
        conect = true;
        httpClient = new DefaultHttpClient();
        Uri uri = Uri.parse(MyURL.PATH_ONIGOKKO);
        post = new HttpPost(uri.toString());
        this.gameData = gameData;
        this.mapView = mapView;
    }


    @Override
    protected Object doInBackground(Object[] p) {

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            Log.e("ERROR:Communication2", e.toString());
        }
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpResponse res = null;
        try {
            Log.d(gameData.getMe().getAccount(), gameData.getMe().getPassword());
            params.add(new BasicNameValuePair("account", gameData.getMe().getAccount()));
            params.add(new BasicNameValuePair("password", gameData.getMe().getPassword()));
            params.add(new BasicNameValuePair("x", gameData.getMe().getPos().getX() + ""));
            params.add(new BasicNameValuePair("y", gameData.getMe().getPos().getY() + ""));
        }catch (Exception e){
            Log.e("ERROR:Communication2", e.toString());
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            res = httpClient.execute(post);
            Log.d("Message2",
                    res.getStatusLine().getStatusCode() + "");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            JSONObject j = new JSONObject(bufferedReader.readLine());
            Log.d("Returncomment", j.toString());
            playerArray = j.getJSONArray("player");

            Log.d("game player", playerArray.toString());


            for(int i = 0; i < playerArray.length(); i++){
                Player player = new Player();
                JSONObject playeR = playerArray.getJSONObject(i);

                player.setAccount(playeR.getString("account"));
                int x = playeR.getInt("x");
                int y = playeR.getInt("y");
                player.setPos(new Position(x, y));

                gameData.resetPlayer(i+1, player);
            }
            ReceptionActivity.communicating = true;
        } catch (Exception e) {
            Log.e("ERROR:Communication", e.toString());
        }






        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        try{

            if(conect){
                Communication2 communication = new Communication2(gameData, mapView);
                communication.execute();
            }else{
                // ReceptionActivity.startTime = startTime;

            }
        }catch (Exception e){
            Log.e("ERROR:communication_last", e.toString());
        }


        mapView.invalidate();

    }
}
