package com.example.barbershopmanagementapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends ArrayAdapter<ReviewItems> {
    private final ArrayList<ReviewItems> reviewData;

    public ReviewAdapter(@NonNull Context context, ArrayList<ReviewItems> reviewItemsArrayList) {
        super(context, 0, reviewItemsArrayList);

        reviewData = reviewItemsArrayList;
    }
    public ReviewItems getItem(int position) {
        return reviewData.get(position);
    }
    @NonNull
    @Override
    public View getView (int position, @NonNull View convertView,  @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.reviews_view, parent, false);
        }
        ReviewItems reviewData = getItem(position);
        TextView revTitle = listItemView.findViewById(R.id.ReviewTitle);
        TextView displayRating = listItemView.findViewById(R.id.DisplayRating);
        TextView displayReview = listItemView.findViewById(R.id.DisplayReview);

        revTitle.setText(reviewData.getUser());
        displayReview.setText(reviewData.getReview());
        displayRating.setText((int) reviewData.getRating());
        return listItemView;
    }
}
