package com.tatar.stormy.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by musta on 7/7/2017.
 */

public class UnixTimeConverter {

    public static String getFormattedTime(long time, String timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));

        Date dateTime = new Date(time * 1000);

        String timeString = formatter.format(dateTime);

        return timeString;
    }

}
