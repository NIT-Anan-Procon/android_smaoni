package jp.ac.anan_nct.smaoni_elide.model;

import android.util.Log;

import java.util.Random;

import jp.ac.anan_nct.smaoni_elide.activity.GameActivity;

/**
 * Created by skriulle on 2015/03/04.
 */
public class SubThread extends Thread{

    int time, player, num;
    private boolean running;

    public SubThread(int time, int player){
        this.time = time;
        this.player = player;
        num = GameActivity.gameData.getGridNum()-1;
        running = true;
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
                    if (GameActivity.gameData.getPlayer(player).getPos().getX() > 0)
                        GameActivity.gameData.getPlayer(player).getPos().setX(GameActivity.gameData.getPlayer(player).getPos().getX() - 1);
                    break;
                case 1:
                    if (GameActivity.gameData.getPlayer(player).getPos().getX() < num)
                        GameActivity.gameData.getPlayer(player).getPos().setX(GameActivity.gameData.getPlayer(player).getPos().getX() + 1);
                    break;
            }
            switch (random.nextInt(3)) {
                case 0:
                    if (GameActivity.gameData.getPlayer(player).getPos().getY() > 0)
                        GameActivity.gameData.getPlayer(player).getPos().setY(GameActivity.gameData.getPlayer(player).getPos().getY() - 1);
                    break;
                case 1:
                    if (GameActivity.gameData.getPlayer(player).getPos().getY() < num)
                        GameActivity.gameData.getPlayer(player).getPos().setY(GameActivity.gameData.getPlayer(player).getPos().getY() + 1);
                    break;
            }

        }
//////////////////////////////////JSON










///////////////////////////////////JSON
    }

    public void stopRunning(){
        Log.d("stopped",""+ this);
        running = false;
    }


}
