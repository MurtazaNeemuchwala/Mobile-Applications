package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView cookieImage;
    ConstraintLayout layout;
    TextView cookieCountDisplay;
    int cookieCounter = 0;
    String tag = "BRUH";
    Button button_Grandma;

    public static float randomNum() {
        float rand = (float) (Math.random() * (.9 - .1) + .1);
        Log.d("BRUH", String.valueOf(rand));
        return rand;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookieImage = findViewById(R.id.imageView_Cookie);
        layout = findViewById(R.id.id_layout);
        cookieCountDisplay = findViewById(R.id.id_textView_cookieCounter);
        button_Grandma = findViewById(R.id.button_Grandma);
        cookieCountDisplay.setText("Cookies: " + cookieCounter);

        button_Grandma.setVisibility(View.INVISIBLE);
        button_Grandma.setClickable(false);

        ScaleAnimation scaleAnimation = new ScaleAnimation(.50f, 1.0f, .50f, 1.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(250);

        button_Grandma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieCounter -= 100;
                cookieCountDisplay.setText("Cookies: " + cookieCounter);
                if (cookieCounter >= 100) {
                    button_Grandma.setVisibility(View.VISIBLE);
                    button_Grandma.setClickable(true);
                } else {
                    button_Grandma.setVisibility(View.INVISIBLE);
                    button_Grandma.setClickable(false);
                }
            }
        });

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieImage.startAnimation(scaleAnimation);
                cookieCounter++;
                cookieCountDisplay.setText("Cookies: " + cookieCounter);

                if (cookieCounter >= 100) {
                    button_Grandma.setVisibility(View.VISIBLE);
                    button_Grandma.setClickable(true);
                } else {
                    button_Grandma.setVisibility(View.INVISIBLE);
                    button_Grandma.setClickable(false);
                }

                TextView textInCode = new TextView(MainActivity.this);
                textInCode.setId(View.generateViewId());
                textInCode.setText("+1");
                textInCode.setTextColor(Color.rgb(210, 105, 30));
                textInCode.setVisibility(View.INVISIBLE);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                textInCode.setLayoutParams(params);

                layout.addView(textInCode);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(layout);

                constraintSet.connect(textInCode.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                constraintSet.connect(textInCode.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(textInCode.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                constraintSet.connect(textInCode.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);

                constraintSet.setVerticalBias(textInCode.getId(), (float) .5);
                constraintSet.setHorizontalBias(textInCode.getId(), randomNum());
                constraintSet.applyTo(layout);

                ScaleAnimation numberAnimation = new ScaleAnimation(2.0f, 3.0f, 2.0f, 3.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, 5f);
                numberAnimation.setDuration(250);

                numberAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        layout.removeView(textInCode);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                textInCode.startAnimation(numberAnimation);
            }
        });
    }
}