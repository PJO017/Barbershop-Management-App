package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbershopmanagementapp.Hairstyles.HairstyleActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;

public class BarberActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Boolean showFavorites = false;
    LinearLayout layout;
    Button chooseButton;

    public void createViews(ArrayList<String> favs) {
        db.collection("Barbers").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot snapshot : snapshotList) {
                        if (showFavorites && favs.contains(snapshot.getString("Name"))) {
                            createBarbers(snapshot.getString("Name"),
                                    String.valueOf(snapshot.getDouble("Rating")), snapshot.getId(), favs.contains(snapshot.getString("Name")));
                        } else if (!showFavorites) {
                            createBarbers(snapshot.getString("Name"),
                                    String.valueOf(snapshot.getDouble("Rating")), snapshot.getId(), favs.contains(snapshot.getString("Name")));
                        }
                    }
                });
    }

    public void setBarberList() {
        layout = findViewById(R.id.layout);

        ImageButton favoritesToggle = findViewById(R.id.bookmark);
        //launches favorite activity on click
        favoritesToggle.setOnClickListener(v -> {
            showFavorites = !showFavorites;
            if (showFavorites) {
                favoritesToggle.setBackgroundResource(R.drawable.favorite_on);
            } else {
                favoritesToggle.setBackgroundResource(R.drawable.favorite);
            }

            layout.removeAllViews();
            setBarberList();
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();

            DocumentReference userRef = db.collection("Users").document(uid);
            userRef.get()
                    .addOnSuccessListener(documentSnapshots -> {
                        if (documentSnapshots.exists()) {
                            ArrayList<String> favorites = (ArrayList<String>) documentSnapshots.get("Fav Barbers");
                            createViews(favorites);
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);
        setBarberList();

        chooseButton = findViewById(R.id.chooseButton);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    } //end onCreate

    private void createBarbers(String name, String rating, String id, boolean fav) {
        Log.d(TAG, "VIEW CREATED" + name);
        View view = getLayoutInflater().inflate(R.layout.barber_view, null);

        TextView nameView = view.findViewById(R.id.nameView);
        TextView ratingView = view.findViewById(R.id.ratingView);

        ToggleButton likeBtn = view.findViewById(R.id.faveBt);
        Button reviewBt = view.findViewById(R.id.reviewBt);

        nameView.setText(name);
        ratingView.setText("Rating: " + rating);

        setLikeBtn(name, likeBtn, fav);

        //launches review activity on click
        reviewBt.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Reviews.class);
            intent.putExtra("barber", name);
            startActivity(intent);
            finish();
        });

        view.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HairstyleActivity.class);
            intent.putExtra("barber", name);
            startActivity(intent);
            finish();
        });

        layout.addView(view);
    }

    //adds favorite
    private void like(String barbName) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            DocumentReference userRef = db.collection("Users").document(uid);
            userRef.update("Fav Barbers", FieldValue.arrayUnion(barbName));
        }
    }

    //deletes favorite
    public void removeFave(String barbName) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            DocumentReference userRef = db.collection("Users").document(uid);
            userRef.update("Fav Barbers", FieldValue.arrayRemove(barbName));
        }
    }

    //checks if user has already favorited a barber
    public void setLikeBtn(String name, ToggleButton button, boolean fav) {
        if (fav) {
            button.setBackgroundResource(R.drawable.on);
            button.setChecked(true);
        } else {
            button.setBackgroundResource(R.drawable.off);
            button.setChecked(false);
        }

        //favorites user's choice of barber
        button.setOnClickListener(v -> {
            if (!button.isChecked()) {
                button.setBackgroundResource(R.drawable.on);
                like(name);
            } else {
                button.setBackgroundResource(R.drawable.off);
                removeFave(name);
            }
        });
    }
}