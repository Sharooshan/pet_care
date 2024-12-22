package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoppingCartAdapter adapter;
    private TextView tvTotalPrice;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.recyclerView);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Assuming ShoppingCart is a singleton or fetched appropriately
        adapter = new ShoppingCartAdapter(this, ShoppingCart.getInstance().getCartItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShoppingCart.getInstance().getCartItems().isEmpty()) {
                    // Handle empty cart scenario (e.g., show a message)
                    Toast.makeText(ShoppingCartActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to checkout
                    Intent intent = new Intent(ShoppingCartActivity.this, PaymentActivity.class);
                    intent.putExtra("totalPrice", String.format("$%.2f", ShoppingCart.getInstance().calculateTotal()));
                    intent.putExtra("orderDate", getCurrentDate());
                    intent.putExtra("orderTime", getCurrentTime());
                    startActivity(intent);
                }
            }
        });
    }

    // Update total price displayed in TextView
    void updateTotalPrice() {
        double totalPrice = ShoppingCart.getInstance().calculateTotal();
        tvTotalPrice.setText(String.format("Total: $%.2f", totalPrice));
    }

    // Helper methods to get current date and time
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
