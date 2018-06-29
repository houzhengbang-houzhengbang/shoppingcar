package com.example.moniyue.view.interfaces;

import com.example.baselei.mvp.IBaseView;
import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.bean.CarBean;

public interface ICarInterface extends IBaseView {
    void onCarAddsuccess(AddCarBean addCarBean);
    void onCarsuccess(CarBean carBean);
}
