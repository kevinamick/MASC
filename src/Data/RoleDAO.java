package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends DAO {
    public ObservableList getRoles() {
        ObservableList<Role> data = FXCollections.observableArrayList();
        try {
            open();
            String query = "SELECT * FROM masc.roles";
            PreparedStatement stmt = database.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));

                data.add(role);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting events.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public Role getRole(Integer id) {
        try {
            open();

            String query = "SELECT * FROM masc.roles WHERE id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));

                return role;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong when getting role.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }

    public ObservableList<Role> getRolesByEvent(Integer id) {
        ObservableList<Role> data = FXCollections.observableArrayList();

        try {
            open();

            String query = "SELECT R.id, R.name FROM masc.roles As R, masc.event_roles as ER WHERE R.id = ER.role_id AND event_id = ?;";
            PreparedStatement stmt = database.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));

                data.add(role);
            }

            return data;
        } catch (Exception e) {
            System.err.println("Something went wrong when getting roles by event.");
            System.err.println(e.getMessage());
        } finally {
            close();
        }

        return null;
    }
}
