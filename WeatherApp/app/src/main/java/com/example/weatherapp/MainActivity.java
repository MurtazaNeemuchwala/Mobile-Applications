package com.example.weatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.Date;

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
    String tempAPILatitude = "40.37977471083948";
    String tempAPILongitude = "-74.52311017230895";
    EditText getLatitudeInput, getLongitudeInput;
    Button city1, city2, city3, pushCoordinates;
    ImageView weatherPicture;
    TextView cityNameTextView, dateTextView, temperatureTextView, timeTextView, weatherDescriptionTextView;
    ConstraintLayout layout;


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
        dateTextView = findViewById(R.id.date_textView);
        temperatureTextView = findViewById(R.id.temperature_textView);
        layout = findViewById(R.id.constraintlayout);
        weatherDescriptionTextView = findViewById(R.id.weatherDescription_textView);

        new AsyncThread().execute();


        pushCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncThread().execute();
            }
        });

        city1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName0);
                temperatureTextView.setText(tempStatus0 + degreeSymbol);
                dateTextView.setText(DATE(timeStamp0));
                timeTextView.setText(Time(timeStamp0));
                weatherDescriptionTextView.setText(weatherStatus0);

                if (weatherStatus0.equalsIgnoreCase("clear sky")) {
                    weatherPicture.setImageResource(R.drawable.day_clearsky);
                } else if (weatherStatus0.equalsIgnoreCase("few clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_fewclouds);
                } else if (weatherStatus0.equalsIgnoreCase("scattered clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                } else if (weatherStatus0.equalsIgnoreCase("broken clouds") || weatherStatus0.equalsIgnoreCase("overcast clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                } else if (weatherStatus0.equalsIgnoreCase("light intensity drizzle") || weatherStatus0.equalsIgnoreCase("drizzle") || weatherStatus0.equalsIgnoreCase("heavy intensity drizzle") || weatherStatus0.equalsIgnoreCase("light intensity drizzle rain") || weatherStatus0.equalsIgnoreCase("drizzle rain") || weatherStatus0.equalsIgnoreCase("heavy intensity drizzle rain") || weatherStatus0.equalsIgnoreCase("shower rain and drizzle") || weatherStatus0.equalsIgnoreCase("heavy shower rain and drizzle") || weatherStatus0.equalsIgnoreCase("shower drizzle") || weatherStatus0.equalsIgnoreCase("light intensity shower rain") || weatherStatus0.equalsIgnoreCase("shower rain") || weatherStatus0.equalsIgnoreCase("heavy intensity shower rain") || weatherStatus0.equalsIgnoreCase("ragged shower rain")) {
                    weatherPicture.setImageResource(R.drawable.day_showerrain);
                } else if (weatherStatus0.equalsIgnoreCase("rain") || weatherStatus0.equalsIgnoreCase("light rain") || weatherStatus0.equalsIgnoreCase("moderate rain") || weatherStatus0.equalsIgnoreCase("heavy intensity rain") || weatherStatus0.equalsIgnoreCase("very heavy rain") || weatherStatus0.equalsIgnoreCase("extreme rain")) {
                    weatherPicture.setImageResource(R.drawable.day_rain);
                } else if (weatherStatus0.equalsIgnoreCase("thunderstorm") || weatherStatus0.equalsIgnoreCase("thunderstorm with light rain") || weatherStatus0.equalsIgnoreCase("thunderstorm with rain") || weatherStatus0.equalsIgnoreCase("thunderstorm with heavy rain") || weatherStatus0.equalsIgnoreCase("light thunderstorm") || weatherStatus0.equalsIgnoreCase("thunderstorm with heavy drizzle") || weatherStatus0.equalsIgnoreCase("heavy thunderstorm") || weatherStatus0.equalsIgnoreCase("ragged thunderstorm") || weatherStatus0.equalsIgnoreCase("thunderstorm with light drizzle") || weatherStatus0.equalsIgnoreCase("thunderstorm with drizzle")) {
                    weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                } else if (weatherStatus0.equalsIgnoreCase("Snow") || weatherStatus0.equalsIgnoreCase("light snow") || weatherStatus0.equalsIgnoreCase("Heavy snow") || weatherStatus0.equalsIgnoreCase("Sleet") || weatherStatus0.equalsIgnoreCase("Light shower sleet") || weatherStatus0.equalsIgnoreCase("Shower sleet") || weatherStatus0.equalsIgnoreCase("Light rain and snow") || weatherStatus0.equalsIgnoreCase("Rain and snow") || weatherStatus0.equalsIgnoreCase("Light shower snow") || weatherStatus0.equalsIgnoreCase("Shower snow") || weatherStatus0.equalsIgnoreCase("Heavy shower snow") || weatherStatus0.equalsIgnoreCase("freezing rain")) {
                    weatherPicture.setImageResource(R.drawable.day_snow);
                } else if (weatherStatus0.equalsIgnoreCase("mist") || weatherStatus0.equalsIgnoreCase("Smoke") || weatherStatus0.equalsIgnoreCase("Haze") || weatherStatus0.equalsIgnoreCase("sand/ dust whirls") || weatherStatus0.equalsIgnoreCase("fog") || weatherStatus0.equalsIgnoreCase("sand") || weatherStatus0.equalsIgnoreCase("dust") || weatherStatus0.equalsIgnoreCase("volcanic ash") || weatherStatus0.equalsIgnoreCase("squalls") || weatherStatus0.equalsIgnoreCase("tornado")) {
                    weatherPicture.setImageResource(R.drawable.day_mist);
                }
            }
        });

        city2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName1);
                temperatureTextView.setText(tempStatus1 + degreeSymbol);
                dateTextView.setText(DATE(timeStamp1));
                timeTextView.setText(Time(timeStamp1));
                if (weatherStatus1.equalsIgnoreCase("clear sky")) {
                    weatherPicture.setImageResource(R.drawable.day_clearsky);
                } else if (weatherStatus1.equalsIgnoreCase("few clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_fewclouds);
                } else if (weatherStatus1.equalsIgnoreCase("scattered clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                } else if (weatherStatus1.equalsIgnoreCase("broken clouds") || weatherStatus1.equalsIgnoreCase("overcast clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                } else if (weatherStatus1.equalsIgnoreCase("light intensity drizzle") || weatherStatus1.equalsIgnoreCase("drizzle") || weatherStatus1.equalsIgnoreCase("heavy intensity drizzle") || weatherStatus1.equalsIgnoreCase("light intensity drizzle rain") || weatherStatus1.equalsIgnoreCase("drizzle rain") || weatherStatus1.equalsIgnoreCase("heavy intensity drizzle rain") || weatherStatus1.equalsIgnoreCase("shower rain and drizzle") || weatherStatus1.equalsIgnoreCase("heavy shower rain and drizzle") || weatherStatus1.equalsIgnoreCase("shower drizzle") || weatherStatus1.equalsIgnoreCase("light intensity shower rain") || weatherStatus1.equalsIgnoreCase("shower rain") || weatherStatus1.equalsIgnoreCase("heavy intensity shower rain") || weatherStatus1.equalsIgnoreCase("ragged shower rain")) {
                    weatherPicture.setImageResource(R.drawable.day_showerrain);
                } else if (weatherStatus1.equalsIgnoreCase("rain") || weatherStatus1.equalsIgnoreCase("light rain") || weatherStatus1.equalsIgnoreCase("moderate rain") || weatherStatus1.equalsIgnoreCase("heavy intensity rain") || weatherStatus1.equalsIgnoreCase("very heavy rain") || weatherStatus1.equalsIgnoreCase("extreme rain")) {
                    weatherPicture.setImageResource(R.drawable.day_rain);
                } else if (weatherStatus1.equalsIgnoreCase("thunderstorm") || weatherStatus1.equalsIgnoreCase("thunderstorm with light rain") || weatherStatus1.equalsIgnoreCase("thunderstorm with rain") || weatherStatus1.equalsIgnoreCase("thunderstorm with heavy rain") || weatherStatus1.equalsIgnoreCase("light thunderstorm") || weatherStatus1.equalsIgnoreCase("thunderstorm with heavy drizzle") || weatherStatus1.equalsIgnoreCase("heavy thunderstorm") || weatherStatus1.equalsIgnoreCase("ragged thunderstorm") || weatherStatus1.equalsIgnoreCase("thunderstorm with light drizzle") || weatherStatus1.equalsIgnoreCase("thunderstorm with drizzle")) {
                    weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                } else if (weatherStatus1.equalsIgnoreCase("Snow") || weatherStatus1.equalsIgnoreCase("light snow") || weatherStatus1.equalsIgnoreCase("Heavy snow") || weatherStatus1.equalsIgnoreCase("Sleet") || weatherStatus1.equalsIgnoreCase("Light shower sleet") || weatherStatus1.equalsIgnoreCase("Shower sleet") || weatherStatus1.equalsIgnoreCase("Light rain and snow") || weatherStatus1.equalsIgnoreCase("Rain and snow") || weatherStatus1.equalsIgnoreCase("Light shower snow") || weatherStatus1.equalsIgnoreCase("Shower snow") || weatherStatus1.equalsIgnoreCase("Heavy shower snow") || weatherStatus1.equalsIgnoreCase("freezing rain")) {
                    weatherPicture.setImageResource(R.drawable.day_snow);
                } else if (weatherStatus1.equalsIgnoreCase("mist") || weatherStatus1.equalsIgnoreCase("Smoke") || weatherStatus1.equalsIgnoreCase("Haze") || weatherStatus1.equalsIgnoreCase("sand/ dust whirls") || weatherStatus1.equalsIgnoreCase("fog") || weatherStatus1.equalsIgnoreCase("sand") || weatherStatus1.equalsIgnoreCase("dust") || weatherStatus1.equalsIgnoreCase("volcanic ash") || weatherStatus1.equalsIgnoreCase("squalls") || weatherStatus1.equalsIgnoreCase("tornado")) {
                    weatherPicture.setImageResource(R.drawable.day_mist);
                }

            }
        });

        city3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                cityNameTextView.setText(cityName2);
                temperatureTextView.setText(tempStatus2 + degreeSymbol);
                dateTextView.setText(DATE(timeStamp2));
                timeTextView.setText(Time(timeStamp2));
                if (weatherStatus2.equalsIgnoreCase("clear sky")) {
                    weatherPicture.setImageResource(R.drawable.day_clearsky);
                } else if (weatherStatus2.equalsIgnoreCase("few clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_fewclouds);
                } else if (weatherStatus2.equalsIgnoreCase("scattered clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_scatteredclouds);
                } else if (weatherStatus2.equalsIgnoreCase("broken clouds") || weatherStatus2.equalsIgnoreCase("overcast clouds")) {
                    weatherPicture.setImageResource(R.drawable.day_brokenclouds);
                } else if (weatherStatus2.equalsIgnoreCase("light intensity drizzle") || weatherStatus2.equalsIgnoreCase("drizzle") || weatherStatus2.equalsIgnoreCase("heavy intensity drizzle") || weatherStatus2.equalsIgnoreCase("light intensity drizzle rain") || weatherStatus2.equalsIgnoreCase("drizzle rain") || weatherStatus2.equalsIgnoreCase("heavy intensity drizzle rain") || weatherStatus2.equalsIgnoreCase("shower rain and drizzle") || weatherStatus2.equalsIgnoreCase("heavy shower rain and drizzle") || weatherStatus2.equalsIgnoreCase("shower drizzle") || weatherStatus2.equalsIgnoreCase("light intensity shower rain") || weatherStatus2.equalsIgnoreCase("shower rain") || weatherStatus2.equalsIgnoreCase("heavy intensity shower rain") || weatherStatus2.equalsIgnoreCase("ragged shower rain")) {
                    weatherPicture.setImageResource(R.drawable.day_showerrain);
                } else if (weatherStatus2.equalsIgnoreCase("rain") || weatherStatus2.equalsIgnoreCase("light rain") || weatherStatus2.equalsIgnoreCase("moderate rain") || weatherStatus2.equalsIgnoreCase("heavy intensity rain") || weatherStatus2.equalsIgnoreCase("very heavy rain") || weatherStatus2.equalsIgnoreCase("extreme rain")) {
                    weatherPicture.setImageResource(R.drawable.day_rain);
                } else if (weatherStatus2.equalsIgnoreCase("thunderstorm") || weatherStatus2.equalsIgnoreCase("thunderstorm with light rain") || weatherStatus2.equalsIgnoreCase("thunderstorm with rain") || weatherStatus2.equalsIgnoreCase("thunderstorm with heavy rain") || weatherStatus2.equalsIgnoreCase("light thunderstorm") || weatherStatus2.equalsIgnoreCase("thunderstorm with heavy drizzle") || weatherStatus2.equalsIgnoreCase("heavy thunderstorm") || weatherStatus2.equalsIgnoreCase("ragged thunderstorm") || weatherStatus2.equalsIgnoreCase("thunderstorm with light drizzle") || weatherStatus2.equalsIgnoreCase("thunderstorm with drizzle")) {
                    weatherPicture.setImageResource(R.drawable.day_thunderstorm);
                } else if (weatherStatus2.equalsIgnoreCase("Snow") || weatherStatus2.equalsIgnoreCase("light snow") || weatherStatus2.equalsIgnoreCase("Heavy snow") || weatherStatus2.equalsIgnoreCase("Sleet") || weatherStatus2.equalsIgnoreCase("Light shower sleet") || weatherStatus2.equalsIgnoreCase("Shower sleet") || weatherStatus2.equalsIgnoreCase("Light rain and snow") || weatherStatus2.equalsIgnoreCase("Rain and snow") || weatherStatus2.equalsIgnoreCase("Light shower snow") || weatherStatus2.equalsIgnoreCase("Shower snow") || weatherStatus2.equalsIgnoreCase("Heavy shower snow") || weatherStatus2.equalsIgnoreCase("freezing rain")) {
                    weatherPicture.setImageResource(R.drawable.day_snow);
                } else if (weatherStatus2.equalsIgnoreCase("mist") || weatherStatus2.equalsIgnoreCase("Smoke") || weatherStatus2.equalsIgnoreCase("Haze") || weatherStatus2.equalsIgnoreCase("sand/ dust whirls") || weatherStatus2.equalsIgnoreCase("fog") || weatherStatus2.equalsIgnoreCase("sand") || weatherStatus2.equalsIgnoreCase("dust") || weatherStatus2.equalsIgnoreCase("volcanic ash") || weatherStatus2.equalsIgnoreCase("squalls") || weatherStatus2.equalsIgnoreCase("tornado")) {
                    weatherPicture.setImageResource(R.drawable.day_mist);
                }
            }
        });

    }

    public int getFahrenheit(double kelvin) {
        return (int) (((kelvin - 273) * 9 / 5) + 32);
    }

    public String Time(long epoch) {
        Date a = new Date(epoch * 1000);
        /*SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, MMMM d, yyyy");*/
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE, MMMM d");
        return DATE_FORMAT.format(a);
    }

    public String DATE(long epoch) {
        Date a = new Date(epoch * 1000);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm aa");
        return DATE_FORMAT.format(a);
    }


    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (String.valueOf(getLatitudeInput.getText()).equals("")) {
                APILatitude = tempAPILatitude;
                APILongitude = tempAPILongitude;
                Log.d(tag, "NOTHING IN SEARCH BARS");
            } else {
                APILatitude = String.valueOf(getLatitudeInput.getText());
                APILongitude = String.valueOf(getLongitudeInput.getText());
                Log.d(tag, "SOMETHING IS IN SEARCH BARS");
            }

        }

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
                Log.d("BRUH!", e.toString());
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
                    city1.callOnClick();

                    a++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}