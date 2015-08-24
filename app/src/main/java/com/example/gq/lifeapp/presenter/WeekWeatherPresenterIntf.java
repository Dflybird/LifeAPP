package com.example.gq.lifeapp.presenter;

import com.example.gq.lifeapp.view.intf.TodayWeatherView;
import com.example.gq.lifeapp.view.intf.WeekWeatherView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by gq on 2015/8/14.
 */
public interface WeekWeatherPresenterIntf {

    void getWeekWeather(String cityName, WeekWeatherView weekWeatherView);

    void refresh(String cityName, WeekWeatherView weekWeatherView);

}
