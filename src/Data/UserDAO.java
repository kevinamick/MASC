package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Random;

public class UserDAO extends DAO {
    public User getUser(Integer id) {
        try {
            String query = "SELECT * FROM masc.users WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getInt("school_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("type_id")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting user.");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void insertUser(String fname, String lname, Integer school_id, String email, String password) {
        try {
            String query = "INSERT INTO masc.users (fname, lname, school_id, email, password) VALUES (?,?,?,?,?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setInt(3, school_id);
            stmt.setString(4, email);
            stmt.setString(5, password); // plain-text (uh-oh)

            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting user.");
            System.err.println(e.getMessage());
        }
    }

    public boolean validateUser(String email, String password) {
        boolean result = false;

        try {
            String query = "SELECT * FROM masc.users WHERE email=? AND password=?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1,email);
            stmt.setString(2,password);

            result = stmt.executeQuery().first();
        } catch (SQLException e) {
            System.err.println("Something went wrong when validating user.");
            System.err.println(e.getMessage());
        }

        return result;
    }

    //public String genKey() {
    //    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    //    StringBuilder salt = new StringBuilder();
    //    Random rnd = new Random();
    //
    //    while (salt.length() < 18) { // length of the random string.
    //        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
    //        salt.append(SALTCHARS.charAt(index));
    //    }
    //
    //    return salt.toString();
    //}
}
