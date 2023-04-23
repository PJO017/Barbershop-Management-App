package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.barbershopmanagementapp.BookAppointmentActivity;
import com.example.barbershopmanagementapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HairstyleActivity extends AppCompatActivity {
    GridView hairstyleGV;
    String barber;
    private Context context;

    public void openBookAppointment(String barber, String hairstyle, Long price) {
        Intent intent = new Intent(this, BookAppointmentActivity.class);
        intent.putExtra("hairstyle", hairstyle);
        intent.putExtra("barber", barber);
        intent.putExtra("price", (long) price);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hairstyles);
        Intent intent = getIntent();
        barber = intent.getStringExtra("barber");

        context = this;

        ArrayList<HairstyleModel> hairstyleModelArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Hairstyles")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        HairstyleModel hairstyle = new HairstyleModel(document.getLong("Count"), document.getString("Name"), document.getLong("Price"), document.getString("Image"));
                        hairstyleModelArrayList.add(hairstyle);
                    }
                    Comparator<HairstyleModel> byName = new Comparator<HairstyleModel>() {
                        @Override
                        public int compare(HairstyleModel h1, HairstyleModel h2) {
                            return h1.getName().compareTo(h2.getName());
                        }
                    };
                    hairstyleModelArrayList.sort(byName);

                    hairstyleGV = findViewById(R.id.idGVHairstyles);
                    HairstyleGVAdapter adapter =
                            new HairstyleGVAdapter(context, hairstyleModelArrayList);
                    hairstyleGV.setAdapter(adapter);

                    hairstyleGV.setOnItemClickListener((parent, view, position, id) -> {
                        HairstyleModel selectedHairstyle = hairstyleModelArrayList.get(position);
                        openBookAppointment(barber, selectedHairstyle.getName(), selectedHairstyle.getPrice());
                    });
                });

    }
}
