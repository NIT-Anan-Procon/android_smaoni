package jp.ac.anan_nct.smaoni_elide.model;

import android.graphics.Color;
import android.location.Location;

import java.util.Random;

/**
 * Created by skriulle on 2015/03/02.
 */
public class Player {

    private int id;
    private String name;
    private Position myPos;
    private Status status;
    private boolean visiblity;
    private int color;
    private int score;

    public Player(){
        this(0, "", new Position(0, 0), Status.RUNNER, Color.BLUE);
        name = makeName();
        score = 100;
    }
    public Player(int id, String name, Position position, Status status, int color){
        this.id = id;
        this.name = name;
        myPos = position;
        this.status = status;
        visiblity = true;
        score = 0;
        this.color = color;
    }
    public Player(Player player){
        id = player.getId();
        name = player.getName();
        myPos = player.getPos();
        status = player.getStatus();
        visiblity = true;
    }

    String makeName(){
        Random r = new Random();
        return (char)('b' + r.nextInt(25))+"さん";
    }


    public void setStatus(Status status){
        this.status = status;
    }
    public Status getStatus(){
        return status;
    }



    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }



    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }


    public void setPos(Position myPos) {
        this.myPos = myPos;
    }
    public void setPos(Location location){
        myPos.where(location);
    }
    public Position getPos(){
        return myPos;
    }


    public void setVisiblity(boolean visiblity) {
        this.visiblity = visiblity;
    }
    public boolean getVisiblity(){
        return visiblity;
    }


    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return color;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

