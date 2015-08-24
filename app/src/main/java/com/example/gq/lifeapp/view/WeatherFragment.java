package com.example.gq.lifeapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseFrgment;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.model.bean.TodayWeatherBean;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;
import com.example.gq.lifeapp.model.impl.TodayWeatherModel;
import com.example.gq.lifeapp.presenter.impl.TodayWeatherPresenter;
import com.example.gq.lifeapp.presenter.impl.WeekWeatherPresenter;
import com.example.gq.lifeapp.service.AutoUpdateWeatherService;
import com.example.gq.lifeapp.util.DividerItemDecoration;
import com.example.gq.lifeapp.util.Utility;
import com.example.gq.lifeapp.view.intf.TodayWeatherView;
import com.example.gq.lifeapp.view.intf.WeekWeatherView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/12.
 */
public class WeatherFragment extends BaseFrgment {

    private static final int GET_TODAYWEATHER = 1;
    private static final int GET_WEEKWEATHER = 2;
    private TextView time, data, city, temp, weather, windD, windS;
    private ImageButton location;
    private String  cityName = "";
    MyRecycleAdapter mRecycleAdapter;
    RecyclerView mRecyclerView;
    PullToRefreshScrollView mPullToRefreshScrollView;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherLocation.startActivity(getActivity());
            }
        });



        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_weather);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getContext(), DividerItemDecoration.HORIZONTAL_LIST));

        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pull_to_refresh_scrollview);

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            cityName = Utility.getTodayWeatherInfoFromSP(MyApplication.getContext()).getCity();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        initWeather();

        new Thread(new Runnable() {
            @Override
            public void run() {

                //获取当天数据
                TodayWeatherPresenter mTodayWeatherPresenter = new TodayWeatherPresenter();
                mTodayWeatherPresenter.getWeather(cityName, new TodayWeatherView() {
                    @Override
                    public void setTodayWeatherInfo(TodayWeatherBean todayWeather) {
                        Message message = new Message();
                        message.what = GET_TODAYWEATHER;
                        message.obj = todayWeather;
                        handler.sendMessage(message);
                    }
                });


                //获取一周数据
                WeekWeatherPresenter mWeekWeatherPresenter = new WeekWeatherPresenter();
                mWeekWeatherPresenter.getWeekWeather(cityName, new WeekWeatherView() {
                    @Override
                    public void setWeekWeatherInfo(ArrayList<WeekWeatherBean> weekWeatherBeans) {
                        Message message = new Message();
                        message.what = GET_WEEKWEATHER;
                        message.obj = weekWeatherBeans;
                        handler.sendMessage(message);
                    }
                });

            }
        }).start();


        final Intent intent = new Intent(getActivity(), AutoUpdateWeatherService.class);
        getActivity().startService(intent);


//刷新
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

                TodayWeatherPresenter t = new TodayWeatherPresenter();
                t.refresh(cityName, new TodayWeatherView() {
                    @Override
                    public void setTodayWeatherInfo(TodayWeatherBean todayWeather) {
                        try {
                            time.setText(todayWeather.getTime());
                            data.setText(todayWeather.getData());
                            city.setText(todayWeather.getCity());
                            temp.setText(todayWeather.getTemp());
                            weather.setText(todayWeather.getWeather());
                            windD.setText(todayWeather.getWindDirection());
                            windS.setText(todayWeather.getWindPower());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }, mPullToRefreshScrollView);

                WeekWeatherPresenter w = new WeekWeatherPresenter();
                w.refresh(cityName, new WeekWeatherView() {
                    @Override
                    public void setWeekWeatherInfo(ArrayList<WeekWeatherBean> weekWeatherBeans) {
                        try {
                            mRecycleAdapter = new MyRecycleAdapter(weekWeatherBeans, MyApplication.getContext());
                            mRecyclerView.setAdapter(mRecycleAdapter);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void findView(View view) {
        time = (TextView) view.findViewById(R.id.weather_today_time);
        data = (TextView) view.findViewById(R.id.weather_today_date);
        city = (TextView) view.findViewById(R.id.weather_city);
        temp = (TextView) view.findViewById(R.id.weather_today_temp);
        weather = (TextView) view.findViewById(R.id.weather_today_weather);
        windD = (TextView) view.findViewById(R.id.weather_today_WD);
        windS = (TextView) view.findViewById(R.id.weather_today_WS);
        location = (ImageButton) view.findViewById(R.id.weather_location);
    }


    private void initWeather() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case GET_TODAYWEATHER:
                        TodayWeatherBean todayWeather = (TodayWeatherBean) msg.obj;
                        time.setText(todayWeather.getTime());
                        data.setText(todayWeather.getData());
                        city.setText(todayWeather.getCity());
                        temp.setText(todayWeather.getTemp());
                        weather.setText(todayWeather.getWeather());
                        windD.setText(todayWeather.getWindDirection());
                        windS.setText(todayWeather.getWindPower());
                        break;
                    case GET_WEEKWEATHER:
                        ArrayList<WeekWeatherBean> weekWeatherBeans = (ArrayList<WeekWeatherBean>) msg.obj;
                        mRecycleAdapter = new MyRecycleAdapter(weekWeatherBeans, MyApplication.getContext());
                        if (!weekWeatherBeans.get(0).getWeather().equals(""))
                        mRecyclerView.setAdapter(mRecycleAdapter);
                        break;
                }
            }
        };
    }


}
