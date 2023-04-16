package com.example.Miniprojekt.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection con = null;

    public static Connection getConnection() throws SQLException {

        String url = "miniprojekttest.mysql.database.azure.com:3306/wishlist_db?ss1mode=require";
        String username = "test1234";
        String password = "!ww12345678";

        try{
            con = DriverManager.getConnection(url,username,password);
        } catch(SQLException exception){
            exception.printStackTrace();
        }
        return con;
    }
}
