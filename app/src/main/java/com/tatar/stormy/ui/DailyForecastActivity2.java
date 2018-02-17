package com.tatar.stormy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tatar.stormy.R;
import com.tatar.stormy.adapter.DailyForecastAdapter;
import com.tatar.stormy.model.DailyWeather;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyForecastActivity2 extends AppCompatActivity {

    private DailyWeather[] dailyWeatherData;

    @BindView(R.id.list)
    ListView listView;

    @BindView(R.id.empty)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast2);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        dailyWeatherData = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);

        DailyForecastAdapter adapter = new DailyForecastAdapter(this, dailyWeatherData);
        listView.setAdapter(adapter);
        listView.setEmptyView(textView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = dailyWeatherData[position].getDayOfTheWeek();
                String condition = dailyWeatherData[position].getSummary();
                String temperature = dailyWeatherData[position].getTemperatureMax() + "";

                String message = String.format("On %s the high will be %s and it will be %s", dayOfTheWeek, temperature, condition);

                Toast.makeText(DailyForecastActivity2.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
