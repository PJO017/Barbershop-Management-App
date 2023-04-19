package com.example.barbershopmanagementapp;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewHolder extends RecyclerView.ViewHolder {
    TextView revTitle, displayRating, displayReview ;
    public ReviewHolder(@NonNull View itemView) {
        super(itemView);
        revTitle = itemView.findViewById(R.id.ReviewTitle);
        displayRating = itemView.findViewById(R.id.DisplayRating);
        displayReview = itemView.findViewById(R.id.DisplayReview);
    }
}
