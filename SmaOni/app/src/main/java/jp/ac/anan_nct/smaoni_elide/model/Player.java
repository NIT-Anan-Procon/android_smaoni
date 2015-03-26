package jp.ac.anan_nct.smaoni_elide.model;

import android.location.Location;

import jp.ac.anan_nct.smaoni_elide.activity.HomeActivity;

/**
 * Created by skriulle on 2015/03/02.
 */
public class Player extends User{

    private Position myPos;
    private boolean isOni;
    private boolean invisiblity;
    private int color;
    private int score;


    public Player(){
        super("", "", "");
        score = 0;
        isOni = false;
        invisiblity = false;
        myPos = new Position(0,0);
    }
    public Player(User user){
        super(user.getAccount(), user.getPassword(), user.getName());
        score = 0;
        isOni = false;
        invisiblity = false;
        myPos = new Position(0,0);
    }
    public Player(String name, Position position, int color){
        super(HomeActivity.me);
        myPos = position;
        isOni = false;
        invisiblity = false;
        score = 0;
        this.color = color;
    }

    public void setPos(Position myPos) {
        this.myPos = myPos;
    }
    public boolean setPos(Location location){
        return myPos.where(location);
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

    public void setInvisiblity(boolean visiblity) {
        this.invisiblity = visiblity;
    }
    public boolean getInvisiblity(){
        return invisiblity;
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

