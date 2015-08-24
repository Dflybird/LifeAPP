package com.example.gq.lifeapp.model.impl;

import android.util.Log;
import android.widget.Toast;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.config.API;
import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.util.Utility;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by gq on 2015/8/17.
 */
public class TodayWeatherLoading {

    private String cityName, url, cityUTF8;
    TodayWeatherBean mTodayWeatherBean;
    private int responseCode;

    public TodayWeatherLoading(String cityName) {
        this.cityName = cityName;
    }

    public TodayWeatherBean load() {
        try {
            cityUTF8 = URLEncoder.encode(cityName, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = API.URL.GETTODAYWEATHER + "?cityname=" + cityUTF8;

        HttpURLConnection connection = null;
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


            JSONObject jsonObject;
            jsonObject = new JSONObject(response).getJSONObject("retData");
            mTodayWeatherBean = new TodayWeatherBean();
            mTodayWeatherBean.setData(jsonObject.getString("date"));
            mTodayWeatherBean.setTime(jsonObject.getString("time"));
            mTodayWeatherBean.setCity(jsonObject.getString("city"));
            mTodayWeatherBean.setTemp(jsonObject.getString("temp"));
            mTodayWeatherBean.setWeather(jsonObject.getString("weather"));
            mTodayWeatherBean.setHighTmp(jsonObject.getString("h_tmp"));
            mTodayWeatherBean.setLowTmp(jsonObject.getString("l_tmp"));
            mTodayWeatherBean.setWindDirection(jsonObject.getString("WD"));
            mTodayWeatherBean.setWindPower(jsonObject.getString("WS"));
            responseCode = connection.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mTodayWeatherBean;
    }

    public int getResponseCode(){
        return responseCode;
    }
}
