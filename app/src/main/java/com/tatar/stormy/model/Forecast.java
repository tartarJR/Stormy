package com.tatar.stormy.model;

/**
 * Created by emuoztu on 7/12/2017.
 */

public class Forecast {

    private CurrentWeather currentWeather;
    private HourlyWeather[] hourlyWeather;
    private DailyWeather[] dailyWeather;

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public HourlyWeather[] getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(HourlyWeather[] hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }

    public DailyWeather[] getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather[] dailyWeather) {
        this.dailyWeather = dailyWeather;
    }
}
