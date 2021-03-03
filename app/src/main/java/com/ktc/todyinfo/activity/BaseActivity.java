package com.ktc.todyinfo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ktc.todyinfo.annotation.ViewInject;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSetting();
    }

    private void initSetting() {
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int layoutId = annotation.mainLayoutId();
            if (layoutId > 0) {
                setContentView(layoutId);
                ButterKnife.bind(this);
            } else {
                throw new RuntimeException("mainLayoutId < 0");
            }
        } else {
            throw new RuntimeException("mainLayoutId = null");
        }
    }
}
