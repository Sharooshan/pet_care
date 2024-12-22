package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etContactNumber, etAddress;
    private Button btnSaveChanges;
    private DatabaseHelper db;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etContactNumber = findViewById(R.id.etContactNumber);
        etAddress = findViewById(R.id.etAddress);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        // Get the username passed from ProfileActivity
        username = getIntent().getStringExtra("username");

        // Load current user details
        loadUserDetails(username);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetails();
            }
        });
    }



    private void loadUserDetails(String username) {
        User user = db.getUserByUsername(username); // Implement this method in DatabaseHelper

        if (user != null) {
            etUsername.setText(user.getUsername());
            etEmail.setText(user.getEmail());
            etContactNumber.setText(user.getContactNumber());
            etAddress.setText(user.getAddress());
        }
    }

    private void saveUserDetails() {
        String newUsername = etUsername.getText().toString().trim();
        String newEmail = etEmail.getText().toString().trim();
        String newContactNumber = etContactNumber.getText().toString().trim();
        String newAddress = etAddress.getText().toString().trim();

        boolean isUpdated = db.updateUser(username, newUsername, newEmail, newContactNumber, newAddress); // Implement this method in DatabaseHelper

        if (isUpdated) {
            Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            // Go back to ProfileActivity
            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
            intent.putExtra("username", newUsername);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(EditProfileActivity.this, "Profile update failed", Toast.LENGTH_SHORT).show();
        }
    }
}
