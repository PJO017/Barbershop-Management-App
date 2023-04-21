package com.example.barbershopmanagementapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BarberAdapter extends ArrayAdapter<Barber> {
    private final ArrayList<Barber> barberData;

    public BarberAdapter(@NonNull Context context, ArrayList<Barber> barberArrayList) {
        super(context, 0, barberArrayList);

        barberData = barberArrayList;
    }

    public Barber getItem(int position) {
        return barberData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.barber_view, parent, false);
        }
        Barber barber = getItem(position);
        TextView barberName = listItemView.findViewById(R.id.nameView);
        TextView barberRating = listItemView.findViewById(R.id.ratingView);

        barberName.setText(barber.getName());
        barberRating.setText("Rating: " + String.valueOf(barber.getRating()));
        Log.d("barber", barber.toString());
        return listItemView;
    }
}
