package com.example.buttondemo;

import android.support.v7.app.AppCompatActivity;
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

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ((Button)view).setText("HOLA");
            }
        });

    }
/*
    public void myButtonClick(View v){
       //b.setText("Clicked");
        ((Button)v).setText("Hi");
    }
*/
}