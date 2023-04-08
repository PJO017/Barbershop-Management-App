package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.barbershopmanagementapp.BookAppointmentActivity;
import com.example.barbershopmanagementapp.Hairstyles.HairstyleGVAdapter;
import com.example.barbershopmanagementapp.Hairstyles.HairstyleModel;
import com.example.barbershopmanagementapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class HairstyleActivity extends AppCompatActivity {
  GridView hairstyleGV;

  public void openBookAppointment(String barber, String hairstyle, int price) {
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
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    ArrayList<HairstyleModel> hairstyleModelArrayList = new ArrayList<>();

    hairstyleModelArrayList.add(new HairstyleModel(
        "hairstyle 1", R.drawable.ic_launcher_foreground, 10));
    hairstyleModelArrayList.add(new HairstyleModel(
        "hairstyle 2", R.drawable.ic_launcher_foreground, 20));
    hairstyleModelArrayList.add(new HairstyleModel(
        "hairstyle 3", R.drawable.ic_launcher_foreground, 30));

    hairstyleGV = findViewById(R.id.idGVHairstyles);
    HairstyleGVAdapter adapter =
        new HairstyleGVAdapter(this, hairstyleModelArrayList);
    hairstyleGV.setAdapter(adapter);
    hairstyleGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View v, int position,
                              long arg3) {
        HairstyleModel choice = hairstyleModelArrayList.get(position);
        openBookAppointment("Barber 1", choice.getHairstyle_name(), choice.getPrice());
        /*
        adapter.setSelectedPosition(position);
        hairstyleGV.setItemChecked(position, true);
        adapter.notifyDataSetChanged();
        if (hairstyleGV.isItemChecked(position)) {
          View view = hairstyleGV.getChildAt(position);
          view.setBackgroundColor(Color.GREEN);
        }
        Log.d("hairstyle",
              hairstyleModelArrayList.get(adapter.getSelectedPosition())
                  .getHairstyle_name());

         */
      }
    });

    /* FirebaseFirestore db = FirebaseFirestore.getInstance();
     db.collection("Hairstyles")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult())
     { String name = (String) document.get("name"); Long priceObj =
     document.getLong("price"); int price = 0; if (priceObj != null) { price =
     priceObj.intValue();
                            }
                            // Log.d(TAG, "Name: " + name);
                            // Log.d(TAG, "Price: " + price);
                            hairstyleModelArrayList.add(new
     HairstyleModel(name,R.drawable.ic_launcher_foreground, price));
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ",
     task.getException());
                    }
                }
            });
     */
  }
}
