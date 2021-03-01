package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView cookieImage;
    ConstraintLayout layout;
    TextView cookieCountDisplay;
    AtomicInteger score = new AtomicInteger();
    Integer cookieCounter= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookieImage = findViewById(R.id.imageView_Cookie);
        layout = findViewById(R.id.id_layout);
        cookieCountDisplay = findViewById(R.id.id_textView_cookieCounter);
        cookieCountDisplay.setText("Cookies: "+cookieCounter);

        TextView textInCode = new TextView(this);
        textInCode.setId(View.generateViewId());
        textInCode.setText("+1");
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

        constraintSet.setVerticalBias(textInCode.getId(), (float) .30);
        constraintSet.setHorizontalBias(textInCode.getId(), (float) .50);
        constraintSet.applyTo(layout);


        ScaleAnimation scaleAnimation = new ScaleAnimation(.50f, 1.0f, .50f, 1.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(250);

        ScaleAnimation numberAnimation = new ScaleAnimation(2.0f, 3.0f, 2.0f, 3.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        numberAnimation.setDuration(250);



        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieImage.startAnimation(scaleAnimation);
                cookieCounter++;
                cookieCountDisplay.setText("Cookies: "+cookieCounter);
                textInCode.setVisibility(View.VISIBLE);
                textInCode.startAnimation(numberAnimation);
                textInCode.setVisibility(View.INVISIBLE);

            }
        });
    }
}