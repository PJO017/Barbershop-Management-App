package com.example.barbershopmanagementapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    Context context;
    List<ClipData.Item> items;

    public ReviewAdapter(Context context, List<ClipData.Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(LayoutInflater.from(context).inflate(R.layout.reviews_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
    holder.displayReview.setText(items.get(position).);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
