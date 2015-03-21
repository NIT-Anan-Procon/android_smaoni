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

import java.util.ArrayList;

/**
 * Created by skriulle on 2015/03/18.
 */
public class AllDelete extends AsyncTask{

    public AllDelete() {
        super();
    }

    @Override
    protected Object doInBackground(Object[] pams) {
        HttpClient httpClient = new DefaultHttpClient();
        Uri uri = Uri.parse(MyURL.PATH_QUIT);
        HttpPost post = new HttpPost(uri.toString());

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse res = httpClient.execute(post);
            Log.d("Message", res.getStatusLine().getStatusCode() + "");
        }catch(Exception e){
            Log.e("ERROR:AllDelete", e.toString());
        }
        return null;
    }
}
