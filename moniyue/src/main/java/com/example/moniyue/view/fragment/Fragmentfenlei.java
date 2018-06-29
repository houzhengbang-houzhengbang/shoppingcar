package com.example.moniyue.view.fragment;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.baselei.BaseFragment;
import com.example.baselei.mvp.BaseModel;
import com.example.moniyue.R;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.mode.FenleiModel;
import com.example.moniyue.presenter.FenliePresenter;
import com.example.moniyue.view.adapter.FenleifuAdapter;
import com.example.moniyue.view.adapter.FenleizhiAdapter;
import com.example.moniyue.view.interfaces.IfenleiInterface;

import java.util.List;

public class Fragmentfenlei extends BaseFragment<FenliePresenter> implements IfenleiInterface {


    private RecyclerView recy_fu;
    private RecyclerView recy_zhi;
    private int cid;
    private FenleifuAdapter fuadapter;
    private FenleizhiAdapter zhiadapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.fragmentfenlei;
    }

    @Override
    protected FenliePresenter initPresenter() {
        return new FenliePresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new FenleiModel();
    }

    @Override
    protected void initView(View view) {
        recy_fu = view.findViewById(R.id.fenleifu_recy);
        recy_zhi = view.findViewById(R.id.fenleizhi_recy);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy_fu.setLayoutManager(linearLayoutManager);

        final LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy_zhi.setLayoutManager(linearLayoutManager2);
    }

    @Override
    protected void initData() {

        f.getfenlei();



    }

    @Override
    public void getfenleifusuccess(FenleifuBean fenleifuBean) {

        fuadapter = new FenleifuAdapter(getActivity());
        fuadapter.setData(fenleifuBean.getData());
        recy_fu.setAdapter(fuadapter);
        f.getfenleizhi(1+"");

        fuadapter.setOnItemClick(new FenleifuAdapter.OnItemClick() {
            @Override
            public void setOnCl0ick(int pos, List<FenleifuBean.DataBean> list) {
                cid = list.get(pos).getCid();
                f.getfenleizhi(cid+"");
            }
        });
    }

    @Override
    public void getfenleizhisuccess(FenleizhiBean fenleizhiBean) {
        zhiadapter = new FenleizhiAdapter(getActivity());
        zhiadapter.setData(fenleizhiBean.getData());
        recy_zhi.setAdapter(zhiadapter);
    }


}
