package com.tatar.stormy.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by musta on 7/7/2017.
 */

public class ConnectivityHelper {

    private Context context;

    public ConnectivityHelper(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {

        boolean isAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            isAvailable = true;
        }

        return isAvailable;
    }

}
