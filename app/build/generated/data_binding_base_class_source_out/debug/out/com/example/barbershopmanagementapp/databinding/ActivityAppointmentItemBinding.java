// Generated by view binder compiler. Do not edit!
package com.example.barbershopmanagementapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.barbershopmanagementapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAppointmentItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView appointmentTime;

  @NonNull
  public final TextView barberNameTextview;

  @NonNull
  public final TextView customerName;

  @NonNull
  public final ImageButton deleteButton;

  private ActivityAppointmentItemBinding(@NonNull LinearLayout rootView,
      @NonNull TextView appointmentTime, @NonNull TextView barberNameTextview,
      @NonNull TextView customerName, @NonNull ImageButton deleteButton) {
    this.rootView = rootView;
    this.appointmentTime = appointmentTime;
    this.barberNameTextview = barberNameTextview;
    this.customerName = customerName;
    this.deleteButton = deleteButton;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAppointmentItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAppointmentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_appointment_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAppointmentItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appointment_time;
      TextView appointmentTime = ViewBindings.findChildViewById(rootView, id);
      if (appointmentTime == null) {
        break missingId;
      }

      id = R.id.barber_name_textview;
      TextView barberNameTextview = ViewBindings.findChildViewById(rootView, id);
      if (barberNameTextview == null) {
        break missingId;
      }

      id = R.id.customer_name;
      TextView customerName = ViewBindings.findChildViewById(rootView, id);
      if (customerName == null) {
        break missingId;
      }

      id = R.id.delete_button;
      ImageButton deleteButton = ViewBindings.findChildViewById(rootView, id);
      if (deleteButton == null) {
        break missingId;
      }

      return new ActivityAppointmentItemBinding((LinearLayout) rootView, appointmentTime,
          barberNameTextview, customerName, deleteButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}