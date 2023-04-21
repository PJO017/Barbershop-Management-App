package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BarberActivity extends AppCompatActivity {
    String name;
    Double rating;
    Button choose;
    ListView barbers;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);
        context = this;

        choose = findViewById(R.id.chooseButton);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        ArrayList<Barber> barberArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference barbersRef = db.collection("Barbers");


        Task<QuerySnapshot> task = barbersRef.get();

        task.addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                for (DocumentSnapshot document : task1.getResult()) {
                    Barber barber = new Barber(document.getString("Name"), document.getDouble("Rating"));
                    Log.d("Barber", barber.toString());
                    barberArrayList.add(barber);
                }

                barbers = findViewById(R.id.barbersListView);
                BarberAdapter adapter = new BarberAdapter(context, barberArrayList);
                barbers.setAdapter(adapter);

            } else {
                Log.d(TAG, "Error getting documents: ", task1.getException());
            }
        });
    }
}