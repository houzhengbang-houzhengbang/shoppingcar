package com.example.baselei.mvp;

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void serviceFail(String msg);
}
