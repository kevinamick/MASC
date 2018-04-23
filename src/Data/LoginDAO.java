package Data;

import Data.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class LoginDAO {
    private Connection database;

    public LoginDAO() {
        database = Database.connect();
    }

    public String genKey() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public void insertUser(String email, String password) {
        String ID = genKey();
        String query = "INSERT INTO masc.cred(ID,email,password) VALUES(?,?,?);";
        try {
            PreparedStatement stmt = database.prepareStatement(query);
            stmt.setString(1,ID);
            stmt.setString(2,email);
            stmt.setString(3,password);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting record");
            System.err.println(e.getMessage());
        }
    }

    public boolean validateUser(String email, String password) {
        String query = "Select * from masc.cred Where email=? and password=?";
        boolean rslt = false;
        try {
            PreparedStatement stmt = database.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2,password);
            rslt = stmt.executeQuery().first();
        } catch (SQLException e) {
            System.err.println("Something went wrong when validating user");
            System.err.println(e.getMessage());
        }

        return rslt;
    }
}
