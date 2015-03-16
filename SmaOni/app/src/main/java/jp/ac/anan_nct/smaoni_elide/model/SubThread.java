package jp.ac.anan_nct.smaoni_elide.model;

import android.util.Log;

import java.util.Random;

import jp.ac.anan_nct.smaoni_elide.activity.SelectActivity;

/**
 * Created by skriulle on 2015/03/04.
 */
public class SubThread extends Thread{

    int time, num;
    private boolean running;
    GameData gameData;
    Player player;
    Position position;


    public SubThread(int time, int p){
        this.time = time;
        gameData = SelectActivity.gameData;
        num = gameData.getGridNum()-1;
        running = true;
        player = gameData.getPlayer(p);
        position = player.getPos();
    }

    @Override
    public void run(){

        Random random = new Random();
        while(running) {

            try {
                sleep(time);
            } catch (Exception e) {
            }

            switch (random.nextInt(3)) {
                case 0:
                    if (position.getX() > 0)
                        position.setX(position.getX() - 1);
                    break;
                case 1:
                    if (position.getX() < num)
                        position.setX(position.getX() + 1);
                    break;
            }
            switch (random.nextInt(3)) {
                case 0:
                    if (position.getY() > 0)
                        position.setY(position.getY() - 1);
                    break;
                case 1:
                    if (position.getY() < num)
                        position.setY(position.getY() + 1);
                    break;
            }

            int newScore = player.getScore() + random.nextInt(21)-10;
            player.setScore(newScore);

        }
//////////////////////////////////JSON










///////////////////////////////////JSON
    }

    public void stopRunning(){
        Log.d("stopped", "" + this);
        running = false;
    }


}
