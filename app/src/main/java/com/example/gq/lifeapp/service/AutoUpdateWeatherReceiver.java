package com.example.gq.lifeapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by gq on 2015/8/15.
 */
public class AutoUpdateWeatherReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,AutoUpdateWeatherService.class);
        context.startService(i);
    }
}
