package com.example.barbershopmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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

        Map<String, Integer> currentWeek = new HashMap<String, Integer>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {
            currentWeek.put(dateFormat.format(calendar.getTime()), 0);
            Log.d("CalendarDates", dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }

        YearMonth currentYearMonth = YearMonth.now();
        String currentMonth = currentYearMonth.getMonth().getDisplayName(
                TextStyle.SHORT, Locale.getDefault()
        );


        Map<String, String> monthMap = new HashMap<String, String>();
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("Appointments");

        ArrayList<String> appt_dates = new ArrayList<String>();

        // Get all appts from firestore
        collectionRef.get().addOnSuccessListener(querySnapshot -> {
            for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                String appt_date = String.valueOf(documentSnapshot.getDate("Date"));
                Log.d("Date", String.valueOf(documentSnapshot.getDate("Date")));

                String[] date = appt_date.split(" ");

                String month = monthMap.get(date[1]);
                String day = date[2];

                if (Objects.equals(month, monthMap.get(currentMonth))) {
                    for (String curDay : currentWeek.keySet()) {
                        if (Objects.equals(day, curDay.split("-")[2])) {
                            Log.d("Match", "");
                            int count = currentWeek.get(curDay);
                            currentWeek.put(curDay, count + 1);
                            break;
                        }
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : currentWeek.entrySet()) {
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                Date date = new Date();
                try {
                    date = dateFormat.parse(key);
                    assert date != null;
                    calendar.setTime(date);
                    String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
                    Log.d("CurrentWeekMap", dayOfWeek + " => " + value);

                    if (Objects.equals(dayOfWeek, "Sunday")) {
                        sundayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Saturday")) {
                        saturdayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Friday")) {
                        fridayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Thursday")) {
                        thursdayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Wednesday")) {
                        wednesdayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Tuesday")) {
                        tuesdayNum.setText(value);
                    }
                    if (Objects.equals(dayOfWeek, "Monday")) {
                        mondayNum.setText(value);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }).addOnFailureListener(e -> {
            // handle failure
        });


    }

}