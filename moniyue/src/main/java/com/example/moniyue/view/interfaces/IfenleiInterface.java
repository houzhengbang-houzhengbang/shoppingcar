package com.example.moniyue.view.interfaces;

import com.example.baselei.mvp.IBaseView;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;

public interface IfenleiInterface extends IBaseView {
    void getfenleifusuccess(FenleifuBean fenleifuBean);
    void getfenleizhisuccess(FenleizhiBean fenleizhiBeanBean);
}
