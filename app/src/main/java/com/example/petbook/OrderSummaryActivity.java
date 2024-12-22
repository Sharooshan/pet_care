package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private TextView nameTextView, expirationTextView, cvvTextView, cardNumberTextView, totalPriceTextView, orderDateTextView, orderTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        nameTextView = findViewById(R.id.nameTextView);
        expirationTextView = findViewById(R.id.expirationTextView);
        cvvTextView = findViewById(R.id.cvvTextView);
        cardNumberTextView = findViewById(R.id.cardNumberTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        orderDateTextView = findViewById(R.id.orderDateTextView);
        orderTimeTextView = findViewById(R.id.orderTimeTextView);

        // Retrieve data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String expiration = intent.getStringExtra("expiration");
        String cvv = intent.getStringExtra("cvv");
        String cardNumber = intent.getStringExtra("cardNumber");
        String totalPrice = ((Intent) intent).getStringExtra("totalPrice");
        String orderDate = intent.getStringExtra("orderDate");
        String orderTime = intent.getStringExtra("orderTime");

        // Set data to TextViews
        nameTextView.setText(name);
        expirationTextView.setText(expiration);
        cvvTextView.setText(cvv);
        cardNumberTextView.setText(cardNumber);
        totalPriceTextView.setText(totalPrice);
        orderDateTextView.setText(orderDate);
        orderTimeTextView.setText(orderTime);
    }
}
