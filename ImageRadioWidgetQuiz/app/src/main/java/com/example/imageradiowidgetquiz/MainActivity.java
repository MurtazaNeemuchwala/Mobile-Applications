package com.example.imageradiowidgetquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView Score, usrInput;
    ImageView compChoice;
    RadioGroup radioGroup;
    Button onGO;
    Toast toast;
    int ScrComp = 0;
    int ScrUsr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Score = findViewById(R.id.Score);
        usrInput = findViewById(R.id.displayChoice);
        compChoice = findViewById(R.id.imageView);
        radioGroup = findViewById(R.id.radioGrp);
        onGO = findViewById(R.id.Go);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RadioOne)
                    usrInput.setText("Choice: 1");
                if (checkedId == R.id.RadioTwo)
                    usrInput.setText("Choice: 2");

            }
        });

        onGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usrInput.getText().equals("Nothing Selected")) {
                    Toast myMessage = Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT);
                    myMessage.show();
                } else {
                    int a = (int) (Math.random() * (2) + 1);

                    if (a == 1) {
                        compChoice.setImageResource(R.drawable.one);
                    } else if (a == 2) {
                        compChoice.setImageResource(R.drawable.two);
                    }


                    if (radioGroup.getCheckedRadioButtonId() == R.id.RadioOne && a == 1) {
                        ScrUsr++;
                        Score.setText("Score: " + ScrUsr + " - " + ScrComp);
                        Toast myMessage = Toast.makeText(MainActivity.this, "You won", Toast.LENGTH_SHORT);
                        myMessage.show();
                    } else {
                        ScrComp++;
                        Score.setText("Score: " + ScrUsr + " - " + ScrComp);
                        Toast myMessage = Toast.makeText(MainActivity.this, "You Lost", Toast.LENGTH_SHORT);
                        myMessage.show();
                    }
                }

            }
        });

    }
}