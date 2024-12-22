package com.example.petbook;

public class Product {
    private String name;
    private String description;
    private double price;
    private double rating;
    private String brand;
    private String type;
    private String ageRange;
    private int imageResId;

    // Constructor
    public Product(String name, String description, double price, double rating, String brand, String type, String ageRange,int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.brand = brand;
        this.type = type;
        this.ageRange = ageRange;
        this.imageResId = imageResId;
    }

    public Product(String string, String string1, double aDouble, int rating, String brand, String type, String ageRange) {
    }

    // Getters
    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getBrand() { return brand; }
    public String getType() { return type; }
    public String getAgeRange() { return ageRange; }
    public int getImageResId() { return imageResId; }
}
