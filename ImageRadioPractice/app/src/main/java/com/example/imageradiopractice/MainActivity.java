package com.example.imageradiopractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RadioGroup radioGroup;
        ImageView imageView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGrp);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.office);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonDwight) {
                    imageView.setImageResource(R.drawable.dwight);
                }
                if (checkedId == R.id.radioButtonJim) {
                    imageView.setImageResource(R.drawable.jim);
                }
                if (checkedId == R.id.radioButtonMike) {
                    imageView.setImageResource(R.drawable.mike_scoot);
                }
            }
        });
    }
}