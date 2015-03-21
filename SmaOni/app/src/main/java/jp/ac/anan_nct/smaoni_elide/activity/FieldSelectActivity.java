package jp.ac.anan_nct.smaoni_elide.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import jp.ac.anan_nct.smaoni_elide.R;
import jp.ac.anan_nct.smaoni_elide.model.Field;

public class FieldSelectActivity extends ActionBarActivity {

    TextView[] texts;
    Field[] fields;
    Button back;

    private final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final int MP = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        texts = new TextView[10];
        fields = new Field[10];

        for(int i = 0; i < texts.length; i++) {
            texts[i] = new TextView(this);
            texts[i].setText("aaaa" + i);
            texts[i].setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(texts[i]);


            fields[i] = new Field(11.1*i, 1.1*i, 11.1*i, 1.1*i);
        }

        fields[5] = new Field(180, 0, 40, 20);
        texts[5].setText("超広い");
        fields[6] = new Field(134.669498, 134.666591, 33.899804, 33.896349);
        texts[6].setText("阿南高専");
        fields[7] = new Field(134.669498, 134.666591, 33.898059, 33.896349);
        texts[7].setText("阿南高専(小)");
        texts[0].setText("aaaa0(不正なフィールド)");

        back = new Button(this);
        back.setText("戻る");
        back.setLayoutParams(new ViewGroup.LayoutParams(WC,WC));
        linearLayout.addView(back);

        setAction();
    }



    void setAction(){
        for(int i = 0; i < texts.length; i++){
            final int j = i;
            texts[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SelectActivity.gameData.setField(fields[j]);
                    SelectActivity.hasSet = true;
                    for(int i = 0; i < texts.length; i++){
                        texts[i].setBackgroundColor(Color.WHITE);
                    }
                    texts[j].setBackgroundColor(Color.parseColor("#ffaaaa"));
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_field_select, menu);
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
