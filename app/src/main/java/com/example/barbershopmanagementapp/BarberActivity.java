package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarberActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;
    FirebaseAuth mAuth;
    Button bt;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        layout = findViewById(R.id.layout);

        db.collection("Barbers").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: Data retrieved");

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList) {
                            Log.d(TAG, "onSuccess: " + snapshot.getString("Name"));
                            Log.d(TAG, "onSuccess: " + snapshot.getLong("Rating"));

                            createBarbers(snapshot.getString("Name"), String.valueOf(snapshot.getLong("Rating")));


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });

        TextView show = findViewById(R.id.favorites);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createBarbers(String name, String rating) {
        Log.d(TAG, "VIEW CREATED" + name);
        View view = getLayoutInflater().inflate(R.layout.barber_view, null);

//        email  = mAuth.getCurrentUser().getEmail();
        email = "user@email.com"; //to be deleted for testing purposes

        TextView nameView = view.findViewById(R.id.nameView);
        TextView ratingView = view.findViewById(R.id.ratingView);

        Button likeBt = view.findViewById(R.id.faveBt);
        Button reviewBt = view.findViewById(R.id.reviewBt);

        nameView.setText(name);
        ratingView.setText("Rating: " + rating);

        likeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like(name, email);
            }
        });

        reviewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchReview();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(getResources().getColor(R.color.newBack));
                launchBooking();
            }
        });

        layout.addView(view);


    }

    private void like(String barbName, String email) {
        Map<String, String> fave = new HashMap<>();
        fave.put("Barber", barbName);
        fave.put("User", email);

        db.collection("Favorites").add(fave)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });

    }

    //launches review
    public void launchReview(){
        Log.d(TAG, "review clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //launches booking
    public void launchBooking(){
        Log.d(TAG, "booking clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}