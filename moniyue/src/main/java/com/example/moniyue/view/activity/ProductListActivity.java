package com.example.moniyue.view.activity;


import com.example.baselei.BaseActivity;
import com.example.baselei.mvp.BaseModel;
import com.example.baselei.mvp.BasePresenter;
import com.example.moniyue.R;
import com.example.moniyue.model.mode.ListModel;
import com.example.moniyue.presenter.ListPresenter;

public class ProductListActivity extends BaseActivity {


    @Override
    protected int bindLayoutId() {

        String pscid = getIntent().getStringExtra("pscid");


        return R.layout.activity_product_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new ListPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new ListModel();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

    }
}
