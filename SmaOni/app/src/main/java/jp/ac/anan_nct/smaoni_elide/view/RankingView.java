package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.activity.ReceptionActivity;
import jp.ac.anan_nct.smaoni_elide.model.Colors;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.Player;

/**
 * Created by skriulle on 2015/03/10.
 */
public class RankingView extends View {

    Player[] players;
    GameData gameData;
    static int[][] ranking;
    int[] colors;

    public RankingView(Context context){
        this(context, null);
    }

    public RankingView(Context context, AttributeSet attrs){
        super(context, attrs);
        gameData = ReceptionActivity.gameData;
        players = gameData.getPlayer();
        Log.d("ranking name ", gameData.getPlayer(0).getName());

        ranking = new int[players.length][2];
        for(int i = 0; i < players.length; i++){
            ranking[i][0] = players[i].getScore();
            ranking[i][1] = i;
        }

        colors = Colors.colors;
    }

    @Override
    public void invalidate(){
        sortRanking();
        super.invalidate();
    }

    void sortRanking(){
        for(int i = 0; i < players.length; i++){
            ranking[i][0] = players[i].getScore();  //score
            ranking[i][1] = i;                      //index
        }
        for(int i =0; i < players.length; i++){
            for(int j = i + 1; j < players.length; j++){
                if(ranking[i][0] < ranking[j][0]){
                    int k = ranking[i][0];
                    ranking[i][0] = ranking[j][0];
                    ranking[j][0] = k;


                    k = ranking[i][1];
                    ranking[i][1] = ranking[j][1];
                    ranking[j][1] = k;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        super.onDraw(canvas);

        Paint paint = new Paint();

        Rect rect = new Rect(0,0,500,70);

        for(int i = 0; i < gameData.getPlayerNum(); i++){
            Log.d("ranking", ""+ranking[i][1]);
        }
        for(int i = 0; i < gameData.getPlayerNum(); i++) {
            Player p = players[ranking[i][1]];
            float dy = (float)(i*70+57);
            paint.setColor(p.getColor());
            paint.setColor(colors[ranking[i][1]]);
            paint.setTextSize(60f);
            canvas.drawRect(rect, paint);
            paint.setColor(Color.BLACK);
            paint.setColor((ranking[i][1] == 0) ? Color.WHITE : Color.BLACK);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(p.getName(), 20, dy, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(p.getScore()+"", 450, dy, paint);
            rect.offset(0, 70);
        }
    }
}
