package jp.ac.anan_nct.smaoni_elide.model;

import android.location.Location;

import jp.ac.anan_nct.smaoni_elide.activity.SelectActivity;

/**
 * Created by skriulle on 2015/03/02.
 */
public class Position {
    int x, y;

    public Position(){
        x = 0;
        y = 0;
    }
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position(Position position){
        x = position.x;
        y = position.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setXY(int x , int y){
        setX(x);
        setY(y);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    protected void where(Location location){
        int gridNum = SelectActivity.gameData.getGridNum();
        Field field = SelectActivity.gameData.getField();

        x = (int)(   (( location.getLongitude() - field.minLng) / (field.maxLng-field.minLng) ) * gridNum );
        y = (int)(   (( location.getLatitude()  - field.minLat) / (field.maxLat-field.minLat) ) * gridNum );

        if(x >= gridNum) x = gridNum-1;
        else if(x < 0) x = 0;
        if(y >= gridNum) y = gridNum-1;
        else if(y < 0) y = 0;

        y = gridNum-1-y;
    }

    public boolean equals(Position position) {
        return (x == position.getX() && y == position.getY());
    }
}
