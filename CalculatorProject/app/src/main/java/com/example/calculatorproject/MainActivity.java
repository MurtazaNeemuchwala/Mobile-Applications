package com.example.calculatorproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
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
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Tokenizer Constructor
                StringTokenizer SplittedEQ = new StringTokenizer(EQ, "/*-+", true);

                //runs through string to search and split according to constructor's parameters
                while (SplittedEQ.hasMoreTokens()) {
                    sEQ.add(SplittedEQ.nextToken());
                }

                double num1 = 0;
                double num2 = 0;

                //value of num1 and num2
                double product = 0;
                //checks to see if product has been used before so code knows not to use num1 (DOES NOT FOLLOW ORDER OF OPERATIONS)
                boolean changed = false;

                //goes through arraylist containing the  equation
                for (int i = 0; i < sEQ.size(); i++) {

                    //chck to see if this is the first "number"
                    if ((sEQ.get(i).equals("*") == false) || (sEQ.get(i).equals("+") == false) || (sEQ.get(i).equals("-") == false) || (sEQ.get(i).equals("/") == false) && (i == 0)) {
                        num1 = Double.parseDouble(sEQ.get(i));
                        //chck to see if this is not the first "number"
                    } else if ((sEQ.get(i).equals("*") == false) || (sEQ.get(i).equals("+") == false) || (sEQ.get(i).equals("-") == false) || (sEQ.get(i).equals("/") == false) && (i < 1)) {
                        num2 = Double.parseDouble(sEQ.get(i));
                        //check to see if it is a math operator but "double product has NOT changed"
                    } else if ((sEQ.get(i).equals("*")) && (!changed)) {
                        product = num1 * num2;
                        changed = true;
                        //check to see if it is a math operator but "double product has NOT changed"
                    } else if ((sEQ.get(i).equals("+")) && (!changed)) {
                        product = num1 + num2;
                        changed = true;
                        //check to see if it is a math operator but "double product has NOT changed"
                    } else if ((sEQ.get(i).equals("-")) && (!changed)) {
                        product = num1 - num2;
                        changed = true;
                        //check to see if it is a math operator but "double product has NOT changed"
                    } else if ((sEQ.get(i).equals("/")) && (!changed)) {
                        //NEED TO DO THE IF DIVIDE BY 0 PART
                        product = num1 / num2;
                        changed = true;
                        //check to see if it is a math operator but "double product has changed"
                    } else if ((sEQ.get(i).equals("+")) && (changed)) {
                        product += num2;
                        //check to see if it is a math operator but "double product has changed"
                    } else if ((sEQ.get(i).equals("-")) && (changed)) {
                        product -= num2;
                        //check to see if it is a math operator but "double product has changed"
                    } else if ((sEQ.get(i).equals("*")) && (changed)) {
                        product *= num2;
                        //check to see if it is a math operator but "double product has changed"
                    } else if ((sEQ.get(i).equals("/")) && (changed)) {
                        product /= num2;
                    }
                    /*
                    REMOVE COMMENT TO MAKE THE BUTTON CHANGE THE DISPLAY
                    String output = Double.toString(product);
                    display.setText(output);
                     */
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
