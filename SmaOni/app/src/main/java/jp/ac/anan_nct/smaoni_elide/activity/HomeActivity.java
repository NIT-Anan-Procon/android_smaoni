package jp.ac.anan_nct.smaoni_elide.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Player;


public class HomeActivity extends ActionBarActivity {

    Button bt1, bt2;
    public static Player me;
    public static boolean hasSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout1);
        layout.setBackgroundResource(R.drawable.aaac);

        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);
        me = new Player();
        hasSet = false;

        setAction();
    }




    private void setAction(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SelectActivity.class));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FieldSetActivity.class));
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            showDialog(0);
            return true;
        }
        return false;
    }

    @Override
    public Dialog onCreateDialog(int id) {
        switch (id) {

            case 0:
                return new AlertDialog.Builder(HomeActivity.this)
                        .setMessage("現実に向き合いますか?")
                        .setCancelable(false)
                        .setPositiveButton("現実に戻る", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HomeActivity.this.finish();
                            }
                        })
                        .setNegativeButton("現実逃避を続ける", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .create();
        }
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "ログイン");
        menu.add(Menu.NONE, 1, Menu.NONE, "運営へのメッセージ");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // addしたときのIDで識別
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(HomeActivity.this, AccountSelectActivity.class));
                return true;

            case 1:
                startActivity(new Intent(HomeActivity.this, CommentActivity.class));
                return true;

        }
        return false;
    }
}
