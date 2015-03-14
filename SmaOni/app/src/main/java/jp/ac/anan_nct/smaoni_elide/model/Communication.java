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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jp.ac.anan_nct.smaoni_elide.activity.ReceptionActivity;
import jp.ac.anan_nct.smaoni_elide.view.MapView;

/**
 * Created by skriulle on 2015/03/14.
 */
public class Communication extends AsyncTask {


    HttpPost post;
    HttpClient httpClient;

    GameData gameData;
    private boolean start;


    MapView mapView;


    public Communication(GameData gameData, MapView mapView) {
        super();
        httpClient = new DefaultHttpClient();
        Uri uri = Uri.parse(MyURL.PATH_RECEIPTION);
        post = new HttpPost(uri.toString());
        this.gameData = gameData;
        this.mapView = mapView;

    }


    @Override
    protected Object doInBackground(Object[] p) {


        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpResponse res = null;
        try {
           // params.add(new BasicNameValuePair("account", "skriulle5@gmail.com"));
           // params.add(new BasicNameValuePair("password", "kashifuku"));
            params.add(new BasicNameValuePair("account", "1122320@st.anan-nct.ac.jp"));
            params.add(new BasicNameValuePair("password", "morikohki"));
            params.add(new BasicNameValuePair("x", gameData.getMe().getPos().getX() + ""));
            params.add(new BasicNameValuePair("y", gameData.getMe().getPos().getY() + ""));
        }catch (Exception e){
            Log.e("ERROR:Communication", e.toString());
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            res = httpClient.execute(post);
            Log.d(res.getStatusLine().getStatusCode() + "", "");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            Log.d(bufferedReader.readLine(),"o");

        } catch (Exception e) {
            Log.e("ERROR:Communication", e.toString());
        }


        try{
            Thread.sleep(5000);
        }catch (Exception e){
            Log.e("ERROR:Communication", e.toString());
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        mapView.invalidate();

        try {
            if(ReceptionActivity.last) {
                Communication communication = new Communication(gameData, mapView);
                communication.execute();
            }
        }catch (Exception e){
            Log.e("ERROR:communication_last", e.toString());
        }

    }


    public void setStart(boolean start) {
        this.start = start;
    }
}
