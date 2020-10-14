package com.example.relativelayoutpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    int count1,count2, count3,count4,count5 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button a = findViewById(R.id.button_1);
        final Button b = findViewById(R.id.button_2);
        final Button c = findViewById(R.id.button_3);
        final Button d = findViewById(R.id.button_4);
        final Button e = findViewById(R.id.button_5);

        final TextView A = findViewById(R.id.textView1);
        final TextView B = findViewById(R.id.textView2);
        final TextView C = findViewById(R.id.textView3);
        final TextView D = findViewById(R.id.textView4);
        final TextView E = findViewById(R.id.textView5);

        a.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count1++;
                A.setText("ON");
                if (count1 % 2 == 0)
                    A.setText("OFF");
                A.setText("ON");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count2++;
                B.setText("ON");
                if (count2 % 2 == 0)
                    B.setText("OFF");
                B.setText("ON");
            }
        });

        c.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count3++;
                C.setText("ON");
                if (count3 % 2 == 0)
                    C.setText("OFF");
                C.setText("ON");
            }
        });

        d.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count4++;
                D.setText("ON");
                if (count4 % 2 == 0)
                    D.setText("OFF");
                D.setText("ON");
            }
        });

        e.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count5++;
                E.setText("ON");
               if (count5 % 2 == 0)
                  E.setText("OFF");
               E.setText("ON");
            }
        });
    }
}