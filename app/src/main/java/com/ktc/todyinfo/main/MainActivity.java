package com.ktc.todyinfo.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ktc.todyinfo.R;
import com.ktc.todyinfo.base.ViewInject;
import com.ktc.todyinfo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.IView {

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

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
        initHomeFragment();
        changeAnimate(rg_main_top, rg_main_bottom);
    }

    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }

    @OnClick({R.id.fac_main_home, R.id.rb_main_shanghai, R.id.rb_main_hangzhou,
            R.id.rb_main_shenzhen, R.id.rb_main_guangzhou})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnimate(rg_main_bottom, rg_main_top);
                    handleTopPosition();
                } else {
                    changeAnimate(rg_main_top, rg_main_bottom);
                    handleBottomPosition();
                }
                break;
            case R.id.rb_main_shanghai:
                mPresenter.replaceFragment(MainConstant.SHANGHAI);
                break;
            case R.id.rb_main_hangzhou:
                mPresenter.replaceFragment(MainConstant.HANGZHOU);
                break;
            case R.id.rb_main_shenzhen:
                mPresenter.replaceFragment(MainConstant.SHENZHEN);
                break;
            case R.id.rb_main_guangzhou:
                mPresenter.replaceFragment(MainConstant.GUANGZHOU);
                break;
        }
    }

    private void handleTopPosition() {
        if (mPresenter.getTopPosition() == MainConstant.GUANGZHOU) {
            mPresenter.replaceFragment(MainConstant.GUANGZHOU);
            rb_main_guangzhou.setChecked(true);
        } else {
            mPresenter.replaceFragment(MainConstant.SHENZHEN);
            rb_main_shenzhen.setChecked(true);
        }
    }

    private void handleBottomPosition() {
        if (mPresenter.getBottomPosition() == MainConstant.HANGZHOU) {
            mPresenter.replaceFragment(MainConstant.HANGZHOU);
            rb_main_hangzhou.setChecked(true);
        } else {
            mPresenter.replaceFragment(MainConstant.SHANGHAI);
            rb_main_shanghai.setChecked(true);
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

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_top, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}