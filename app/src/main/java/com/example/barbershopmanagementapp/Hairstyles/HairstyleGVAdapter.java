package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.barbershopmanagementapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class HairstyleGVAdapter extends ArrayAdapter<HairstyleModel> {
    private final ArrayList<HairstyleModel> data;
    private int selectedPosition = -1;
    private HairstyleModel selectedHairstyle;

    public HairstyleGVAdapter(@NonNull Context context, ArrayList<HairstyleModel> hairstyleModelArrayList) {
        super(context, 0, hairstyleModelArrayList);
        data = hairstyleModelArrayList;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        selectedHairstyle = data.get(position);
        notifyDataSetChanged();
    }

    public HairstyleModel getSelectedHairstyle() {
        return selectedHairstyle;
    }

    public HairstyleModel getItem(int position) {
        return data.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hairstyle_card, parent, false);
        }


        HairstyleModel hairstyleModel = getItem(position);
        TextView hairstyleName = listItemView.findViewById(R.id.hairstyleName);
        ImageView hairstyleImg = listItemView.findViewById(R.id.hairstyleImg);
        TextView hairstylePrice = listItemView.findViewById(R.id.hairstylePrice);

        hairstyleName.setText(hairstyleModel.getName());
        hairstylePrice.setText("Price: " + hairstyleModel.getPrice());


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        String index = hairstyleModel.getName().split(" ")[1];
        StorageReference imageRef = storage.getReferenceFromUrl("gs://barbershop-management-app.appspot.com/" + "hairstyle" + index + ".jpg");

        long bytes = 0;
        File localFile = null;
        try {
            localFile = File.createTempFile("image", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert localFile != null;
        imageRef.getBytes(30000).addOnSuccessListener(taskSnapshot -> {
            // Image downloaded successfully
            Log.d("image", "Image downloaded");
            Bitmap bitmap = BitmapFactory.decodeByteArray(taskSnapshot, 0, taskSnapshot.length);
            hairstyleImg.setImageBitmap(bitmap);
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.d("image", "Image not downloaded");
        });

        if (position == selectedPosition) {
            listItemView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.selected_hairstyle_card));
        } else {
            listItemView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.hairstyle_card));
        }

        listItemView.setOnClickListener(v -> {
            setSelectedPosition(position);
        });
        return listItemView;
    }
}
