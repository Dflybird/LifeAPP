package com.example.gq.lifeapp.model;

import com.example.gq.lifeapp.presenter.OnWeekWeatherListener;

/**
 * Created by gq on 2015/8/14.
 */
public interface WeekWeatherModelIntf {

    void loadWeekWeather(String cityName, OnWeekWeatherListener listener);
}
