package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.Double.valueOf;

//https://api.openweathermap.org/data/2.5/find?lat=40.37977471083948&lon=-74.52311017230895&cnt=3&appid=b14595f196573fa7954bbc013e0d406e

//http://api.openweathermap.org/data/2.5/find?lat=40.37977471083948&lon=-74.52311017230895&cnt=3&appid=b14595f196573fa7954bbc013e0d406e
public class MainActivity extends AppCompatActivity {
    URL url;
    String tag = "BRUH";
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    String degreeSymbol = " \u2109";
    String APILatitude = "40.37977471083948";
    String APILongitude = "-74.52311017230895";
    EditText getLatitudeInput, getLongitudeInput;
    Button city1, city2, city3, pushCoordinates;
    ImageView weatherPicture;
    TextView cityNameTextView, timeTextView, temperatureTextView;

    //Important Globally accessible JSON OBJECTS
    JSONObject jsonObject, cityinfo0, cityinfo1, cityinfo2, cityWeatherInfo0, cityWeatherInfo1, cityWeatherInfo2;

    //Global City Name
    String cityName0, cityName1, cityName2;

    //Global tempStatus
    Double tempStatus0, tempStatus1, tempStatus2;

    //Global Weather Status
    String weatherStatus0, weatherStatus1, weatherStatus2;

    //Global Time Status
    long timeStamp0, timeStamp1, timeStamp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLatitudeInput = findViewById(R.id.latitude_editText);
        getLongitudeInput = findViewById(R.id.longitude_editText);
        city1 = findViewById(R.id.city1_button);
        city2 = findViewById(R.id.city2_button);
        city3 = findViewById(R.id.city3_button);
        pushCoordinates = findViewById(R.id.coordinates_GOButton);
        weatherPicture = findViewById(R.id.weather_image);
        cityNameTextView = findViewById(R.id.cityName_textView);
        timeTextView = findViewById(R.id.time_textView);
        temperatureTextView = findViewById(R.id.temperature_textView);

        new AsyncThread().execute();

        pushCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APILatitude = String.valueOf(getLatitudeInput.getText());
                APILongitude = String.valueOf(getLongitudeInput.getText());
                new AsyncThread().execute();
            }
        });

        city1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName0);
                temperatureTextView.setText(tempStatus0 + degreeSymbol);
                timeTextView.setText(Time(timeStamp0));
                switch (weatherStatus0) {
                    case "clear sky":
                        weatherPicture.setImageResource(R.drawable.day_clearsky);
                        break;
                    case "few clouds":
                        weatherPicture.setImageResource(R.drawable.day_fewclouds);
                        break;
                    case "scattered clouds":
                        weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                        break;
                    case "broken clouds":
                        weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                        break;
                    case "shower rain":
                        weatherPicture.setImageResource(R.drawable.day_showerrain);
                        break;
                    case "rain":
                        weatherPicture.setImageResource(R.drawable.day_rain);
                        break;
                    case "thunderstorm":
                        weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                        break;
                    case "snow":
                        weatherPicture.setImageResource(R.drawable.day_snow);
                        break;
                    case "mist":
                        weatherPicture.setImageResource(R.drawable.day_mist);
                        break;
                }
            }
        });

        city2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName1);
                temperatureTextView.setText(tempStatus1 + degreeSymbol);
                switch (weatherStatus1) {
                    case "clear sky":
                        weatherPicture.setImageResource(R.drawable.day_clearsky);
                        break;
                    case "few clouds":
                        weatherPicture.setImageResource(R.drawable.day_fewclouds);
                        break;
                    case "scattered clouds":
                        weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                        break;
                    case "broken clouds":
                        weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                        break;
                    case "shower rain":
                        weatherPicture.setImageResource(R.drawable.day_showerrain);
                        break;
                    case "rain":
                        weatherPicture.setImageResource(R.drawable.day_rain);
                        break;
                    case "thunderstorm":
                        weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                        break;
                    case "snow":
                        weatherPicture.setImageResource(R.drawable.day_snow);
                        break;
                    case "mist":
                        weatherPicture.setImageResource(R.drawable.day_mist);
                        break;
                }

            }
        });

        city3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName2);
                temperatureTextView.setText(tempStatus2 + degreeSymbol);
                switch (weatherStatus2) {
                    case "clear sky":
                        weatherPicture.setImageResource(R.drawable.day_clearsky);
                        break;
                    case "few clouds":
                        weatherPicture.setImageResource(R.drawable.day_fewclouds);
                        break;
                    case "scattered clouds":
                        weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                        break;
                    case "broken clouds":
                        weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                        break;
                    case "shower rain":
                        weatherPicture.setImageResource(R.drawable.day_showerrain);
                        break;
                    case "rain":
                        weatherPicture.setImageResource(R.drawable.day_rain);
                        break;
                    case "thunderstorm":
                        weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                        break;
                    case "snow":
                        weatherPicture.setImageResource(R.drawable.day_snow);
                        break;
                    case "mist":
                        weatherPicture.setImageResource(R.drawable.day_mist);
                        break;
                }
            }
        });

    }

    public int getFahrenheit(double kelvin) {
        int f = (int) (((kelvin - 273) * 9 / 5) + 32);
        return f;
    }

    public String Time(long epoch) {
        Date a = new Date(epoch * 1000);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        String date = DATE_FORMAT.format(a);
        return date;
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/find?lat=" + APILatitude + "&lon=" + APILongitude + "&cnt=3&appid=b14595f196573fa7954bbc013e0d406e");
                Log.d(tag, url.toString());
                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                if ((line = bufferedReader.readLine()) != null) {
                    do {
                        info += line;
                    } while ((line = bufferedReader.readLine()) != null);
                }
                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d(tag, e.toString());
            }

            Log.d(tag, info);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONObject jsonObject = new JSONObject(info);
                JSONArray info = jsonObject.getJSONArray("list");
                int a = 0;

                for (int i = 0; i < info.length(); i++) {
                    if (a == 0) {
                        Log.d(tag, info.getJSONObject(i).toString());
                        cityinfo0 = info.getJSONObject(i);
                        cityWeatherInfo0 = cityinfo0.getJSONArray("weather").getJSONObject(0);

                        cityName0 = cityinfo0.getString("name");
                        Log.d(tag, cityName0);

                        tempStatus0 = cityinfo0.getJSONObject("main").getDouble("temp");
                        tempStatus0 = (double) getFahrenheit(tempStatus0);
                        Log.d(tag, tempStatus0.toString());

                        weatherStatus0 = cityWeatherInfo0.getString("description");
                        Log.d(tag, weatherStatus0);

                        timeStamp0 = cityinfo0.getLong("dt");
                        Log.d(tag, timeStamp0 + "");

                    }

                    if (a == 1) {
                        Log.d(tag, info.getJSONObject(i).toString());
                        cityinfo1 = info.getJSONObject(i);
                        cityWeatherInfo1 = cityinfo1.getJSONArray("weather").getJSONObject(0);

                        cityName1 = cityinfo1.getString("name");
                        Log.d(tag, cityName1);

                        tempStatus1 = cityinfo1.getJSONObject("main").getDouble("temp");
                        tempStatus1 = (double) getFahrenheit(tempStatus1);
                        Log.d(tag, tempStatus1.toString());

                        weatherStatus1 = cityWeatherInfo1.getString("description");
                        Log.d(tag, weatherStatus1);

                        timeStamp1 = cityinfo1.getLong("dt");
                        Log.d(tag, timeStamp1 + "");

                    }

                    if (a == 2) {
                        Log.d(tag, info.getJSONObject(i).toString());
                        cityinfo2 = info.getJSONObject(i);
                        cityWeatherInfo2 = cityinfo2.getJSONArray("weather").getJSONObject(0);

                        cityName2 = cityinfo2.getString("name");
                        Log.d(tag, cityName2);

                        tempStatus2 = cityinfo2.getJSONObject("main").getDouble("temp");
                        tempStatus2 = (double) getFahrenheit(tempStatus2);
                        Log.d(tag, tempStatus2.toString());

                        weatherStatus2 = cityWeatherInfo2.getString("description");
                        Log.d(tag, weatherStatus2);

                        timeStamp2 = cityinfo2.getLong("dt");
                        Log.d(tag, timeStamp2 + "");

                    }

                    city1.setText(cityName0);
                    city2.setText(cityName1);
                    city3.setText(cityName2);

                    a++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}