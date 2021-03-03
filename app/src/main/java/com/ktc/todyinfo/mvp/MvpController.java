package com.ktc.todyinfo.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MvpController implements ILifeCircle{

    // 存放P层实例
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle lifeCircle) {
        this.lifeCircles.add(lifeCircle);
    }

    @Override
    public void onCreate(Bundle saveInstanceState, Intent intent, Bundle getArguments) {
        for (ILifeCircle presenter : this.lifeCircles) {
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
                presenter.onCreate(saveInstanceState, intent, getArguments);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState, Intent intent, Bundle getArguments) {
        for (ILifeCircle presenter : this.lifeCircles) {
            if (intent == null) {
                intent = new Intent();
            }
            if (getArguments == null) {
                getArguments = new Bundle();
                presenter.onActivityCreated(saveInstanceState, intent, getArguments);
            }
        }
    }

    @Override
    public void onStart() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onDestroy();
        }
    }

    @Override
    public void destroyView() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.destroyView();
        }
    }

    @Override
    public void onViewDestroyed() {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onViewDestroyed();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        for (ILifeCircle presenter : this.lifeCircles) {
            presenter.attachView(iMvpView);
        }
    }
}
