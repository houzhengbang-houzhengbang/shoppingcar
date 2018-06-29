package com.example.moniyue.model.mode;

import com.example.baselei.mvp.BaseModel;
import com.example.moniyue.model.api.RetrofitApi;
import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListModel extends BaseModel {

/*
    public void CarAddp(String pid,final ImodelCaradd imodelCaradd) {

        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<AddCarBean> getfulei = retrofitInterface.getAdd(pid);
        getfulei.subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(new Observer<AddCarBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onNext(AddCarBean addCarBean) {
                    if (addCarBean != null) {
                        imodelCaradd.fenleicaraddm(addCarBean);
                    }
                }
                @Override
                public void onError(Throwable e) {
                }
                @Override
                public void onComplete() {
                }
            });


    }

    public interface ImodelCaradd {
        void fenleicaraddm(AddCarBean addCarBean);
    }
*/



}
