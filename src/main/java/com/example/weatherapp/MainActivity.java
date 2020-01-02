package com.example.weatherapp;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private String longi = "79.1378";
    private String lati = "10.7870";
    private String t;
    private final String USGS_REQUEST_URL =
            "\n" +
                    "https://api.openweathermap.org/data/2.5/weather?lat=" +lati+ "&lon="+longi+"&appid=f61f66ee0a5643314a123a1900671323";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // then update the UI.
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

    }


    private void updateUi(Event weather) {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(weather.mname);
        TextView mainTextView = (TextView) findViewById(R.id.main);
        mainTextView.setText( weather.mmain);
        TextView tempTextView = (TextView) findViewById(R.id.temp);
        t= weather.mtemp+"°C";
        tempTextView.setText(t);
        TextView feelsLikeTextView = (TextView) findViewById(R.id.feels_like);
        t= weather.mfeels_like+"°C";
        feelsLikeTextView.setText(t);
        TextView maxTextView = (TextView) findViewById(R.id.maxtemp);
        t=weather.mmaxtemp+"°C";
        maxTextView.setText(t);
        TextView mintempTextView = (TextView) findViewById(R.id.mintemp);
        t= weather.mmintemp+"°C";
        mintempTextView.setText(t);
        TextView speedTextView = (TextView) findViewById(R.id.speed);
        t= weather.mspeed+"m/s";
        speedTextView.setText(t);
        TextView humidityTextView = (TextView) findViewById(R.id.humidity);
        t=weather.mhumidity+"%";
        humidityTextView.setText(t);

        TextView magnitudeTextView = (TextView) findViewById(R.id.pressure);
        magnitudeTextView.setText(weather.mpressure);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, Event> {


        /**
         * This method is invoked (or called) on a background thread, so we can perform
         * long-running operations like making a network request.
         *
         * It is NOT okay to update the UI from a background thread, so we just return an
         * {@link Event} object as the result.
         */
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("loading");
            pd.show();
        }
        protected Event doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            Event result = Utils.fetchWeatherData(urls[0]);
            return result;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         *
         * It IS okay to modify the UI within this method. We take the {@link Event} object
         * (which was returned from the doInBackground() method) and update the views on the screen.
         */
        protected void onPostExecute(Event result) {
            pd.dismiss();
            // If there is no result, do nothing.
            if (result == null) {
                Toast.makeText(MainActivity.this,"CHECK YOUR INTERNET CONNECTION and restart the App",Toast.LENGTH_LONG).show();
                return;
            }

            updateUi(result);
        }
    }
}
