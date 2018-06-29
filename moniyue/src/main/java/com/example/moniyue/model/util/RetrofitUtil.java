package com.example.moniyue.model.util;

import android.util.Log;

import com.example.moniyue.model.api.RetrofitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil retrofitutil;
    private Retrofit retrofit;

     private RetrofitUtil() { }
       private RetrofitUtil(String baseUrl) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
            retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())            .client(okHttpClient)
                .build();
        }
    public static RetrofitUtil getInstance(String baseUrl) {
        if (retrofitutil == null) {
            synchronized (RetrofitUtil.class) {
                if (null == retrofitutil) {
                    retrofitutil = new RetrofitUtil(baseUrl);
                }
            }
        }
        return retrofitutil;
    }
    public static RetrofitUtil getInstance() {
        if (null == retrofitutil) {
            return getInstance("https://www.zhaoapi.cn/");
        }
        return retrofitutil;
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public RetrofitApi getRetrofitInterface() {
        RetrofitApi apiService = retrofit.create(RetrofitApi.class);
        return apiService;
    }
    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.e("myMessage", message);
        }
    }
}
