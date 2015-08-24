package com.example.gq.lifeapp.presenter.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;
import com.example.gq.lifeapp.model.impl.TodayWeatherLoading;
import com.example.gq.lifeapp.model.impl.WeekWeatherLoading;
import com.example.gq.lifeapp.model.impl.WeekWeatherModel;
import com.example.gq.lifeapp.presenter.OnWeekWeatherListener;
import com.example.gq.lifeapp.presenter.WeekWeatherPresenterIntf;
import com.example.gq.lifeapp.view.intf.TodayWeatherView;
import com.example.gq.lifeapp.view.intf.WeekWeatherView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/15.
 */
public class WeekWeatherPresenter implements WeekWeatherPresenterIntf {

    private WeekWeatherModel mWeekWeatherModel = WeekWeatherModel.getInstance();
    private WeekWeatherLoading mWeekWeatherLoading;
    private WeekWeatherView mWeekWeatherView;

    @Override
    public void getWeekWeather(String cityName,final WeekWeatherView weekWeatherView) {
        mWeekWeatherModel.loadWeekWeather(cityName, new OnWeekWeatherListener() {
            @Override
            public void onSuccess(ArrayList<WeekWeatherBean> weekWeather) {
                weekWeatherView.setWeekWeatherInfo(weekWeather);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void refresh(String cityName, WeekWeatherView weekWeatherView) {
        this.mWeekWeatherView = weekWeatherView;

        new ContentAsyncTask().execute(cityName);
    }

    private class ContentAsyncTask extends AsyncTask<String, Void, ArrayList<WeekWeatherBean>> {

        @Override
        protected ArrayList<WeekWeatherBean> doInBackground(String... params) {
            mWeekWeatherLoading = new WeekWeatherLoading(params[0]);
            return mWeekWeatherLoading.load();
        }

        @Override
        protected void onPostExecute(ArrayList<WeekWeatherBean> weekWeatherBeans) {
            super.onPostExecute(weekWeatherBeans);
            mWeekWeatherView.setWeekWeatherInfo(weekWeatherBeans);

        }
    }

}
