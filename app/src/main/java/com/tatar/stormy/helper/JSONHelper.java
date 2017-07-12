package com.tatar.stormy.helper;

import com.tatar.stormy.model.CurrentWeather;
import com.tatar.stormy.model.DailyWeather;
import com.tatar.stormy.model.Forecast;
import com.tatar.stormy.model.HourlyWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {

    //private static final String TAG = JSONHelper.class.getSimpleName();

    public static Forecast parseForecastDetails(String jsonData) throws JSONException {

        Forecast forecast = new Forecast();

        forecast.setCurrentWeather(getCurrentDetails(jsonData));
        forecast.setHourlyWeather(getHourlyWeatherDetails(jsonData));
        forecast.setDailyWeather(getDailyWeatherDetails(jsonData));

        return forecast;
    }

    private static CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

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

        return currentWeather;
    }

    private static HourlyWeather[] getHourlyWeatherDetails(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timzeZone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray hourlyData = hourly.getJSONArray("data");

        HourlyWeather[] hourlyWeatherData = new HourlyWeather[hourlyData.length()];

        for (int i = 0; i < hourlyData.length(); i++) {

            JSONObject jsonHour = hourlyData.getJSONObject(i);

            HourlyWeather hourlyWeather = new HourlyWeather();
            hourlyWeather.setTime(jsonHour.getLong("time"));
            hourlyWeather.setSummary(jsonHour.getString("summary"));
            hourlyWeather.setTemperature(jsonHour.getDouble("temperature"));
            hourlyWeather.setIcon(jsonHour.getString("icon"));
            hourlyWeather.setTimeZone(timzeZone);

            hourlyWeatherData[i] = hourlyWeather;
        }

        return hourlyWeatherData;
    }

    private static DailyWeather[] getDailyWeatherDetails(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timzeZone = forecast.getString("timezone");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray dailyData = daily.getJSONArray("data");

        DailyWeather[] dailyWeatherData = new DailyWeather[dailyData.length()];

        for (int i = 0; i < dailyData.length(); i++) {

            JSONObject jsonDay = dailyData.getJSONObject(i);

            DailyWeather dailyWeather = new DailyWeather();
            dailyWeather.setTime(jsonDay.getLong("time"));
            dailyWeather.setSummary(jsonDay.getString("summary"));
            dailyWeather.setTemperatureMax(jsonDay.getDouble("temperatureMax"));
            dailyWeather.setIcon(jsonDay.getString("icon"));
            dailyWeather.setTimeZone(timzeZone);

            dailyWeatherData[i] = dailyWeather;
        }

        return dailyWeatherData;

    }
}
