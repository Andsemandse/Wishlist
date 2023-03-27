package com.example.Miniprojekt.Model;


public class WishList {
    private String name;
    private String description;
    private int price;
    private int amount;
    private String link;
    private boolean fulfilled;

    public WishList(String name, String description, int price, int amount, String link, boolean fulfilled) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.link = link;
        this.fulfilled = fulfilled;
    }

    //getter

    public String getName() {
        return name;
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


    public void setName(String name) {
        this.name = name;
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





