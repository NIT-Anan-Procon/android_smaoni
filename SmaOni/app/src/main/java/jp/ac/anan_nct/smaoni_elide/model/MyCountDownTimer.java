package jp.ac.anan_nct.smaoni_elide.model;

import android.os.CountDownTimer;

/**
 * Created by skriulle on 2015/03/16.
 */

public abstract class MyCountDownTimer extends CountDownTimer {

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

    }

    @Override
    public abstract void onFinish();

    @Override
    public abstract void onTick(long millisUntilFinished);
}