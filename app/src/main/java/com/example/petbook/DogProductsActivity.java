package com.example.petbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class DogProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DogProductsAdapter adapter;
    private List<Product> productList = new ArrayList<>();
    private List<Product> filteredList = new ArrayList<>();
    private Spinner spinnerBrand, spinnerType, spinnerAgeRange;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_products);

        recyclerView = findViewById(R.id.recyclerView);
        spinnerBrand = findViewById(R.id.spinnerBrand);
        spinnerType = findViewById(R.id.spinnerType);
        spinnerAgeRange = findViewById(R.id.spinnerAgeRange);

        // Example data for demonstration
        productList.add(new Product("josi dog active", "High-quality dog food", 15.99, 4.5, "Nikola", "Dry Food", "Adult", R.drawable.treats));
        productList.add(new Product("josera festival", "Premium dog nutrition", 19.99, 4.8, "Nikola", "Wet Food", "Puppy", R.drawable.treats));
        // Add other products...
        productList.add(new Product("Josera Kids", " high Premium dog nutrition", 16.99, 4.8, "Nikola", "Wet Food", "Adult", R.drawable.welcom));

        // Initialize RecyclerView and adapter
        filteredList.addAll(productList);
        adapter = new DogProductsAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Populate spinners with filter options
        populateSpinners();

        // Set listeners for spinners
        setSpinnerListeners();
    }

    private void populateSpinners() {
        // Populate spinners with unique values from the product list
        Set<String> brands = new HashSet<>();
        Set<String> types = new HashSet<>();
        Set<String> ageRanges = new HashSet<>();
        for (Product product : productList) {
            brands.add(product.getBrand());
            types.add(product.getType());
            ageRanges.add(product.getAgeRange());
        }

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(brands));
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(types));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typeAdapter);

        ArrayAdapter<String> ageRangeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(ageRanges));
        ageRangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgeRange.setAdapter(ageRangeAdapter);
    }

    private void setSpinnerListeners() {
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerAgeRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void applyFilters() {
        String selectedBrand = spinnerBrand.getSelectedItem() != null ? spinnerBrand.getSelectedItem().toString() : "";
        String selectedType = spinnerType.getSelectedItem() != null ? spinnerType.getSelectedItem().toString() : "";
        String selectedAgeRange = spinnerAgeRange.getSelectedItem() != null ? spinnerAgeRange.getSelectedItem().toString() : "";

        filteredList.clear();
        for (Product product : productList) {
            if ((selectedBrand.isEmpty() || product.getBrand().equals(selectedBrand)) &&
                    (selectedType.isEmpty() || product.getType().equals(selectedType)) &&
                    (selectedAgeRange.isEmpty() || product.getAgeRange().equals(selectedAgeRange))) {
                filteredList.add(product);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
