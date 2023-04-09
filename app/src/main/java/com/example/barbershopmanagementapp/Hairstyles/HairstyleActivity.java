package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.barbershopmanagementapp.BookAppointmentActivity;
import com.example.barbershopmanagementapp.Hairstyles.HairstyleGVAdapter;
import com.example.barbershopmanagementapp.Hairstyles.HairstyleModel;
import com.example.barbershopmanagementapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class HairstyleActivity extends AppCompatActivity {
    GridView hairstyleGV;
    private Context context;

    public void openBookAppointment(String barber, String hairstyle, Long price) {
        Intent intent = new Intent(this, BookAppointmentActivity.class);
        intent.putExtra("hairstyle", hairstyle);
        intent.putExtra("barber", barber);
        intent.putExtra("price", price);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairstyles);

        context = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        /* hairstyleModelArrayList.add(new HairstyleModel(
                (Long) 10,"hairstyle 1",  10));
        hairstyleModelArrayList.add(new HairstyleModel(
                "hairstyle 2",  20));
        hairstyleModelArrayList.add(new HairstyleModel(
                "hairstyle 3",  30));

         */

        ArrayList<HairstyleModel> hairstyleModelArrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Hairstyles")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            HairstyleModel hairstyle = new HairstyleModel(document.getLong("Count"), document.getString("Name"), document.getLong("Price"));
                            for (HairstyleModel h : hairstyleModelArrayList) {
                                Log.d("HairstyleList", h.toString());
                            }
                            hairstyleModelArrayList.add(hairstyle);
                        }

                        hairstyleGV = findViewById(R.id.idGVHairstyles);
                        HairstyleGVAdapter adapter =
                                new HairstyleGVAdapter(context, hairstyleModelArrayList);
                        hairstyleGV.setAdapter(adapter);
                        hairstyleGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                                    long arg3) {
                                HairstyleModel choice = hairstyleModelArrayList.get(position);
                                openBookAppointment("Barber 1", choice.getName(), choice.getPrice());
                            }
                        });
                    }
                });

    }
}
