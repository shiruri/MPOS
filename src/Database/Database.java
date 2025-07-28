package Database;

import java.sql.*;

public class Database {

    private static String dbPassword = "Mfa6sbHAgi";
    private static String dbUsername = "sql12791655";
    private static Connection con = null;
    private Statement stmt = null;// 
    // singleton pattern for creating connection 
    public static synchronized Connection getCon() throws ClassNotFoundException, SQLException {
        if (con == null) {
            con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com/sql12791655", dbUsername, dbPassword);
            System.out.println("Connection Started");

        }

        return con; // singleton design for initializing database

    }

}
