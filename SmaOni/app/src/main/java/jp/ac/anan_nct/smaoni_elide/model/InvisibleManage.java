package jp.ac.anan_nct.smaoni_elide.model;

import android.os.AsyncTask;
import android.util.Log;

import jp.ac.anan_nct.smaoni_elide.activity.ReceptionActivity;

/**
 * Created by skriulle on 2015/03/19.
 */
public class InvisibleManage extends AsyncTask {

    Player p;
    GameData gameData;
    public InvisibleManage(int i) {
        super();

        gameData = ReceptionActivity.gameData;
        p = gameData.getPlayer(i);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try{
            for(int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                p.setInvisiblity(true);
            }
        }catch (Exception e){
            Log.e("ERROR:invisibleManager", e.toString());
        }




        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        p.setInvisiblity(false);
    }
}
