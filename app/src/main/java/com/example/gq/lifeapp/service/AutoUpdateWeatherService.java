package com.example.gq.lifeapp.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.model.impl.TodayWeatherModel;
import com.example.gq.lifeapp.model.impl.WeekWeatherModel;
import com.example.gq.lifeapp.util.Utility;

/**
 * Created by gq on 2015/8/15.
 */
public class AutoUpdateWeatherService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateTodayWeather();
                updateWeekWeather();
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 4 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateWeatherReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateTodayWeather() {
        String cityName = Utility.getTodayWeatherInfoFromSP(MyApplication.getContext()).getCity();
        TodayWeatherModel todayWeatherModel = TodayWeatherModel.getInstance();
        todayWeatherModel.loadTodayWeather(cityName, null);

    }

    private void updateWeekWeather() {
        String cityName = Utility.getTodayWeatherInfoFromSP(MyApplication.getContext()).getCity();
        WeekWeatherModel weekWeatherModel = WeekWeatherModel.getInstance();
        weekWeatherModel.loadWeekWeather(cityName,null);

    }
}
