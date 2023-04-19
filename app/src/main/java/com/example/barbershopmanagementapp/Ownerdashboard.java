package com.example.barbershopmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Ownerdashboard extends AppCompatActivity {

    TextView textView, textView2, textView3, textView4, textView6, textView7, textView8, textView16;
    TextView sundayNum, mondayNum, tuesdayNum, wednesdayNum, thursdayNum, fridayNum, saturdayNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerdashboard);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView16 = findViewById(R.id.textView16);

        sundayNum = findViewById(R.id.sundayNum);
        mondayNum = findViewById(R.id.mondayNum);
        tuesdayNum = findViewById(R.id.tuesdayNum);
        wednesdayNum = findViewById(R.id.wednesdayNum);
        thursdayNum = findViewById(R.id.thursdayNum);
        fridayNum = findViewById(R.id.fridayNum);
        saturdayNum = findViewById(R.id.saturdayNum);

        //initialize total number of daily customers
        int numCustomers = 0;
        textView8.setText(Integer.toString(numCustomers));

        //initialize number of customers each day
        int sunday = 0;
        sundayNum.setText(Integer.toString(sunday));
        int monday = 0;
        mondayNum.setText(Integer.toString(monday));
        int tuesday = 0;
        tuesdayNum.setText(Integer.toString(tuesday));
        int wednesday = 0;
        wednesdayNum.setText(Integer.toString(wednesday));
        int thursday = 0;
        thursdayNum.setText(Integer.toString(thursday));
        int friday = 0;
        fridayNum.setText(Integer.toString(friday));
        int saturday = 0;
        saturdayNum.setText(Integer.toString(saturday));
    }

}