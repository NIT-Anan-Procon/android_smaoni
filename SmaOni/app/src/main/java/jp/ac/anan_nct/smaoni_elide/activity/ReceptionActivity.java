package jp.ac.anan_nct.smaoni_elide.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Random;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Colors;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.Player;
import jp.ac.anan_nct.smaoni_elide.model.Position;
import jp.ac.anan_nct.smaoni_elide.model.Status;
import jp.ac.anan_nct.smaoni_elide.view.MapView;
import jp.ac.anan_nct.smaoni_elide.view.MemberView;

public class ReceptionActivity extends ActionBarActivity {

    LinearLayout linearLayout;
    LinkedList<MemberView> memberViews;
    final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    final int MP = LinearLayout.LayoutParams.MATCH_PARENT;

    Button gotoGame, addMember;
    MapView mapView;

    public static GameData gameData;

    JSONArray jsonArray;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

        mapView = (MapView)findViewById(R.id.mapRecieption);
        mapView.setTouchable(false);

        gameData = SelectActivity.gameData;
        gameData.setPlayerNum(gameData.getPlayerNum());
        linearLayout = (LinearLayout) findViewById(R.id.linear1);
        memberViews = new LinkedList<MemberView>();

        Player p = new Player(3, "aさん", new Position(0, 0), Status.RUNNER, Color.BLUE);
        //本来なら自分のIDと名前
/**
        MemberView m1 = new MemberView(this, null);
        gameData.resetPlayer(0, p);
        m1.setInfo(0, gameData.getPlayer(0));
        memberViews.add(m1);
        linearLayout.addView(m1, new LinearLayout.LayoutParams(WC, WC));
        i = 1;
*/
        i = 0;
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", p.getName());
            jsonObject.put("id",p.getId());

            Log.d("json", jsonObject.toString());
            /////////////送る

        }catch (JSONException e){}

        gotoGame = (Button) findViewById(R.id.button6);
        addMember = (Button) findViewById(R.id.button7);

        gotoGame.setEnabled(false);

        setAction();

        jsonCame(jsonObject);

        mapView.invalidate();

    }

    void jsonCame(JSONObject jsonObject){//JSONObject
        Player p = new Player();
        try {
            p.setId(jsonObject.getInt("id"));
            p.setName(jsonObject.getString("name"));
        }catch(JSONException e) {
        }catch(Exception e){}

        Log.d("player", p.getName() + " " + p.getId());
        p.setColor(Colors.colors[i]);
        gameData.resetPlayer(i, p);

        MemberView memberView = new MemberView(this, null);
        memberView.setInfo(i++, p);
        memberViews.add(memberView);
        linearLayout.addView(memberView, new LinearLayout.LayoutParams(WC, WC));
    }

    void jsonArrayCame(JSONArray jsonArray){  //JSONArray
        this.i = 0;
        try{
            for(int i = 0; i < jsonArray.length(); i++) {   //送られてくるJSONArrayの長さは指定以上にならない
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonCame(jsonObject);
            }
        }catch (JSONException e){}

        mapView.invalidate();
    }


    void setAction(){
        for(int i = 0; i < memberViews.size(); i++){
            final int j = i;
         /*   memberViews[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });*/
        }
        gotoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("おちょ" + i, "" + memberViews.size());
                if(i != gameData.getPlayerNum()){
                    /*Player[] players = new Player[i];
                    for(int j = 0; j < i; j++){
                        players[j] = gameData.getPlayer(j);
                    }
                    gameData.setPlayerNum(i);*/
                }else {
                    startActivity(new Intent(ReceptionActivity.this, OniGokkoActivity.class));
                }
            }
        });
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i < gameData.getPlayerNum()) {
                    int index = memberViews.size();
                    Player p = new Player();
                    Random r = new Random();
                    int a = r.nextInt(gameData.getGridNum()), b = r.nextInt(gameData.getGridNum());
                    p.setPos(new Position(a, b));
                    p.setColor(Colors.colors[index]);
                    gameData.resetPlayer(i, p);
                    MemberView m1 = new MemberView(ReceptionActivity.this, null);
                    m1.setInfo(i, p);
                    memberViews.add(m1);
                    linearLayout.addView(m1);
                    i++;
                    mapView.invalidate();
                    if(i == gameData.getPlayerNum()){
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
