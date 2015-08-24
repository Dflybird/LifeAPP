package com.example.gq.lifeapp.model.bean;

/**
 * Created by gq on 2015/8/13.
 */
public class TodayWeatherBean {
    private String data;
    private String time;
    private String city;
    private String temp;
    private String highTmp;
    private String lowTmp;
    private String weather;
    private String windDirection;
    private String windPower;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHighTmp() {
        return highTmp;
    }

    public void setHighTmp(String highTmp) {
        this.highTmp = highTmp;
    }

    public String getLowTmp() {
        return lowTmp;
    }

    public void setLowTmp(String lowTmp) {
        this.lowTmp = lowTmp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }
}
