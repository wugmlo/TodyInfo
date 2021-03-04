package com.ktc.todyinfo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ktc.todyinfo.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifeCircleMvpFragment {

    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int layoutId = annotation.mainLayoutId();
            if (layoutId > 0) {
                view =  initFragmentView(inflater, layoutId);
                bindView(view);
                afterBindView();
            } else {
                throw new RuntimeException("mainLayoutId < 0");
            }
        } else {
            throw new RuntimeException("mainLayoutId = null");
        }
        return view;
    }

    protected View initFragmentView(LayoutInflater inflater, int layoutId){
        return inflater.from(mContext).inflate(layoutId, null);
    }

    private void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    public abstract void afterBindView();
}
