package com.example.gq.lifeapp.model.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.config.API;
import com.example.gq.lifeapp.model.TodayWeatherModelIntf;
import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.presenter.OnTodayWeatherListener;
import com.example.gq.lifeapp.util.Utility;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by gq on 2015/8/13.
 */
public class TodayWeatherModel implements TodayWeatherModelIntf {

    TodayWeatherLoading mTodayWeatherLoading;
    private int responseCode;

    private static TodayWeatherModel instance = new TodayWeatherModel();

    public static TodayWeatherModel getInstance() {
        return instance;
    }

    @Override
    public void loadTodayWeather(String cityName, OnTodayWeatherListener listener) {

        mTodayWeatherLoading = new TodayWeatherLoading(cityName);

        //异步请求
        new ContentAsyncTask().execute();
        responseCode = mTodayWeatherLoading.getResponseCode();
        listener.onSuccess(Utility.getTodayWeatherInfoFromSP(MyApplication.getContext()));
    }

    private class ContentAsyncTask extends AsyncTask<Void, Void, TodayWeatherBean> {

        @Override
        protected TodayWeatherBean doInBackground(Void... params) {
            return mTodayWeatherLoading.load();
        }

        @Override
        protected void onPostExecute(TodayWeatherBean todayWeatherBean) {
            super.onPostExecute(todayWeatherBean);
            try {
                Utility.saveTodayWeatherInfoToSP(MyApplication.getContext(), todayWeatherBean);
            } catch (NullPointerException e){
                e.printStackTrace();
            }

        }
    }


}
