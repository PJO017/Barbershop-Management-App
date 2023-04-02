package com.example.barbershopmanagementapp;

public class Customer {
    private String name;
    private String appointmentTime;

    public Customer(String name, String appointmentTime) {
        this.name = name;
        this.appointmentTime = appointmentTime;
    }

    public String getName() {
        return name;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }
}
