package jp.ac.anan_nct.smaoni_elide.model;

import android.location.Location;

import java.util.Random;

/**
 * Created by skriulle on 2015/03/02.
 */
public class Player extends User{

    private Position myPos;
    private boolean isOni;
    private boolean visiblity;
    private int color;
    private int score;
    private String account;
    private String password;

    public Player(){
        super("skriulle5@gmail.com", "kashifuku", "もりりん");
        score = 100;
        account = "skriulle5@gmail.com";
        password = "kashifuku";
        isOni = false;
        visiblity = true;
        myPos = new Position();
    }
    public Player(String name, Position position, int color){
        super("skriulle5@gmail.com", "kashifuku", "もりりん");
        myPos = position;
        isOni = false;
        visiblity = true;
        score = 0;
        this.color = color;
    }

    String makeName(){
        Random r = new Random();
        return (char)('b' + r.nextInt(25))+"さん";
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

    public void setOni(boolean isOni) {
        this.isOni = isOni;
    }
    public boolean getOni(){
        return isOni;
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


    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}

