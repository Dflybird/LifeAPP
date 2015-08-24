package com.example.gq.lifeapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseActivity;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.util.Utility;

/**
 * Created by gq on 2015/8/22.
 */
public class WeatherLocation extends BaseActivity {

    private EditText editText;
    private Button button;
    private String cityName;

    public static void startActivity(Context context){
        Intent i = new Intent(context, WeatherLocation.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = editText.getText().toString();
                Utility.saveTodayWeatherCityToSP(MyApplication.getContext(), cityName);
                finish();
            }
        });
    }

    private void findView() {
        editText = (EditText) findViewById(R.id.location_edit);
        button = (Button) findViewById(R.id.location_button);
    }
}
