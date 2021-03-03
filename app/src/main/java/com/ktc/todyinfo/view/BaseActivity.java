package com.ktc.todyinfo.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ktc.todyinfo.annotation.ViewInject;
import com.ktc.todyinfo.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends LifeCircleMvpActivity {

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
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("mainLayoutId < 0");
            }
        } else {
            throw new RuntimeException("mainLayoutId = null");
        }
    }

    public abstract void afterBindView();

    private void bindView() {
        ButterKnife.bind(this);
    }
}
