package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.DTO.WishListFormDTO;
import com.example.Miniprojekt.Model.Users;
import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Model.Wishlist;
import com.example.Miniprojekt.Utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
@Component
public class WishListRepository implements InterfaceRepository {

    @Value("${spring.utility.url}")
    String url;
    @Value("${spring.utility.user}")
    String user;
    @Value("${spring.utility.psw}")
    String psw;

    public ArrayList<Users> getListOfUsers() {
        ArrayList<Users> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(url, user, psw);
            String SQL = "select * from wishlist_DB.users";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                Users user = new Users();
                //user.setUserName(rs.getString("title"));
                resultList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultList;
    }


    public ArrayList<Wishlist> getListOfWishlists(int id) {
        ArrayList<Wishlist> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(url, user, psw);
            String SQL = "select * from wishlist_DB.wishlist where user_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                //wishlist.setTitle(rs.getString("title"));
                resultList.add(wishlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultList;
    }

    public ArrayList<Wish> getWishList(int id) {
        ArrayList<Wish> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(url, user, psw);
            String SQL = "select * from wishlist_DB.wish where wishlist_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setWishName(rs.getString("wishName"));
                //wish.setAmount(rs.getInt("amount"));
                wish.setDetails(rs.getString("details"));
                wish.setLink(rs.getString("link"));
                wish.setFulfilled(rs.getBoolean("fulfilled"));
                wish.setPrice(rs.getInt("price"));
                resultList.add(wish);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return resultList;
    }


    public void addWish(Wish form) {
        try (Connection con = DriverManager.getConnection(url, user, psw)) {

            String insertWishSQL = "INSERT INTO wish (wishname, details, price, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement insertWishStmt = con.prepareStatement(insertWishSQL, Statement.RETURN_GENERATED_KEYS);
            insertWishStmt.setString(1, form.getWishName());
            insertWishStmt.setString(2, form.getDetails());
            insertWishStmt.setInt(3, form.getPrice());
            insertWishStmt.setInt(4, form.getAmount());

            insertWishStmt.executeUpdate();

            // get generated wish ID
            ResultSet rs = insertWishStmt.getGeneratedKeys();
            if (rs.next()) {
                int wishId = rs.getInt(1);
                int UserId = rs.getInt(2);

                //insert wishlist item into wishlist table
                String insertWishlistSQL = "INSERT INTO wishlist (wish_id, user_id, amount) VALUES (?, ?)";
                PreparedStatement insertWishlistStmt = con.prepareStatement(insertWishlistSQL);
                insertWishlistStmt.setInt(1, wishId);
                insertWishlistStmt.setInt(2, UserId);
                insertWishlistStmt.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
