package com.example.widgetquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seek1, seek2;
    Switch aSwitch;
    TextView textView1, textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seek1 = findViewById(R.id.seekBar);
        seek2 = findViewById(R.id.seekBar2);
        aSwitch = findViewById(R.id.switch2);
        textView1 = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);

        textView1.setText("Seek 1");
        textView2.setText("Seek 2");

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seek1.getProgress() ==  seek2.getProgress())
                    aSwitch.setChecked(true);
                else
                    aSwitch.setChecked(false);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seek1.getProgress() ==  seek2.getProgress())
                    aSwitch.setChecked(true);
                else
                    aSwitch.setChecked(false);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



            }
        });
    }
}