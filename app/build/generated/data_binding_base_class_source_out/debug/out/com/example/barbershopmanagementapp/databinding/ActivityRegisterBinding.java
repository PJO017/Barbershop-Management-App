// Generated by view binder compiler. Do not edit!
package com.example.barbershopmanagementapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.barbershopmanagementapp.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RadioButton barberRadioBtn;

  @NonNull
  public final Button btnReg;

  @NonNull
  public final RadioButton customerRadioBtn;

  @NonNull
  public final TextInputEditText email;

  @NonNull
  public final TextView loginNow;

  @NonNull
  public final TextView loginText;

  @NonNull
  public final TextInputEditText name;

  @NonNull
  public final RadioButton ownerRadioBtn;

  @NonNull
  public final TextInputEditText password;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RadioGroup roleRadioGroup;

  private ActivityRegisterBinding(@NonNull LinearLayout rootView,
      @NonNull RadioButton barberRadioBtn, @NonNull Button btnReg,
      @NonNull RadioButton customerRadioBtn, @NonNull TextInputEditText email,
      @NonNull TextView loginNow, @NonNull TextView loginText, @NonNull TextInputEditText name,
      @NonNull RadioButton ownerRadioBtn, @NonNull TextInputEditText password,
      @NonNull ProgressBar progressBar, @NonNull RadioGroup roleRadioGroup) {
    this.rootView = rootView;
    this.barberRadioBtn = barberRadioBtn;
    this.btnReg = btnReg;
    this.customerRadioBtn = customerRadioBtn;
    this.email = email;
    this.loginNow = loginNow;
    this.loginText = loginText;
    this.name = name;
    this.ownerRadioBtn = ownerRadioBtn;
    this.password = password;
    this.progressBar = progressBar;
    this.roleRadioGroup = roleRadioGroup;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.barberRadioBtn;
      RadioButton barberRadioBtn = ViewBindings.findChildViewById(rootView, id);
      if (barberRadioBtn == null) {
        break missingId;
      }

      id = R.id.btn_reg;
      Button btnReg = ViewBindings.findChildViewById(rootView, id);
      if (btnReg == null) {
        break missingId;
      }

      id = R.id.customerRadioBtn;
      RadioButton customerRadioBtn = ViewBindings.findChildViewById(rootView, id);
      if (customerRadioBtn == null) {
        break missingId;
      }

      id = R.id.email;
      TextInputEditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.loginNow;
      TextView loginNow = ViewBindings.findChildViewById(rootView, id);
      if (loginNow == null) {
        break missingId;
      }

      id = R.id.login_text;
      TextView loginText = ViewBindings.findChildViewById(rootView, id);
      if (loginText == null) {
        break missingId;
      }

      id = R.id.name;
      TextInputEditText name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.ownerRadioBtn;
      RadioButton ownerRadioBtn = ViewBindings.findChildViewById(rootView, id);
      if (ownerRadioBtn == null) {
        break missingId;
      }

      id = R.id.password;
      TextInputEditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.roleRadioGroup;
      RadioGroup roleRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (roleRadioGroup == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((LinearLayout) rootView, barberRadioBtn, btnReg,
          customerRadioBtn, email, loginNow, loginText, name, ownerRadioBtn, password, progressBar,
          roleRadioGroup);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
