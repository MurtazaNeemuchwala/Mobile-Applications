package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//https://api.openweathermap.org/data/2.5/find?lat=40.37977471083948&lon=-74.52311017230895&cnt=3&appid=b14595f196573fa7954bbc013e0d406e

//http://api.openweathermap.org/data/2.5/find?lat=40.37977471083948&lon=-74.52311017230895&cnt=3&appid=b14595f196573fa7954bbc013e0d406e
public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader bufferedReader;
    String info = "";
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("HI", "hi");
        (new AsyncThread()).execute();
    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/find?lat=40.37977471083948&lon=-74.52311017230895&cnt=3&appid=b14595f196573fa7954bbc013e0d406e");
                connection = url.openConnection();
                stream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while ((line=bufferedReader.readLine()) != null) {
                    info +=line;
                }
                jsonObject = new JSONObject(info);

            } catch (Exception e) {
                Log.d("BRUH",e.toString());
            }

            Log.d("BRUH", info);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                JSONObject jsonObject = new JSONObject(info);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}