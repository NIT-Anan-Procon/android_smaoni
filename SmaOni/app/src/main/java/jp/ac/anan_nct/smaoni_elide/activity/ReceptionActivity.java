package jp.ac.anan_nct.smaoni_elide.activity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Communication;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.JSONRequest;
import jp.ac.anan_nct.smaoni_elide.model.JSONRequestEvent;
import jp.ac.anan_nct.smaoni_elide.model.MyCountDownTimer;
import jp.ac.anan_nct.smaoni_elide.model.MyURL;
import jp.ac.anan_nct.smaoni_elide.model.Player;
import jp.ac.anan_nct.smaoni_elide.model.Position;
import jp.ac.anan_nct.smaoni_elide.view.MapView;
import jp.ac.anan_nct.smaoni_elide.view.MemberView;

public class ReceptionActivity extends GPS{

    final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    final int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final int REQUEST_GALLERY = 0;

    MapView mapView;
    MemberView memberView;

    public static GameData gameData;

    JSONRequest jsonRequest;
    JSONRequestEvent je;

    HttpPost post;
    HttpClient httpClient;

    Toast toast;
    MyCountDownTimer myCountDownTimer;

    Communication communication;
    public static boolean last, communicating;
    public static Date startTime;
    MediaPlayer mediaPlayer;
    int i;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        mediaPlayer = MediaPlayer.create(this, R.raw.meka_ge_keihou03);
        toast = Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT);
        myCountDownTimer = new MyCountDownTimer(100000000, 1000) {
            @Override
            public void onFinish() {

            }

            @Override
            public void onTick(long millisUntilFinished) {
                if(Communication.start) {//Communication.startTime;
                    Log.d("Receiption", "startGame");
                    Intent intent = new Intent(ReceptionActivity.this, OniGokkoActivity.class);
                    Log.d("intent", intent.toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };


        myCountDownTimer.start();

        gameData = SelectActivity.gameData;
        gameData.setPlayerNum(gameData.getPlayerNum());
        colors = gameData.getColors();



        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_reception);

            je = new JSONRequestEvent() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Player p = new Player();
                    try {
                        p.setName(jsonObject.getString("name"));
                    } catch (JSONException e) {
                        Log.e("ERROR:ReceiptionActivity", e.toString());
                    }
                }
            };
            jsonRequest = new JSONRequest(je);

            mapView = (MapView)findViewById(R.id.mapRecieption);
            mapView.setTouchable(false);
            memberView = (MemberView)findViewById(R.id.member);



            Player p = new Player(gameData.getMe().name, new Position(-1, -1), Color.BLUE);
            gameData.resetPlayer(0, p);

            addJSONObject(p);


            mapView.invalidate();
            memberView.invalidate();

            httpClient = new DefaultHttpClient();
            Uri uri = Uri.parse(MyURL.PATH_RECEIPTION);
            post = new HttpPost(uri.toString());

            last = true;
            communicating = false;
            communication = new Communication(gameData, mapView, memberView);
            communication.execute();




        }catch(Exception e){
            Log.e("ERROR:ReceiptionActivity" , e.toString());
        }

        startTime = new Date();

    }



    void showToast(){
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), "フィールドから出ています！危ない！！！", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);

        if(gameData.getMe().setPos(location)){
            showToast();
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }


        Position p = gameData.getMe().getPos();
        gameData.getPlayer(0).setPos(p);

        mapView.invalidate();
    }



    void addJSONObject(Player p){
        JSONObject jsonObject = new JSONObject();
        Log.d("accountだよ" , p.getAccount());
        try{
            jsonObject.put("account", p.getAccount());
            jsonObject.put("name", p.name);
            Position pos = p.getPos();
            jsonObject.put("x", pos.getX());
            jsonObject.put("y", pos.getY());

        }catch (Exception e){
            Log.e("ERROR:RecieptionActivity", e.toString());
        }

        jsonCame(jsonObject);
    }

    Player jsonCame(JSONObject jsonObject){//JSONObject
        Player p = new Player();
        try {
            p.setName(jsonObject.getString("name"));
            int x = jsonObject.getInt("x");
            int y = jsonObject.getInt("y");
            p.setPos(new Position(x, y));
        }catch(Exception e){
            Log.e("ERROR:RecieptionActivity", e.toString());
        }

        p.setColor(colors[i]);
        gameData.resetPlayer(i, p);

        MemberView memberView = new MemberView(this, null);
        memberView.setInfo(i++, p);

        return p;
    }


    @Override
    protected void onPause() {
        super.onPause();
        communication.setLast(false);
        myCountDownTimer.cancel();
        finish();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {

        // 戻るボタンが押されたとき
        if(e.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            last = false;
        }
        return super.dispatchKeyEvent(e);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reception, menu);
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
