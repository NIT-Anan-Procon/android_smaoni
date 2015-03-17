package jp.ac.anan_nct.smaoni_elide.activity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import java.util.Random;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.SubThread;

public class GameActivity extends GPS {


    public static GameData gameData;

    SubThread[] subThread;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        toast = Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT);

        gameData = ReceptionActivity.gameData;

        setContentView(R.layout.activity_game);

        subThread = new SubThread[gameData.getPlayerNum()-1];
        for(int i = 0, p = gameData.getPlayerNum()-1;i < p; i++) {
            Random r = new Random();
            subThread[i] = new SubThread(r.nextInt(1500)+500, i+1);
            subThread[i].start();
        }

        Log.d("player", "" + gameData.getPlayerNum());
    }

    void showToast(){
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), "フィールドから出ています！危ない！！！", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        for(SubThread st : subThread){
            st.stopRunning();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);


        Log.d("GPS","working");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }
}
