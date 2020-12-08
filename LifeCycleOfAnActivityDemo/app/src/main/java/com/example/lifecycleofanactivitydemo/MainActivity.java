package com.example.lifecycleofanactivitydemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag", "onCreate");
        button=findViewById(R.id.button);
        if(i==0) {
            button.setText("Value " + i);
        }
        if(savedInstanceState!= null) {
            i = savedInstanceState.getInt("key");
            button.setText("Value "+i);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                button.setText("Value "+(i));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key", i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tag", "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag", "onStop");
    }
}