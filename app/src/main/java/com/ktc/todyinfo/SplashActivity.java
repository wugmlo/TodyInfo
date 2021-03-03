package com.ktc.todyinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.VideoView;

import com.ktc.todyinfo.activity.HomepageActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.vv_play)
    VideoView vv_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        vv_play.setVideoURI(Uri.parse("android.resource://" +
                getPackageName() + File.separator + R.raw.splash));
        vv_play.setOnPreparedListener(MediaPlayer::start);
        vv_play.setOnCompletionListener(mp -> {
            startActivity(new Intent(SplashActivity.this, HomepageActivity.class));
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}