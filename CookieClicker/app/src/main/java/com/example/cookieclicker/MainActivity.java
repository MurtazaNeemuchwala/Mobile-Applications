package com.example.cookieclicker;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView cookieImage;
    ConstraintLayout layout;
    TextView cookieCountDisplay, text_grandma, text_superGrandma;
    AtomicInteger cookieCounter = new AtomicInteger();
    String grandmaBaseText = "Grandmas: ";
    String superGrandmaBaseText = "Super Grandmas: ";
    Button button_Grandma;
    Button button_superGrandma;
    int grandmaCount = 0;
    int superGrandmaCount = 0;

    public static float randomNum() {
        float rand = (float) (Math.random() * (.9 - .1)/* + .1*/);
        Log.d("BRUH", String.valueOf(rand));
        return rand;
    }

    public void updateText() {
        //updates the count display when using the threads to inc the value
        cookieCountDisplay.setText("Cookies: " + cookieCounter.get());
    }

    public void animateNumbers(int value) {
        TextView textInCode = new TextView(MainActivity.this);
        textInCode.setId(View.generateViewId());
        textInCode.setText("+"+value);
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
                Log.d("murtaza", String.valueOf(layout.getChildCount()));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textInCode.startAnimation(numberAnimation);
    }

    public void updateViews() {
        //button visibility logic
        if (cookieCounter.get() >= 30) {
            button_Grandma.setVisibility(View.VISIBLE);
            button_Grandma.setClickable(true);
            button_Grandma.setBackgroundColor(Color.GREEN);
        } else {
            button_Grandma.setClickable(false);
            button_Grandma.setBackgroundColor(Color.RED);
        }

        if (cookieCounter.get() >= 500) {
            button_superGrandma.setVisibility(View.VISIBLE);
            button_superGrandma.setClickable(true);
            button_superGrandma.setBackgroundColor(Color.GREEN);
        } else {
            button_superGrandma.setClickable(false);
            button_superGrandma.setBackgroundColor(Color.RED);
        }
    }

    public class grandmaThread extends AsyncTask<String, Void, Void> {
        final int DELAY_TIME = 1000;
        Handler taskHandler = new Handler();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                cookieCounter.getAndAdd(3 * grandmaCount);
                animateNumbers(3 * grandmaCount);
                updateText();
                updateViews();
                taskHandler.postDelayed(task, DELAY_TIME);
            }
        };

        public void reRunTask() {
            task.run();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                reRunTask();
                updateText();
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }

    public class superGrandmaThread extends AsyncTask<String, Void, Void> {
        final int DELAY_TIME = 5000;
        Handler taskHandler = new Handler();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                cookieCounter.getAndAdd(30 * superGrandmaCount);
                animateNumbers(30 * superGrandmaCount);
                updateText();
                updateViews();
                taskHandler.postDelayed(task, DELAY_TIME);
            }
        };

        public void reRunTask() {
            task.run();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                reRunTask();
                updateText();
                updateViews();
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cookieImage = findViewById(R.id.imageView_Cookie);
        layout = findViewById(R.id.id_layout);

        cookieCountDisplay = findViewById(R.id.id_textView_cookieCounter);
        cookieCountDisplay.setText("Cookies: " + cookieCounter.get());

        button_Grandma = findViewById(R.id.button_Grandma);
        button_Grandma.setVisibility(View.INVISIBLE);
        button_Grandma.setClickable(false);

        text_grandma = findViewById(R.id.textView_Grandmas);
        text_grandma.setVisibility(View.INVISIBLE);

        button_superGrandma = findViewById(R.id.button_superGrandma);
        button_superGrandma.setVisibility(View.INVISIBLE);
        button_superGrandma.setClickable(false);

        text_superGrandma = findViewById(R.id.textView_superGrandma);
        text_superGrandma.setVisibility(View.INVISIBLE);

        ScaleAnimation scaleAnimation = new ScaleAnimation(.50f, 1.0f, .50f, 1.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleAnimation.setDuration(250);

        button_superGrandma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (superGrandmaCount == 0) {
                    new superGrandmaThread().execute();
                }

                superGrandmaCount++;
                String temp = superGrandmaBaseText + superGrandmaCount;
                text_superGrandma.setText(temp);

                cookieCounter.getAndSet(cookieCounter.get() - 500);
                updateText();
                updateViews();

                if (superGrandmaCount > 0) {
                    text_superGrandma.setVisibility(View.VISIBLE);
                }
            }
        });


        button_Grandma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grandmaCount == 0) {
                    new grandmaThread().execute();
                }

                grandmaCount++;
                String temp = grandmaBaseText + grandmaCount;
                text_grandma.setText(temp);

                cookieCounter.getAndSet(cookieCounter.get() - 30);
                updateText();
                updateViews();

                if (grandmaCount > 0) {
                    text_grandma.setVisibility(View.VISIBLE);
                }
            }
        });

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieImage.startAnimation(scaleAnimation);
                cookieCounter.getAndIncrement();
                updateText();
                updateViews();
                animateNumbers(1);

                /*TextView textInCode = new TextView(MainActivity.this);
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

                textInCode.startAnimation(numberAnimation);*/
            }
        });
    }
}