package com.example.moniyue.presenter;

import com.example.baselei.mvp.BasePresenter;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.mode.FenleiModel;
import com.example.moniyue.view.interfaces.IfenleiInterface;

public class FenliePresenter extends BasePresenter<FenleiModel,IfenleiInterface> {
    public void getfenlei() {
        model.fenleifu(new FenleiModel.Imodelfenleifu() {
            @Override
            public void fenleifum(FenleifuBean fenleifuBean) {
                view.getfenleifusuccess(fenleifuBean);
            }
        });
    }

    public void getfenleizhi(String cid) {
        model.fenleizhi(cid, new FenleiModel.Imodelfenleizhi() {
            @Override
            public void fenleizhim(FenleizhiBean fenleizhiBean) {
                 view.getfenleizhisuccess(fenleizhiBean);
            }
        });

    }
}

