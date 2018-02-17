package com.tatar.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = dailyWeatherData[position].getDayOfTheWeek();
        String condition = dailyWeatherData[position].getSummary();
        String temperature = dailyWeatherData[position].getTemperatureMax() + "";

        String message = String.format("On %s the high will be %s and it will be %s", dayOfTheWeek, temperature, condition);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
