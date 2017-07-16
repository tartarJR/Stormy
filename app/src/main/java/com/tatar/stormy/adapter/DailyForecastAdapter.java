package com.tatar.stormy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tatar.stormy.R;
import com.tatar.stormy.helper.ImageIdHelper;
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
        return dailyWeatherData.length;
    }

    @Override
    public Object getItem(int i) {
        return dailyWeatherData[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    } //no need. Tag items for easy reference.

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.daily_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            viewHolder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            viewHolder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DailyWeather dayDailyWeather = dailyWeatherData[position];

        viewHolder.iconImageView.setImageResource(ImageIdHelper.getIconId(dayDailyWeather.getIcon()));
        viewHolder.temperatureLabel.setText(dayDailyWeather.getTemperatureMax() + "");
        viewHolder.dayLabel.setText(dayDailyWeather.getDayOfTheWeek());

        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImageView;
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
