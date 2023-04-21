package com.example.barbershopmanagementapp;

import com.google.firebase.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Customer implements Comparable<Customer> {
    private String Customer;
    private Timestamp Date;
    private String Barber;
    private String HairStyle;
    private String documentId;
    private boolean modified;

    private boolean markedForDeletion;

    public Customer() {
        // Empty constructor for Firestore deserialization
    }

    public Customer(String Customer, Timestamp Date, String Barber, String HairStyle) {
        this.Customer = Customer;
        this.Date = Date;
        this.Barber = Barber;
        this.HairStyle = HairStyle;
    }

    public String getCustomer() {
        return Customer;
    }

    public Timestamp getDate() {
        return Date;
    }

    public String getFormattedDate() {
        if (Date != null) {
            Date javaDate = Date.toDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return sdf.format(javaDate);
        }
        return "";
    }

    public String getBarber() {
        return Barber;
    }

    public String getHairStyle() {
        return HairStyle;
    }

    public boolean isMarkedForDeletion() {
        return markedForDeletion;
    }

    public void setMarkedForDeletion(boolean markedForDeletion) {
        this.markedForDeletion = markedForDeletion;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setDate(Timestamp timestamp) {
        this.Date = timestamp;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Customer='" + Customer + '\'' +
                ", Date='" + getFormattedDate() + '\'' +
                ", Barber='" + Barber + '\'' +
                ", HairStyle='" + HairStyle + '\'' +
                '}';
    }

    @Override
    public int compareTo(Customer other) {
        if (this.getDate() == null || other.getDate() == null) {
            return 0;
        }
        return this.getDate().compareTo(other.getDate());
    }


}
