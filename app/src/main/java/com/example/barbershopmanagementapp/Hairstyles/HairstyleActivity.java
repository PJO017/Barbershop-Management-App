package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.barbershopmanagementapp.BookAppointmentActivity;
import com.example.barbershopmanagementapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class HairstyleActivity extends AppCompatActivity {
    GridView hairstyleGV;
    Button chooseButton;
    HairstyleModel choice;
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

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ArrayList<HairstyleModel> hairstyleModelArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Hairstyles")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        HairstyleModel hairstyle = new HairstyleModel(document.getLong("Count"), document.getString("Name"), document.getLong("Price"));
                        hairstyleModelArrayList.add(hairstyle);
                    }

                    hairstyleGV = findViewById(R.id.idGVHairstyles);
                    HairstyleGVAdapter adapter =
                            new HairstyleGVAdapter(context, hairstyleModelArrayList);
                    hairstyleGV.setAdapter(adapter);

                    chooseButton = findViewById(R.id.chooseButton);
                    chooseButton.setOnClickListener(v -> {
                        choice = adapter.getSelectedHairstyle();
                        openBookAppointment("Barber 1", choice.getName(), choice.getPrice());
                    });
                });

    }
}
