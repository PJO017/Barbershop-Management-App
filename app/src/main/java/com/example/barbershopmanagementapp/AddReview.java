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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AddReview extends AppCompatActivity {
    TextView custName;
    TextView barbName;
    EditText rev;
    Button buttonRev;
    RatingBar rate;
    public void openReview (String barber, String review, float rating) {
        Intent intent = new Intent(this, Reviews.class);
        intent.putExtra("barber", barber);
        intent.putExtra("review", review);
        intent.putExtra("rating", rating);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        //custName = findViewById(R.id.cust_name);
        //barbName = findViewById(R.id.bname);
        rev = (EditText) findViewById(R.id.review_input);
        buttonRev = (Button) findViewById(R.id.btn_review);
        rate = (RatingBar) findViewById(R.id.ratingBar);

        buttonRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> review = new HashMap<>();

                review.put("Customer Name", custName.getText().toString());
                review.put("Barber Name", barbName.getText().toString());
                review.put("Review", rev.getText().toString());
                review.put("Rating", rate.getRating());
                db.collection("Reviews").
                        add(review)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
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