package com.example.barbershopmanagementapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    TextView hairstyle;
    TextView barber;
    CalendarView calendar;
    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointment);
        Intent data = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        hairstyle = findViewById(R.id.hairstyleTV);
        barber = findViewById(R.id.barberTV);

        hairstyle.setText(data.getStringExtra("hairstyle"));
        barber.setText(data.getStringExtra("barber"));

        CalendarView calendar = findViewById(R.id.appointmentCalendarView);
        calendar.setDate(System.currentTimeMillis());

        timeButton = findViewById(R.id.timePickerButton);
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
               hour = selectedHour;
               minute = selectedMinute;
               timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
