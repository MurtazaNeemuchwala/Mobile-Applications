package com.example.widgetsday11demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textSeek;
    SeekBar seekBar;
    Switch aSwitch;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSeek = findViewById(R.id.id_textView1);
        seekBar = findViewById(R.id.id_seekBar);
        aSwitch = findViewById(R.id.switch1);
        textView = findViewById(R.id.textView2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeek.setText("" + progress + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textSeek.setText("START " + seekBar.getProgress() + "%");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textSeek.setText("STOP " + seekBar.getProgress() + "%");
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    textView.setText("ON");
                    aSwitch.setText("ON");

                } else {
                    textView.setText("OFF");
                    aSwitch.setText("OFF");
                }
            }
        });

    }
}