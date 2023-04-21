package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddReview extends AppCompatActivity {
    String customer;
    EditText rev;
    Button buttonRev;
    RatingBar rate;

    Intent intent = getIntent();
    String barber = intent.getStringExtra("Barber");

    public void openReview(String barber, String review, long rating) {
        Intent intent = new Intent(this, Reviews.class);
        intent.putExtra("review", review);
        intent.putExtra("rating", rating);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // The user is already signed in, so you can get the user's information here.
            String uid = currentUser.getUid();
            String email = currentUser.getEmail();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("Users").document(uid);

            userRef.get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // The user document exists, so you can access the user's information here.
                            customer = documentSnapshot.getString("Name");
                            // ...
                        }
                    })
                    .addOnFailureListener(e -> {
                        // An error occurred while trying to retrieve the user document.
                    });
        }

        rev = (EditText) findViewById(R.id.review_input);
        buttonRev = (Button) findViewById(R.id.btn_review);
        rate = (RatingBar) findViewById(R.id.ratingBar);


        buttonRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Intent intent = new Intent(AddReview.this, Reviews.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }
        });

    }
}