package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    String email;

    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        layout = findViewById(R.id.layout);

        //        email  = mAuth.getCurrentUser().getEmail();
        email = "user@email.com"; //to be deleted for testing purposes

        db.collection("Users").orderBy("Fav Barbers", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: Data retrieved");

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList) {
                            Log.d(TAG, "onSuccess: " + snapshot.toString());

//                            createFave(snapshot.getString("Barber"), snapshot.getId());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });


        db.collection("Favorites").whereEqualTo("User", email)
            .get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    Log.d(TAG, "onSuccess: Data retrieved");

                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot snapshot: snapshotList) {
                        Log.d(TAG, "onSuccess: " + snapshot.getString("Barber"));
                        Log.d(TAG, "onSuccess: " + snapshot.getString("User"));

                        createFave(snapshot.getString("Barber"), snapshot.getId());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: ", e);
                }
            });

    }

    private void createFave(String barber, String id) {
        Log.d(TAG, "VIEW CREATED" + email);
        View view = getLayoutInflater().inflate(R.layout.favorite_view, null);

        TextView nameView = view.findViewById(R.id.faveName);
        ImageButton delete = view.findViewById(R.id.delFave);

        nameView.setText(barber);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFave(id);
                layout.removeView(view);
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


    public void removeFave(String id){
        db.collection("Favorites").document(id).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: doc: " + id + " deleted");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });
    }

    public void launchBooking(){
        Log.d(TAG, "booking clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}