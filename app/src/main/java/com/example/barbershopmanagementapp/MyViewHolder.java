package com.example.barbershopmanagementapp;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, ratingView;
    Button faveBt, reviewBt;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.nameView);
        ratingView = itemView.findViewById(R.id.ratingView);
        faveBt = itemView.findViewById(R.id.faveBt);
        reviewBt = itemView.findViewById(R.id.reviewBt);

        faveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "Like button clicked");
            }
        });

    }
}
