package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnProfile, btnProductCatalog, btnEducation;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfile = findViewById(R.id.btnProfile);
        btnProductCatalog = findViewById(R.id.btnProductCatalog);
        btnEducation = findViewById(R.id.btnEducation);

        // Get the username passed from LoginActivity
        username = getIntent().getStringExtra("username");

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton(v);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("username", username); // Pass username to ProfileActivity
                startActivity(intent);
            }
        });

        btnProductCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton(v);
                Intent intent = new Intent(MainActivity.this, ProductCatalogActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Or handle the back navigation as required
            }
        });

        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButton(v);
                Intent intent = new Intent(MainActivity.this, EducationalContentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void animateButton(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        view.startAnimation(animation);
    }
}
