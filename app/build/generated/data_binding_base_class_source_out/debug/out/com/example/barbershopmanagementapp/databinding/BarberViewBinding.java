// Generated by view binder compiler. Do not edit!
package com.example.barbershopmanagementapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.barbershopmanagementapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BarberViewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ToggleButton faveBt;

  @NonNull
  public final TextView nameView;

  @NonNull
  public final TextView ratingView;

  @NonNull
  public final Button reviewBt;

  private BarberViewBinding(@NonNull LinearLayout rootView, @NonNull ToggleButton faveBt,
      @NonNull TextView nameView, @NonNull TextView ratingView, @NonNull Button reviewBt) {
    this.rootView = rootView;
    this.faveBt = faveBt;
    this.nameView = nameView;
    this.ratingView = ratingView;
    this.reviewBt = reviewBt;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BarberViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BarberViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.barber_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BarberViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.faveBt;
      ToggleButton faveBt = ViewBindings.findChildViewById(rootView, id);
      if (faveBt == null) {
        break missingId;
      }

      id = R.id.nameView;
      TextView nameView = ViewBindings.findChildViewById(rootView, id);
      if (nameView == null) {
        break missingId;
      }

      id = R.id.ratingView;
      TextView ratingView = ViewBindings.findChildViewById(rootView, id);
      if (ratingView == null) {
        break missingId;
      }

      id = R.id.reviewBt;
      Button reviewBt = ViewBindings.findChildViewById(rootView, id);
      if (reviewBt == null) {
        break missingId;
      }

      return new BarberViewBinding((LinearLayout) rootView, faveBt, nameView, ratingView, reviewBt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}