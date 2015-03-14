package jp.ac.anan_nct.smaoni_elide.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Field;
import jp.ac.anan_nct.smaoni_elide.model.GameData;

public class FieldSetActivity extends GPS{


    boolean setting;

    Button button, start;
    TextView test;
    GameData gameData;

    ArrayList<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_set);
        locations = new ArrayList<Location>();
        setting = false;
        button = (Button)findViewById(R.id.button5);
        start = (Button)findViewById(R.id.set_start);
        test = (TextView)findViewById(R.id.test);

        gameData = SelectActivity.gameData;

        setAction();
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        if (setting) {
            locations.add(location);

            Field f = gameData.getField();

            if(location.getLatitude() > f.maxLat)       f.maxLat = location.getLatitude();
            else if(location.getLatitude() < f.minLat)  f.minLat = location.getLatitude();
            if(location.getLongitude() > f.maxLng)      f.maxLng = location.getLongitude();
            else if(location.getLongitude() < f.minLng) f.minLng = location.getLongitude();
        }
        test.setText("asfgsh"+setting);
    }

    void setAction(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //セットしたフィールドをデータベースに保存
                finish();
                startActivity(new Intent(FieldSetActivity.this, SelectActivity.class));
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting = !setting;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_field_set, menu);
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
