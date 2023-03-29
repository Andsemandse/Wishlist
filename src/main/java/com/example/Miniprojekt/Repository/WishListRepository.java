package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Component
public class WishListRepository implements InterfaceRepository {

    @Value("${spring.utility.url}")
    String url;
    @Value("${spring.utility.user}")
    String user;
    @Value("${spring.utility.psw}")
    String psw;

    // TODO: 29-03-2023 metode skal rettes.
    public ArrayList<Wish> getWishList(int id){
        ArrayList<Wish> resultList = new ArrayList<>();
        try {
            Connection con = ConnectionManager.getConnection(url,user,psw);
            String SQL = "select * from wishlist_DB.wish where wishlist_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setWishName(rs.getString("wishName"));
                //wish.setAmount(rs.getInt("amount"));
                wish.setDescription(rs.getString("details"));
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
