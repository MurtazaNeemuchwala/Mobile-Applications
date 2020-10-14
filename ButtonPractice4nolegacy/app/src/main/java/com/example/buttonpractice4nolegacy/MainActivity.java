package com.example.buttonpractice4nolegacy;

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
       // b.findViewById(R.id.id_button1);
         b = (Button) findViewById(R.id.id_button1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randNum = (int) (Math.random()*3);

                switch(randNum){
                    case 1:
                        b.setTextColor(Color.MAGENTA);
                        b.setText("Magenta");
                        break;
                    case 2:
                        b.setTextColor(Color.GREEN);
                        b.setText("Green");
                        break;
                }
            }
        });
    }
}