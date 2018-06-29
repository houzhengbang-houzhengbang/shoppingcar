package com.example.moniyue.view.fragment;


import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.baselei.BaseFragment;
import com.example.baselei.mvp.BaseModel;
import com.example.moniyue.R;
import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.bean.CarBean;
import com.example.moniyue.model.bean.PriceAndCountBean;
import com.example.moniyue.model.mode.CarModel;
import com.example.moniyue.presenter.CarPresenter;
import com.example.moniyue.view.activity.MainActivity;
import com.example.moniyue.view.adapter.CarAdapter;
import com.example.moniyue.view.interfaces.ICarInterface;

import java.util.ArrayList;
import java.util.List;

public class Fragmentcar extends BaseFragment<CarPresenter> implements ICarInterface {


    private List<List<CarBean.DataBean.ListBean>> child = new ArrayList<>();

    private ExpandableListView elv;
    private static CheckBox cb;
    private static TextView tvTotal;
    private static TextView tvCount;
    private CarAdapter adapter;
    private List<CarBean.DataBean> group;


    @Override
    protected int bindLayoutId() {
        return R.layout.fragmentcar;
    }

    @Override
    protected CarPresenter initPresenter() {
        return new CarPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new CarModel();
    }

    @Override
    protected void initView(View view) {
        cb = view.findViewById(R.id.car_check);
        elv = view.findViewById(R.id.car_elv);
        tvCount = view.findViewById(R.id.car_Count);
        tvTotal = view.findViewById(R.id.car_Total);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.AllOrNone(cb.isChecked());
            }
        });

    }

    @Override
    protected void initData() {

        f.getCarm();


    }

    @Override
    public void onCarAddsuccess(AddCarBean addCarBean) {

    }

    @Override
    public void onCarsuccess(CarBean carBean) {

        //获取数据成功以后得到一级集合的数据
        group = carBean.getData();
        //通过循环取出二级集合的数据
        for (int i = 0; i < group.size(); i++) {
            List<CarBean.DataBean.ListBean> list = group.get(i).getList();
            child.add(list);
        }
        //初始适配器
        adapter = new CarAdapter(getActivity(), group, child);
        elv.setAdapter(adapter);
//默认展开列表，不写的话无法展开
        for (int i = 0; i < group.size(); i++) {
            elv.expandGroup(i);
        }
    }


    //是否全选
    public static void setAllChecked(boolean bool) {
      cb.setChecked(bool);
    }

    // 计算总价和总数的方法
    public static void setPriceAndCount(PriceAndCountBean priceAndCount) {
        tvCount.setText("合计：" + priceAndCount.getPrice());
        tvTotal.setText("去结算(" + priceAndCount.getCount() + ")");
    }
}





