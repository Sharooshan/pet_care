package com.example.petbook;

import java.util.ArrayList;
import java.util.List;

public class CartHelper {

    private static List<Product> cart = new ArrayList<>();

    public static void addProductToCart(Product product) {
        cart.add(product);
    }

    public static List<Product> getCart() {
        return new ArrayList<>(cart);
    }

    public static void removeProductFromCart(Product product) {
        cart.remove(product);
    }

    public static double getTotalPrice() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }
}
