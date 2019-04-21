package com.comp3710.exam2;

public class Transaction {
    private String date, category;
    private double amount;

    public Transaction(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Transaction() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
