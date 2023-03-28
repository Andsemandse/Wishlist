package com.example.Miniprojekt.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection con = null;

    public static Connection getConnection(String url,String username,String password) throws SQLException {

        if(con==null){
            con = DriverManager.getConnection(url,username,password);
        }

        return con;
    }
}
