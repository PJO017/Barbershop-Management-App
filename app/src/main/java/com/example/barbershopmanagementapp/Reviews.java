package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reviews extends AppCompatActivity {
    TextView customer, barber, review, rating;
    Button add;
    ListView reviews;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        context = this;
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reviews.this, AddReview.class);
                startActivity(intent);
                finish();
            }
        });


        ArrayList<ReviewItems> reviewItemsArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reviewsRef = db.collection("Reviews");
        Query query = reviewsRef.whereEqualTo("Barber", "Barber 2");

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Log.d("Customer", document.getString("Customer"));
                Log.d("Rating", document.getLong("Rating").toString());
                ReviewItems reviewItems = new ReviewItems(document.getString("Review"), document.getDouble("Rating"), document.getString("Customer"));
                reviewItemsArrayList.add(reviewItems);
            }
            reviews = findViewById(R.id.reviews_list_view);
            ReviewAdapter adapter = new ReviewAdapter(context, reviewItemsArrayList);
            reviews.setAdapter(adapter);
        });
    }
}