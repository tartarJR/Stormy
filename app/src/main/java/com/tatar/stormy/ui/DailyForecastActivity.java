package com.tatar.stormy.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.tatar.stormy.R;

public class DailyForecastActivity extends ListActivity {

    String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                DailyForecastActivity.this,
                android.R.layout.simple_list_item_1,
                daysOfTheWeek
        );

        setListAdapter(adapter);
    }
}
