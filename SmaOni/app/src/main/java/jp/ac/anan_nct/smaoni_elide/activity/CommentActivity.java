package jp.ac.anan_nct.smaoni_elide.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import jp.ac.anan_nct.smaoni_elide.R;

public class CommentActivity extends ActionBarActivity {

    EditText text1, text2;
    Button submit;
    HttpPost post;
    HttpClient httpClient;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        submit = (Button) findViewById(R.id.submit);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);


        httpClient = new DefaultHttpClient();
        Uri uri = Uri.parse("http://219.94.232.92:3000/api/post_comment");
        post = new HttpPost(uri.toString());


        setAction();
    }

    void startCommunication(){

        final Button submit = this.submit;
        final Handler handler = new Handler() {
            @Override
            public void close() {
            }
            @Override
            public void flush() {
            }
            @Override
            public void publish(LogRecord record) {
            }
        };

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] p) {
                ArrayList<NameValuePair> params = new ArrayList <NameValuePair>();
                params.add( new BasicNameValuePair("comment", text2.getText().toString()));
                params.add( new BasicNameValuePair("account", "kasssshi"));
                params.add( new BasicNameValuePair("password", "12345"));

                HttpResponse res = null;

                try {
                    post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
                    res = httpClient.execute(post);
                    Log.d(res.getStatusLine().getStatusCode()+"", "");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
                    Log.d(bufferedReader.readLine(),"asfgsfd");

                } catch (Exception e) {
                    Log.e("ERROR:CommentActivity", e.toString());
                }



                return true;
            }
            @Override
            protected void onPostExecute(Object result) {
                submit.setEnabled(true);
            }
        };
        asyncTask.execute();
    }

    void setAction(){
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(false);
                startCommunication();

            }
        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
