package com.example.gq.lifeapp.model.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.config.API;
import com.example.gq.lifeapp.model.WeekWeatherModelIntf;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;
import com.example.gq.lifeapp.presenter.OnWeekWeatherListener;
import com.example.gq.lifeapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by gq on 2015/8/14.
 */
public class WeekWeatherModel implements WeekWeatherModelIntf {

    private static WeekWeatherModel instance = new WeekWeatherModel();

    public static WeekWeatherModel getInstance() {
        return instance;
    }

    private WeekWeatherLoading mWeekWeatherLoading;

    @Override
    public void loadWeekWeather(String cityName, OnWeekWeatherListener listener) {
        mWeekWeatherLoading = new WeekWeatherLoading(cityName);

        new ContentAsyncTask().execute();
        listener.onSuccess(Utility.getWeekWeatherInfoFromSP(MyApplication.getContext()));
    }

    private class ContentAsyncTask extends AsyncTask<Void, Void, ArrayList<WeekWeatherBean>> {

        @Override
        protected ArrayList<WeekWeatherBean> doInBackground(Void... params) {
            return mWeekWeatherLoading.load();
        }

        @Override
        protected void onPostExecute(ArrayList<WeekWeatherBean> weekWeatherBeans) {
            super.onPostExecute(weekWeatherBeans);
            Utility.saveWeekWeatherInfoToSP(MyApplication.getContext(), weekWeatherBeans);
        }
    }


}
