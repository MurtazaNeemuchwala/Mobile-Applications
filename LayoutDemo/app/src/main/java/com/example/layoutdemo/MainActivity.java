package com.example.layoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView land, hello;
    Button buttonPort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.id_hello);
        land = findViewById(R.id.id_textLand);
        buttonPort = findViewById(R.id.id_portButton);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            hello.setText("HI");
            buttonPort.setText("Port Button");
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            land.setText("LandButton");
        }
    }
}