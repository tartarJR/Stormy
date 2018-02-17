package com.tatar.stormy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tatar.stormy.R;
import com.tatar.stormy.model.HourlyWeather;

/**
 * Created by musta on 2/17/2018.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.HourlyViewHolder> {

    private Context context;
    private HourlyWeather[] hourlyWeathers;

    public HourlyForecastAdapter(Context context, HourlyWeather[] hourlyWeathers) {
        this.context = context;
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

    public class HourlyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView hourLabel;
        public ImageView iconImage;
        public TextView summaryLabel;
        public TextView temperatureLabel;

        public HourlyViewHolder(View itemView) {
            super(itemView);

            hourLabel = itemView.findViewById(R.id.hourTextView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            summaryLabel = itemView.findViewById(R.id.summaryTextView);
            temperatureLabel = itemView.findViewById(R.id.temperatureTextView);

            itemView.setOnClickListener(this);
        }

        public void bindHour(HourlyWeather hourlyWeather) {
            hourLabel.setText(hourlyWeather.getHour());
            iconImage.setImageResource(hourlyWeather.getIconId());
            summaryLabel.setText(hourlyWeather.getSummary());
            temperatureLabel.setText(hourlyWeather.getTemperature() + "");
        }

        @Override
        public void onClick(View v) {
            String time = hourLabel.getText().toString();
            String temperature = temperatureLabel.getText().toString();
            String summary = summaryLabel.getText().toString();

            String message = String.format("At %s it will be %s and %s", time, temperature, summary);

            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
