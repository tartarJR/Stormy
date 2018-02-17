package com.tatar.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.tatar.stormy.helper.ImageIdHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emuoztu on 7/12/2017.
 */

public class HourlyWeather implements Parcelable {

    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timeZone;

    public HourlyWeather() {
    }

    protected HourlyWeather(Parcel in) {
        time = in.readLong();
        summary = in.readString();
        temperature = in.readDouble();
        icon = in.readString();
        timeZone = in.readString();
    }

    public static final Creator<HourlyWeather> CREATOR = new Creator<HourlyWeather>() {
        @Override
        public HourlyWeather createFromParcel(Parcel in) {
            return new HourlyWeather(in);
        }

        @Override
        public HourlyWeather[] newArray(int size) {
            return new HourlyWeather[size];
        }
    };

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTemperature() {
        return (int) Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public int getIconId() {
        return ImageIdHelper.getIconId(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getHour() {
        SimpleDateFormat format = new SimpleDateFormat("h a");
        Date date = new Date(time * 1000);

        return format.format(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
        dest.writeString(summary);
        dest.writeDouble(temperature);
        dest.writeString(icon);
        dest.writeString(timeZone);
    }
}
