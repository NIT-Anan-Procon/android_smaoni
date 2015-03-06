package jp.ac.anan_nct.smaoni_elide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import jp.ac.anan_nct.smaoni_elide.activity.GameActivity;
import jp.ac.anan_nct.smaoni_elide.model.Condition;
import jp.ac.anan_nct.smaoni_elide.model.Position;


//import android.util.Log;

/**
 * Created by skriulle on 2015/03/02.
 */

public class MapView extends View {


    Condition[][] conditions;
    private int[][][] colorsss;

    int width;
    boolean isTouched = false;

    int x = -1, y = -1;
    float rawX, rawY;

    MotionEvent me;
    int num;
    int w;

    int playerNum;

    int[] colors;
    Paint[] paints;

    public MapView(Context context){
        this(context, null);
    }

    public MapView(Context context, AttributeSet attrs){
        super(context, attrs);

        num = GameActivity.gameData.getGridNum();
        conditions = new Condition[num][num];

        colors = GameActivity.gameData.getColors();

        playerNum = GameActivity.gameData.getPlayerNum();

        for(int j = 0; j < num; j++){
            for(int i = 0; i < num; i++){
                conditions[i][j] = new Condition();
            }
        }
        colorsss = new int[num][num][playerNum+1];

        for(int i = 0; i < playerNum; i++) {
            colorsss[0][0][i] = colors[i];
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent e){
////////////////////////////////////////////////////////////////for TEST
        me = e;
        w = getWidth();
        x = (int)(((e.getRawX()-50)/(getWidth()-100))*num); //タッチしたx座標
        y = (int)((e.getRawY()-330)/(getWidth()-100)*num);  //タッチしたy座標
        GameActivity.gameData.getPlayer(0).setPos(new Position(x, y));
        if(e.getRawY()<50)y=-1;
        rawX = e.getRawX();
        rawY = e.getRawY();
        if(e.getAction() == MotionEvent.ACTION_DOWN) isTouched = true;
        else if(e.getAction() == MotionEvent.ACTION_UP) isTouched = false;
        invalidate();                                         ////タッチするたび更新
        return true;
////////////////////////////////////////////////////////////////for TEST
    }

    @Override
    public void invalidate(){

        colorsss = GameActivity.gameData.getColorsss();

        super.invalidate();
    }

    private Rect[] makeCell(int[] colors, Rect rect0, int num){
        if(colors[0] == GameActivity.gameData.getColors()[0] && colors[1] != 0){
            return makeCell2(colors, rect0, num);
        }else {
            Rect[] rects = new Rect[num];
            paints = new Paint[num];
            int num_under = (num + 1) / 2;
            int bottom = rect0.bottom;
            boolean over3 = (num > 3);
            if (over3) {
                num = num / 2;
                bottom -= rect0.height() / 2;
            }
            for (int i = 0; i < num; i++) {
                Rect rect = new Rect(rect0.left + rect0.width() / num * i, rect0.top, rect0.left + rect0.width() / num * (i + 1), bottom);
                rects[i] = rect;
                paints[i] = new Paint();
                paints[i].setColor(colors[i]);
            }
            if (over3) {
                for (int i = num; i < num + num_under; i++) {
                    Rect rect = new Rect(rect0.left + rect0.width() / num_under * (i - num), bottom, rect0.left + rect0.width() / num_under * (i + 1 - num), rect0.bottom);
                    rects[i] = rect;
                    paints[i] = new Paint();
                    paints[i].setColor(colors[i]);
                }
            }
            return rects;
        }
    }


    private Rect[] makeCell2(int[] colors, Rect rect0, int num) {
        Rect[] rects = new Rect[num];
        paints = new Paint[num];
        num-=1;
        int num_under= 0;
        int bottom = rect0.bottom;
        boolean over3 = (num > 3);
        if(over3){
            num = num/2;
            num_under= (num+1) / 2;
            bottom -= rect0.height()/2;
        }
        for(int i = 0; i < num; i++){
            Rect rect = new Rect(rect0.left+rect0.width()/num*(i), rect0.top, rect0.left+rect0.width()/num*(i+1), bottom);
            rects[i] = rect;
            paints[i] = new Paint();
            paints[i].setColor(colors[i+1]);
        }
        if(over3){
            for(int i = num; i < num+num_under; i++){
                Rect rect = new Rect(rect0.left+rect0.width()/num_under*(i-num), bottom, rect0.left+rect0.width()/num_under*(i+1-num), rect0.bottom);
                rects[i] = rect;
                paints[i] = new Paint();
                paints[i].setColor(colors[i+1]);
            }
        }
        rects[num+num_under] = new Rect(rect0.left+rect0.width()/5, rect0.top+rect0.height()/5, rect0.right-rect0.width()/5, rect0.bottom-rect0.height()/5);
        paints[num+num_under] = new Paint();
        paints[num+num_under].setColor(colors[0]);
        return rects;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();

        width = canvas.getWidth()-100;

        Rect rect = new Rect(55,55,width/num+45,width/num+45);
        canvas.drawColor(Color.parseColor("#cccccc"));
        paint.setColor(Color.parseColor("#ffffff"));


        for(int j = 0; j < num; j++){           //y担当
            for(int i = 0; i < num; i++) {      //x担当
                int k;                          //セルにいる人数
                for(k = 0; colorsss[j][i][k] != 0; k++);
                if(k == 0){
                    canvas.drawRect(rect, paint);
                }else{
                    int m = 0;
                    Rect[] rects = makeCell(colorsss[j][i], rect, k);
                    for(Rect r : rects){
                        if(rects[m] == null){
                            Log.d("rect" , "null");
                            break;
                        }
                        if(paints[m] == null){
                            Log.d("paint" , "null");
                            break;
                        }
                        canvas.drawRect(r,paints[m++]);
                        Log.d("m" , "" + m);
                    }
                }
                rect.offset(width / num, 0);
            }

            rect = new Rect(55,55+width/num*(j+1),width/num+45,55+width/num*(j+2)-10);
        }
        paint.setTextSize(100f);

        if(isTouched){
            paint.setColor(Color.GREEN);
            canvas.drawRect(me.getRawX() - 50, me.getRawY() - 350, me.getRawX() + 50, me.getRawY() - 250, paint);
        }

    }

}
