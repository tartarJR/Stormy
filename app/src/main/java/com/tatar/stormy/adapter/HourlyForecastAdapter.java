package com.tatar.stormy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tatar.stormy.R;
import com.tatar.stormy.model.HourlyWeather;

/**
 * Created by musta on 2/17/2018.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyViewHolder> {

    private HourlyWeather[] hourlyWeathers;

    public HourlyForecastAdapter(HourlyWeather[] hourlyWeathers) {
        this.hourlyWeathers = hourlyWeathers;
    }

    @Override
    public HourlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);

        HourlyViewHolder hourlyViewHolder = new HourlyViewHolder(view);

        return hourlyViewHolder;
    }

    @Override
    public void onBindViewHolder(HourlyViewHolder holder, int position) {
        holder.bindHour(hourlyWeathers[position]);
    }

    @Override
    public int getItemCount() {
        return hourlyWeathers.length;
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder {

        public TextView hourLabel;
        public ImageView iconImage;
        public TextView summaryLabel;
        public TextView temperatureLabel;

        public HourlyViewHolder(View itemView) {
            super(itemView);

            hourLabel = itemView.findViewById(R.id.hourTextView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            summaryLabel = itemView.findViewById(R.id.temperatureTextView);
            temperatureLabel = itemView.findViewById(R.id.summaryTextView);
        }

        public void bindHour(HourlyWeather hourlyWeather) {
            hourLabel.setText(hourlyWeather.getHour());
            iconImage.setImageResource(hourlyWeather.getIconId());
            summaryLabel.setText(hourlyWeather.getSummary());
            temperatureLabel.setText(hourlyWeather.getTemperature());
        }
    }
}
