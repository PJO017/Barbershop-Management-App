package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import static com.example.barbershopmanagementapp.R.id.barberRV;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BarberActivity extends AppCompatActivity {


    private FirebaseFirestore db;

    RecyclerView barberRV;
    List<Barber> barberList;
    MyAdapter barberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        db = FirebaseFirestore.getInstance();
        barberList = new ArrayList<>();

        barberRV = findViewById(R.id.barberRV);
        barberRV.setLayoutManager(new LinearLayoutManager(this));

        barberAdapter = new MyAdapter(this, barberList);
        barberRV.setAdapter(barberAdapter);

        db.collection("Barbers").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: Data retrieved");

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList) {
                            Log.d(TAG, "onSuccess: " + snapshot.getString("Name"));
                            Log.d(TAG, "onSuccess: " + snapshot.getLong("Rating"));

                            Barber barber = snapshot.toObject(Barber.class);
                            barberList.add(barber);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });




//        List<Barber> barbers = new ArrayList<Barber>();
//        List<String> names = new ArrayList<String>();
//        List<String> rates = new ArrayList<String>();
//        barbers.add(new Barber("John", "3.5"));
//        barbers.add(new Barber("jack", "3.7"));
//        //gets list of all docs in barbers collection
//        FirebaseFirestore.getInstance()
//                .collection("Barbers")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d(TAG, "onSuccess: We're getting the data");
//
//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            Log.d(TAG, "onSuccess: " + snapshot.getString("Name"));
//                            Log.d(TAG, "onSuccess: " + snapshot.getLong("Rating "));
//
//
//                            names.add(snapshot.getString("Name"));
//                            rates.add(snapshot.getLong("Rating ").toString());
//                            barbers.add(new Barber(snapshot.getString("Name"), snapshot.getLong("Rating ").toString()));
//                            Log.d(TAG, "onSuccess: " + barbers.toString());
//                            Log.d(TAG, "onSuccess: " + names.toString());
//                            Log.d(TAG, "onSuccess: " + rates.toString());
//
//
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ", e);
//                    }
//                });
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), barbers));

    }


    // customer clicks on star button to favorite barber
    public void saveFave(View view) {
        /*
        * if this barber is already favorited
        *   change star back to white
        * else
        *   change star to yellow
        *
        * add barber to customers favorites in FB
        * */
    }

    //customer clicks on this to leave a review and rating for barber (not my US)
    public void leaveReview(View view){
        /*
        * directs them to the reviews page
        * */
    }

    //customer clicks on "Choose" button at the bottom of the page in order to select them as a barber (not my US)
    public void book(View view){
        /*
        * if this barber is already selected
        *   un-highlight
        * if this barber is selected
        *   highlight                               simply marking that this barber was chosen for booking
        *
        * direct them to booking page
        * */
    }
}