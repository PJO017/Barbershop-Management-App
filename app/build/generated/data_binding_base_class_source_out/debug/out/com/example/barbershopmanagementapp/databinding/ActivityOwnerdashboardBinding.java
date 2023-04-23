// Generated by view binder compiler. Do not edit!
package com.example.barbershopmanagementapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.barbershopmanagementapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOwnerdashboardBinding implements ViewBinding {
  @NonNull
  private final LinearLayoutCompat rootView;

  @NonNull
  public final TextView SalesTotal;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final TextView dailyCustomers;

  @NonNull
  public final TextView dashboardTitle;

  @NonNull
  public final TextView fridayNum;

  @NonNull
  public final Button manageAppointments;

  @NonNull
  public final TextView mondayNum;

  @NonNull
  public final TextView saturdayNum;

  @NonNull
  public final TextView sundayNum;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView thursdayNum;

  @NonNull
  public final TextView tuesdayNum;

  @NonNull
  public final TextView wednesdayNum;

  private ActivityOwnerdashboardBinding(@NonNull LinearLayoutCompat rootView,
      @NonNull TextView SalesTotal, @NonNull ConstraintLayout constraintLayout,
      @NonNull TextView dailyCustomers, @NonNull TextView dashboardTitle,
      @NonNull TextView fridayNum, @NonNull Button manageAppointments, @NonNull TextView mondayNum,
      @NonNull TextView saturdayNum, @NonNull TextView sundayNum, @NonNull TextView textView16,
      @NonNull TextView textView2, @NonNull TextView textView4, @NonNull TextView textView6,
      @NonNull TextView textView7, @NonNull TextView thursdayNum, @NonNull TextView tuesdayNum,
      @NonNull TextView wednesdayNum) {
    this.rootView = rootView;
    this.SalesTotal = SalesTotal;
    this.constraintLayout = constraintLayout;
    this.dailyCustomers = dailyCustomers;
    this.dashboardTitle = dashboardTitle;
    this.fridayNum = fridayNum;
    this.manageAppointments = manageAppointments;
    this.mondayNum = mondayNum;
    this.saturdayNum = saturdayNum;
    this.sundayNum = sundayNum;
    this.textView16 = textView16;
    this.textView2 = textView2;
    this.textView4 = textView4;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.thursdayNum = thursdayNum;
    this.tuesdayNum = tuesdayNum;
    this.wednesdayNum = wednesdayNum;
  }

  @Override
  @NonNull
  public LinearLayoutCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOwnerdashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOwnerdashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ownerdashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOwnerdashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.SalesTotal;
      TextView SalesTotal = ViewBindings.findChildViewById(rootView, id);
      if (SalesTotal == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.dailyCustomers;
      TextView dailyCustomers = ViewBindings.findChildViewById(rootView, id);
      if (dailyCustomers == null) {
        break missingId;
      }

      id = R.id.dashboardTitle;
      TextView dashboardTitle = ViewBindings.findChildViewById(rootView, id);
      if (dashboardTitle == null) {
        break missingId;
      }

      id = R.id.fridayNum;
      TextView fridayNum = ViewBindings.findChildViewById(rootView, id);
      if (fridayNum == null) {
        break missingId;
      }

      id = R.id.manageAppointments;
      Button manageAppointments = ViewBindings.findChildViewById(rootView, id);
      if (manageAppointments == null) {
        break missingId;
      }

      id = R.id.mondayNum;
      TextView mondayNum = ViewBindings.findChildViewById(rootView, id);
      if (mondayNum == null) {
        break missingId;
      }

      id = R.id.saturdayNum;
      TextView saturdayNum = ViewBindings.findChildViewById(rootView, id);
      if (saturdayNum == null) {
        break missingId;
      }

      id = R.id.sundayNum;
      TextView sundayNum = ViewBindings.findChildViewById(rootView, id);
      if (sundayNum == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.thursdayNum;
      TextView thursdayNum = ViewBindings.findChildViewById(rootView, id);
      if (thursdayNum == null) {
        break missingId;
      }

      id = R.id.tuesdayNum;
      TextView tuesdayNum = ViewBindings.findChildViewById(rootView, id);
      if (tuesdayNum == null) {
        break missingId;
      }

      id = R.id.wednesdayNum;
      TextView wednesdayNum = ViewBindings.findChildViewById(rootView, id);
      if (wednesdayNum == null) {
        break missingId;
      }

      return new ActivityOwnerdashboardBinding((LinearLayoutCompat) rootView, SalesTotal,
          constraintLayout, dailyCustomers, dashboardTitle, fridayNum, manageAppointments,
          mondayNum, saturdayNum, sundayNum, textView16, textView2, textView4, textView6, textView7,
          thursdayNum, tuesdayNum, wednesdayNum);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
