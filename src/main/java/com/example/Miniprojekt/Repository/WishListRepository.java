package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.DTO.WishListFormDTO;
import com.example.Miniprojekt.Model.Users;
import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Model.Wishlist;
import com.example.Miniprojekt.Utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository

@Component
public class WishListRepository implements InterfaceRepository {

    private final String url;
    private final String user;
    private final String psw;

    public WishListRepository() {
        this.url = "jdbc:mysql://localhost:3306/wishlist_DB";
        this.user = "";
        this.psw = "";
    }


/*
    @Value("${spring.utility.url}")
    String url;
    @Value("${spring.utility.user}")
    String user;
    @Value("${spring.utility.psw}")
    String psw;

 */

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, psw);
    }

    public ArrayList<Users> getListOfUsers(){
        ArrayList<Users> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13");
            String SQL = "select * from wishlist_DB.users";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                Users user = new Users();
                user.setName(rs.getString("userName"));
                user.setId(rs.getInt("id"));
                resultList.add(user);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

        return resultList;
    }


    public ArrayList<Wishlist> getListOfLists(int id){
        ArrayList<Wishlist> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13");
            String SQL = "select * from wishlist_DB.wishlist where user_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setTitle(rs.getString("title"));
                wishlist.setId(rs.getInt("id"));
                resultList.add(wishlist);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

        return resultList;
    }
    public ArrayList<Wish> getWishList(int id){
        ArrayList<Wish> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13");
            String SQL = "select * from wishlist_DB.wish where wishlist_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setWishName(rs.getString("wishName"));
                //wish.setAmount(rs.getInt("amount"));
                wish.setDetails(rs.getString("details"));
                wish.setPrice(rs.getInt("price"));
                resultList.add(wish);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

        return resultList;
    }

    public void addUser(Users users){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13")){
            String insertUsersSQL = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement insertUsersStmt = con.prepareStatement(insertUsersSQL);
            insertUsersStmt.setString(1, users.getName());
            insertUsersStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWish(Wish form) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13")) {

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
                String insertWishlistSQL = "INSERT INTO wishlist (wish_id, user_id) VALUES (?, ?)";
                PreparedStatement insertWishlistStmt = con.prepareStatement(insertWishlistSQL);
                insertWishlistStmt.setInt(1, wishId);
                insertWishlistStmt.setInt(2, UserId);
                insertWishlistStmt.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void editWish(Wish form) {
        try (Connection con = DriverManager.getConnection(url, user, psw)) {

            // update wishtable with new values/edit
            String updateWishSQL = "UPDATE wish SET wishname=?, details=?, price=?, amount=? WHERE id=?";
            PreparedStatement updateWishStmt = con.prepareStatement(updateWishSQL);
            updateWishStmt.setString(1, form.getWishName());
            updateWishStmt.setString(2, form.getDetails());
            updateWishStmt.setInt(3, form.getPrice());
            updateWishStmt.setInt(4, form.getAmount());
            updateWishStmt.setInt(5, form.getId());


            updateWishStmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteWish(int wishId) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlist_DB","testuser","Sivertsen13")) {

            // Delete a wish from the wishtable
            String deleteWishSQL = "DELETE FROM wish WHERE id=?";
            PreparedStatement deleteWishStmt = con.prepareStatement(deleteWishSQL);
            deleteWishStmt.setInt(1, wishId);
            deleteWishStmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
