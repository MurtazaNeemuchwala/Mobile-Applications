package com.example.layoutquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button a;
    Button b;
    TextView A;
    TextView B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.id_button_1);
        A = findViewById(R.id.id_textView_1);

        b = findViewById(R.id.id_button_2);
        B = findViewById(R.id.id_TextView_2);
        b.setOnClickListener(this);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (A.getText() == "Not Clicked") {
                    A.setText("Clicked");
                } else {
                    A.setText("Not Clicked");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (B.getText() == "Not Clicked") {
            B.setText("Clicked");
        } else {
            B.setText("Not Clicked");
        }
    }
}