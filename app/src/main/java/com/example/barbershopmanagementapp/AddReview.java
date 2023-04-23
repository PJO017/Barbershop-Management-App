package com.example.barbershopmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddReview extends AppCompatActivity {
    String customer;
    EditText rev;
    Button buttonRev;
    String barber;
    RatingBar rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Intent intent = getIntent();
        barber = intent.getStringExtra("Barber");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // The user is already signed in, so you can get the user's information here.
            String uid = currentUser.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("Users").document(uid);

            userRef.get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // The user document exists, so you can access the user's information here.
                            customer = documentSnapshot.getString("Name");
                            // ...
                        }
                    });
        }

        rev = (EditText) findViewById(R.id.review_input);
        buttonRev = (Button) findViewById(R.id.btn_review);
        rate = (RatingBar) findViewById(R.id.ratingBar);


        buttonRev.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> review = new HashMap<>();

            review.put("Review", rev.getText().toString());
            review.put("Rating", rate.getRating());
            review.put("Customer", customer);
            review.put("Barber", barber);
            db.collection("Reviews").
                    add(review)
                    .addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Intent intent1 = new Intent(getApplicationContext(), BarberActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    });
        });

    }
}