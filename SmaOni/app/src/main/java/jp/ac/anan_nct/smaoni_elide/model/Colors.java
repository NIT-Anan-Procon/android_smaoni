package jp.ac.anan_nct.smaoni_elide.model;

import android.graphics.Color;

/**
 * Created by skriulle on 2015/03/09.
 */
public class Colors {

    public static int[] colors = {Color.BLUE, Color.YELLOW, Color.parseColor("#ff99ff"),Color.parseColor("#007000"), Color.BLACK,Color.CYAN,Color.DKGRAY, Color.parseColor("#ffa500"), 0};

/*
    public Colors(int me){
        int[] origin = {Color.YELLOW, Color.parseColor("#ff99ff"),Color.parseColor("#007000"), Color.BLACK,Color.CYAN,Color.DKGRAY, Color.parseColor("#ffa500"), 0};
        colors = new int[origin.length+1];
        int j =0;
        for(int i = 0; i < origin.length; i++){
            if(i == me){
                colors[i] = Color.BLUE;
                j = 1;
            }

            colors[i+j] = origin[i];
        }
    }
*/
}
