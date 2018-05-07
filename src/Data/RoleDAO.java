package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
