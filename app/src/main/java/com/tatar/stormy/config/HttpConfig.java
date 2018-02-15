package com.tatar.stormy.config;

/**
 * Created by musta on 7/7/2017.
 */

public class HttpConfig {

    public static final double LATITUDE = 37.8267;
    public static final double LONGITUDE = -122.4233;

    public static final String API_KEY = ""; // ENTERY OUR API KEY HERE

    public static final String FORECAST_URL = "https://api.darksky.net/forecast/" + API_KEY + "/" + LATITUDE + "," + LONGITUDE;

}
