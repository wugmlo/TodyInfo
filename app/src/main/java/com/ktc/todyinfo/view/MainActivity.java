package com.ktc.todyinfo.view;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ktc.todyinfo.R;
import com.ktc.todyinfo.annotation.ViewInject;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.fac_main_home)
    FloatingActionButton fac_main_home;

    @BindView((R.id.rg_main_top))
    RadioGroup rg_main_top;
    @BindView((R.id.rg_main_bottom))
    RadioGroup rg_main_bottom;

    @BindView(R.id.rb_main_hangzhou)
    RadioButton rb_main_hangzhou;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rb_main_shanghai;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton rb_main_shenzhen;
    @BindView(R.id.rb_main_guangzhou)
    RadioButton rb_main_guangzhou;

    private boolean isChangeTopOrBottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initView();
    }

    private void initView() {
        changeAnimate(rg_main_top, rg_main_bottom);
    }

    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnimate(rg_main_top, rg_main_bottom);
                } else {
                    changeAnimate(rg_main_bottom, rg_main_top);
                }
                break;
        }
    }

    private void changeAnimate(RadioGroup gone, RadioGroup show) {
        // gone
        gone.clearAnimation();
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        // show
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }
}