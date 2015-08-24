package com.example.gq.lifeapp.presenter;

import com.example.gq.lifeapp.model.bean.TodayWeatherBean;

/**
 * Created by gq on 2015/8/13.
 */
public interface OnTodayWeatherListener {

    /**
     * 成功时回调
     * @param todayWeatherBean
     */
    void onSuccess(TodayWeatherBean todayWeatherBean);

    /**
     * 失败时回调
     */
    void onError();
}
