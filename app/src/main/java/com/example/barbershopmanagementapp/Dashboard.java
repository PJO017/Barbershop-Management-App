package com.example.barbershopmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity {

    private LinearLayout itemList;
    private List<Customer> customerList;
    private Map<Customer, View> customerViews;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //get the current user role!!
        userRole = getIntent().getStringExtra("userRole");

        itemList = findViewById(R.id.item_list);
        customerViews = new HashMap<>();

        customerList = new ArrayList<>();
        loadCustomersFromFirebase();

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges(customerList);
            }
        });

        if ("Owner".equals(userRole)) {
            saveButton.setVisibility(View.GONE);

        }
    }

    private void loadCustomersFromFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference customersRef = db.collection("Appointments");

        customersRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    Dashboard.this.customerList.clear();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Customer customer = document.toObject(Customer.class);
                        customer.setDocumentId(document.getId());
                        Log.d("CustomerData", "Fetched customer: " + customer.toString());
                        Dashboard.this.customerList.add(customer);
                    }

                    // Sorting the items of the list here so that the earliest appointment render at the top, or so I hope.
                    Collections.sort(customerList);

                    // This is to update the ui and view the changes before saving them.
                    updateUI(customerList);
                } else {
                    Log.e("Dashboard", "Error loading appointments: ", task.getException());
                    Toast.makeText(Dashboard.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(List<Customer> customerList) {
        for (Customer customer : customerList) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.activity_appointment_item, null);
            customerViews.put(customer, itemView); // Store the view for each customer
            TextView customerName = itemView.findViewById(R.id.customer_name);
            TextView appointmentTime = itemView.findViewById(R.id.appointment_time);
            TextView BarberName = itemView.findViewById(R.id.barber_name_textview);
            ImageButton optionsButton = itemView.findViewById(R.id.delete_button);

            customerName.setText("Customer: " + customer.getCustomer());
            appointmentTime.setText(customer.getFormattedDate());
            BarberName.setText("Barber: " + customer.getBarber());

            if ("Owner".equals(userRole)) {
                optionsButton.setVisibility(View.GONE);

            }

            optionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create and show the popup menu
                    PopupMenu popupMenu = new PopupMenu(Dashboard.this, optionsButton);
                    popupMenu.inflate(R.menu.customer_options_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit_option:
                                    EditCustomerFragment editCustomerFragment = new EditCustomerFragment(customer, new EditCustomerFragment.OnEditCompleteListener() {
                                        @Override
                                        public void onEditComplete(Customer customer, Timestamp newDate) {
                                            customer.setDate(newDate);
                                            customer.setModified(true);
                                            updateCustomerUI(customer); // Update the UI for this specific customer
                                        }
                                    });
                                    editCustomerFragment.show(getSupportFragmentManager(), "EditCustomerFragment"); // This line was missing
                                    return true;

                                case R.id.delete_option:
                                    customer.setMarkedForDeletion(true);
                                    itemView.setVisibility(View.GONE);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });

                    popupMenu.show();
                }
            });

            itemList.addView(itemView);
        }
    }

    private void updateCustomerUI(Customer customer) {
        View itemView = customerViews.get(customer);
        if (itemView != null) {
            TextView appointmentTime = itemView.findViewById(R.id.appointment_time);
            appointmentTime.setText(customer.getFormattedDate());
        }
    }

    private void saveChanges(List<Customer> customerList) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference customersRef = db.collection("Appointments");

        List<Task<Void>> tasks = new ArrayList<>();

        for (Customer customer : this.customerList) {
            if (customer.isMarkedForDeletion()) {
                String documentId = customer.getDocumentId();
                Task<Void> deleteTask = customersRef.document(documentId).delete();
                tasks.add(deleteTask);
            } else if (customer.isModified()) {
                String documentId = customer.getDocumentId();
                // Only update the specific fields that have changed
                Map<String, Object> updates = new HashMap<>();
                updates.put("Date", customer.getDate());
                Task<Void> updateTask = customersRef.document(documentId).update(updates);
                tasks.add(updateTask);
            }
        }
        Toast.makeText(Dashboard.this, "Changes Saved!", Toast.LENGTH_SHORT).show();


        // Wait for all tasks (deletions and updates) to complete before refreshing the UI
        Tasks.whenAll(tasks).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Remove the deleted customers from the local list
                    Iterator<Customer> iterator = customerList.iterator();
                    while (iterator.hasNext()) {
                        Customer customer = iterator.next();
                        if (customer.isMarkedForDeletion()) {
                            iterator.remove();
                        } else if (customer.isModified()) {
                            customer.setModified(false);
                        }
                    }

                    itemList.removeAllViews();
                    loadCustomersFromFirebase();
                } else {
                    // Handle any errors
                    Log.e("Dashboard", "Error updating appointments: ", task.getException());
                }
            }
        });
    }
}
