package com.example.baselei;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselei.mvp.BaseModel;
import com.example.baselei.mvp.BasePresenter;
import com.example.baselei.mvp.IBaseView;

public abstract class BaseFragment<F extends BasePresenter> extends Fragment implements IBaseView {
    public  F f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(bindLayoutId(),container,false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(bindLayoutId());
        f = initPresenter();
        if(f != null){
            f.attch(initModel(), this);
        }
        initData();

    }

    protected abstract int bindLayoutId();

    protected abstract F initPresenter();

    protected abstract BaseModel initModel();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void showLoading() {

    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void serviceFail(String msg) {

    }

/*    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null){
            presenter.attch();
        }
    }*/
}
