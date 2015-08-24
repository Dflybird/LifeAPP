package com.example.gq.lifeapp.model;

import android.content.Context;

import com.example.gq.lifeapp.presenter.OnTodayWeatherListener;

/**
 * Created by gq on 2015/8/13.
 */
public interface TodayWeatherModelIntf {

    /**
     * 获取天气信息，并解析JSON
     * @param cityName
     * @param listener
     */
    void loadTodayWeather(String cityName, OnTodayWeatherListener listener);

}
