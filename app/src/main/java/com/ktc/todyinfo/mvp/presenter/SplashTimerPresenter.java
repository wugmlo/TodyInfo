package com.ktc.todyinfo.mvp.presenter;

import android.util.Log;

import com.ktc.todyinfo.mvp.ISplashActivityContract;
import com.ktc.todyinfo.view.SplashActivity;
import com.ktc.todyinfo.mvp.IMvpView;
import com.ktc.todyinfo.mvp.base.BaseMvpPresenter;
import com.ktc.todyinfo.utils.CountDownTimer;

public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.IView>
                    implements ISplashActivityContract.IPresenter{

    public static final String TAG = "SplashTimerPresenter";

    private CountDownTimer countDownTimer;

    public SplashTimerPresenter(ISplashActivityContract.IView iMvpView) {
        super(iMvpView);
    }

    public void initCountDownTimer() {
        getView().setTvTimerClickable(true);
        countDownTimer = new CountDownTimer(5, new CountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");
            }
            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
                getView().setTvTimerClickable(true);
            }
        });
        countDownTimer.start();
    }

    public void cancel() {
        countDownTimer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
        Log.e(TAG, "onDestroy");
    }

    /**
     * 防止空指针
     */
    @Override
    protected ISplashActivityContract.IView getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
