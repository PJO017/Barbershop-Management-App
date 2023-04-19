package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.barbershopmanagementapp.R;

import java.util.ArrayList;

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

    public int getSelectedPosition() {
        return selectedPosition;
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

        if (position == selectedPosition) {
            listItemView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.selected_hairstyle));
        } else {
            listItemView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), android.R.color.transparent));
        }

        listItemView.setOnClickListener(v -> {
            setSelectedPosition(position);
        });
        return listItemView;
    }
}
