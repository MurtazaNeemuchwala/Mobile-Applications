package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject rootObject = new JSONObject();
        try {
            rootObject.put("name", "Murtaza Neemuchwala");
            rootObject.put("ID", 10016294);
            rootObject.put("Birthday", "09-14-03");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("TAG_INFO", rootObject.toString());

        JSONObject blockOneCourse = new JSONObject();
        try {
            blockOneCourse.put("block", "1B");
            blockOneCourse.put("course name", "Computer Science");
            blockOneCourse.put("grade", 96);
            //add block 1 to root obj
            rootObject.put("Class 1", blockOneCourse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("TAG_INFO", rootObject.toString());
        try {
            Log.d("TAG_INFO", String.valueOf(blockOneCourse.getInt("grade")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject blockTwoCourse = new JSONObject();
        try {
            blockTwoCourse.put("block", "2B");
            blockTwoCourse.put("course name", "Latin");
            blockTwoCourse.put("grade", 100);
            //add block 1 to root obj
            rootObject.put("Class 2", blockTwoCourse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("TAG_INFO", rootObject.toString());
        try {
            Log.d("TAG_INFO", String.valueOf(blockTwoCourse.getInt("grade")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}