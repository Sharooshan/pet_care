package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText nameEditText, expirationEditText, cvvEditText, cardNumberEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        nameEditText = findViewById(R.id.nameEditText);
        expirationEditText = findViewById(R.id.expirationEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        cardNumberEditText = findViewById(R.id.cardNumberEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String expiration = expirationEditText.getText().toString();
            String cvv = cvvEditText.getText().toString();
            String cardNumber = cardNumberEditText.getText().toString();

            Intent intent = new Intent(PaymentActivity.this, OrderSummaryActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("expiration", expiration);
            intent.putExtra("cvv", cvv);
            intent.putExtra("cardNumber", cardNumber);
            // Pass additional order details as needed
            intent.putExtra("totalPrice", getIntent().getStringExtra("totalPrice"));
            intent.putExtra("orderDate", getIntent().getStringExtra("orderDate"));
            intent.putExtra("orderTime", getIntent().getStringExtra("orderTime"));
            // You can add more details if needed

            startActivity(intent);
        });
    }
}
