package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Random;

public class UserTypeDAO extends DAO {
    public UserType getUserType(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.user_types WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("access")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting user.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList getUserTypes() {
        ObservableList<UserType> data = FXCollections.observableArrayList();
        try {
            open();

            String query = "SELECT * FROM masc.user_types";
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                UserType utype = new UserType();
                utype.setId(rs.getInt("id"));
                utype.setName(rs.getString("name"));
                utype.setAccess(rs.getInt("access"));

                data.add(utype);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting user types.");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    public UserType getUserTypeByName(String name) {
        try {
            open();

            String query = "SELECT * FROM masc.user_types WHERE name = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("access")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting user.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }
}
