package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection driver;

    public static Connection connect() {

        Connection driver = null;

        try {
            Object instance = Class.forName("com.mysql.jdbc.Driver").newInstance();
            driver = DriverManager.getConnection("jdbc:mysql://192.168.10.10:3306?useSSL=false", "homestead", "secret");
            return driver;
        } catch (Exception e) {
            System.err.println("Something went wrong when connecting to database.");
            System.err.println(e.getMessage());
        }

        return driver;
    }

    public static void disconnect() {
        try {
            driver.close();
        } catch (Exception e) {
            System.err.println("Something happened when disconnecting database.");
            System.err.println(e.getMessage());
        }
    }
}
