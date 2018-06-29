package com.example.moniyue.model.api;


import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.bean.CarBean;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    //https://www.zhaoapi.cn/product/getCatagory
    @GET("product/getCatagory")
    Observable<FenleifuBean> getfulei();
    //https://www.zhaoapi.cn/product/getProductCatagory?cid=1
    @GET("product/getProductCatagory")
    Observable<FenleizhiBean> getzhilei(@Query("cid") String cid);


    //https://www.zhaoapi.cn/product/getProducts
    @GET("product/getProducts")
    Observable<ListBean> getlist(@Query("pscid") String pscid);

    //https://www.zhaoapi.cn/product/addCart?uid=15005& pid = 1
    @GET("product/addCart?uid=15005")
    Observable<AddCarBean> getAdd(@Query("pid") String pid);

    //https://www.zhaoapi.cn/product/getCarts ?uid=15005

    @GET("product/getCarts?uid=15005")
    Observable<CarBean> getCar();
}
