package com.tatar.stormy.helper;

import android.util.Log;

import com.tatar.stormy.model.CurrentWeather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by musta on 7/7/2017.
 */

public class JSONHelper {

    private static final String TAG = JSONHelper.class.getSimpleName();

    public static CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);

        String timzeZone = forecast.getString("timezone");

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTimeZone(timzeZone);

        Log.v(TAG, UnixTimeConverter.getFormattedTime(currently.getLong("time"), timzeZone));

        return currentWeather;
    }

}
