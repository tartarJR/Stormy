package com.tatar.stormy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tatar.stormy.R;
import com.tatar.stormy.model.DailyWeather;
import com.tatar.stormy.model.HourlyWeather;

import java.util.Arrays;

public class HourlyForecastActivity extends AppCompatActivity {

    private HourlyWeather[] hourlyWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        hourlyWeathers = Arrays.copyOf(parcelables, parcelables.length, HourlyWeather[].class);
    }
}
