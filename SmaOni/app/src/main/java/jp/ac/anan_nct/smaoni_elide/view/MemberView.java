package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.activity.SelectActivity;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.Player;

/**
 * Created by skriulle on 2015/03/02.
 */
public class MemberView extends View {

    Player[] players;
    GameData gameData;


    public MemberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameData = SelectActivity.gameData;
        players = gameData.getPlayer();
    }


    public void setInfo(int i, Player player){
        players[i] = player;
    }

    public void invalidate() {
        super.invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Rect rect = new Rect(0, 0, 1000, 150);
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        Rect minirect = new Rect(30, 15, 150, 135);
        Paint miniPaint = new Paint();
        miniPaint.setColor(Color.RED);
        Paint numPaint = new Paint();
        numPaint.setColor(Color.GREEN);
        numPaint.setTextSize(100f);
        Paint namePaint = new Paint();
        namePaint.setColor(Color.BLACK);
        namePaint.setTextSize(130f);
        Paint stroke = new Paint();
        stroke.setStyle(Paint.Style.STROKE);



        canvas.drawRect(rect, paint);
        canvas.drawRect(rect, stroke);

        int i = 0;
        do{
            canvas.drawRect(rect, paint);
            canvas.drawRect(rect, stroke);

            canvas.drawRect(minirect, miniPaint);

            canvas.drawText(Integer.toString(i + 1), 60 , 110 + 150*i, numPaint);

            canvas.drawText(players[i].getName(), 200, 130 + 150*i, namePaint);
            i++;

            rect.offset(0,150);
            minirect.offset(0,150);

            if(i >= gameData.getPlayerNum())    break;
            if(players[i] == null)              break;
        }while (true);

    }
}
