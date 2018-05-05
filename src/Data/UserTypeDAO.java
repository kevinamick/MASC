package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Random;

public class UserTypeDAO extends DAO {
    public UserType getUserType(Integer id) {
        try {
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
        }

        return null;
    }

    public UserType getUserTypeByName(String name) {
        try {
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
        }

        return null;
    }
}
