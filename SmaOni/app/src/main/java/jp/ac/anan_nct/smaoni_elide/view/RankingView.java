package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.activity.SelectActivity;
import jp.ac.anan_nct.smaoni_elide.model.GameData;
import jp.ac.anan_nct.smaoni_elide.model.Player;

/**
 * Created by skriulle on 2015/03/10.
 */
public class RankingView extends View {

    Player[] players;
    GameData gameData;

    public RankingView(Context context){
        this(context, null);
    }

    public RankingView(Context context, AttributeSet attrs){
        super(context, attrs);
        gameData = SelectActivity.gameData;
        players = gameData.getPlayer();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        super.onDraw(canvas);

        Paint paint = new Paint();

        Rect rect = new Rect(0,0,900,100);

        canvas.drawRect(rect, paint);


    }
}
