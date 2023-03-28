package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishListRepository implements InterfaceRepository {

    String url;

    String user;

    String psw;

    public ArrayList<Wish> getList(){
        ArrayList<Wish> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(url,user,psw);
            String SQL = "select * from wishlist_DB.wich";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setWishName(rs.getString("wishName"));
                //wish.setAmount(rs.getInt("amount"));
                wish.setDescription(rs.getString("description"));
                wish.setLink(rs.getString("link"));
                wish.setFulfilled(rs.getBoolean("fulfilled"));
                wish.setPrice(rs.getInt("price"));
                resultList.add(wish);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

        return resultList;
    }
}
