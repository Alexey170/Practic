package com.company;
import java.sql.DriverManager;
import java.sql.Connection;

public class BD_connection {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static  String username = "postgres";
    private static  String password = "1";

    public Connection To_BD( String URL,String name, String Password ){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            if ( conn != null) {
                System.out.println("Connection to  DB succesfull!");
            }
        }

        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return conn;
    }
}
