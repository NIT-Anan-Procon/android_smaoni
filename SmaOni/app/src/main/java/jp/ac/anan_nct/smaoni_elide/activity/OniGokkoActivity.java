package jp.ac.anan_nct.smaoni_elide.activity;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Status;
import jp.ac.anan_nct.smaoni_elide.view.MapView;

public class OniGokkoActivity extends GameActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txLng = (TextView)findViewById(R.id.txLng);
        txLat = (TextView)findViewById(R.id.txLat);
        mapView = (MapView)findViewById(R.id.map1);

        for(int i = 0; i < gameData.getPlayerNum(); i++){
            gameData.getPlayer(i).setStatus(Status.RUNNER);
        }
        gameData.getPlayer(0).setStatus(Status.ONI);
    }


    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);


        //おくる


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oni_gokko, menu);
        return true;
    }
}
