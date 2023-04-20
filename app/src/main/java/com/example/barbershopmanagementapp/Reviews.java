package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reviews extends AppCompatActivity {
    TextView customer, barber, review, rating;

    RecyclerView ReviewRV;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        context = this;


        ArrayList<ReviewItems> reviewItemsArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Review").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                ReviewItems reviewItems = new ReviewItems(document.getString("Review"), document.getLong("Rating"));
                reviewItemsArrayList.add(reviewItems);
            }


            ReviewRV = findViewById(R.id.recyclerView);
            ReviewAdapter adapter = new ReviewAdapter(context, reviewItemsArrayList);
            //ReviewRV.setAdapter(adapter);
        });
        db.collection("Barber").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document: queryDocumentSnapshots) {

            }
        });
    }
}