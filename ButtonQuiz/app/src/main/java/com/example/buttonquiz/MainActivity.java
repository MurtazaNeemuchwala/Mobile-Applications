package com.example.buttonquiz;


import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button red;
    Button blue;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button red = findViewById(R.id.RED);
        final Button blue = findViewById(R.id.BLUE);
        final Button size = findViewById(R.id.Size);


        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                red.setTextColor(Color.RED);

                if (name != null)
                    red.setText(name);

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blue.setTextColor(Color.BLUE);
                name = (String) blue.getText();

            }
        });

        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
            }
        });

    }
} 