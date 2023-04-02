package com.example.barbershopmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout itemList = findViewById(R.id.item_list);

        List<Customer> customerList = loadCustomersFromXml();

        for (Customer customer : customerList) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.activity_appointment_item, null);

            TextView customerName = itemView.findViewById(R.id.customer_name);
            TextView appointmentTime = itemView.findViewById(R.id.appointment_time);
            ImageButton deleteButton = itemView.findViewById(R.id.delete_button);

            customerName.setText(customer.getName());
            appointmentTime.setText(customer.getAppointmentTime());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Delete the customer's appointment
                }
            });
            itemList.addView(itemView);
        }
    }


    private List<Customer> loadCustomersFromXml() {
        List<Customer> customerList = new ArrayList<>();
        try {
            // Get the XML file as a stream
            InputStream inputStream = getResources().openRawResource(R.raw.customers);

            // Create a new XML document builder
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Parse the XML file into a document
            Document document = builder.parse(inputStream);

            // Get a list of all customer elements in the document
            NodeList customerNodes = document.getElementsByTagName("customer");

            // Loop through each customer element and create a Customer object for it
            for (int i = 0; i < customerNodes.getLength(); i++) {
                Element customerElement = (Element) customerNodes.item(i);
                String name = customerElement.getElementsByTagName("name").item(0).getTextContent();
                String appointmentTime = customerElement.getElementsByTagName("appointment_time").item(0).getTextContent();
                customerList.add(new Customer(name, appointmentTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

}