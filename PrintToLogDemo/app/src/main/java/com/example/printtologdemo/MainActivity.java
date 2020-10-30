package com.example.printtologdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textView);
        int x = 10;
        Log.d("MN_Print", "The value is" + x);
        x++;
        Log.d("MN_Print_ADD", "The value is" + x);

        try {
            int intArray[];    //declaring array
            intArray = new int[20];  // allocating memory to array

            System.out.println(intArray[50]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}