package com.example.Miniprojekt.Model;


public class Wish {
    private String wishName;
    private String details;
    private int price;
    private int amount;
    private int id;


    public Wish() {

    }

    //getter

    public String getWishName() {
        return wishName;
    }

    public String getDetails() {
        return details;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }


    public int getId() {
        return id;
    }
    //setter


    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setId(int id) {
        this.id = id;
    }
}





