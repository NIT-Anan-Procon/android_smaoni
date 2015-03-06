package jp.ac.anan_nct.smaoni_elide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Field;
import jp.ac.anan_nct.smaoni_elide.model.GameData;

public class SelectActivity extends ActionBarActivity {

    public static GameData gameData;
    static boolean hasSet;

    Button btn1, btn2, btn3, btn4, btn5, btn6;
    TextView tx1, tx2, tx3;
    Button button3, button4, button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        gameData = new GameData();

        btn1 = (Button)findViewById(R.id.button11);
        btn2 = (Button)findViewById(R.id.button12);
        btn3 = (Button)findViewById(R.id.button21);
        btn4 = (Button)findViewById(R.id.button22);
        btn5 = (Button)findViewById(R.id.button31);
        btn6 = (Button)findViewById(R.id.button32);

        tx1 = (TextView)findViewById(R.id.text1);
        tx2 = (TextView)findViewById(R.id.text2);
        tx3 = (TextView)findViewById(R.id.text3);

        indicate();

        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button = (Button)findViewById(R.id.button);

//////////////////////////////////////////////////////for TEST
        gameData.setField(new Field(10,9,8,7));
//////////////////////////////////////////////////////for TEST

        setAction();
    }


    void indicate(){
        tx1.setText("" + gameData.getPlayerNum());
        tx2.setText("" + gameData.getOniNum());
        tx3.setText("" + gameData.getGridNum());
    }

    void setAction(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if((a = gameData.getPlayerNum()) > 2) gameData.setPlayerNum(a-1);
                if(gameData.getOniNum() >= (a = gameData.getPlayerNum()))  gameData.setOniNum(a-1);
                indicate();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if ((a = gameData.getPlayerNum()) < 8) gameData.setPlayerNum(a + 1);
                indicate();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if((a = gameData.getOniNum()) > 1) gameData.setOniNum(a-1);
                indicate();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if((a = gameData.getOniNum()) < gameData.getPlayerNum()-1) gameData.setOniNum(a+1);
                indicate();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if((a = gameData.getGridNum()) > 2) gameData.setGridNum(a-1);
                indicate();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if((a = gameData.getGridNum()) < 15) gameData.setGridNum(a+1);
                indicate();
            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, FieldSetActivity.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, FieldSelectActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, ReceptionActivity.class));
            }
        });



    }



















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
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
