package com.example.gq.lifeapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.gq.lifeapp.config.API;
import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gq on 2015/8/15.
 */
public class Utility {



    //解析网页返回的数据
    public static String readStream(InputStream in) {

        StringBuilder response = new StringBuilder();
        InputStreamReader isReader;
        try {
            String line;

            //将字节流转化为字符流
            isReader = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(isReader);
            while ((line = reader.readLine()) != null) {

                //若读取不为空，就加到StringBuilder中
                response.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(response);
    }

    // 将当天天气存入
    public static void saveTodayWeatherInfoToSP(Context context, TodayWeatherBean todayWeatherBean){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("today_city", todayWeatherBean.getCity());
        editor.putString("today_date", todayWeatherBean.getData());
        editor.putString("today_time", todayWeatherBean.getTime());
        editor.putString("today_temp", todayWeatherBean.getTemp());
        editor.putString("today_htmp", todayWeatherBean.getHighTmp());
        editor.putString("today_ltmp", todayWeatherBean.getLowTmp());
        editor.putString("today_weather", todayWeatherBean.getWeather());
        editor.putString("today_WD", todayWeatherBean.getWindDirection());
        editor.putString("today_WS", todayWeatherBean.getWindPower());
        editor.commit();
    }

    public static void saveTodayWeatherCityToSP(Context context, String cityName){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("today_city", cityName);
        editor.commit();
    }

    public static TodayWeatherBean getTodayWeatherInfoFromSP(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        TodayWeatherBean todayWeatherBean = new TodayWeatherBean();
        todayWeatherBean.setCity(sp.getString("today_city", ""));
        todayWeatherBean.setData(sp.getString("today_date", ""));
        todayWeatherBean.setTime(sp.getString("today_time", ""));
        todayWeatherBean.setTemp(sp.getString("today_temp", ""));
        todayWeatherBean.setHighTmp(sp.getString("today_htmp", ""));
        todayWeatherBean.setLowTmp(sp.getString("today_ltmp", ""));
        todayWeatherBean.setWeather(sp.getString("today_weather", ""));
        todayWeatherBean.setWindDirection(sp.getString("today_WD", ""));
        todayWeatherBean.setWindPower(sp.getString("today_WS", ""));
        return todayWeatherBean;
    }

    public static void saveWeekWeatherInfoToSP(Context context, ArrayList<WeekWeatherBean> weekWeatherBeans){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        for (int i = 0; i<weekWeatherBeans.size();i++){
            WeekWeatherBean weekWeatherBean = weekWeatherBeans.get(i);
            editor.putString("week_date"+i, weekWeatherBean.getDate());
            editor.putString("week_weekday"+i, weekWeatherBean.getWeekDay());
            editor.putString("week_weather"+i, weekWeatherBean.getWeather());
            editor.putString("week_htmp"+i, weekWeatherBean.getHighTemp());
            editor.putString("week_ltmp"+i, weekWeatherBean.getLowTemp());
        }
        editor.commit();
    }

    public static ArrayList<WeekWeatherBean> getWeekWeatherInfoFromSP(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<WeekWeatherBean> weekWeatherBeans = new ArrayList<>();
        for (int i = 0;i<7;i++){
            WeekWeatherBean weekWeatherBean = new WeekWeatherBean();
            weekWeatherBean.setDate(sp.getString("week_date"+i, ""));
            weekWeatherBean.setWeekDay(sp.getString("week_weekday" + i, ""));
            weekWeatherBean.setWeather(sp.getString("week_weather" + i, ""));
            weekWeatherBean.setHighTemp(sp.getString("week_htmp" + i, ""));
            weekWeatherBean.setLowTemp(sp.getString("week_ltmp" + i, ""));
            weekWeatherBeans.add(weekWeatherBean);
        }
        return weekWeatherBeans;
    }
}
