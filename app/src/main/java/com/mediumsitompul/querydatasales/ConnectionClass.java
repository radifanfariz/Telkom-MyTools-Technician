package com.mediumsitompul.querydatasales;





import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionClass {
    String classs = "com.mysql.jdbc.Driver";
//    String url = "jdbc:mysql://192.168.100.78/db_mytools";
    String url = "jdbc:mysql://36.89.34.66/db_mytools";
    //String url = "jdbc:mysql://192.168.43.85/db_mytools";







    String un = "root";
    String password = "@$^talikom!#%";



    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);

            conn = DriverManager.getConnection(url, un, password);


//            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}

