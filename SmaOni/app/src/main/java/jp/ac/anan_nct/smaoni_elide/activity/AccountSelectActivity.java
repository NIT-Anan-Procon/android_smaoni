package jp.ac.anan_nct.smaoni_elide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Player;

public class AccountSelectActivity extends ActionBarActivity {

    Button A, B, C, D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_select);

        A = (Button)findViewById(R.id.buttonA);
        B = (Button)findViewById(R.id.buttonB);
        C = (Button)findViewById(R.id.buttonC);
        D = (Button)findViewById(R.id.buttonD);

        setAction();
    }

    void setAction(){
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMe("yahooLOVE@gmail.com", "kashifuku", "かし"); //skriulle5@gmail.com
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMe("HackU_Brilliant@st.anan-nct.ac.jp", "morikohki", "もりりん"); //1122320@st.anan-nct.ac.jp
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMe("happy_turn@yahoo.co.jp", "happyturn", "ひらっぴ");
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMe("makko_kujira@docomo.ne.jp", "shironagasu", "GUCCI");
            }
        });

    }


    void setMe(String account, String password, String name){
        Player me = HomeActivity.me;
        me.setAccount(account);
        me.setPassword(password);
        me.setName(name);
        HomeActivity.hasSet = true;
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_select, menu);
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
