package com.ktc.todyinfo.utils;

import android.os.Handler;
import android.os.Looper;

public class CountDownTimer implements Runnable{

    private final ICountDownHandler countDownHandler;

    private Handler handler;
    private boolean isRun;
    private int time;
    private int countDownTime;

    public CountDownTimer(int time, ICountDownHandler countDownHandler) {
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;
        handler = new Handler();
    }

    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler != null) {
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0) {
                if (countDownHandler != null) {
                    countDownHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                handler.postDelayed(this, 1000);
            }
        }
    }

    public void start() {
        isRun = true;
        handler.post(this);
    }

    public void cancel() {
        isRun = false;
        handler.removeCallbacks(this);
    }

    public interface ICountDownHandler {
        /**
         * 倒计时回调
         */
        void onTicker(int time);

        /**
         * 完成时回调
         */
        void onFinish();
    }
}
