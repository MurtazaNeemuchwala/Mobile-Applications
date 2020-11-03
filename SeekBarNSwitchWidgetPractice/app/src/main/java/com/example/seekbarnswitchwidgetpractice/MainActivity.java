package com.example.seekbarnswitchwidgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    SeekBar seekBar;
    TextView textView;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editTextTextPersonName);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        aSwitch = findViewById(R.id.switch1);


        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();

                String blue = "Blue";
                String red = "red";
                String green = "green";


                if (str.equalsIgnoreCase(blue))
                    textView.setTextColor(Color.BLUE);
                else if (str.equalsIgnoreCase(red))
                    textView.setTextColor(Color.RED);
                else if (str.equalsIgnoreCase(green))
                    textView.setTextColor(Color.GREEN);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setTextSize(progress);

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
                if (isChecked)
                    seekBar.setEnabled(false);
                else
                    seekBar.setEnabled(true);
            }
        });
    }
}