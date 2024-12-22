package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail, tvContactNumber, tvAddress;
    private Button btnEditProfile, btnAddPaymentMethod;
    private DatabaseHelper db;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DatabaseHelper(this);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvContactNumber = findViewById(R.id.tvContactNumber);
        tvAddress = findViewById(R.id.tvAddress);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnAddPaymentMethod = findViewById(R.id.btnAddPaymentMethod);

        // Get the username passed from MainActivity
        username = getIntent().getStringExtra("username");

        // Fetch and display user details
        displayUserDetails();

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to EditProfileActivity to edit user details
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("username", username); // Pass username to EditProfileActivity
                startActivity(intent);
            }
        });

        btnAddPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement adding payment method functionality here
                // You can navigate to a new activity or implement the logic inline
                // For example:
                // Intent intent = new Intent(ProfileActivity.this, AddPaymentMethodActivity.class);
                // startActivity(intent);
                // Implement your logic to add payment method
            }
        });
    }

    private void displayUserDetails() {
        // Fetch user details from database or shared preferences
        // Example: Assuming you have a User class with getter methods

        User user = db.getUserByUsername(username); // Implement this method in DatabaseHelper to fetch user details

        if (user != null) {
            tvUsername.setText("Username: " + user.getUsername());
            tvEmail.setText("Email: " + user.getEmail());
            tvContactNumber.setText("Contact Number: " + user.getContactNumber());
            tvAddress.setText("Address: " + user.getAddress());
        }
    }
}
