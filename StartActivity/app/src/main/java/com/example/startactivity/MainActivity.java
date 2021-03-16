package com.example.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView displayResultText;
    Button startButton;
    public static final int CODE_START = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayResultText = findViewById(R.id.id_tex_rerturnValue);
        startButton = findViewById(R.id.id_button_start);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, Numbers.class);
                startIntent.putExtra("TEST", "This is a test");
                startActivityForResult(startIntent, CODE_START);
            }
        });
    }
}