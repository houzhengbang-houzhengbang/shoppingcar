package com.example.moniyue.presenter;


import com.example.baselei.mvp.BasePresenter;
import com.example.moniyue.model.bean.AddCarBean;
import com.example.moniyue.model.bean.CarBean;
import com.example.moniyue.model.mode.CarModel;
import com.example.moniyue.view.interfaces.ICarInterface;

public class CarPresenter extends BasePresenter<CarModel,ICarInterface> {
    public void getCarm() {
        model.CarAddp( new CarModel.ImodelCar() {
            @Override
            public void carm(CarBean carBean) {
                view.onCarsuccess(carBean);
            }
        });
    }

}

