package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.Model.Users;
import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Model.Wishlist;

import java.util.ArrayList;

public interface InterfaceRepository {
    ArrayList<Wish> getWishList(int id);

    ArrayList<Wishlist> getListOfLists(int id);

    ArrayList<Users> getListOfUsers();

    void addWish(Wish form);

    void addUser(Users form);

    void editWish(Wish form);
}
