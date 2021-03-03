package com.ktc.todyinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ktc.todyinfo.R;
import com.ktc.todyinfo.annotation.ViewInject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.fac_main_home)
    FloatingActionButton fac_home;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

    }

    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {

    }
}