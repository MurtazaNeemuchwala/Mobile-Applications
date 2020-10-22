package com.example.calculatorproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, clear, enter, mult, div, sub, add;
    TextView display;
    String EQ;
    ArrayList<String> sEQ = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StringTokenizer st1 = new StringTokenizer(a, " ");
        one = findViewById(R.id.id_one);
        two = findViewById(R.id.id_two);
        three = findViewById(R.id.id_three);
        four = findViewById(R.id.id_four);
        five = findViewById(R.id.id_five);
        six = findViewById(R.id.id_six);
        seven = findViewById(R.id.id_seven);
        eight = findViewById(R.id.id_eight);
        nine = findViewById(R.id.id_nine);
        zero = findViewById(R.id.id_zero);
        clear = findViewById(R.id.id_clear);
        enter = findViewById(R.id.id_enter);
        mult = findViewById(R.id.id_multiplication);
        div = findViewById(R.id.id_division);
        sub = findViewById(R.id.id_subtraction);
        add = findViewById(R.id.id_Addition);
        display = findViewById(R.id.display);
        EQ = "";
        display.setText(EQ);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += one.getText();
                display.setText(EQ);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += two.getText();
                display.setText(EQ);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += three.getText();
                display.setText(EQ);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += four.getText();
                display.setText(EQ);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += five.getText();
                display.setText(EQ);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += six.getText();
                display.setText(EQ);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += seven.getText();
                display.setText(EQ);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += eight.getText();
                display.setText(EQ);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += nine.getText();
                display.setText(EQ);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += zero.getText();
                display.setText(EQ);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ = "";
                display.setText(EQ);
                sEQ.clear();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double product = 0;
                boolean error;
                try {
                    try {
                        //Tokenizer Constructor
                        StringTokenizer SplittedEQ = new StringTokenizer(EQ, "/*-+", true);

                        //runs through string to search and split according to constructor's parameters
                        while (SplittedEQ.hasMoreTokens()) {
                            sEQ.add(SplittedEQ.nextToken());
                        }
                        /* value of num1 and num2 */
                        product = 0;
                        error = false;
                        /* goes through arraylist containing the  equation */

                        for (int i = 0; i < sEQ.size(); i++) {

                            if (i == 0) {
                                product += Double.parseDouble(sEQ.get(i));
                            } else if (sEQ.get(i).equals("-")) {
                                product -= Double.parseDouble(sEQ.get(i + 1));
                            } else if (sEQ.get(i).equals("*")) {
                                product *= Double.parseDouble(sEQ.get(i + 1));
                            } else if (sEQ.get(i).equals("+")) {
                                product += Double.parseDouble(sEQ.get(i + 1));
                            } else if (sEQ.get(i).equals("/")) {
                                product /= Double.parseDouble(sEQ.get(i + 1));
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        error = true;
                    }
                } catch (NumberFormatException e) {
                    error = true;
                }

                if ( error == true){ //uses try catch block to find error and display error 
                    display.setText("ERROR");
                }
                else if (product % 1 != 0) { //Decimals needed
                    String DECoutput = String.valueOf(product);
                    display.setText(DECoutput);
                } else if (product % 1 == 0) {//Decimals not needed
                    int INTproduct = (int) product;
                    String INToutput = String.valueOf(INTproduct);
                    display.setText(INToutput);
                }


            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += mult.getText();
                display.setText(EQ);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += div.getText();
                display.setText(EQ);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += sub.getText();
                display.setText(EQ);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EQ += add.getText();
                display.setText(EQ);
            }
        });
    }
}
