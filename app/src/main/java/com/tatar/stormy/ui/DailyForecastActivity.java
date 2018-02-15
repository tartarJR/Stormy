package com.tatar.stormy.ui;

import android.app.ListActivity;
import android.os.Bundle;

import com.tatar.stormy.R;
import com.tatar.stormy.adapter.DailyForecastAdapter;
import com.tatar.stormy.model.DailyWeather;

public class DailyForecastActivity extends ListActivity {

    private DailyWeather[] dailyWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        DailyForecastAdapter adapter = new DailyForecastAdapter(DailyForecastActivity.this, dailyWeatherData);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                DailyForecastActivity.this,
                android.R.layout.simple_list_item_1,
                daysOfTheWeek
        );

        setListAdapter(adapter);*/
    }
}
