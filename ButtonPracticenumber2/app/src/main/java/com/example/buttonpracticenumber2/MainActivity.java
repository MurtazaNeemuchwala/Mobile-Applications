package com.example.buttonpracticenumber2;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.id_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNum = (int) (Math.random() * 4);

                switch (randomNum) {
                    case 1: {
                        b.setTextColor(Color.GREEN);
                        b.setText("GREEN");
                        break;
                    }
                    case 2: {
                        b.setTextColor(Color.BLUE);
                        b.setText("BLUE");
                        break;
                    }
                    case 3: {
                        b.setTextColor(Color.RED);
                        b.setText("RED");
                        break;
                    }
                }
            }
        });
    }
}