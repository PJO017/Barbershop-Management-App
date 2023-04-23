// Generated by view binder compiler. Do not edit!
package com.example.barbershopmanagementapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.barbershopmanagementapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBookAppointmentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CalendarView appointmentCalendarView;

  @NonNull
  public final CardView barberCV;

  @NonNull
  public final ImageView barberIV;

  @NonNull
  public final TextView barberTV;

  @NonNull
  public final TextView bookAppointmentTitle;

  @NonNull
  public final Button bookButton;

  @NonNull
  public final CardView hairstyleCV;

  @NonNull
  public final ImageView hairstyleIV;

  @NonNull
  public final TextView hairstyleTV;

  @NonNull
  public final TextView textView;

  @NonNull
  public final Button timePickerButton;

  @NonNull
  public final Toolbar toolbar;

  private ActivityBookAppointmentBinding(@NonNull LinearLayout rootView,
      @NonNull CalendarView appointmentCalendarView, @NonNull CardView barberCV,
      @NonNull ImageView barberIV, @NonNull TextView barberTV,
      @NonNull TextView bookAppointmentTitle, @NonNull Button bookButton,
      @NonNull CardView hairstyleCV, @NonNull ImageView hairstyleIV, @NonNull TextView hairstyleTV,
      @NonNull TextView textView, @NonNull Button timePickerButton, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.appointmentCalendarView = appointmentCalendarView;
    this.barberCV = barberCV;
    this.barberIV = barberIV;
    this.barberTV = barberTV;
    this.bookAppointmentTitle = bookAppointmentTitle;
    this.bookButton = bookButton;
    this.hairstyleCV = hairstyleCV;
    this.hairstyleIV = hairstyleIV;
    this.hairstyleTV = hairstyleTV;
    this.textView = textView;
    this.timePickerButton = timePickerButton;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBookAppointmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBookAppointmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_book_appointment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBookAppointmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appointmentCalendarView;
      CalendarView appointmentCalendarView = ViewBindings.findChildViewById(rootView, id);
      if (appointmentCalendarView == null) {
        break missingId;
      }

      id = R.id.barberCV;
      CardView barberCV = ViewBindings.findChildViewById(rootView, id);
      if (barberCV == null) {
        break missingId;
      }

      id = R.id.barberIV;
      ImageView barberIV = ViewBindings.findChildViewById(rootView, id);
      if (barberIV == null) {
        break missingId;
      }

      id = R.id.barberTV;
      TextView barberTV = ViewBindings.findChildViewById(rootView, id);
      if (barberTV == null) {
        break missingId;
      }

      id = R.id.bookAppointmentTitle;
      TextView bookAppointmentTitle = ViewBindings.findChildViewById(rootView, id);
      if (bookAppointmentTitle == null) {
        break missingId;
      }

      id = R.id.bookButton;
      Button bookButton = ViewBindings.findChildViewById(rootView, id);
      if (bookButton == null) {
        break missingId;
      }

      id = R.id.hairstyleCV;
      CardView hairstyleCV = ViewBindings.findChildViewById(rootView, id);
      if (hairstyleCV == null) {
        break missingId;
      }

      id = R.id.hairstyleIV;
      ImageView hairstyleIV = ViewBindings.findChildViewById(rootView, id);
      if (hairstyleIV == null) {
        break missingId;
      }

      id = R.id.hairstyleTV;
      TextView hairstyleTV = ViewBindings.findChildViewById(rootView, id);
      if (hairstyleTV == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.timePickerButton;
      Button timePickerButton = ViewBindings.findChildViewById(rootView, id);
      if (timePickerButton == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityBookAppointmentBinding((LinearLayout) rootView, appointmentCalendarView,
          barberCV, barberIV, barberTV, bookAppointmentTitle, bookButton, hairstyleCV, hairstyleIV,
          hairstyleTV, textView, timePickerButton, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}