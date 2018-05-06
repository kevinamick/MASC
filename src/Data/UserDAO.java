package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setSchoolId(rs.getInt("school_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setTypeId(rs.getInt("type_id"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting user.");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void updateUser(User user) {
        try {
            String query = "UPDATE masc.users SET fname = ?, lname = ?, school_id = ?, email = ?, password = ?, type_id = ? WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setInt(3, user.getSchoolId());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getTypeId());
            stmt.setInt(7, user.getUserId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when updating user.");
            System.err.println(e.getMessage());
        }
    }

    public ObservableList getAllUsers() {
        ObservableList<User> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM masc.users";

        try {
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setSchoolId(rs.getInt("school_id"));
                user.setEmail(rs.getString("email"));
                data.add(user);
            }
            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting all users");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insertUser(User user) {
        try {
            String query = "INSERT INTO masc.users (fname, lname, school_id, email, password, type_id) VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setInt(3, user.getSchoolId());
            stmt.setString(4, user.getEmail());
            stmt.setInt(6,user.getTypeId());
            //TODO: encrypt this
            stmt.setString(5, user.getPassword()); // plain-text (uh-oh)

            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Something went wrong when inserting user.");
            System.err.println(e.getMessage());
        }
    }

    public User validateUser(String email, String password) {
        try {
            String query = "SELECT * FROM masc.users WHERE email=? AND password=?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1,email);
            stmt.setString(2,password);

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
            System.err.println("Something went wrong when validating user.");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void deleteUser(User user) {
        try {
            String query = "DELETE FROM masc.users WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, user.getUserId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Something went wrong when deleting user.");
            System.err.println(e.getMessage());
        }
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
