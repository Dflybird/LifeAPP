package com.example.gq.lifeapp.view.intf;

import com.example.gq.lifeapp.model.bean.TodayWeatherBean;

/**
 * Created by gq on 2015/8/13.
 */
public interface TodayWeatherView {

    /**
     * 通过返回的WeatherBean对象，在UI层更新数据
     * @param todayWeather
     */
    void setTodayWeatherInfo(TodayWeatherBean todayWeather);
}
