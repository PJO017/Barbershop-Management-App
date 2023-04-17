package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Reviews extends AppCompatActivity {
    TextView firstName;
    TextView lastName;
    TextView rev;
    Button buttonRev;
    RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        
        firstName = findViewById(R.id.fname);
        lastName = findViewById(R.id.lname);
        buttonRev = (Button) findViewById(R.id.btn_review);
        rate = findViewById(R.id.ratingBar);

        buttonRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> review = new HashMap<>();

                review.put("First Name", String.valueOf(firstName.getText()));
                review.put("Last Name", String.valueOf(lastName.getText()));
                review.put("Review", String.valueOf(rev.getText()));
                //review.put("First Name", String.valueOf(firstName.getText()));
                
            }
        });

    }
}