package com.example.moniyue.model.mode;

import android.util.Log;

import com.example.baselei.mvp.BaseModel;
import com.example.moniyue.model.api.RetrofitApi;
import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.bean.CarBean;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CarModel extends BaseModel {

    public void CarAddp(final ImodelCar imodelCar) {

        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<CarBean> getfulei = retrofitInterface.getCar();
        getfulei.subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(new Observer<CarBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onNext(CarBean carBean) {
                    if (carBean != null) {
                        imodelCar.carm(carBean);
                    }
                }
                @Override
                public void onError(Throwable e) {
                    Log.e("123", "onError---: "+e );
                }
                @Override
                public void onComplete() {
                }
            });


    }

    public interface ImodelCar {
        void carm(CarBean carBean);
    }



}
