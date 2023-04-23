package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;

public class BarberActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String currentUID = "16ggfOzNqiV01g6Tx86RvJrbNKT2";
    Boolean showFavorites = false;
    LinearLayout layout;

    public void createViews(ArrayList<String> favs) {
        db.collection("Barbers").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d(TAG, "onSuccess: Data retrieved");

                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot snapshot : snapshotList) {
                        Log.d(TAG, "onSuccess: " + snapshot.getString("Name"));
                        Log.d(TAG, "onSuccess: " + snapshot.getDouble("Rating"));

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
            Log.d("toggleFav", showFavorites.toString());
            showFavorites = !showFavorites;
            if (showFavorites) {
                favoritesToggle.setBackgroundResource(R.drawable.favorite_on);
            } else {
                favoritesToggle.setBackgroundResource(R.drawable.favorite);
            }

            layout.removeAllViews();
            setBarberList();
        });

        DocumentReference userRef = db.collection("Users").document(currentUID);
        userRef.get()
                .addOnSuccessListener(documentSnapshots -> {
                    if (documentSnapshots.exists()) {
                        ArrayList<String> favorites = (ArrayList<String>) documentSnapshots.get("Fav Barbers");
                        createViews(favorites);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);
        setBarberList();
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
        });

        layout.addView(view);
    }

    //adds favorite
    private void like(String barbName) {
        DocumentReference userRef = db.collection("Users").document(currentUID);
        userRef.update("Fav Barbers", FieldValue.arrayUnion(barbName)).addOnSuccessListener(unused -> Log.d("Favorite", "Favorite added: " + barbName));
    }

    //deletes favorite
    public void removeFave(String barbName) {
        DocumentReference userRef = db.collection("Users").document(currentUID);
        userRef.update("Fav Barbers", FieldValue.arrayRemove(barbName)).addOnSuccessListener(unused -> Log.d("Favorite", "Favorite removed: " + barbName));

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