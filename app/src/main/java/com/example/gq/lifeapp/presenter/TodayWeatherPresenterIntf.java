package com.example.gq.lifeapp.presenter;

import android.content.Context;

import com.example.gq.lifeapp.view.intf.TodayWeatherView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by gq on 2015/8/14.
 */
public interface TodayWeatherPresenterIntf {

    /**
     * 传入城市名获得天气信息
     * @param cityName
     * @param todayWeatherView
     */
    void getWeather(String cityName, TodayWeatherView todayWeatherView);

    void refresh(String cityName, TodayWeatherView todayWeatherView, PullToRefreshScrollView mPullToRefreshScrollView);
}
