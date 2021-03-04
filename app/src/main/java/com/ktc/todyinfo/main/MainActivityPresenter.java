package com.ktc.todyinfo.main;

import androidx.fragment.app.Fragment;

import com.ktc.todyinfo.R;
import com.ktc.todyinfo.main.fragment.GuangzhouFragment;
import com.ktc.todyinfo.main.fragment.HanzhouFragment;
import com.ktc.todyinfo.main.fragment.ShanghaiFragment;
import com.ktc.todyinfo.main.fragment.ShenzhenFragment;
import com.ktc.todyinfo.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.IView>
        implements IMainActivityContract.IPresenter {

    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;
    private int mTopPosition;
    private int mBottomPosition;

    public MainActivityPresenter(IMainActivityContract.IView iMvpView) {
        super(iMvpView);
    }

    @Override
    protected IMainActivityContract.IView getEmptyView() {
        return IMainActivityContract.emptyView;
    }


    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = MainConstant.SHANGHAI;
        replaceFragment(mCurrentFragmentIndex);
    }

    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    @Override
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null) {
            addAndShowFragment(mFragment);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
        }
        setCurChecked(mCurrentFragmentIndex);
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPosition;
    }

    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex) {
            case MainConstant.SHANGHAI:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mBottomPosition = MainConstant.SHANGHAI;
                break;
            case MainConstant.HANGZHOU:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mBottomPosition = MainConstant.HANGZHOU;
                break;
            case MainConstant.SHENZHEN:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                mTopPosition = MainConstant.SHENZHEN;
                break;
            case MainConstant.GUANGZHOU:
                mCurrentCheckedId = R.id.rb_main_guangzhou;
                mTopPosition = MainConstant.GUANGZHOU;
                break;
        }
    }

    private void newCurrentFragment(int mCurrentFragmentIndex) {
        Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case MainConstant.SHANGHAI:
                fragment = new ShanghaiFragment();
                break;
            case MainConstant.HANGZHOU:
                fragment = new HanzhouFragment();
                break;
            case MainConstant.SHENZHEN:
                fragment = new ShenzhenFragment();
                break;
            case MainConstant.GUANGZHOU:
                fragment = new GuangzhouFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()) {
            getView().showFragment(mFragment);
        } else {
            getView().addFragment(mFragment);
        }
    }

    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()) {
            getView().hideFragment(mFragment);
        }
    }
}
