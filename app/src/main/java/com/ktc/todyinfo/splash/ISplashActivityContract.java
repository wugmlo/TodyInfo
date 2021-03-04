package com.ktc.todyinfo.splash;

import com.ktc.todyinfo.mvp.ILifeCircle;
import com.ktc.todyinfo.mvp.IMvpView;
import com.ktc.todyinfo.mvp.MvpController;

public interface ISplashActivityContract {

    interface IView extends IMvpView {
        void setTvTimer(String time);
        void setTvTimerClickable(boolean flag);
    }

    interface IPresenter extends ILifeCircle {
        void initCountDownTimer();
    }

    IView emptyView = new IView() {
        @Override
        public void setTvTimer(String time) {

        }

        @Override
        public void setTvTimerClickable(boolean flag) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
