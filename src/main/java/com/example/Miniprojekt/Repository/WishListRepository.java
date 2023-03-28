package com.example.Miniprojekt.Repository;

import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Utility.ConnectionManager;

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
            String SQL = "select * from superheroesdata.superhero";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                Wish wish = new Wish();

                resultList.add(wish);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }





        return resultList;
    }
}
