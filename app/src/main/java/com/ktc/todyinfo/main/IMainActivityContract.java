package com.ktc.todyinfo.main;

import androidx.fragment.app.Fragment;

import com.ktc.todyinfo.mvp.ILifeCircle;
import com.ktc.todyinfo.mvp.IMvpView;
import com.ktc.todyinfo.mvp.MvpController;

public interface IMainActivityContract {

    interface IView extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedIndex();

        void replaceFragment(int mCurrentFragmentIndex);

        int getTopPosition();

        int getBottomPosition();
    }

    IView emptyView = new IView() {

        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpController getMvpController() {
            return null;
        }
    };
}
