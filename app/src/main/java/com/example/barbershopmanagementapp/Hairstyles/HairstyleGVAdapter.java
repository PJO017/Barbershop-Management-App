package com.example.barbershopmanagementapp.Hairstyles;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.barbershopmanagementapp.Hairstyles.HairstyleModel;
import com.example.barbershopmanagementapp.R;

import java.util.ArrayList;

public class HairstyleGVAdapter extends ArrayAdapter<HairstyleModel> {
    private int selectedPosition = -1;

    public HairstyleGVAdapter(@NonNull Context context, ArrayList<HairstyleModel> hairstyleModelArrayList) {
        super(context, 0, hairstyleModelArrayList);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hairstyle_card, parent, false);
        }


        HairstyleModel hairstyleModel = getItem(position);
        TextView hairstyleName = listItemView.findViewById(R.id.hairstyleName);
        ImageView hairstyleImg = listItemView.findViewById(R.id.hairstyleImg);
        TextView hairstylePrice = listItemView.findViewById(R.id.hairstylePrice);

        hairstyleName.setText(hairstyleModel.getHairstyle_name());
        hairstyleImg.setImageResource(hairstyleModel.getImgId());
        hairstylePrice.setText("Price: " + hairstyleModel.getPrice());
        return listItemView;
    }
}
