package com.tatar.stormy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tatar.stormy.model.DailyWeather;

/**
 * Created by emuoztu on 7/13/2017.
 */

public class DailyForecastAdapter extends BaseAdapter {

    private Context context;
    private DailyWeather[] dailyWeatherData;

    public DailyForecastAdapter(Context context, DailyWeather[] dailyWeatherData) {
        this.context = context;
        this.dailyWeatherData = dailyWeatherData;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
