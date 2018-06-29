package com.example.moniyue.model.mode;

import android.util.Log;

import com.example.baselei.mvp.BaseModel;
import com.example.moniyue.model.api.RetrofitApi;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FenleiModel extends BaseModel {

    public void fenleifu(final Imodelfenleifu imodelfenleifu) {

        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<FenleifuBean> getfulei = retrofitInterface.getfulei();
        getfulei.subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(new Observer<FenleifuBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onNext(FenleifuBean fenleifuBean) {
                    if (fenleifuBean != null) {
                        imodelfenleifu.fenleifum(fenleifuBean);
//                    Log.e("FFFFFFFFFFFFFFF",fenleifuBean.getData().size()+"------------------");
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

    public interface Imodelfenleifu {
        void fenleifum(FenleifuBean fenleifuBean);
    }

    public void fenleizhi(String cid,final Imodelfenleizhi imodelfenleizhi) {

        RetrofitApi retrofitInterface = RetrofitUtil.getInstance().getRetrofitInterface();
        Observable<FenleizhiBean> getfulei = retrofitInterface.getzhilei(cid);
        getfulei.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FenleizhiBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(FenleizhiBean fenleizhiBean) {
                if (fenleizhiBean != null) {
                    imodelfenleizhi.fenleizhim(fenleizhiBean);
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

    public interface Imodelfenleizhi {
        void fenleizhim(FenleizhiBean fenleizhiBean);
    }


}
