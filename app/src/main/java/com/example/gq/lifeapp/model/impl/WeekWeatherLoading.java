package com.example.gq.lifeapp.model.impl;

import android.util.Log;

import com.example.gq.lifeapp.config.API;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;
import com.example.gq.lifeapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by gq on 2015/8/17.
 */
public class WeekWeatherLoading {

    private String cityName, url, cityUTF8, date;
    private ArrayList<WeekWeatherBean> weekWeather = new ArrayList<>();
    private WeekWeatherBean mWeekWeatherBean;

    public WeekWeatherLoading(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<WeekWeatherBean> load() {

        try {
            cityUTF8 = URLEncoder.encode(cityName, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = API.URL.GETWEEKWEATHER + "?cityname=" + cityUTF8;

        // getJsonData
        HttpURLConnection connection;
        try {
            URL httpURL = new URL(url);
            connection = (HttpURLConnection) httpURL.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setReadTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            //传入apikey
            connection.setRequestProperty("apikey", API.APIKEY.WEATHERKEY);

            InputStream in = connection.getInputStream();
            String response = Utility.readStream(in);

            JSONObject jsonObject, todayJson, historyJson, forecastJson;
            JSONArray jsonArray;
            jsonObject = new JSONObject(response).getJSONObject("retData");

            jsonArray = jsonObject.getJSONArray("history");
            for (int i = 5; i < jsonArray.length(); i++) {
                historyJson = jsonArray.getJSONObject(i);
                mWeekWeatherBean = new WeekWeatherBean();
                date = historyJson.getString("date");
                mWeekWeatherBean.setDate(String.valueOf(date.subSequence(5, date.length())));
                mWeekWeatherBean.setHighTemp(historyJson.getString("hightemp"));
                mWeekWeatherBean.setLowTemp(historyJson.getString("lowtemp"));
                mWeekWeatherBean.setWeather(historyJson.getString("type"));
                mWeekWeatherBean.setWeekDay(historyJson.getString("week"));
                weekWeather.add(mWeekWeatherBean);
            }

            todayJson = jsonObject.getJSONObject("today");
            mWeekWeatherBean = new WeekWeatherBean();
            date = todayJson.getString("date");
            mWeekWeatherBean.setDate(String.valueOf(date.subSequence(5, date.length())));
            mWeekWeatherBean.setHighTemp(todayJson.getString("hightemp"));
            mWeekWeatherBean.setLowTemp(todayJson.getString("lowtemp"));
            mWeekWeatherBean.setWeather(todayJson.getString("type"));
            mWeekWeatherBean.setWeekDay(todayJson.getString("week"));
            weekWeather.add(mWeekWeatherBean);

            jsonArray = jsonObject.getJSONArray("forecast");
            for (int i = 0; i < jsonArray.length(); i++) {
                forecastJson = jsonArray.getJSONObject(i);
                mWeekWeatherBean = new WeekWeatherBean();
                date = forecastJson.getString("date");
                mWeekWeatherBean.setDate(String.valueOf(date.subSequence(5, date.length())));
                mWeekWeatherBean.setHighTemp(forecastJson.getString("hightemp"));
                mWeekWeatherBean.setLowTemp(forecastJson.getString("lowtemp"));
                mWeekWeatherBean.setWeather(forecastJson.getString("type"));
                mWeekWeatherBean.setWeekDay(forecastJson.getString("week"));
                weekWeather.add(mWeekWeatherBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weekWeather;
    }
}
