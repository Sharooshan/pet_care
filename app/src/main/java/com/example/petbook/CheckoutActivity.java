package com.example.petbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private RecyclerView recyclerView;
    private CheckoutAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize views
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        recyclerView = findViewById(R.id.recyclerView);

        // Receive data from Intent
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);
        List<Product> cartItems = (List<Product>) getIntent().getSerializableExtra("cartItems");

        // Set total price
        tvTotalPrice.setText(String.format("Total Price: $%.2f", totalPrice));

        // Set up RecyclerView with CheckoutAdapter to display cart items
        adapter = new CheckoutAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
