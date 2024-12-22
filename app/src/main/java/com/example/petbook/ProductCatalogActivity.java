package com.example.petbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProductCatalogActivity extends AppCompatActivity {

    private Button btnDogProducts, btnCatProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catalog);

        btnDogProducts = findViewById(R.id.btnDogProducts);
//        btnCatProducts = findViewById(R.id.btnCatProducts);

        btnDogProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCatalogActivity.this, DogProductsActivity.class);
                startActivity(intent);
            }
        });


    }
}
