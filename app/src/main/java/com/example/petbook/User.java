package com.example.petbook;

public class User {
    private String username;
    private String email;
    private String contactNumber;
    private String address;

    public User(String username, String email, String contactNumber, String address) {
        this.username = username;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}
