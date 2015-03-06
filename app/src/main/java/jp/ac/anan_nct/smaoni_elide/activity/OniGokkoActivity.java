package jp.ac.anan_nct.smaoni_elide.activity;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.view.MapView;

public class OniGokkoActivity extends GameActivity {


    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        i = 0;
        txLng = (TextView)findViewById(R.id.txLng);
        txLat = (TextView)findViewById(R.id.txLat);
        mapView = (MapView)findViewById(R.id.map1);
    }


    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oni_gokko, menu);
        return true;
    }
}
