package com.example.gq.lifeapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.model.bean.WeekWeatherBean;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/16.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<WeekWeatherBean> weekWeatherBeans;
    private LayoutInflater mInflater;

    public MyRecycleAdapter(ArrayList<WeekWeatherBean> weekWeatherBeans, Context context) {
        this.weekWeatherBeans = weekWeatherBeans;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weekweather, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDate.setText(weekWeatherBeans.get(position).getDate());
        holder.tvWeek.setText(weekWeatherBeans.get(position).getWeekDay());
        holder.tvHtmp.setText(weekWeatherBeans.get(position).getHighTemp());
        holder.tvLtmp.setText(weekWeatherBeans.get(position).getLowTemp());
        String p = String.valueOf(position);
        String s = String.valueOf(weekWeatherBeans.size());
        String weather = weekWeatherBeans.get(position).getWeather();
        switch (weather){
            case "晴":holder.ivWeather.setImageResource(R.mipmap.iconfont_qing);
                break;
            case "雷阵雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_leizhenyu);
                break;
            case "多云":holder.ivWeather.setImageResource(R.mipmap.iconfont_duoyun);
                break;
            case "大雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_dayu);
                break;
            case "中雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_zhongyu);
                break;
            case "小雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_xiaoyu);
                break;
            case "阴":holder.ivWeather.setImageResource(R.mipmap.iconfont_yin);
                break;
            case "阵雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_zhenyubaitian);
                break;
            case "大到暴雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_baoyu);
                break;
            case "暴雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_baoyu);
                break;
            case "小到中雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_zhongyu);
                break;
            case "中到大雨":holder.ivWeather.setImageResource(R.mipmap.iconfont_dayu);
                break;
            case "小雪":holder.ivWeather.setImageResource(R.mipmap.iconfont_xiaoxue);
                break;
            case "中雪":holder.ivWeather.setImageResource(R.mipmap.iconfont_zhongxue);
                break;
            case "大雪":holder.ivWeather.setImageResource(R.mipmap.iconfont_daxue);
                break;
            case "暴雪":holder.ivWeather.setImageResource(R.mipmap.iconfont_baoxue);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return weekWeatherBeans.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tvDate, tvWeek, tvLtmp, tvHtmp;
    ImageView ivWeather;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvDate = (TextView) itemView.findViewById(R.id.weather_week_date);
        tvWeek = (TextView) itemView.findViewById(R.id.weather_week_week);
        tvHtmp = (TextView) itemView.findViewById(R.id.weather_week_htmp);
        tvLtmp = (TextView) itemView.findViewById(R.id.weather_week_ltmp);
        ivWeather = (ImageView) itemView.findViewById(R.id.weather_week_weather);
    }
}