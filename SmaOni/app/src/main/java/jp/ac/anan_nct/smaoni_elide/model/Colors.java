package jp.ac.anan_nct.smaoni_elide.model;

import android.graphics.Color;

/**
 * Created by skriulle on 2015/03/09.
 */
public class Colors {

    private static final int[] originColors = {Color.YELLOW, Color.parseColor("#ff99ff"),Color.parseColor("#007000"), Color.BLACK,Color.CYAN,Color.DKGRAY, Color.parseColor("#ffa500"), 0};

    public static int[] colors = originColors;

    public static void setMe(int me){
        colors = originColors;
        for(int i = colors.length-1; i > me; i--){
            colors[i] = colors[i-1];
        }
        colors[me] = Color.BLUE;
    }
}
