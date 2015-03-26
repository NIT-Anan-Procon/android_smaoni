package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.activity.GameActivity;
import jp.ac.anan_nct.smaoni_elide.model.GameData;

/**
 * Created by skriulle on 2015/03/22.
 */
public class ResultView extends View {

    GameData gameData;
    int[][] ranking;


    public ResultView(Context context){
        super(context);
    }
    public  ResultView(Context context, AttributeSet attrs){
        super(context, attrs);

        gameData = GameActivity.gameData;
        try {
            ranking = RankingView.ranking;
        }catch (Exception e){
            Log.e("ERROR:ResultView", e.toString());
        }


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = new Rect(0,0,1000,400);
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);

        paint.setTextSize(140f);;
        canvas.drawText("1位", 28, 295, paint);
        String name = gameData.getPlayer(ranking[0][1]).getName();
        paint.setTextSize((name.length() >= 4)?180f:220f);
        Log.d(name.length()+"","長さ");
        canvas.drawText(name, 265, 300, paint);

        rect = new Rect(0, 400, 1000, 700);
        for(int i = 1; i < gameData.getPlayerNum(); i++){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.LTGRAY);
            canvas.drawRect(rect, paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawRect(rect, paint);

            paint.setTextSize(90f);
            canvas.drawText((i+1) + "位", 45, 300*i+290, paint);

            paint.setTextSize(150f);
            String name1 = gameData.getPlayer(ranking[i][1]).getName();
            canvas.drawText(name1, 300, 300*i + 300, paint);

            rect.offset(0,300);
        }


    }
}
