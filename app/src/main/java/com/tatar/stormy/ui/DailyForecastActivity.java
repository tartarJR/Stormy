package com.tatar.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.tatar.stormy.R;
import com.tatar.stormy.adapter.DailyForecastAdapter;
import com.tatar.stormy.model.DailyWeather;

import java.util.Arrays;

public class DailyForecastActivity extends ListActivity {

    private DailyWeather[] dailyWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent intent = getIntent();

        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        dailyWeatherData = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);

        DailyForecastAdapter adapter = new DailyForecastAdapter(DailyForecastActivity.this, dailyWeatherData);
        setListAdapter(adapter);
    }
}
