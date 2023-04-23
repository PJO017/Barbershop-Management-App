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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reviews extends AppCompatActivity {
    TextView displayName, displayRating;
    String barber;
    Button add;
    ListView reviews;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        context = this;

        Intent intent = getIntent();
        barber = intent.getStringExtra("barber");

        displayName = findViewById(R.id.barber_name);
        displayRating = findViewById(R.id.barber_rating);
        add = findViewById(R.id.add);

        displayName.setText(barber);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddReview.class);
                intent.putExtra("Barber", barber);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<ReviewItems> reviewItemsArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reviewsRef = db.collection("Reviews");

        Query query = reviewsRef.whereEqualTo("Barber", barber);

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            Double avgRating = 0.0;
            int count = 0;
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                avgRating += document.getDouble("Rating");
                count++;
                Log.d("Customer", document.getString("Customer"));
                Log.d("Rating", document.getLong("Rating").toString());
                ReviewItems reviewItems = new ReviewItems(document.getString("Review"), document.getDouble("Rating"), document.getString("Customer"));
                Log.d("Review", reviewItems.toString());
                reviewItemsArrayList.add(reviewItems);
            }
            displayRating.setText(String.valueOf(avgRating / count));
            reviews = findViewById(R.id.reviews_list_view);
            ReviewAdapter adapter = new ReviewAdapter(context, reviewItemsArrayList);
            reviews.setAdapter(adapter);
        });
    }
}