package com.example.barbershopmanagementapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    TextView hairstyle;
    ImageView hairstyleIV;
    TextView barber;
    CalendarView calendar;
    String year, month, day;
    Button timeButton;
    int hour, minute;
    Button bookButton;

    public void openPaymentActivity(String barber, String hairstyle, Long price, int hour, int minute, String year, String month, String day) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("hairstyle", hairstyle);
        intent.putExtra("barber", barber);
        intent.putExtra("price", price);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Intent data = getIntent();

        Context context = this;

        hairstyle = findViewById(R.id.hairstyleTV);
        hairstyleIV = findViewById(R.id.hairstyleIV);
        barber = findViewById(R.id.barberTV);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        String index = data.getStringExtra("hairstyle").split(" ")[1];
        StorageReference imageRef = storage.getReferenceFromUrl("gs://barbershop-management-app.appspot.com/" + "hairstyle" + index + ".jpg");

        long bytes = 0;
        File localFile = null;
        try {
            localFile = File.createTempFile("image", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert localFile != null;
        imageRef.getBytes(30000).addOnSuccessListener(taskSnapshot -> {
            // Image downloaded successfully
            Log.d("image", "Image downloaded");
            Bitmap bitmap = BitmapFactory.decodeByteArray(taskSnapshot, 0, taskSnapshot.length);
            hairstyleIV.setImageBitmap(bitmap);
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.d("image", "Image not downloaded");
        });

        hairstyle.setText(data.getStringExtra("hairstyle"));
        barber.setText(data.getStringExtra("barber"));

        CalendarView calendar = findViewById(R.id.appointmentCalendarView);
        calendar.setDate(System.currentTimeMillis());
        calendar.setMinDate(System.currentTimeMillis() - 1000);
        LocalDate currentdate = LocalDate.now();
        day = String.valueOf(currentdate.getDayOfMonth());
        month = String.valueOf(currentdate.getMonthValue());
        year = String.valueOf(currentdate.getYear());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int selectedYear, int selectedMonth, int dayOfMonth) {
                String s_day = (dayOfMonth < 10) ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String s_year = String.valueOf(selectedYear);
                String s_month = String.valueOf(selectedMonth + 1);
                day = s_day;
                month = s_month;
                year = s_year;
            }
        });

        timeButton = findViewById(R.id.timePickerButton);

        bookButton = findViewById(R.id.bookButton);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentActivity(data.getStringExtra("barber"), data.getStringExtra("hairstyle"), (Long) data.getLongExtra("price", 0), hour, minute, year, month, day);
            }
        });
    }

    public static String convertTime(int hour, int minute) {
        String timeSet = "";
        if (hour > 12) {
            hour -= 12;
            timeSet = "PM";
        } else if (hour == 0) {
            hour += 12;
            timeSet = "AM";
        } else if (hour == 12) {
            timeSet = "PM";
        } else {
            timeSet = "AM";
        }

        String min = "";
        if (minute < 10)
            min = "0" + minute;
        else
            min = String.valueOf(minute);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hour).append(':')
                .append(min).append(" ").append(timeSet).toString();

        return aTime;
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(convertTime(hour, minute));

            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
