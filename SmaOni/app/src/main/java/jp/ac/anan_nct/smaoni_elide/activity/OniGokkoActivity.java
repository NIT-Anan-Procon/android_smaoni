package jp.ac.anan_nct.smaoni_elide.activity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Status;
import jp.ac.anan_nct.smaoni_elide.view.MapView;
import jp.ac.anan_nct.smaoni_elide.view.RankingView;

public class OniGokkoActivity extends GameActivity {

    public static MapView mapView;
    //   public static TextView txLng, txLat;
    public static RankingView rankingView;
    public static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    //    txLng = (TextView)findViewById(R.id.txLng);
    //    txLat = (TextView)findViewById(R.id.txLat);
        mapView = (MapView)findViewById(R.id.map1);
        rankingView = (RankingView)findViewById(R.id.gameRanking);
        button = (Button)findViewById(R.id.buttonRanking);

        for(int i = 0; i < gameData.getPlayerNum(); i++){
            gameData.getPlayer(i).setStatus(Status.RUNNER);
        }
        gameData.getPlayer(0).setStatus(Status.ONI);

        setAction();
    }

    void setAction(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankingView.invalidate();
                Log.d("おした","");
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);


        mapView.invalidate();
        rankingView.invalidate();
        //おくる


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oni_gokko, menu);
        return true;
    }
}
