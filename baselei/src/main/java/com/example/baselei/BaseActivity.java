package com.example.baselei;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.baselei.mvp.BaseModel;
import com.example.baselei.mvp.BasePresenter;
import com.example.baselei.mvp.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

   public  P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        initView();
        presenter = initPresenter();
        if(presenter != null){
            presenter.attch(initModel(), this);
        }
        initData();
        
    }

    protected abstract int bindLayoutId();

    protected abstract P initPresenter();

    protected abstract BaseModel initModel();

    protected abstract void initView();
    
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
