package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarberActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;
    FirebaseAuth mAuth;

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

                            createBarbers(snapshot.getString("Name"),
                                    String.valueOf(snapshot.getLong("Rating")), snapshot.getId());


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });

        ImageButton button = findViewById(R.id.bookmark);
        //launches favorite activity on click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });
    } //end onCreate

    private void createBarbers(String name, String rating, String id) {
        Log.d(TAG, "VIEW CREATED" + name);
        View view = getLayoutInflater().inflate(R.layout.barber_view, null);

//        email  = mAuth.getCurrentUser().getEmail();
        email = "user@email.com"; //to be deleted for testing purposes

        TextView nameView = view.findViewById(R.id.nameView);
        TextView ratingView = view.findViewById(R.id.ratingView);

        ToggleButton likeBt = view.findViewById(R.id.faveBt);
        Button reviewBt = view.findViewById(R.id.reviewBt);

        nameView.setText(name);
        ratingView.setText("Rating: " + rating);

        checkIfLiked(name, likeBt);

        //favorites user's choice of barber
        likeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeBt.isChecked()){
                    likeBt.setBackgroundResource(R.drawable.on);
                    like(name, email);
                }else{
                    likeBt.setBackgroundResource(R.drawable.off);
                    removeFave(id);
                }

            }
        });

        //launches review activity on click
        reviewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchReview();
            }
        });

        //launches booking activity on click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundColor(getResources().getColor(R.color.newBack));
                launchBooking();
            }
        });

        layout.addView(view);
    } //end createBarbers

    //adds favorite
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

    } //end like()

    //deletes favorite
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
    } //end removeFave()

    //checks if user has already favorited a barber
    public void checkIfLiked(String barbName, ToggleButton button){
        db.collection("Favorites").whereEqualTo("User", email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: checkIfFavorited");

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot: snapshotList) {

                            if (barbName.equals(snapshot.getString("Barber"))){
                                Log.d(TAG, "barber " + barbName + " = " + snapshot.getString("Barber"));
                                button.setBackgroundResource(R.drawable.on);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });
    }// end checkIfFavorited

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