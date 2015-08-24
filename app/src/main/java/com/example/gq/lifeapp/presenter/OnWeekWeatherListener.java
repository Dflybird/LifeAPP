package com.example.gq.lifeapp.presenter;

import com.example.gq.lifeapp.model.bean.WeekWeatherBean;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/15.
 */
public interface OnWeekWeatherListener {

    void onSuccess(ArrayList<WeekWeatherBean> weekWeather);

    void onError();
}
