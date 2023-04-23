package com.example.barbershopmanagementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.barbershopmanagementapp.R;
import com.google.firebase.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditCustomerFragment extends DialogFragment {

    private EditText editTime;
    private Button saveButton;
    private Customer customer;
    private OnEditCompleteListener listener;

    public EditCustomerFragment(Customer customer, OnEditCompleteListener listener) {
        this.customer = customer;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        editTime = view.findViewById(R.id.edit_time);
        saveButton = view.findViewById(R.id.save_edit_button);

        editTime.setText(customer.getFormattedDate());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTime = editTime.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                try {
                    Date date = sdf.parse(newTime);
                    if (date != null) {
                        Timestamp newDate = new Timestamp(date);
                        listener.onEditComplete(customer, newDate);
                        dismiss();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    public interface OnEditCompleteListener {
        void onEditComplete(Customer customer, Timestamp newDate);
    }
}
