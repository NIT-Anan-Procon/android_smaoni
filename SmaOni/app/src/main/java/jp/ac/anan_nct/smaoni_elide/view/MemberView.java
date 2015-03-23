package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.model.Player;

/**
 * Created by skriulle on 2015/03/02.
 */
public class MemberView extends View {

    Player player;
    int num;

    public MemberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        player = new Player();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(900,150);
    }

    public void setInfo(int i, Player player){
        num = i;
        this.player = player;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#cccccc"));

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        Rect rect = new Rect(30,30, 160, 160);
        canvas.drawRect(rect, paint);

        paint.setColor(Color.GREEN);
        paint.setTextSize(100);
        canvas.drawText(Integer.toString(num+1), 60, 130, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(130);
        canvas.drawText(player.getName(), 200, 130, paint);


        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,900,150, paint);

    }
}
