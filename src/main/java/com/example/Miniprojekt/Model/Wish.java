package com.example.Miniprojekt.Model;


public class Wish {
    private String wishName;
    private String description;
    private int price;
    private int amount;
    private String link;
    private boolean fulfilled;

    public Wish() {

    }

    //getter

    public String getWishName() {
        return wishName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getLink() {
        return link;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }
    //setter


    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}





