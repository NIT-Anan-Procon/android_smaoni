package jp.ac.anan_nct.smaoni_elide.activity;

import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Communication2;
import jp.ac.anan_nct.smaoni_elide.model.MyCountDownTimer;
import jp.ac.anan_nct.smaoni_elide.view.MapView;
import jp.ac.anan_nct.smaoni_elide.view.RankingView;

public class OniGokkoActivity extends GameActivity {

    public static MapView mapView;
    public static RankingView rankingView;
    public static Button button;
    MyCountDownTimer myCountDownTimer, myCountDownTimer1;
    MediaPlayer mediaPlayer;

    TextView timerView;

    Communication2 communication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mediaPlayer = MediaPlayer.create(this, R.raw.meka_ge_keihou03);

        mapView = (MapView)findViewById(R.id.map1);
        rankingView = (RankingView)findViewById(R.id.gameRanking);
        button = (Button)findViewById(R.id.buttonRanking);
        communication = new Communication2(gameData, mapView, rankingView);
        communication.execute();


        for(int i = 0; i < gameData.getPlayerNum(); i++){
            gameData.getPlayer(i).setOni(false);
            gameData.getPlayer(i).setInvisiblity(false);
        }
        gameData.getPlayer(0).setOni(true);

        setAction();

        timerView = (TextView)findViewById(R.id.timerView);

        myCountDownTimer = new MyCountDownTimer(1000/*5 * 60 * 1000*/, 1000) {
            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "ゲーム終了", Toast.LENGTH_SHORT).show();
                Communication2.conect = false;
                myCountDownTimer1.start();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                String time = (((millisUntilFinished+1)/1000/60 >= 10) ? "" : "0" )+Long.toString((millisUntilFinished+1)/1000/60) + ":";
                time += (((millisUntilFinished+1)/1000%60 >= 10) ? "" : "0" )+Long.toString((millisUntilFinished+1)/1000%60);
                timerView.setText(time);
            }
        };
        myCountDownTimer1 = new MyCountDownTimer(1000, 500) {
            @Override
            public void onFinish() {
                startActivity(new Intent(OniGokkoActivity.this, ResultActivity.class));
            }
            @Override
            public void onTick(long millisUntilFinished) {
            }
        };

        myCountDownTimer.start();
    }

    void setAction(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankingView.invalidate();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        Communication2.conect = false;
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);

        if(gameData.getPlayer(0).setPos(location)){
            showToast();
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
        }

        mapView.invalidate();
        rankingView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oni_gokko, menu);
        return true;
    }
}
