package com.tatar.stormy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tatar.stormy.R;
import com.tatar.stormy.adapter.HourlyForecastAdapter;
import com.tatar.stormy.model.DailyWeather;
import com.tatar.stormy.model.HourlyWeather;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends AppCompatActivity {

    private HourlyWeather[] hourlyWeathers;

    @BindView(R.id.hourlyWeatherRecyclerView)
    RecyclerView hourlyWeatherRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        hourlyWeathers = Arrays.copyOf(parcelables, parcelables.length, HourlyWeather[].class);

        HourlyForecastAdapter hourlyForecastAdapter = new HourlyForecastAdapter(this, hourlyWeathers);
        hourlyWeatherRecyclerView.setAdapter(hourlyForecastAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        hourlyWeatherRecyclerView.setLayoutManager(layoutManager);

        hourlyWeatherRecyclerView.setHasFixedSize(true);
    }
}
