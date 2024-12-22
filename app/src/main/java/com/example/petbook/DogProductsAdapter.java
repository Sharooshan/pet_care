package com.example.petbook;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogProductsAdapter extends RecyclerView.Adapter<DogProductsAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;
    private ShoppingCart cart;

    public DogProductsAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.cart = ShoppingCart.getInstance(); // Singleton instance of the cart
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductDescription.setText(product.getDescription());
        holder.tvProductPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.imgProduct.setImageResource(product.getImageResId()); // Set the product image


        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddToCart", "Button clicked for product: " + product.getName());
                cart.addProduct(product);
                Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnManageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingCartActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductDescription, tvProductPrice;
        ImageView imgProduct; // Add this field
        Button btnAddToCart, btnManageCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct); // Initialize the ImageView
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnManageCart = itemView.findViewById(R.id.btnManageCart);
        }
    }
}
