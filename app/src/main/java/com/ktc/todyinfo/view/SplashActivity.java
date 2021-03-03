package com.ktc.todyinfo.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.ktc.todyinfo.R;
import com.ktc.todyinfo.annotation.ViewInject;
import com.ktc.todyinfo.mvp.ISplashActivityContract;
import com.ktc.todyinfo.mvp.presenter.SplashTimerPresenter;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.IView{

    @BindView(R.id.vv_play)
    VideoView vv_play;
    @BindView(R.id.tv_splash_skip)
    TextView tv_splash_skip;

//    private SplashTimerPresenter timerPresenter;

    private ISplashActivityContract.IPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initVideo();
        initListener();
    }

    private void initTimerPresenter() {
        presenter = new SplashTimerPresenter(this);
        presenter.initCountDownTimer();
    }

    private void initVideo() {
        vv_play.setVideoURI(Uri.parse("android.resource://" +
                getPackageName() + File.separator + R.raw.splash));
    }

    private void initListener() {
        vv_play.setOnPreparedListener(MediaPlayer::start);
        vv_play.setOnCompletionListener(mp -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });
        tv_splash_skip.setOnClickListener((v) -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void setTvTimer(String s) {
        tv_splash_skip.setText(s);
    }

    @Override
    public void setTvTimerClickable(boolean b) {
        tv_splash_skip.setClickable(b);
    }
}