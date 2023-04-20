package com.example.barbershopmanagementapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    TextView appointment, price, barber, hairstyle;

    public static String convertTime(int hour, int minute) {
        if (hour > 12) {
            hour -= 12;
        } else if (hour == 0) {
            hour += 12;
        }

        String min;
        if (minute < 10)
            min = "0" + minute;
        else
            min = String.valueOf(minute);

        return String.valueOf(hour) + ':' +
                min;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent data = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        appointment = findViewById(R.id.appointmentTV);
        String date = data.getStringExtra("month") + "/" + data.getStringExtra("day") + "/" + data.getStringExtra("year");
        String date_time_string = BookAppointmentActivity.convertTime(data.getIntExtra("hour", 0), data.getIntExtra("minute", 0)) + " @ " + date;
        appointment.setText(date_time_string);


        price = findViewById(R.id.priceTV);
        Resources res = getResources();
        price.setText(res.getString(R.string.price, data.getLongExtra("price", 0)));

        barber = findViewById(R.id.barberTV);
        barber.setText(data.getStringExtra("barber"));

        hairstyle = findViewById(R.id.hairstyleTV);
        hairstyle.setText(data.getStringExtra("hairstyle"));

        EditText name = (EditText) findViewById(R.id.name);

        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(v -> {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> appt = new HashMap<>();

            appt.put("Customer", name.getText().toString());
            appt.put("Barber", barber.getText());
            appt.put("HairStyle", hairstyle.getText());

            String dateTime = data.getStringExtra("year") + "-" + data.getStringExtra("month") + "-" + data.getStringExtra("day") + " " + convertTime(data.getIntExtra("hour", 0), data.getIntExtra("minute", 0)) + ":" + "00";
            Log.d("time", dateTime);
            appt.put("Date", Timestamp.valueOf(dateTime));


            db.collection("Appointments").
                    add(appt)
                    .addOnSuccessListener((OnSuccessListener) o -> {
                    })
                    .addOnFailureListener(e -> {
                    });
        });
    }
}
