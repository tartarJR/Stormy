package com.tatar.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tatar.stormy.R;
import com.tatar.stormy.config.HttpConfig;
import com.tatar.stormy.helper.ConnectivityHelper;
import com.tatar.stormy.helper.ImageIdHelper;
import com.tatar.stormy.helper.JSONHelper;
import com.tatar.stormy.helper.UnixTimeConverter;
import com.tatar.stormy.model.CurrentWeather;
import com.tatar.stormy.model.Forecast;

import org.json.JSONException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String DAILY_FORECAST = "daily_forecast";

    private Forecast forecast;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.refreshImageView)
    ImageView refreshImageView;
    @BindView(R.id.iconImageView)
    ImageView iconImageView;
    @BindView(R.id.timeLabel)
    TextView timeLabel;
    @BindView(R.id.temperatureLabel)
    TextView temperatureLabel;
    @BindView(R.id.humidityValue)
    TextView humidityValue;
    @BindView(R.id.precipValue)
    TextView precipValue;
    @BindView(R.id.summaryLabel)
    TextView summaryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        progressBar.setVisibility(View.INVISIBLE);

        refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getForecast();
            }
        });

        getForecast();
    }

    private void getForecast() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        ConnectivityHelper connectivityHelper = new ConnectivityHelper(getApplicationContext());

        if (connectivityHelper.isNetworkAvailable()) {
            toggleRefresh();

            Request request = new Request.Builder().url(HttpConfig.FORECAST_URL).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });

                    Log.e(TAG, "IOException is caught: ", e);

                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });

                    try {
                        String jsonData = response.body().string();

                        if (response.isSuccessful()) {
                            forecast = JSONHelper.parseForecastDetails(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IOException is caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSONException is caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(MainActivity.this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay() {

        CurrentWeather currentWeather = forecast.getCurrentWeather();

        iconImageView.setImageResource(ImageIdHelper.getIconId(currentWeather.getIcon()));
        timeLabel.setText("At " + UnixTimeConverter.getFormattedTime(currentWeather.getTime(), currentWeather.getTimeZone()) + " it will be");
        temperatureLabel.setText(currentWeather.getTemperature() + "");
        humidityValue.setText(currentWeather.getHumidity() + "");
        precipValue.setText(currentWeather.getPrecipChance() + "%");
        summaryLabel.setText(currentWeather.getSummary());
    }

    private void toggleRefresh() {

        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
            refreshImageView.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            refreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void alertUserAboutError() {
        AlertDialogFragment alertDialog = new AlertDialogFragment();
        alertDialog.show(getFragmentManager(), getString(R.string.error_dialog_tag));
    }

    @OnClick(R.id.dailyButton)
    public void startDailyActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST, forecast.getDailyWeather());
        startActivity(intent);
    }

}
