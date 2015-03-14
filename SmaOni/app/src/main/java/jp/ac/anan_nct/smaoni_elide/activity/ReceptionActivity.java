package jp.ac.anan_nct.smaoni_elide.activity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Colors;
import jp.ac.anan_nct.smaoni_elide.model.Communication;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.JSONRequest;
import jp.ac.anan_nct.smaoni_elide.model.JSONRequestEvent;
import jp.ac.anan_nct.smaoni_elide.model.LoginJsonBuilder;
import jp.ac.anan_nct.smaoni_elide.model.MyURL;
import jp.ac.anan_nct.smaoni_elide.model.Player;
import jp.ac.anan_nct.smaoni_elide.model.Position;
import jp.ac.anan_nct.smaoni_elide.model.Status;
import jp.ac.anan_nct.smaoni_elide.view.MapView;
import jp.ac.anan_nct.smaoni_elide.view.MemberView;

public class ReceptionActivity extends GPS{

    LinearLayout linearLayout;
    LinkedList<MemberView> memberViews;
    final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    final int MP = LinearLayout.LayoutParams.MATCH_PARENT;

    Button gotoGame, addMember;
    MapView mapView;

    public static GameData gameData;

    JSONArray jsonArray;

    JSONRequest jsonRequest;
    JSONRequestEvent je;

    HttpPost post;
    HttpClient httpClient;

    Communication communication;
    public static boolean last;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

        je = new JSONRequestEvent() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Player p = new Player();
                try {
                    p.setName(jsonObject.getString("name"));
                    Log.d("name", p.getName());
                }catch (JSONException e){}
            }
        };
        jsonRequest = new JSONRequest(je);

        mapView = (MapView)findViewById(R.id.mapRecieption);
        mapView.setTouchable(false);

        gameData = SelectActivity.gameData;
        gameData.setPlayerNum(gameData.getPlayerNum());
        linearLayout = (LinearLayout) findViewById(R.id.linear1);
        memberViews = new LinkedList<MemberView>();

        jsonArray = new JSONArray();

        Player p = new Player(3, "aさん", new Position(0, 0), Status.RUNNER, Color.BLUE);
        //本来なら自分のIDと名前
/*
        MemberView m1 = new MemberView(this, null);
        gameData.resetPlayer(0, p);
        m1.setInfo(0, gameData.getPlayer(0));
        memberViews.add(m1);
        linearLayout.addView(m1, new LinearLayout.LayoutParams(WC, WC));
        i = 1;
*/
        addJSONObject(p);

        gotoGame = (Button) findViewById(R.id.button6);
        addMember = (Button) findViewById(R.id.button7);

        gotoGame.setEnabled(false);

        setAction();

        jsonArrayCame(jsonArray);

        mapView.invalidate();

        httpClient = new DefaultHttpClient();
        Uri uri = Uri.parse(MyURL.PATH_RECEIPTION);
        post = new HttpPost(uri.toString());

        last = true;
        communication = new Communication(gameData, mapView);
        communication.execute();
    }


    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);

        gameData.getMe().setPos(location);

        Position p = gameData.getMe().getPos();
        Log.d("working", "" + p.getX() + " " + p.getY());
        gameData.getPlayer(0).setPos(p);

        mapView.invalidate();
    }





    void addJSONObject(Player p){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("account", p.getName());
            jsonObject.put("id",p.getId());
            Position pos = p.getPos();
            jsonObject.put("x", pos.getX());
            jsonObject.put("y", pos.getY());


            Log.d("json", jsonObject.toString());

            jsonArray.put(jsonObject);
        }catch (Exception e){
            Log.e("ERROR:RecieptionActivity", e.toString());
        }
    }

    Player jsonCame(JSONObject jsonObject){//JSONObject
        Player p = new Player();
        try {
            p.setId(jsonObject.getInt("id"));
            p.setName(jsonObject.getString("account"));
            int x = jsonObject.getInt("x");
            int y = jsonObject.getInt("y");
            Log.d("x "+ x, "y " + y);
            p.setPos(new Position(x, y));
        }catch(JSONException e) {
        }catch(Exception e){
            Log.e("ERROR:RecieptionActivity", e.toString());
        }

        Log.d("player", p.getName() + " " + p.getId());
        p.setColor(Colors.colors[i]);
        gameData.resetPlayer(i, p);

        MemberView memberView = new MemberView(this, null);
        memberView.setInfo(i++, p);
        memberViews.add(memberView);
        linearLayout.addView(memberView, new LinearLayout.LayoutParams(WC, WC));

        return p;
    }

    void jsonArrayCame(JSONArray jsonArray){  //JSONArray
        //配列をclear
        gameData.setPlayerNum(gameData.getPlayerNum());
        memberViews.clear();
        linearLayout.removeAllViews();

        this.i = 0;
        try{
            for(int i = 0; i < jsonArray.length(); i++) {   //送られてくるJSONArrayの長さは指定以上にならない
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Player p = jsonCame(jsonObject);
                Log.d("眠すぎ", "わろた");
                gameData.resetPlayer(i, p);
            }
        }catch (Exception e){
            Log.e("ERROR:RecieptionActivity", e.toString());
        }

    }


    void setAction(){
        gotoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.setStart(true);
                last = false;
                jsonRequest.send(new LoginJsonBuilder());
                startActivity(new Intent(ReceptionActivity.this, OniGokkoActivity.class));
            }
        });
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jsonArray.length() < gameData.getPlayerNum()) {
                    Player p = new Player();
                    int a = (int)(Math.random()*gameData.getGridNum());
                    int b = (int)(Math.random()*gameData.getGridNum());
                    p.setPos(new Position(a,b));

                    addJSONObject(p);
                    jsonArrayCame(jsonArray);
                    if(jsonArray.length() == gameData.getPlayerNum()){
                        gotoGame.setEnabled(true);
                    }
                }
            }
        });
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
