package com.example.gq.lifeapp.presenter.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.model.impl.TodayWeatherLoading;
import com.example.gq.lifeapp.model.impl.TodayWeatherModel;
import com.example.gq.lifeapp.presenter.OnTodayWeatherListener;
import com.example.gq.lifeapp.presenter.TodayWeatherPresenterIntf;
import com.example.gq.lifeapp.view.intf.TodayWeatherView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by gq on 2015/8/14.
 */
public class TodayWeatherPresenter implements TodayWeatherPresenterIntf {

    private TodayWeatherModel mTodayWeatherModel = TodayWeatherModel.getInstance();
    private TodayWeatherLoading mTodayWeatherLoading;
    private TodayWeatherView mtodayWeatherView;
    private PullToRefreshScrollView mPullToRefreshScrollView;

    @Override
    public void getWeather(String cityName, final TodayWeatherView todayWeatherView) {

        mTodayWeatherModel.loadTodayWeather(cityName, new OnTodayWeatherListener() {
            @Override
            public void onSuccess(TodayWeatherBean todayWeatherBean) {
                todayWeatherView.setTodayWeatherInfo(todayWeatherBean);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void refresh(String cityName, TodayWeatherView todayWeatherView,PullToRefreshScrollView mPullToRefreshScrollView) {
        this.mtodayWeatherView = todayWeatherView;
        this.mPullToRefreshScrollView = mPullToRefreshScrollView;

        new ContentAsyncTask().execute(cityName);
    }

    private class ContentAsyncTask extends AsyncTask<String, Void, TodayWeatherBean>{

        @Override
        protected TodayWeatherBean doInBackground(String... params) {
            mTodayWeatherLoading = new TodayWeatherLoading(params[0]);
            return mTodayWeatherLoading.load();
        }

        @Override
        protected void onPostExecute(TodayWeatherBean todayWeatherBean) {
            super.onPostExecute(todayWeatherBean);
            mtodayWeatherView.setTodayWeatherInfo(todayWeatherBean);
            mPullToRefreshScrollView.onRefreshComplete();
        }
    }
}

