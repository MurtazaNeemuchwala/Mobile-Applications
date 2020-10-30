package com.example.widgetpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch a;
    Switch b;
    Switch c;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.switch1);
        b = findViewById(R.id.switch2);
        c = findViewById(R.id.switch3);
        textView = findViewById(R.id.textView2);


        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(a.isChecked() && b.isChecked() && c.isChecked())
                    textView.setTextColor(Color.GREEN);
                if(a.isChecked() && c.isChecked() && !b.isChecked())
                    textView.setTextColor(Color.BLUE);
                if(!a.isChecked() && !b.isChecked() && c.isChecked())
                    textView.setTextColor(Color.RED);

            }
        });
    }
}