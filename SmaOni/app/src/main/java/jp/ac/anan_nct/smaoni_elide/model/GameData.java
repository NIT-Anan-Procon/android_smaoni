package jp.ac.anan_nct.smaoni_elide.model;

import android.graphics.Color;
import android.util.Log;

import java.util.Random;

import jp.ac.anan_nct.smaoni_elide.activity.OniGokkoActivity;

/**
 * Created by skriulle on 2015/03/02.
 */
public class GameData{
    private int gridNum, playerNum, oniNum;
    private Field field;
    private Player[] players;
    private int me;
    private int[] colors= {Color.BLUE, Color.RED,Color.YELLOW, Color.parseColor("#ff99ff"),Color.parseColor("#007000"), Color.BLACK,Color.CYAN,Color.DKGRAY};
    private Position oniPosition;
    private int oniIs;

    public GameData() {
        gridNum = 8;
        playerNum = 4;
        oniNum = 1;
        field = new Field();
        players = new Player[playerNum];
        for(Player p : players){
            p = new Player();
        }
        me = 0;
        oniPosition = new Position();
        oniIs = 0;
    }

    public GameData(int gridNum, int playerNum, int runnerNum, Field field, Player[] players, int me) {
        this.gridNum = gridNum;
        this.playerNum = playerNum;
        this.oniNum = oniNum;
        this.field = field;
        this.players = players;
        this.me = me;
    }

    public GameData(GameData gd) {
        gridNum = gd.gridNum;
        playerNum = gd.playerNum;
        oniNum = gd.oniNum;
        field = gd.field;
        players = gd.players;
        me = gd.me;
    }

    public void setField(Field field) {
        this.field = field;
    }
    public Field getField() {
        return field;
    }



    public void setGridNum(int gridNum) {
        this.gridNum = gridNum;
    }
    public int getGridNum() {
        return gridNum;
    }



    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
        players = new Player[this.playerNum];
        for (Player p : players) {
            p = new Player();
        }
    }
    public int getPlayerNum() {
        return players.length;
    }



    public void resetPlayer(int i, Player player) {
        players[i] = player;
    }



    public void setOniNum(int oniNum) {
        this.oniNum = oniNum;
    }
    public int getOniNum() {
        return oniNum;
    }
    public Position oniWhere(){
        return oniPosition;
    }





    public Player getPlayer(int i) {
        return players[i];
    }
    public Player[] getPlayer() {
        return players;
    }




    public void setMe(int m){me = m;}
    public int getMe(){
        return me;
    }



    public int[][][] getColorsss(){
        int[][][] colorsss = new int[gridNum][gridNum][playerNum+1];
        for(int j = 0; j < gridNum; j++){
            for(int i = 0; i < gridNum; i++){
                for(int k = 0; k < playerNum+1; k++){
                    colorsss[j][i][k] = -1;
                    Log.d("jik", "" + colorsss[j][i][k]);
                }
            }
        }

        for(int j = 0; j < gridNum; j++){
            for(int i = 0; i < gridNum; i++){
                int[] values = new int[playerNum+1];
                int k = 0, l = 0;
                for(Player p : players){
                    if(p.getPos().getX() == i && p.getPos().getY() == j){
                        colorsss[j][i][k] = colors[l];
                        values[k] = l;
                        if(players[l].getStatus() == Status.ONI){
                            oniPosition = players[l].getPos();
                            oniIs = k;
                        }
                        k++;
                    }
                    l++;
                }
                if(k > 1 && oniIs != -1) {
                    Random r = new Random();
                    int next = r.nextInt(k - 1);
                    next = (next >= oniIs) ? next + 1 : next;

                    getPlayer(values[oniIs]).setStatus(Status.RUNNER);
                    getPlayer(values[next]).setStatus(Status.ONI);
                    OniGokkoActivity.txLng.setText("ONI is " + values[next]);
                }
                oniIs = -1;
            }

        }
        OniGokkoActivity.txLat.setText(oniPosition.getX()+" "+oniPosition.getY());
        for(int o = 0; o < playerNum; o++){
            if(getPlayer(o).getPos().getX() == oniPosition.getX() && getPlayer(o).getPos().getY() == oniPosition.getY()){
                OniGokkoActivity.txLng.setText("ONI is " + o);
            }
        }

        return colorsss;
    }


    public int[] getColors() {
        return colors;
    }
}

